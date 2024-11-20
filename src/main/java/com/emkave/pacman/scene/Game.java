package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Entity;
import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.LinkedList;


public class Game {
    private long score;
    private byte level;
    private final UILabel scoreLabel;


    public Game() throws IOException {
        Application.uiLayerPane.getChildren().clear();

        if (REGISTRY_KEYS.GET_ISCONTINUED()) {
            this.score = Long.parseLong(ConfigHandler.getScore());
        }

        this.scoreLabel = new UILabel(Application.localeResourceBundle.getString("score") + this.score, 22);
        scoreLabel.setTranslateY(-310);
        scoreLabel.setTranslateX(-130);
        scoreLabel.setFill(Color.WHITE);

        UILabel levelLabel = new UILabel(Application.localeResourceBundle.getString("level") + this.level, 22);
        levelLabel.setTranslateY(-310);
        levelLabel.setTranslateX(110);
        levelLabel.setFill(Color.WHITE);

        MapHandler.loadGameMap();
        MapHandler.loadEntities();

        Application.uiLayerPane.getChildren().addAll(
                MapHandler.getGameMapFramePane(), this.scoreLabel, levelLabel
        );

        Application.window.getScene().setOnKeyPressed(this::handleKeyPress);

        this.startGameLoop();
    }


    public void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override public void handle(long now) {
                if (!REGISTRY_KEYS.GET_ISPAUSED()) {
                    updateGame(now);
                }
            }
        };

        gameLoop.start();
    }


    private void showPauseMenu() {
        VBox pauseMenu = new VBox(10);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 255);");
        pauseMenu.setMaxWidth(REGISTRY_KEYS.GET_SCREEN_WIDTH()-120);
        pauseMenu.setMaxHeight(REGISTRY_KEYS.GET_SCREEN_HEIGHT()-120);

        UILabel pauseLabel = new UILabel(Application.localeResourceBundle.getString("pause"), 40);
        pauseLabel.setTranslateY(-210);
        pauseLabel.setFill(Color.WHITE);

        UITextBasedButton resumeButton = new UITextBasedButton(Application.localeResourceBundle.getString("continue"));
        resumeButton.setOnAction(e -> {
            hidePauseMenu();
            resumeGame();
        });
        resumeButton.setTranslateY(10);

        UITextBasedButton exitButton = new UITextBasedButton(Application.localeResourceBundle.getString("exit"));
        exitButton.setOnAction(e -> {
            Application.window.getScene().setOnKeyPressed(null);
            new MainMenu();
        });
        exitButton.setTranslateY(20);

        pauseMenu.getChildren().addAll(pauseLabel, resumeButton, exitButton);
        Application.uiLayerPane.getChildren().add(pauseMenu);
    }


    public void hidePauseMenu() {
        Application.uiLayerPane.getChildren().removeIf(node -> node instanceof VBox);
    }


    private void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();

        switch (code) {
            case W: case A:
            case S: case D:
                //this.player.handleKeyPress(event);
                break;

            case ESCAPE:
                if (!REGISTRY_KEYS.GET_ISPAUSED()) {
                    this.pauseGame();
                    this.showPauseMenu();
                } else {
                    this.hidePauseMenu();
                    this.resumeGame();
                }
                break;

            default:
                break;
        }
    }


    private void updateGame(long now) {
        MapHandler.renderEntities();
    }


    public void pauseGame() {
        REGISTRY_KEYS.SET_ISPAUSED(true);
    }


    public void resumeGame() {
        REGISTRY_KEYS.SET_ISPAUSED(false);
    }
}
