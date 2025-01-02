package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import com.emkave.pacman.handler.SceneHandler;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import java.util.Objects;

public class MainMenu {
    private static UITextBasedButton playButton;
    private static UITextBasedButton optionsButton;
    private static UITextBasedButton statsButton;
    private static UITextBasedButton exitButton;


    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        playButton = new UITextBasedButton(Application.localeResourceBundle.getString("play"));
        playButton.setOnAction(event -> {
            playButton.getStyleClass().add("main-menu-play-pressed");
            try {
                Game.setGameDots(0);
                Game.setLastGameScore(0);
                if (Game.getGameLevel() != 0) {
                    Game.setGameLevel(1);
                }
                Game.setGameScore(0);
                Pacman.setLivesCount(3);
                Pacman.getCollected().clear();
                SceneHandler.loadGame();
            } catch (Exception e) {
                throw new RuntimeException("MainMenu::load() -> " + e.getMessage());
            }
        });
        playButton.setTranslateY(90);

        optionsButton = new UITextBasedButton(Application.localeResourceBundle.getString("settings"));
        optionsButton.setOnAction(event -> {
            optionsButton.getStyleClass().add("main-menu-options-pressed");
            SceneHandler.loadSettings();
        });
        optionsButton.setTranslateY(150);

        statsButton = new UITextBasedButton(Application.localeResourceBundle.getString("stats"));
        statsButton.setOnAction(event -> {
            statsButton.getStyleClass().add("main-menu-options-pressed");
            SceneHandler.loadStatistics();
        });
        statsButton.setTranslateY(210);

        exitButton = new UITextBasedButton(Application.localeResourceBundle.getString("exit"));
        exitButton.setOnAction(event -> {
            exitButton.getStyleClass().remove("main-menu-button");
            exitButton.getStyleClass().add("main-menu-exit-pressed");
            Application.window.close();
        });
        exitButton.setTranslateY(270);

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
                pinkyGIFImageView, inkyGIFImageView,
                blinkyGIFImageView, mainLabelView,
                exitButton, optionsButton
                , playButton, statsButton);


        Application.window.getScene().setOnKeyPressed(MainMenu::handleKeyPress);

        return uiLayer;
    }


    private static void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();

        switch (code) {
            case HOME:
                Game.setGameLevel(0);
                break;

            default:
                break;
        }
    }


    public void reloadUI() {
        playButton.setButtonText(Application.localeResourceBundle.getString("play"));
        optionsButton.setButtonText(Application.localeResourceBundle.getString("settings"));
        statsButton.setButtonText(Application.localeResourceBundle.getString("stats"));
        exitButton.setButtonText(Application.localeResourceBundle.getString("exit"));
    }
}
