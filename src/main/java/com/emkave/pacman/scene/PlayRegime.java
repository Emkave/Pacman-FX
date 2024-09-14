package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.scene.paint.Color;

import java.io.IOException;

public class PlayRegime {
    public PlayRegime() {
        Application.uiLayerPane.getChildren().clear();

        UILabel playLabel = new UILabel(Application.resourceBundle.getString("game_regime"), 40);
        playLabel.setFill(Color.WHITE);
        playLabel.setTranslateY(-270);

        UITextBasedButton newGameButton = new UITextBasedButton(Application.resourceBundle.getString("new_game"));
        newGameButton.setOnAction(event -> {

        });

        UILabel newGameWarningLabel = new UILabel(Application.resourceBundle.getString("new_game_warning"), 10);
        newGameWarningLabel.setTranslateY(30);
        newGameWarningLabel.setFill(Color.DARKRED);

        UITextBasedButton continueButton = new UITextBasedButton(Application.resourceBundle.getString("continue"));
        continueButton.setOnAction(event -> {
            try {
                var game = new Game();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        continueButton.setTranslateY(80);

        UILabel continueLevelLabel = new UILabel(Application.resourceBundle.getString("level") + ConfigHandler.getLevel(), 10);
        continueLevelLabel.setTranslateY(110);
        continueLevelLabel.setFill(Color.CYAN);


        UITextBasedButton multiplayerButton = new UITextBasedButton(Application.resourceBundle.getString("multiplayer"));
        multiplayerButton.setOnAction(event -> {

        });
        multiplayerButton.setTranslateY(160);

        UILabel multiplayerDescriptionLabel = new UILabel(Application.resourceBundle.getString("multiplayer_info"), 10);
        multiplayerDescriptionLabel.setTranslateY(190);
        multiplayerDescriptionLabel.setFill(Color.WHITE);


        UITextBasedButton backButton = new UITextBasedButton(Application.resourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            new MainMenu();
        });
        backButton.setTranslateY(320);
        backButton.setTranslateX(-140);


        Application.uiLayerPane.getChildren().addAll(
                playLabel, backButton, continueButton, multiplayerButton, newGameButton,
                newGameWarningLabel, continueLevelLabel, multiplayerDescriptionLabel
        );
    }
}
