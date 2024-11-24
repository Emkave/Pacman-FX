package com.emkave.pacman.entity;


import com.emkave.pacman.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Pinky extends Mob {
    public Pinky() {
        super(new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pinky_moves_down.gif")))), 0, 0);
    }
}
