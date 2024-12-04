package com.emkave.pacman.entity.mob;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.Objects;
import java.util.Stack;


public class Pacman extends Mob {
    private Stack<ImageView> lives = new Stack<>();

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
            char tileType = MapHandler.getGameMap()[this.y + this.d_y][this.x + this.d_x];

            Collectible tile = MapHandler.getCollectible(MapHandler.getGameMap()[this.y][this.x], this.x, this.y);

            if (tile != null) {
                tile.effect();
            }

            if (tileType != '1' && tileType != '2' && tileType != '3' && tileType != '4' && tileType != '7' && tileType != '8') {
                //this.clearFromMap();
                this.x += this.d_x;
                this.y += this.d_y;
                //this.placeInMap();
                this.moveToCell();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("Pacman pos: " + this.x + " " + this.y);
    }


    public void decreaseLives() {
        MapHandler.getGameMapPane().getChildren().remove(this.lives.peek());
        this.lives.pop();
    }


    public void increaseLives() {
        ImageView img = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Tiles/pacmanhp.png"))));
        img.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() + 20);
        img.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT() + 20);
        img.setTranslateY(600);
        img.setTranslateX(this.getLives().size() * img.getFitWidth());

        this.lives.add(img);
        MapHandler.getGameMapPane().getChildren().add(img);
    }


    public Stack<ImageView> getLives() {
        return this.lives;
    }
}
