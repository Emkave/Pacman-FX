package com.example.pacman.scene;

import com.example.pacman.Application;
import com.example.pacman.ui.UIImageBasedButton;
import com.example.pacman.ui.UITextBasedButton;
import com.example.pacman.ui.UILabel;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Settings {
    private Stage stage;

    public Settings(Stage stage) {
        this.stage = stage;
    }

    public void initializeSettings() {
        Application.uiLayer.getChildren().clear();

        UILabel mainLabel = new UILabel(Application.resourceBundle.getString("settings"), 40);
        mainLabel.setTextFill(Color.WHITE);
        mainLabel.setTranslateY(-290);

        UILabel langSectLabel = new UILabel(Application.resourceBundle.getString("language"), 20);
        langSectLabel.setTextFill(Color.GRAY);
        langSectLabel.setTranslateY(-230);

        UILabel rusLangLabel = new UILabel(Application.resourceBundle.getString("russian"), 10);
        rusLangLabel.setTextFill(Color.GRAY);
        rusLangLabel.setTranslateY(-180);
        rusLangLabel.setTranslateX(-130);
        rusLangLabel.setAlignment(Pos.CENTER);

        UILabel engLangLabel = new UILabel(Application.resourceBundle.getString("english"), 10);
        engLangLabel.setTextFill(Color.GRAY);
        engLangLabel.setTranslateY(-180);
        engLangLabel.setAlignment(Pos.CENTER);

        UILabel czeLangLabel = new UILabel(Application.resourceBundle.getString("czech"), 10);
        czeLangLabel.setTextFill(Color.GRAY);
        czeLangLabel.setTranslateX(130);
        czeLangLabel.setTranslateY(-180);
        czeLangLabel.setAlignment(Pos.CENTER);

        UITextBasedButton backButton = new UITextBasedButton(Application.resourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            MainMenu mainMenu = new MainMenu(stage);
            mainMenu.initializeMenu();
        });
        backButton.setTranslateY(270);

        UIImageBasedButton rusLangButton = new UIImageBasedButton("Images/Flags/sov.png");
        rusLangButton.setOnAction(event -> {
            rusLangButton.getStyleClass().add("rus-lang-options-pressed");
        });
        rusLangButton.setTranslateX(-130);
        rusLangButton.setTranslateY(-130);
        rusLangButton.setButtonSize(100, 100);

        UIImageBasedButton engLangButton = new UIImageBasedButton("Images/Flags/eng.png");
        engLangButton.setOnAction(event -> {
            engLangButton.getStyleClass().add("eng-lang-options-pressed");
        });
        engLangButton.setTranslateY(-130);
        engLangButton.setButtonSize(100, 100);

        UIImageBasedButton czeLangButton = new UIImageBasedButton("Images/Flags/cze.png");
        czeLangButton.setOnAction(event -> {
            czeLangButton.getStyleClass().add("cze-lang-options-pressed");
        });
        czeLangButton.setTranslateX(130);
        czeLangButton.setTranslateY(-130);
        czeLangButton.setButtonSize(100, 100);

        Application.uiLayer.getChildren().addAll(czeLangLabel, engLangLabel, rusLangLabel, czeLangButton, engLangButton, rusLangButton, langSectLabel, mainLabel, backButton);
    }
}
