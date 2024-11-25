package com.emkave.pacman.entity;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.scene.image.ImageView;
import kotlin.Pair;


public abstract class Entity {
    protected int x, y; // Every entity has its own position
    protected ImageView imageView; // Every entity has its own image view


    Entity(ImageView __imageView, final int __x, final int __y) {
        this.x = __x;
        this.y = __y;
        this.imageView = __imageView;
        this.imageView.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()+7);
        this.imageView.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()+6);
    }


    protected abstract void render();


    public ImageView getImageView() { // Every entity can return its image view
        return (this.imageView.getImage() == null) ? null : this.imageView;
    }


    public int getX() {
        return this.x;
    }


    public int getY() {
        return this.y;
    }
}