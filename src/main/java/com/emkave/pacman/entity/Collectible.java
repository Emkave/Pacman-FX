package com.emkave.pacman.entity;

import javafx.scene.image.ImageView;

public abstract class Collectible extends Entity { // Every collectible is an entity
    protected byte amount = 0;


    Collectible(ImageView __imageView) {
        super(__imageView);
    }


    @Override public void render() {

    }
}
