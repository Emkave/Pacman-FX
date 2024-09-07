package com.example.pacman.scene;

import com.example.pacman.Application;
import com.example.pacman.handler.ConfigHandler;
import com.example.pacman.handler.SoundHandler;
import com.example.pacman.ui.UIImageBasedButton;
import com.example.pacman.ui.UITextBasedButton;
import com.example.pacman.ui.UILabel;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;


public class Settings {
    private UIImageBasedButton muteButton;

    public Settings() {
        Application.uiLayerPane.getChildren().clear();

        UILabel mainLabel = new UILabel(Application.resourceBundle.getString("settings"), 40);
        mainLabel.setTextFill(Color.WHITE);
        mainLabel.setTranslateY(-270);

        UILabel langSectLabel = new UILabel(Application.resourceBundle.getString("language"), 26);
        langSectLabel.setTextFill(Color.GRAY);
        langSectLabel.setTranslateY(-150);

        UILabel rusLangLabel = new UILabel(Application.resourceBundle.getString("russian"), 15);
        rusLangLabel.setTextFill(Color.GRAY);
        rusLangLabel.setTranslateY(-60);
        rusLangLabel.setTranslateX(-169);
        rusLangLabel.setAlignment(Pos.CENTER);

        UILabel engLangLabel = new UILabel(Application.resourceBundle.getString("english"), 15);
        engLangLabel.setTextFill(Color.GRAY);
        engLangLabel.setTranslateY(-60);
        engLangLabel.setAlignment(Pos.CENTER);

        UILabel czeLangLabel = new UILabel(Application.resourceBundle.getString("czech"), 15);
        czeLangLabel.setTextFill(Color.GRAY);
        czeLangLabel.setTranslateX(169);
        czeLangLabel.setTranslateY(-60);
        czeLangLabel.setAlignment(Pos.CENTER);

        UILabel soundsLabel = new UILabel(Application.resourceBundle.getString("sounds"), 26);
        soundsLabel.setTextFill(Color.GRAY);
        soundsLabel.setAlignment(Pos.CENTER);
        soundsLabel.setTranslateX(220);
        soundsLabel.setTranslateY(270);

        UITextBasedButton backButton = new UITextBasedButton(Application.resourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            new MainMenu();
        });
        backButton.setTranslateY(320);
        backButton.setTranslateX(-140);

        UIImageBasedButton rusLangButton = new UIImageBasedButton("Images/Flags/sov.png");
        rusLangButton.setOnAction(event -> {
            rusLangButton.getStyleClass().add("rus-lang-options-pressed");
            ConfigHandler.setLanguageSettings("ru", "RU");
            reloadUI();
        });
        rusLangButton.setTranslateX(-169);
        //rusLangButton.setTranslateY(-130);
        rusLangButton.setButtonSize(130, 130);

        UIImageBasedButton engLangButton = new UIImageBasedButton("Images/Flags/eng.png");
        engLangButton.setOnAction(event -> {
            engLangButton.getStyleClass().add("eng-lang-options-pressed");
            ConfigHandler.setLanguageSettings("en", "EN");
            reloadUI();
        });
        //engLangButton.setTranslateY(-130);
        engLangButton.setButtonSize(130, 130);

        UIImageBasedButton czeLangButton = new UIImageBasedButton("Images/Flags/cze.png");
        czeLangButton.setOnAction(event -> {
            czeLangButton.getStyleClass().add("cze-lang-options-pressed");
            ConfigHandler.setLanguageSettings("cz", "CZ");
            reloadUI();
        });
        czeLangButton.setTranslateX(169);
        //czeLangButton.setTranslateY(-130);
        czeLangButton.setButtonSize(130, 130);

        this.muteButton = new UIImageBasedButton(SoundHandler.isMuted() ? "Images/UI/mute.png" : "Images/UI/unmute.png");
        this.muteButton.setOnAction(actionEvent -> this.toggleMute());
        this.muteButton.setTranslateY(320);
        this.muteButton.setTranslateX(220);
        this.muteButton.setButtonSize(60, 60);

        Application.uiLayerPane.getChildren().addAll(czeLangLabel, engLangLabel,
                this.muteButton, soundsLabel,
                rusLangLabel, czeLangButton, engLangButton,
                rusLangButton, langSectLabel, mainLabel, backButton);
    }


    private void toggleMute() {
        SoundHandler.toggleMute();
        this.muteButton.changeImage(SoundHandler.isMuted() ? "Images/UI/mute.png" : "Images/UI/unmute.png");
    }


    private void reloadUI() {
        Application.uiLayerPane.getChildren().clear();
        new Settings();
    }
}
