package com.example.pacman.scene;

import com.almasb.fxgl.ui.UI;
import com.example.pacman.Application;
import com.example.pacman.handler.ConfigHandler;
import com.example.pacman.ui.UILabel;
import com.example.pacman.ui.UITextBasedButton;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

        });
        continueButton.setTranslateY(80);

        UILabel continueLevelLabel = new UILabel(Application.resourceBundle.getString("continue_level") + ConfigHandler.getLevel(), 10);
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
