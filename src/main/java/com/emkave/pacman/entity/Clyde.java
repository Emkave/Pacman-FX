package com.emkave.pacman.entity;

import com.emkave.pacman.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Clyde extends Mob {
    public Clyde() {
        super(new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/clyde_moves_right.gif")))));
    }
}
