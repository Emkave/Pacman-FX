package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import com.emkave.pacman.handler.SceneHandler;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class PlayRegime {
    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        UILabel playLabel = new UILabel(Application.localeResourceBundle.getString("game_regime"), 40);
        playLabel.setFill(Color.WHITE);
        playLabel.setTranslateY(-270);

        UITextBasedButton newGameButton = new UITextBasedButton(Application.localeResourceBundle.getString("new_game"));
        newGameButton.setOnAction(event -> {
            try {
                REGISTRY_KEYS.SET_LAST_GAME_LEVEL(1);
                REGISTRY_KEYS.SET_LAST_GAME_SCORE(0);
                REGISTRY_KEYS.SET_PACLIVES(3);
                SceneHandler.loadGame();
            } catch (Exception e) {
                throw new RuntimeException("PlayRegime::load() -> " + e.getMessage());
            }
        });

        UILabel newGameWarningLabel = new UILabel(Application.localeResourceBundle.getString("new_game_warning"), 10);
        newGameWarningLabel.setTranslateY(30);
        newGameWarningLabel.setFill(Color.DARKRED);

        UITextBasedButton continueButton = new UITextBasedButton(Application.localeResourceBundle.getString("continue"));
        continueButton.setOnAction(event -> {
            try {
                SceneHandler.loadGame();
            } catch (Exception e) {
                throw new RuntimeException("PlayRegime::load() -> " + e.getMessage());
            }
        });
        continueButton.setTranslateY(80);

        UILabel continueLevelLabel = new UILabel(Application.localeResourceBundle.getString("level") + ConfigHandler.getLevel(), 10);
        continueLevelLabel.setTranslateY(110);
        continueLevelLabel.setFill(Color.CYAN);


        UITextBasedButton backButton = new UITextBasedButton(Application.localeResourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            SceneHandler.exitScene();
        });
        backButton.setTranslateY(320);
        backButton.setTranslateX(-140);


        uiLayer.getChildren().addAll(
                playLabel, backButton, continueButton, newGameButton,
                newGameWarningLabel, continueLevelLabel
        );

        return uiLayer;
    }
}
