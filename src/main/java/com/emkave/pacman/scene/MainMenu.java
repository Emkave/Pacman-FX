package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.handler.SceneHandler;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class MainMenu {
    private static UILabel scoreLabel;
    private static UITextBasedButton playButton, optionsButton, statsButton, exitButton;

    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        MainMenu.playButton = new UITextBasedButton(Application.localeResourceBundle.getString("play"));
        playButton.setOnAction(event -> {
            playButton.getStyleClass().add("main-menu-play-pressed");
            SceneHandler.loadGameRegimes();
        });
        playButton.setTranslateY(90);

        MainMenu.optionsButton = new UITextBasedButton(Application.localeResourceBundle.getString("settings"));
        optionsButton.setOnAction(event -> {
            optionsButton.getStyleClass().add("main-menu-options-pressed");
            SceneHandler.loadSettings();
        });
        optionsButton.setTranslateY(150);

        MainMenu.statsButton = new UITextBasedButton(Application.localeResourceBundle.getString("stats"));
        statsButton.setOnAction(event -> {
            statsButton.getStyleClass().add("main-menu-options-pressed");
            SceneHandler.loadStatistics();
        });
        statsButton.setTranslateY(210);

        MainMenu.exitButton = new UITextBasedButton(Application.localeResourceBundle.getString("exit"));
        exitButton.setOnAction(event -> {
            exitButton.getStyleClass().remove("main-menu-button");
            exitButton.getStyleClass().add("main-menu-exit-pressed");
            Application.window.close();
        });
        exitButton.setTranslateY(270);

        MainMenu.scoreLabel = new UILabel(Application.localeResourceBundle.getString("your_top_score") + ConfigHandler.getTopScore(), 10);
        scoreLabel.setTranslateY(-100);
        scoreLabel.setFill(Color.WHITE);

        Image blinkyGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/blinky_moves_right.gif")));
        ImageView blinkyGIFImageView = new ImageView(blinkyGIF);
        blinkyGIFImageView.setFitWidth(70);
        blinkyGIFImageView.setPreserveRatio(true);
        blinkyGIFImageView.setTranslateY(-170);
        blinkyGIFImageView.setTranslateX(-148);

        Image inkyGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/inky_moves_right.gif")));
        ImageView inkyGIFImageView = new ImageView(inkyGIF);
        inkyGIFImageView.setFitWidth(70);
        inkyGIFImageView.setPreserveRatio(true);
        inkyGIFImageView.setTranslateY(-170);
        inkyGIFImageView.setTranslateX(-50);

        Image pinkyGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pinky_moves_right.gif")));
        ImageView pinkyGIFImageView = new ImageView(pinkyGIF);
        pinkyGIFImageView.setFitWidth(70);
        pinkyGIFImageView.setPreserveRatio(true);
        pinkyGIFImageView.setTranslateY(-170);
        pinkyGIFImageView.setTranslateX(50);

        Image clydeGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/clyde_moves_right.gif")));
        ImageView clydeGIFImageView = new ImageView(clydeGIF);
        clydeGIFImageView.setFitWidth(70);
        clydeGIFImageView.setPreserveRatio(true);
        clydeGIFImageView.setTranslateY(-170);
        clydeGIFImageView.setTranslateX(148);

        Image mainLabel = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/UI/Pacman-FX.png")));
        ImageView mainLabelView = new ImageView(mainLabel);
        mainLabelView.setFitWidth(450);
        mainLabelView.setPreserveRatio(true);
        mainLabelView.setTranslateY(-290);

        uiLayer.getChildren().addAll(clydeGIFImageView,
                pinkyGIFImageView, inkyGIFImageView, MainMenu.scoreLabel,
                blinkyGIFImageView, mainLabelView,
                MainMenu.exitButton, MainMenu.optionsButton
                , MainMenu.playButton, MainMenu.statsButton);


        return uiLayer;
    }


    public static void reloadUI() {
        MainMenu.playButton.setButtonText(Application.localeResourceBundle.getString("play"));
        MainMenu.optionsButton.setButtonText(Application.localeResourceBundle.getString("settings"));
        MainMenu.statsButton.setButtonText(Application.localeResourceBundle.getString("stats"));
        MainMenu.exitButton.setButtonText(Application.localeResourceBundle.getString("exit"));
        MainMenu.scoreLabel.setText(Application.localeResourceBundle.getString("your_top_score")
                + ConfigHandler.getTopScore());
    }
}
