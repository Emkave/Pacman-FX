package com.emkave.pacman.entity.mob;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Entity;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


public abstract class Mob extends Entity implements Runnable { // Every mob is an entity
    //protected static final ReentrantLock unique_lock = new ReentrantLock();
    protected int d_x = 0, d_y = 0; // Every mob has its own direction vector (delta x, delta y)
    protected Image dirUpImg, dirRightImg, dirDownImg, dirLeftImg;
    protected char mobSymbol;

    private volatile boolean running = true;
    private AnimationTimer timer;
    private Thread thread;


    Mob(final String mobName) {
        super();
        this.dirUpImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_up.gif")));
        this.dirRightImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_right.gif")));
        this.dirDownImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_down.gif")));
        this.dirLeftImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_left.gif")));
        this.imageView.setImage(this.dirUpImg);

        if (!(this instanceof Pacman)) {
            this.thread = new Thread(this);
            this.thread.start();
        }

        this.imageView.setTranslateX(this.x * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() - 2);
        this.imageView.setTranslateY(this.y * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT() - 2);
    }


    protected abstract void autopilot();



    @Override public void render() {

    }


    protected String coordKey(int[] coord) {
        return coord[0] + "x" + coord[1];
    }


    protected int[] pathFind(int targetX, int targetY) {
        final int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

        boolean[][] visited = new boolean[REGISTRY_KEYS.GET_MAP_HEIGHT()][REGISTRY_KEYS.GET_MAP_WIDTH()];
        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> track = new HashMap<>();

        queue.add(new int[]{this.y, this.x});
        visited[this.y][this.x] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currY = current[0];
            int currX = current[1];


            if (currX == targetX && currY == targetY) {
                int[] backtrack = current;

                while (track.containsKey(this.coordKey(backtrack)) && !Arrays.equals(track.get(this.coordKey(backtrack)), new int[]{this.y, this.x})) {
                    backtrack = track.get(this.coordKey(backtrack));
                }

                int dirX = backtrack[1] - this.x;
                int dirY = backtrack[0] - this.y;

                return new int[]{dirX, dirY};
            }


            for (int[] direction : directions) {
                int newY = (currY + direction[0] + REGISTRY_KEYS.GET_MAP_HEIGHT()) % REGISTRY_KEYS.GET_MAP_HEIGHT();
                int newX = (currX + direction[1] + REGISTRY_KEYS.GET_MAP_WIDTH()) % REGISTRY_KEYS.GET_MAP_WIDTH();

                ReentrantLock unique_lock = new ReentrantLock();
                unique_lock.lock();
                try {
                    var cell = MapHandler.getGameMap()[newY][newX];

                    if (!visited[newY][newX] && cell != '1' && cell != '2' && cell != '3' && cell != '4' && cell != '7' && cell != '8') {
                        visited[newY][newX] = true;
                        int[] next = new int[]{newY, newX};
                        queue.add(next);
                        track.put(this.coordKey(next), current);
                    }
                } finally {
                    unique_lock.unlock();
                }
            }
        }

        return null;
    }


    protected void moveToCell() {
        boolean wrapped = false;

        if (this.x == 0) {
            this.x = REGISTRY_KEYS.GET_MAP_WIDTH() - 1;
            wrapped = true;
        } else if (this.x >= REGISTRY_KEYS.GET_MAP_WIDTH() - 1) {
            this.x = 0;
            wrapped = true;
        }


        if (wrapped) {
            this.imageView.setTranslateX(this.x * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() - 2);
            this.imageView.setTranslateY(this.y * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT() - 2);
        } else {
            final double currentX = this.imageView.getTranslateX() / REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH();
            final double currentY = this.imageView.getTranslateY() / REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT();

            final double deltaX = this.x - currentX;
            final double deltaY = this.y - currentY;

            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                if (deltaX > 0) {
                    this.imageView.setImage(this.dirRightImg);
                } else {
                    this.imageView.setImage(this.dirLeftImg);
                }
            } else if (Math.abs(deltaY) > Math.abs(deltaX)) {
                if (deltaY > 0) {
                    this.imageView.setImage(this.dirDownImg);
                } else {
                    this.imageView.setImage(this.dirUpImg);
                }
            }

            TranslateTransition transition = new TranslateTransition(Duration.millis(300), this.imageView);

            transition.setToX(this.x * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()-2);
            transition.setToY(this.y * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()-2);
            transition.setInterpolator(Interpolator.LINEAR);
            transition.setOnFinished(event -> {
                this.imageView.setTranslateX(this.x * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()-2);
                this.imageView.setTranslateY(this.y * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()-2);
            });

            Platform.runLater(transition::play);
        }
    }


    @Override public void run() {
        this.timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override public void handle(long now) {
                if (!running) {
                    this.stop();
                }
                if (!REGISTRY_KEYS.GET_ISPAUSED()) {
                    if (now - lastUpdate >= 300000000) {
                        autopilot();
                        lastUpdate = now;
                    }
                }
            }
        };
        Platform.runLater(timer::start);
    }


    public void stop() {
        this.running = false;

        if (timer != null) {
            Platform.runLater(timer::stop);
        }
    }


    public synchronized Thread getThread() {
        return this.thread;
    }
}

