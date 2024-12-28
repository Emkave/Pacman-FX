package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.Mob;
import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.scene.*;
import com.emkave.pacman.ui.UILabel;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;


public class SceneHandler {
    public static void loadMainMenu() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(MainMenu.load());
    }


    public static void loadSettings() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(Settings.load());
    }


    public static void loadStatistics() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(Statistics.load());
    }


    public static void loadGame() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(Game.load());

        SoundHandler.playSoundEffect("intro");

        PauseTransition intro = new PauseTransition(Duration.seconds(4.21));
        intro.setOnFinished(event -> {
            try {
                MapHandler.loadGameEntities();
                Game.startGameLoop();
                REGISTRY_KEYS.SET_ISPAUSED(false);
            } catch (Exception e) {
                throw new RuntimeException("SceneHandler::loadGame() -> " + e.getMessage());
            }
        });

        intro.play();
    }


    public static void loadUsernamePromptScene() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(UsernamePrompt.load());
    }


    public static void loadLevelTransition() throws InterruptedException {
        REGISTRY_KEYS.SET_ISPAUSED(true);
        Application.window.getScene().setOnKeyPressed(null);


        EntityHandler.stopAllThreads();
        for (Map.Entry<Integer, Collectible> entry : EntityHandler.getCollectibleMap().entrySet()) {
            MapHandler.getGameMapPane().getChildren().remove(entry.getValue().getImageView());
        }

        Pacman.getLives().clear();

        for (Mob mob : EntityHandler.getMobs().values()) {
            MapHandler.getGameMapPane().getChildren().remove(mob.getImageView());
        }
        EntityHandler.getMobs().clear();
        EntityHandler.getCollectibleMap().clear();

        PauseTransition preTransitionDelay = new PauseTransition(Duration.seconds(3));
        preTransitionDelay.setOnFinished(_ -> {
            MapHandler.getGameMapPane().getChildren().clear();
            StackPane transitionStage = new StackPane();
            transitionStage.setStyle("-fx-background-color: black;");
            transitionStage.setMaxWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
            transitionStage.setMaxHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());

            UILabel levelLabel = new UILabel(Application.localeResourceBundle.getString("level") + Game.getGameLevel(), 40);
            levelLabel.setFill(Color.WHITE);

            transitionStage.getChildren().add(levelLabel);

            Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
            Application.frameLayerPane.getChildren().add(transitionStage);

            PauseTransition postTransition = new PauseTransition(Duration.seconds(3));
            postTransition.setOnFinished(postEvent -> {
                try {
                    Game.setGameScore(Game.getLastGameScore());
                    SceneHandler.loadGame();
                } catch (Exception e) {
                    throw new RuntimeException("SceneHandler::loadLevelTransition() -> " + e.getMessage());
                }
            });

            postTransition.play();
        });

        preTransitionDelay.play();
    }


    public static void loadDeathScene(final int x, final int y) {
        REGISTRY_KEYS.SET_ISPAUSED(true);
        Application.window.getScene().setOnKeyPressed(null);
        Game.setGameDots(0);

        EntityHandler.stopAllThreads();
        for (Map.Entry<Integer, Collectible> entry : EntityHandler.getCollectibleMap().entrySet()) {
            MapHandler.getGameMapPane().getChildren().remove(entry.getValue().getImageView());
        }

        for (Mob mob : EntityHandler.getMobs().values()) {
            MapHandler.getGameMapPane().getChildren().remove(mob.getImageView());
        }

        EntityHandler.getMobs().clear();
        EntityHandler.getCollectibleMap().clear();

        ImageView deathAnimation = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pacdeath.gif"))));
        deathAnimation.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
        deathAnimation.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
        deathAnimation.setTranslateX(x * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
        deathAnimation.setTranslateY(y * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());

        MapHandler.getGameMapPane().getChildren().add(deathAnimation);

        PauseTransition deathAnimationDuration = new PauseTransition(Duration.seconds(3));
        deathAnimationDuration.setOnFinished(event -> {
            MapHandler.getGameMapPane().getChildren().remove(deathAnimation);
            Pacman.setLivesCount(Pacman.getLivesCount()-1);

            REGISTRY_KEYS.SET_ISPAUSED(false);

            if (Pacman.getLivesCount() == 0) {
                SceneHandler.loadEndOfGameScene();
            } else {
                try {
                    SceneHandler.loadLevelTransition();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SoundHandler.playSoundEffect("death");
        deathAnimationDuration.play();
    }


    public static void loadEndOfGameScene() {
        REGISTRY_KEYS.SET_ISPAUSED(true);
        Application.window.getScene().setOnKeyPressed(null);

        StackPane gameOverStage = new StackPane();
        gameOverStage.setStyle("-fx-background-color: black;");
        gameOverStage.setMaxWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
        gameOverStage.setMaxHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());

        UILabel gameOverLabel = new UILabel(Application.localeResourceBundle.getString("game_over"), 40);
        gameOverLabel.setFill(Color.WHITE);
        gameOverLabel.setTranslateY(-50);

        UILabel scoreLabel = new UILabel(Application.localeResourceBundle.getString("score") + Game.getGameScore(), 20);
        scoreLabel.setFill(Color.WHITE);
        scoreLabel.setTranslateY(30);

        UILabel levelLabel = new UILabel(Application.localeResourceBundle.getString("level") + Game.getGameLevel(), 20);
        levelLabel.setFill(Color.WHITE);
        levelLabel.setTranslateY(70);

        gameOverStage.getChildren().addAll(gameOverLabel, scoreLabel, levelLabel);

        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(gameOverStage);

        PauseTransition gameOverPause = new PauseTransition(Duration.seconds(5));
        gameOverPause.setOnFinished(event -> {

            try {
                SceneHandler.loadUsernamePromptScene();
            } catch (Exception e) {
                throw new RuntimeException("SceneHandler::loadEndOfGameScene() -> " + e.getMessage());
            }
        });

        gameOverPause.play();
    }
}
