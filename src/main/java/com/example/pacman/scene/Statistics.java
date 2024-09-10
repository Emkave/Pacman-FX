package com.example.pacman.scene;

import com.example.pacman.Application;
import com.example.pacman.ui.UILabel;
import com.example.pacman.ui.UITextBasedButton;
import javafx.scene.paint.Color;

import java.util.List;

public class Statistics {
    public Statistics() {
        Application.uiLayerPane.getChildren().clear();

        UILabel statisticsLabel = new UILabel(Application.resourceBundle.getString("stats"), 40);
        statisticsLabel.setFill(Color.WHITE);
        statisticsLabel.setTranslateY(-270);

        UITextBasedButton backButton = new UITextBasedButton(Application.resourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            new MainMenu();
        });
        backButton.setTranslateY(320);
        backButton.setTranslateX(-140);


        Application.uiLayerPane.getChildren().addAll(
                statisticsLabel, backButton
        );
    }
}
