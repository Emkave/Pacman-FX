package com.example.pacman.scene;

import com.example.pacman.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Game {
    public Game() {
        Application.uiLayerPane.getChildren().clear();

        ImageView mapView = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/UI/map.png"))));
        mapView.setFitWidth(470.4);
        mapView.setFitHeight(604.8);

        Application.uiLayerPane.getChildren().addAll(
            mapView
        );
    }
}
