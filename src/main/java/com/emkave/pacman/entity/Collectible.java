package com.emkave.pacman.entity;

import javafx.scene.image.ImageView;

public abstract class Collectible extends Entity { // Every collectible is an entity
    Collectible(ImageView __imageView, final double x, final double y) {
        super(__imageView, x, y);
    }


    @Override public void render() {

    }
}
