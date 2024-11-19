package com.emkave.pacman.entity;

import com.emkave.pacman.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class Inky extends Entity {
    public Inky(double startX, double startY) throws IOException {
        super(startX, startY);

        this.imageView = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/inky_moves_down.gif"))));

    }
}
