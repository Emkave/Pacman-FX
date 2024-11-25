package com.emkave.pacman.entity;

import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public abstract class Mob extends Entity { // Every mob is an entity
    protected int d_x, d_y; // Every mob has its own direction vector (delta x, delta y)


    Mob(ImageView __imageView, final int x, final int y) {
        super(__imageView, x, y);
        this.d_x = 0; this.d_y = 0;
    }


    @Override public void render() {

    }


    protected void moveToCell() {
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

