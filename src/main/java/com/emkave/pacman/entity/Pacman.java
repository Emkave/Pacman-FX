package com.emkave.pacman.entity;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.Objects;


public class Pacman extends Mob {
    public Pacman() {
        super(new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pacman_moves_right.gif")))), 1, 1);
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
            int tileType = MapHandler.getGameMap()[this.y + this.d_y][this.x + this.d_x];

            if (tileType != 1 && tileType != 2 && tileType != 3 && tileType != 4 && tileType != 7 && tileType != 8) {
                this.x += this.d_x;
                this.y += this.d_y;
                this.moveToCell();
            }
        } catch (Exception _) {}

        System.out.println("Pacman pos: " + this.x + " " + this.y);
    }
}
