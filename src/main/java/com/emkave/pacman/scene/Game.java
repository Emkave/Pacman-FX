package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.*;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.io.IOException;


public class Game {
    private static long lastEntityUpdateTime = 0;
    private static long score;
    private static UILabel scoreLabel;


    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        if (REGISTRY_KEYS.GET_ISCONTINUED()) {
            Game.score = REGISTRY_KEYS.GET_LAST_GAME_SCORE();
        }

        Game.scoreLabel = new UILabel(Application.localeResourceBundle.getString("score") + Game.score, 22);
        scoreLabel.setTranslateY(-310);
        scoreLabel.setTranslateX(-130);
        scoreLabel.setFill(Color.WHITE);

        UILabel levelLabel = new UILabel(Application.localeResourceBundle.getString("level") + REGISTRY_KEYS.GET_GAME_LEVEL(), 22);
        levelLabel.setTranslateY(-310);
        levelLabel.setTranslateX(110);
        levelLabel.setFill(Color.WHITE);

        MapHandler.loadGameMap();

        uiLayer.getChildren().addAll(
                MapHandler.getGameMapPane(), Game.scoreLabel, levelLabel
        );

        return uiLayer;
    }


    public static void startGameLoop() {
        Application.window.getScene().setOnKeyPressed(Game::handleKeyPress);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override public void handle(long now) {
                if (!REGISTRY_KEYS.GET_ISPAUSED()) {
                    if (REGISTRY_KEYS.GET_AMOUNT_GAME_DOTS() == 0) {
                        REGISTRY_KEYS.SET_GAME_LEVEL(REGISTRY_KEYS.GET_GAME_LEVEL()+1);
                        this.stop();
                        SceneHandler.loadLevelTransition();
                    } else {
                        if ((now - Game.lastEntityUpdateTime) >= 300000000) {
                            FruitHandler.placeFruit();
                            MapHandler.renderEntities();
                            Game.lastEntityUpdateTime = now;
                            Game.scoreLabel.setText(Application.localeResourceBundle.getString("score") + Game.score);
                        }
                    }
                }
            }
        };

        gameLoop.start();
    }


    private static void showPauseMenu() {
        REGISTRY_KEYS.SET_ISPAUSED(true);

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
            Game.hidePauseMenu();
            Game.resumeGame();
            REGISTRY_KEYS.SET_ISPAUSED(false);
        });
        resumeButton.setTranslateY(10);

        UITextBasedButton exitButton = new UITextBasedButton(Application.localeResourceBundle.getString("exit"));
        exitButton.setOnAction(e -> {
            Application.window.getScene().setOnKeyPressed(null);
            EntityHandler.stopAllThreads();
            try {
                JSONHandler.saveGameState(
                        Game.score,
                        REGISTRY_KEYS.GET_PACLIVES(),
                        REGISTRY_KEYS.GET_GAME_LEVEL(),
                        ConfigHandler.getUsername(),
                        EntityHandler.getCollectibleMap(),
                        EntityHandler.getMobs()
                );
            } catch (Exception e) {
                throw new RuntimeException("Game::showPauseMenu() -> " + e.getMessage());
            }
            EntityHandler.getMobs().clear();
            EntityHandler.getCollectibleMap().clear();
            REGISTRY_KEYS.SET_ISPAUSED(false);
            SceneHandler.exitScene();
        });
        exitButton.setTranslateY(20);

        UILabel warningLabel = new UILabel(Application.localeResourceBundle.getString("pause_exit_warning"), 10);
        warningLabel.setFill(Color.RED);
        warningLabel.setTranslateY(30);
        warningLabel.setTextAlignment(TextAlignment.CENTER);
        warningLabel.setLineSpacing(4.0);

        pauseMenu.getChildren().addAll(pauseLabel, resumeButton, exitButton, warningLabel);
        SceneHandler.getFrameStack().peek().getChildren().add(pauseMenu);
    }


    public static void hidePauseMenu() {
        SceneHandler.getFrameStack().peek().getChildren().removeIf(node -> node instanceof VBox);
    }


    private static void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();

        switch (code) {
            case W: case A:
            case S: case D:
                ((Pacman)EntityHandler.getMobs().get('!')).handleKeyPress(event);
                break;

            case ESCAPE:
                if (!REGISTRY_KEYS.GET_ISPAUSED()) {
                    Game.pauseGame();
                    Game.showPauseMenu();
                } else {
                    Game.hidePauseMenu();
                    Game.resumeGame();
                }
                break;

            case T:
                //Pacman.decreaseLives();
                break;

            case Y:
                //Pacman.increaseLives();
                break;

            default:
                break;
        }
    }


    public static void pauseGame() {
        REGISTRY_KEYS.SET_ISPAUSED(true);
    }


    public static void resumeGame() {
        REGISTRY_KEYS.SET_ISPAUSED(false);
    }


    public static void addScore(final long p) {
        Game.score += p;
    }
}
