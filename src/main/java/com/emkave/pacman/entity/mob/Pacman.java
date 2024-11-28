package com.emkave.pacman.entity.mob;

import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.handler.MapHandler;
import javafx.scene.input.KeyEvent;


public class Pacman extends Mob {
    private static byte lives = 3;

    public Pacman() {
        super("pacman");
        this.x = 1;
        this.y = 1;
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

            if (tileType != '1' && tileType != '2' && tileType != '3' && tileType != '4' && tileType != '7' && tileType != '8') {
                this.x += this.d_x;
                this.y += this.d_y;
                this.moveToCell();
                Collectible tile = MapHandler.getCollectible(tileType, this.x, this.y);

                if (tile != null) {
                    tile.effect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Pacman pos: " + this.x + " " + this.y);
    }


    public static void decreaseLives() {
        Pacman.lives--;
    }


    public static void increaseLives() {
        Pacman.lives++;
    }
}
