package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Entity;
import javafx.scene.image.Image;

import java.util.Objects;

public abstract class Collectible extends Entity { // Every collectible is an entity
    Collectible(final String colName) {
        this.imageView.setImage(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Tiles/"+colName+".png"))));
    }


    protected void effect() {};


    @Override public void render() {

    }
}
