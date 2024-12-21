package com.emkave.pacman.entity.mob;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Entity;
import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


public abstract class Mob extends Entity implements Runnable { // Every mob is an entity
    protected static final ReentrantLock unique_lock = new ReentrantLock();
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
            // Calculate delta movements
            final double currentX = this.imageView.getTranslateX() / REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH();
            final double currentY = this.imageView.getTranslateY() / REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT();

            final double deltaX = this.x - currentX;
            final double deltaY = this.y - currentY;

            // Determine direction based on deltas
            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                // Horizontal movement
                if (deltaX > 0) {
                    this.imageView.setImage(this.dirRightImg);
                } else {
                    this.imageView.setImage(this.dirLeftImg);
                }
            } else if (Math.abs(deltaY) > Math.abs(deltaX)) {
                // Vertical movement
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


    public Thread getThread() {
        return this.thread;
    }
}

