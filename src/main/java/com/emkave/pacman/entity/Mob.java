package com.emkave.pacman.entity;

import javafx.scene.image.ImageView;

public abstract class Mob extends Entity { // Every mob is an entity
    protected double d_x, d_y; // Every mob has its own direction vector (delta x, delta y)


    Mob(ImageView __imageView) {
        super(__imageView);
    }


    @Override public void render() {

    }
}
