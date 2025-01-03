package com.emkave.pacman.entity;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.scene.image.ImageView;


public abstract class Entity {
    protected int x, y; // Every entity has its own position
    protected ImageView imageView = new ImageView(); // Every entity has its own image view


    protected Entity() {
        this.imageView.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()+7);
        this.imageView.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()+6);
    }


    protected abstract void render();


    public ImageView getImageView() { // Every entity can return its image view
        return (this.imageView.getImage() == null) ? null : this.imageView;
    }


    public synchronized int getX() {
        return this.x;
    }


    public synchronized int getY() {
        return this.y;
    }


    public synchronized void setX(final int x) {
        this.x = x;
        this.imageView.setTranslateX(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() * this.x);
    }


    public synchronized void setY(final int y) {
        this.y = y;
        this.imageView.setTranslateY(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT() * this.y);
    }
}