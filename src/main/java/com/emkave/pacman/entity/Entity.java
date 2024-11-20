package com.emkave.pacman.entity;
import javafx.scene.image.ImageView;
import kotlin.Pair;


public abstract class Entity {
    protected double x, y; // Every entity has its own position
    protected ImageView imageView; // Every entity has its own image view


    Entity(ImageView __imageView) {
        this.imageView = __imageView;
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


    public boolean setSize(double width, double height) { // Every entity is sizeable
        if (this.imageView.getImage() != null) {
            this.imageView.setFitWidth(width);
            this.imageView.setFitHeight(height);
            return true;
        }

        return false;
    }


    public boolean setPosition(double x, double y) { // Every entity able to set position
        if (this.imageView.getImage() != null && x >= 0 && y >= 0 && x < 19 && y < 22) {
            this.x = x; this.y = y;
            return true;
        }

        return false;
    }
}
