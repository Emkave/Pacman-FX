package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.Mob;
import com.emkave.pacman.scene.*;
import com.emkave.pacman.ui.UILabel;
import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Map;
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


    public static void loadGame() throws IOException {
        REGISTRY_KEYS.SET_ISPAUSED(false);
        SceneHandler.frameStack.add(Game.load());
        SceneHandler.triggerChange();
        SoundHandler.playIntroMusic();
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


    public static void loadNewLevelTransition() {
        REGISTRY_KEYS.SET_ISPAUSED(true);
        Application.window.getScene().setOnKeyPressed(null);

        EntityHandler.stopAllThreads();
        for (Map.Entry<Integer, Collectible> entry : EntityHandler.getCollectibleMap().entrySet()) {
            MapHandler.getGameMapPane().getChildren().remove(entry.getValue().getImageView());
        }

        for (Mob mob : EntityHandler.getMobs()) {
            MapHandler.getGameMapPane().getChildren().remove(mob.getImageView());
        }
        EntityHandler.getMobs().clear();
        EntityHandler.getCollectibleMap().clear();

        SceneHandler.frameStack.pop();

        REGISTRY_KEYS.SET_GAME_LEVEL(REGISTRY_KEYS.GET_GAME_LEVEL()+1);

        PauseTransition preTransitionDelay = new PauseTransition(Duration.seconds(3));
        preTransitionDelay.setOnFinished(event -> {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            postTransition.play();
        });

        preTransitionDelay.play();
    }


    private static void triggerChange() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(SceneHandler.getFrameStack().peek());
    }
}
