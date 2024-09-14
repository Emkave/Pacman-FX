package com.emkave.pacman.entity;

import com.emkave.pacman.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Objects;

public class Pacman extends Entity {
    private byte currentDirection = 1;

    public Pacman(double startX, double startY) throws IOException {
        super(startX, startY);

        this.imageView = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pacman_moves_right.gif"))));
        this.imageView.setFitWidth(Entity.Igs_y);
        this.imageView.setFitHeight(Entity.Igs_y);


        super.renderOnMap();
    }


    public void updatePosition() {
        try {
            switch (this.currentDirection) {
                case 0:
                    if (Entity.map[(int)super.y-1][(int)super.x] != 1) {
                        this.y--;
                    }
                    break;

                case 1:
                    if (Entity.map[(int)super.y][(int)super.x+1] != 1) {
                        this.x++;
                    }
                    break;

                case 2:
                    if (Entity.map[(int)super.y+1][(int)super.x] != 1) {
                        this.y++;
                    }
                    break;

                case 3:
                    if (Entity.map[(int)super.y][(int)super.x-1] != 1) {
                        this.x--;
                    }
                    break;
            }
        } catch (Exception _) {}

        super.renderOnMap();

        System.out.println("Pacman pos: " + this.x + " " + this.y);
    }


    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                if (this.currentDirection != 0) {
                    this.velocityX = 0;
                    this.velocityY = -this.speed;
                    this.currentDirection = 0;
                }
                break;

            case S:
                if (this.currentDirection != 2) {
                    this.velocityX = 0;
                    this.velocityY = this.speed;
                    this.currentDirection = 2;
                }
                break;

            case A:
                if (this.currentDirection != 3) {
                    this.velocityX = -this.speed;
                    this.velocityY = 0;
                    this.currentDirection = 3;
                }
                break;

            case D:
                if (this.currentDirection != 1) {
                    this.velocityX = this.speed;
                    this.velocityY = 0;
                    this.currentDirection = 1;
                }
                break;

            default:
                break;
        }
    }


    public void handleKeyRelease(KeyEvent event) {
        switch (event.getCode()) {
            case W: case S: case A: case D:
                velocityY = 0;
                velocityX = 0;
                break;
        }
    }


    public ImageView getPacmanImageView() {
        return this.imageView;
    }
}
