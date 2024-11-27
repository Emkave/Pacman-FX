package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
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
        SceneHandler.frameStack.add(Game.load());
        SceneHandler.triggerChange();
        Game.startGameLoop();
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


    private static void triggerChange() {
        Application.frameLayerPane.getChildren().removeIf(node -> node instanceof StackPane);
        Application.frameLayerPane.getChildren().add(SceneHandler.getFrameStack().peek());
    }
}
