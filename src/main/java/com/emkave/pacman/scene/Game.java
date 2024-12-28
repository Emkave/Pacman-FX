package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.mob.Mob;
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


public class Game {
    private static int gameDots = 0;
    private static int gameLevel = 1;
    private static long gameScore = 0;
    private static long lastGameScore = 0;
    private static long lastEntityUpdateTime = 0;
    private static UILabel scoreLabel;



    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        Game.scoreLabel = new UILabel(Application.localeResourceBundle.getString("score") + Game.getGameScore(), 22);
        scoreLabel.setTranslateY(-310);
        scoreLabel.setTranslateX(-130);
        scoreLabel.setFill(Color.WHITE);

        UILabel levelLabel = new UILabel(Application.localeResourceBundle.getString("level") + Game.getGameLevel(), 22);
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
                    if (Game.gameDots == 0) {
                        REGISTRY_KEYS.SET_ISPAUSED(true);
                        Game.setGameLevel(Game.getGameLevel()+1);
                        Game.setLastGameScore(Game.getGameScore());
                        this.stop();
                        try {
                            SceneHandler.loadLevelTransition();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        if ((now - Game.lastEntityUpdateTime) >= 300000000) {
                            byte chasing_status = 0;
                            for (Mob mob : EntityHandler.getMobs().values()) {
                                if (!(mob instanceof Pacman)) {
                                    if (mob.getChasing()) {
                                        chasing_status |= 1;
                                        break;
                                    }
                                }
                            }

                            for (Mob mob : EntityHandler.getMobs().values()) {
                                if (!(mob instanceof Pacman)) {
                                    if (!mob.getChasing()) {
                                        chasing_status |= 2;
                                        break;
                                    }
                                }
                            }

                            switch (chasing_status) {
                                case 1:
                                    SoundHandler.playSoundEffect("siren");
                                    break;

                                case 2:
                                    SoundHandler.playSoundEffect("fright");
                                    break;

                                case 3:
                                    SoundHandler.playSoundEffect("siren");
                                    SoundHandler.playSoundEffect("fright");
                                    break;

                                default:
                                    break;
                            }

                            FruitHandler.placeFruit();
                            MapHandler.renderEntities();
                            Game.lastEntityUpdateTime = now;
                            Game.scoreLabel.setText(Application.localeResourceBundle.getString("score") + Game.gameScore);
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
            EntityHandler.getMobs().clear();
            EntityHandler.getCollectibleMap().clear();
            REGISTRY_KEYS.SET_ISPAUSED(false);
            Game.hidePauseMenu();
            SceneHandler.loadMainMenu();
        });
        exitButton.setTranslateY(20);

        UILabel warningLabel = new UILabel(Application.localeResourceBundle.getString("pause_exit_warning"), 10);
        warningLabel.setFill(Color.RED);
        warningLabel.setTranslateY(30);
        warningLabel.setTextAlignment(TextAlignment.CENTER);
        warningLabel.setLineSpacing(4.0);

        pauseMenu.getChildren().addAll(pauseLabel, resumeButton, exitButton, warningLabel);
        Application.frameLayerPane.getChildren().add(pauseMenu);
    }


    public static void hidePauseMenu() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof VBox);
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
        Game.gameScore += p;
    }


    public static void setGameLevel(final int level) {
        Game.gameLevel = level;
    }


    public static void setGameDots(final int dots) {
        Game.gameDots = dots;
    }


    public static void setGameScore(final long score) {
        Game.gameScore = score;
    }


    public static void setLastGameScore(final long score) {
        Game.lastGameScore = score;
    }


    public static int getGameLevel() {
        return Game.gameLevel;
    }


    public static int getGameDots() {
        return Game.gameDots;
    }


    public static long getGameScore() {
        return Game.gameScore;
    }


    public static long getLastGameScore() {
        return Game.lastGameScore;
    }
}
