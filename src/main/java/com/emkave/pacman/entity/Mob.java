package com.emkave.pacman.entity;

import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public abstract class Mob extends Entity { // Every mob is an entity
    protected int gridX, gridY;
    protected double d_x, d_y; // Every mob has its own direction vector (delta x, delta y)
    protected double renderX, renderY;
    protected double speed = REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() / 10.0;


    Mob(ImageView __imageView, final double x, final double y) {
        super(__imageView, x, y);
        this.gridX = (int)x;
        this.gridY = (int)y;
    }


    @Override public void render() {
    }
}

