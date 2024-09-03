package com.example.pacman.scene;

import com.example.pacman.Application;
import com.example.pacman.ui.UIImageBasedButton;
import com.example.pacman.ui.UITextBasedButton;
import com.example.pacman.ui.UILabel;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Settings {
    private Stage stage;

    public Settings(Stage stage) {
        this.stage = stage;
    }

    public void initializeSettings() {
        Application.uiLayer.getChildren().clear();


        UILabel label = new UILabel(Application.resourceBundle.getString("settings"), 40);
        label.setTextFill(Color.WHITE);
        label.setTranslateY(-290);

        UITextBasedButton backButton = new UITextBasedButton(Application.resourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            MainMenu mainMenu = new MainMenu(stage);
            mainMenu.initializeMenu();
        });
        backButton.setTranslateY(270);


        Application.uiLayer.getChildren().addAll(label, backButton);
    }
}
