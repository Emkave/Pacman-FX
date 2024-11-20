package com.emkave.pacman.entity;

import com.emkave.pacman.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.Objects;


public class Pacman extends Mob {
    public Pacman() {
        super(new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pacman_moves_right.gif")))));
        this.d_x = 1; this.d_y = 0;

    }


    public void updatePosition() {
        try {
            if (this.x + this.d_x > 0 && this.x + this.d_x < 19) {
                this.x += this.d_x;
            }

            if (this.y + this.d_y > 0 && this.y + this.d_y < 22) {
                this.y += this.d_y;
            }
        } catch (Exception _) {}

        System.out.println("Pacman pos: " + this.x + " " + this.y);
    }


    public void handleKeyPress(KeyEvent event) { // Pacman should be able to be controlled
        switch (event.getCode()) { // Pacman's direction vector should be assigned
            case W:
                this.d_x = 0;
                this.d_y = -1;
                break;

            case S:
                this.d_x = 0;
                this.d_y = 1;
                break;

            case A:
                this.d_x = -1;
                this.d_y = 0;
                break;

            case D:
                this.d_x = 1;
                this.d_y = 0;
                break;

            default:
                break;
        }
    }


    @Override public void render() {

    }
}
