package com.emkave.pacman.entity;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.scene.image.ImageView;
import kotlin.Pair;


public abstract class Entity {
    protected double x, y; // Every entity has its own position
    protected ImageView imageView; // Every entity has its own image view


    Entity(ImageView __imageView, final double __x, final double __y) {
        this.x = __x;
        this.y = __y;
        this.imageView = __imageView;
        this.imageView.setSmooth(false);
        this.imageView.setPreserveRatio(true);
        this.imageView.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
        this.imageView.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
        this.imageView.setTranslateX(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() * this.x + 1);
        this.imageView.setTranslateY(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT() * this.y + 2);
    }


    protected abstract void render();


    public Pair<Double, Double> getSize() { // Every entity has its size
        if (this.imageView.getImage() == null) {
            return null;
        }

        return new Pair<>(this.imageView.getFitWidth(), this.imageView.getFitHeight());
    }


    public ImageView getImageView() { // Every entity can return its image view
        return (this.imageView.getImage() == null) ? null : this.imageView;
    }


    public boolean setPosition(double x, double y) { // Every entity able to set position
        if (this.imageView.getImage() != null && x >= 0 && y >= 0 && x < REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH() && y < REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()) {
            this.x = x; this.y = y;
            return true;
        }

        return false;
    }
}