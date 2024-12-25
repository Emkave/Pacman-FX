package com.emkave.pacman.entity.mob;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.handler.*;
import com.emkave.pacman.scene.Game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.Objects;
import java.util.Stack;


public class Pacman extends Mob {
    private static Stack<ImageView> lives = new Stack<>();

    private static Stack<Collectible> collected = new Stack<>();


    public Pacman() {
        super("pacman");
        this.x = 13;
        this.y = 17;
        this.d_y = -1;
        this.d_x = 0;
        this.mobSymbol = '!';
    }


    @Override public void autopilot() {
        this.render();
    }


    public void handleKeyPress(KeyEvent event) { // Pacman should be able to be controlled
        switch (event.getCode()) { // Pacman's direction vector should be assigned
            case W: this.d_x = 0;
                    this.d_y = -1;
                    break;
            case S: this.d_x = 0;
                    this.d_y = 1;
                    break;
            case A: this.d_x = -1;
                    this.d_y = 0;
                    break;
            case D:
                this.d_x = 1;
                this.d_y = 0;
                break;
            default: break;
        }
    }


    @Override public void render() {
        try {
            Collectible collectible = EntityHandler.getCollectible(this.x, this.y);

            if (collectible != null) {
                collectible.effect();
            }

            Mob mob = this.caughtByGhost();
            if (mob != null) {
                if (!mob.chasing) {
                    Game.addScore(400);
                    SoundHandler.playSoundEffect("eatghost");
                    mob.respawn();
                } else {
                    SceneHandler.loadDeathScene(this.x, this.y);
                    return;
                }
            }

            char nextTile = MapHandler.getGameMap()[this.y + this.d_y][this.x + this.d_x];
            if (nextTile != '1' && nextTile != '2' && nextTile != '3' && nextTile != '4' && nextTile != '7' && nextTile != '8') {
                this.x += this.d_x;
                this.y += this.d_y;
                this.moveToCell();
            }

        } catch (Exception e) {
            throw new RuntimeException("Pacman::render() -> " + e.getMessage());
        }
    }


    public static void increaseLives() {
        ImageView img = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Tiles/pacmanhp.png"))));
        img.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() + 20);
        img.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT() + 20);
        img.setTranslateY(600);
        img.setTranslateX(Pacman.getLives().size() * img.getFitWidth());

        Pacman.lives.add(img);
        MapHandler.getGameMapPane().getChildren().add(img);
    }


    public void addCollected(Collectible collectible) {
        try {

            Collectible copel = collectible.getClass().getDeclaredConstructor().newInstance();

            copel.getImageView().setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()+10);
            copel.getImageView().setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()+10);
            copel.getImageView().setTranslateY(600);
            copel.getImageView().setTranslateX(REGISTRY_KEYS.GET_GAME_MAP_WIDTH() - (Pacman.collected.size() * copel.getImageView().getFitWidth() + 30));

            Pacman.collected.add(copel);
            MapHandler.getGameMapPane().getChildren().add(copel.getImageView());
        } catch (Exception e) {
            throw new RuntimeException("Pacman::addCollected() -> " + e.getMessage());
        }
    }


    public static Stack<ImageView> getLives() {
        return Pacman.lives;
    }


    public static Stack<Collectible> getCollected() {
        return Pacman.collected;
    }


    public synchronized int[] getDirection() {
        return new int[]{this.d_x, this.d_y};
    }


    private Mob caughtByGhost() {
        for (Mob mob : EntityHandler.getMobs().values()) {
            if (!(mob instanceof Pacman) && this.x == mob.getX() && this.y == mob.getY()) {
                return mob;
            }
        }

        return null;
    }
}
