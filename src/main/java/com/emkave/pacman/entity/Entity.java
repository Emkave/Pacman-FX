package com.emkave.pacman.entity;

import com.emkave.pacman.handler.MapHandler;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Entity {
    protected double x, y;
    protected double velocityX, velocityY;
    protected final double speed = 2;
    protected ImageView imageView;

    static final double Igc_x = 470.4 / 2;
    static final double Igc_y = 604.8 / 2;
    static final double Igs_x = 470.4 / 20;
    static final double Igs_y = 604.8 / 22;
    static int[][] map;

    public Entity(double startX, double startY) throws IOException {
        this.x = startX;
        this.y = startY;
        Entity.map = MapHandler.loadMap();
    }


    public void renderOnMap() {
        this.imageView.setTranslateX(-Entity.Igc_x + ((this.x + 1) * Entity.Igs_x));
        this.imageView.setTranslateY(-Entity.Igc_y + ((this.y + 1) * Entity.Igs_y));
    }
}
