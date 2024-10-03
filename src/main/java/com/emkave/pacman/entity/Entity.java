package com.emkave.pacman.entity;

import com.emkave.pacman.handler.MapHandler;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Entity {
    protected double x, y;
    protected double d_x, d_y;
    protected ImageView imageView;

    static final double Igc_x = (double) 550 / 2;
    static final double Igc_y = (double) 550 / 2;
    static final double Igs_x = (double) 550 / 20;
    static final double Igs_y = (double) 550 / 22;
    static int[][] map;

    public Entity(double startX, double startY) throws IOException {
        this.x = startX;
        this.y = startY;
        Entity.map = MapHandler.loadMap();
    }


    public ImageView getImageView() {
        return this.imageView;
    }


    public void renderOnMap() {
        this.imageView.setTranslateX(-Igc_x + (Igs_x * this.x));
        this.imageView.setTranslateY(-Igc_y + (Igs_y * this.y));
    }
}
