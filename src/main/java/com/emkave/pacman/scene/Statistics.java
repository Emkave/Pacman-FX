package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.SceneHandler;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Statistics {
    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        UILabel statisticsLabel = new UILabel(Application.localeResourceBundle.getString("stats"), 40);
        statisticsLabel.setFill(Color.WHITE);
        statisticsLabel.setTranslateY(-270);

        UILabel cannotConnectLabel = new UILabel(Application.localeResourceBundle.getString("cant_connect_to_database"), 20);
        cannotConnectLabel.setFill(Color.RED);
        cannotConnectLabel.setTextAlignment(TextAlignment.CENTER);

        UITextBasedButton backButton = new UITextBasedButton(Application.localeResourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            SceneHandler.exitScene();
        });
        backButton.setTranslateY(320);
        backButton.setTranslateX(-140);

        uiLayer.getChildren().addAll(
                statisticsLabel, backButton, cannotConnectLabel
        );

        return uiLayer;
    }
}
