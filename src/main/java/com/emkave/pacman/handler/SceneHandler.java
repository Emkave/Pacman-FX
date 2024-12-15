package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.Mob;
import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.scene.*;
import com.emkave.pacman.ui.UILabel;
import javafx.animation.PauseTransition;
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
    private static Stack<Pane> frameStack = new Stack<>();


    public static void loadMainMenu() {
        SceneHandler.frameStack.add(MainMenu.load());
        SceneHandler.triggerChange();
    }


    public static void loadSettings() {
        SceneHandler.frameStack.add(Settings.load());
        SceneHandler.triggerChange();
    }


    public static void loadStatistics() {
        SceneHandler.frameStack.add(Statistics.load());
        SceneHandler.triggerChange();
    }


    public static void loadGame() {
        SceneHandler.frameStack.add(Game.load());
        SceneHandler.triggerChange();

        SoundHandler.playIntroMusic(() -> {
            try {
                MapHandler.loadGameEntities();
                Game.startGameLoop();
                REGISTRY_KEYS.SET_ISPAUSED(false);
            } catch (Exception e) {
                throw new RuntimeException("SceneHandler::loadGame() -> " + e.getMessage());
            }
        });
    }


    public static void loadGameRegimes() {
        SceneHandler.frameStack.add(PlayRegime.load());
        SceneHandler.triggerChange();
    }


    public static void exitScene() {
        SceneHandler.frameStack.pop();
        SceneHandler.triggerChange();
    }


    public static Stack<Pane> getFrameStack() {
        return SceneHandler.frameStack;
    }


    public static void loadLevelTransition() {
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

        SceneHandler.frameStack.pop();

        PauseTransition preTransitionDelay = new PauseTransition(Duration.seconds(3));
        preTransitionDelay.setOnFinished(_ -> {
            MapHandler.getGameMapPane().getChildren().clear();
            StackPane transitionStage = new StackPane();
            transitionStage.setStyle("-fx-background-color: black;");
            transitionStage.setMaxWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
            transitionStage.setMaxHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());

            UILabel levelLabel = new UILabel(Application.localeResourceBundle.getString("level") + REGISTRY_KEYS.GET_GAME_LEVEL(), 40);
            levelLabel.setFill(Color.WHITE);

            transitionStage.getChildren().add(levelLabel);

            SceneHandler.frameStack.add(transitionStage);
            SceneHandler.triggerChange();

            PauseTransition postTransition = new PauseTransition(Duration.seconds(3));
            postTransition.setOnFinished(postEvent -> {
                SceneHandler.frameStack.pop();

                try {
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
        REGISTRY_KEYS.SET_AMOUNT_GAME_DOTS(0);

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
            REGISTRY_KEYS.SET_PACLIVES(REGISTRY_KEYS.GET_PACLIVES()-1);
            REGISTRY_KEYS.SET_ISPAUSED(false);

            if (REGISTRY_KEYS.GET_PACLIVES() == 0) {
                REGISTRY_KEYS.SET_LAST_GAME_LEVEL(1);
                SceneHandler.loadEndOfGameScene();
            } else {
                SceneHandler.loadLevelTransition();
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

        UILabel scoreLabel = new UILabel(Application.localeResourceBundle.getString("score") + REGISTRY_KEYS.GET_LAST_GAME_SCORE(), 20);
        scoreLabel.setFill(Color.WHITE);
        scoreLabel.setTranslateY(30);

        UILabel levelLabel = new UILabel(Application.localeResourceBundle.getString("level") + REGISTRY_KEYS.GET_LAST_GAME_LEVEL(), 20);
        levelLabel.setFill(Color.WHITE);
        levelLabel.setTranslateY(70);

        gameOverStage.getChildren().addAll(gameOverLabel, scoreLabel, levelLabel);
        SceneHandler.frameStack.add(gameOverStage);
        SceneHandler.triggerChange();

        DatabaseHandler.saveFinalResult(REGISTRY_KEYS.GET_LAST_GAME_LEVEL(), REGISTRY_KEYS.GET_LAST_GAME_SCORE());

        PauseTransition gameOverPause = new PauseTransition(Duration.seconds(5));
        gameOverPause.setOnFinished(event -> {
            SceneHandler.frameStack.clear();
            try {
                SceneHandler.loadMainMenu();
            } catch (Exception e) {
                throw new RuntimeException("SceneHandler::loadEndOfGameScene() -> " + e.getMessage());
            }
        });

        gameOverPause.play();
    }


    private static void triggerChange() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(SceneHandler.getFrameStack().peek());
    }
}
