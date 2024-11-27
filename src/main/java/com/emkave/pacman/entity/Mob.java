package com.emkave.pacman.entity;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;


public abstract class Mob extends Entity { // Every mob is an entity
    protected int d_x = 0, d_y = 0; // Every mob has its own direction vector (delta x, delta y)
    protected Image dirUpImg, dirRightImg, dirDownImg, dirLeftImg;

    Mob(final String mobName) {
        this.dirUpImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_up.gif")));
        this.dirRightImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_right.gif")));
        this.dirDownImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_down.gif")));
        this.dirLeftImg = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/"+mobName+"_moves_left.gif")));
        this.imageView.setImage(this.dirUpImg);
    }


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

            transition.play();
        }
    }
}

