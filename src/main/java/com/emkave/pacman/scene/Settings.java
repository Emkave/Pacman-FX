package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.ui.UIImageBasedButton;
import com.emkave.pacman.ui.UITextBasedButton;
import com.emkave.pacman.ui.UILabel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Objects;


public class Settings {
    private final UIImageBasedButton muteButton;

    public Settings() {
        Application.uiLayerPane.getChildren().clear();

        UILabel mainLabel = new UILabel(Application.resourceBundle.getString("settings"), 40);
        mainLabel.setFill(Color.WHITE);
        mainLabel.setTranslateY(-270);

        ImageView langIconView = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/UI/lang_icon.jpg"))));
        langIconView.setPreserveRatio(true);
        langIconView.setTranslateY(-145);
        langIconView.setFitWidth(67);

        UILabel rusLangLabel = new UILabel("РУССКИЙ", 15);
        rusLangLabel.setFill(Color.WHITE);
        rusLangLabel.setTranslateY(-60);
        rusLangLabel.setTranslateX(-169);

        UILabel engLangLabel = new UILabel("ENGLISH", 15);
        engLangLabel.setFill(Color.WHITE);
        engLangLabel.setTranslateY(-60);

        UILabel czeLangLabel = new UILabel("ČEŠTINA", 15);
        czeLangLabel.setFill(Color.WHITE);
        czeLangLabel.setTranslateX(169);
        czeLangLabel.setTranslateY(-60);

        UILabel soundsLabel = new UILabel(Application.resourceBundle.getString("sounds"), 26);
        soundsLabel.setFill(Color.WHITE);
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
        rusLangButton.setButtonSize(130, 130);

        UIImageBasedButton engLangButton = new UIImageBasedButton("Images/Flags/eng.png");
        engLangButton.setOnAction(event -> {
            engLangButton.getStyleClass().add("eng-lang-options-pressed");
            ConfigHandler.setLanguageSettings("en", "EN");
            reloadUI();
        });
        engLangButton.setButtonSize(130, 130);

        UIImageBasedButton czeLangButton = new UIImageBasedButton("Images/Flags/cze.png");
        czeLangButton.setOnAction(event -> {
            czeLangButton.getStyleClass().add("cze-lang-options-pressed");
            ConfigHandler.setLanguageSettings("cz", "CZ");
            reloadUI();
        });
        czeLangButton.setTranslateX(169);
        czeLangButton.setButtonSize(130, 130);

        this.muteButton = new UIImageBasedButton(SoundHandler.isMuted() ? "Images/UI/mute.png" : "Images/UI/unmute.png");
        this.muteButton.setOnAction(actionEvent -> this.toggleMute());
        this.muteButton.setTranslateY(320);
        this.muteButton.setTranslateX(220);
        this.muteButton.setButtonSize(60, 60);

        Application.uiLayerPane.getChildren().addAll(czeLangLabel, engLangLabel,
                this.muteButton, soundsLabel,
                rusLangLabel, czeLangButton, engLangButton,
                rusLangButton, langIconView, mainLabel, backButton);
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
