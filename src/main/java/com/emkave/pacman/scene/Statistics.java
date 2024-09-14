package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Statistics {
    public Statistics() {
        Application.uiLayerPane.getChildren().clear();

        UILabel statisticsLabel = new UILabel(Application.resourceBundle.getString("stats"), 40);
        statisticsLabel.setFill(Color.WHITE);
        statisticsLabel.setTranslateY(-270);

        UILabel cannotConnectLabel = new UILabel(Application.resourceBundle.getString("cant_connect_to_database"), 20);
        cannotConnectLabel.setFill(Color.RED);
        cannotConnectLabel.setTextAlignment(TextAlignment.CENTER);


        UITextBasedButton backButton = new UITextBasedButton(Application.resourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            new MainMenu();
        });
        backButton.setTranslateY(320);
        backButton.setTranslateX(-140);


        Application.uiLayerPane.getChildren().addAll(
                statisticsLabel, backButton, cannotConnectLabel
        );
    }
}
