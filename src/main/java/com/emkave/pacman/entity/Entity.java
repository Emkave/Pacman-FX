package com.emkave.pacman.entity;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.scene.image.ImageView;
import kotlin.Pair;


public abstract class Entity {
    protected int x, y; // Every entity has its own position
    protected ImageView imageView = new ImageView(); // Every entity has its own image view


    Entity() {
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