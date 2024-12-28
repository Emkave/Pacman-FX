package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.DatabaseHandler;
import com.emkave.pacman.handler.SceneHandler;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.Map;


public class Statistics {
    private static VBox playerListBox;


    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        UILabel statisticsLabel = new UILabel(Application.localeResourceBundle.getString("stats"), 40);
        statisticsLabel.setFill(Color.WHITE);
        statisticsLabel.setTranslateY(-270);

        UILabel cannotConnectLabel = null;
        if (DatabaseHandler.isConnected() == null && DatabaseHandler.connectToDatabase() == null) {
            cannotConnectLabel = new UILabel(Application.localeResourceBundle.getString("cant_connect_to_database"), 20);
            cannotConnectLabel.setFill(Color.RED);
            cannotConnectLabel.setTextAlignment(TextAlignment.CENTER);
        } else {
            Statistics.playerListBox = new VBox(10);
            Statistics.playerListBox.setAlignment(Pos.CENTER_LEFT);
            Statistics.updatePlayerList();

            uiLayer.getChildren().addAll(Statistics.playerListBox);
        }

        UITextBasedButton backButton = new UITextBasedButton(Application.localeResourceBundle.getString("back"));
        backButton.setOnAction(event -> {
            backButton.getStyleClass().add("settings-back-pressed");
            SceneHandler.loadMainMenu();
            DatabaseHandler.stopConnection();
        });
        backButton.setTranslateY(320);
        backButton.setTranslateX(-140);

        if (DatabaseHandler.isConnected() == null) {
            uiLayer.getChildren().add(cannotConnectLabel);
        }

        uiLayer.getChildren().addAll(statisticsLabel, backButton);

        return uiLayer;
    }


    private static void updatePlayerList() {
        playerListBox.getChildren().clear();

        List<Map<String, Object>> players = DatabaseHandler.getTopPlayers(0, 15);  // Top 15 players

        if (players.isEmpty()) {
            UILabel noPlayersLabel = new UILabel(Application.localeResourceBundle.getString("no_players"), 20);
            noPlayersLabel.setFill(Color.RED);
            playerListBox.getChildren().add(noPlayersLabel);
        } else {
            for (Map<String, Object> player : players) {
                String displayText = player.get("username") + " : " +
                        player.get("score") + " : " +
                        player.get("level");

                UILabel playerLabel = new UILabel(displayText, 20);
                playerLabel.setFill(Color.VIOLET);
                playerLabel.setTextAlignment(TextAlignment.LEFT);
                playerLabel.setTranslateX(210);
                playerListBox.getChildren().add(playerLabel);
            }
        }
    }
}
