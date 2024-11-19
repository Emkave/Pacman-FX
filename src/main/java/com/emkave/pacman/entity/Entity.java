package com.emkave.pacman.entity;
import javafx.scene.image.ImageView;
import kotlin.Pair;

import java.io.IOException;

public class Entity {
    protected double x, y;
    protected double d_x, d_y;
    protected ImageView imageView;


    public Entity(double startX, double startY) throws IOException {
        this.x = startX;
        this.y = startY;
    }


    public Pair<Double, Double> getSize() {
        if (this.imageView.getImage() == null) {
            return null;
        }

        return new Pair<>(this.imageView.getFitWidth(), this.imageView.getFitHeight());
    }


    public ImageView getImageView() {
        return (this.imageView.getImage() == null) ? null : this.imageView;
    }


    public boolean setSize(double width, double height) {
        if (this.imageView.getImage() != null) {
            this.imageView.setFitWidth(width);
            this.imageView.setFitHeight(height);
            return true;
        }

        return false;
    }


    public boolean setPosition(double x, double y) {
        if (this.imageView.getImage() != null && x >= 0 && y >= 0 && x < 19 && y < 22) {
            this.imageView.setTranslateX(x);
            this.imageView.setTranslateY(y);
            return true;
        }

        return false;
    }


    public void renderOnMap() {
        this.imageView.setTranslateX((470.4 / 19 - 7) * this.x);
        this.imageView.setTranslateY((604.8 / 22 + 7) * this.y);

        this.d_x = 0;
        this.d_y = 0;
    }
}
