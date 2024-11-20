package com.emkave.pacman.entity;

import com.emkave.pacman.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


public class Inky extends Mob {
    public Inky() {
        super(new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/inky_moves_down.gif")))));
    }
}
