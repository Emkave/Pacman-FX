package com.emkave.pacman.scene;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Pacman;
import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class Game {
    private AnimationTimer gameLoop;
    private boolean isPaused = false;
    private long score = Long.parseLong(ConfigHandler.getScore());
    private Pacman player;


    public Game() throws IOException {
        Application.uiLayerPane.getChildren().clear();

        this.player = new Pacman(2, 1);

        ImageView mapView = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/UI/map.jpg"))));
        mapView.setFitWidth(470.4);
        mapView.setFitHeight(604.8);


        Application.uiLayerPane.getChildren().addAll(
                mapView, this.player.getPacmanImageView()
        );

        Application.window.getScene().setOnKeyPressed(this::handleKeyPress);

        startGameLoop();
    }


    public void startGameLoop() {
        this.gameLoop = new AnimationTimer() {

            @Override public void handle(long now) {
                if (!isPaused) {
                    updateGame(now);
                }
            }
        };

        this.gameLoop.start();
    }


    private void updateGame(long now) {
        this.player.updatePosition();
    }


    public void pauseGame() {
        this.isPaused = true;
    }


    public void resumeGame() {
        this.isPaused = false;
    }


    private void showPauseMenu() {
        VBox pauseMenu = new VBox(10);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 255);");
        pauseMenu.setMaxWidth(Application.SCREEN_WIDHT-130);
        pauseMenu.setMaxHeight(Application.SCREEN_HEIGHT-130);

        UILabel pauseLabel = new UILabel(Application.resourceBundle.getString("pause"), 40);
        pauseLabel.setTranslateY(-210);
        pauseLabel.setFill(Color.WHITE);

        UITextBasedButton resumeButton = new UITextBasedButton(Application.resourceBundle.getString("resume"));
        resumeButton.setOnAction(e -> {
            hidePauseMenu();
            resumeGame();
        });
        resumeButton.setTranslateY(10);

        UITextBasedButton exitButton = new UITextBasedButton(Application.resourceBundle.getString("exit"));
        exitButton.setOnAction(e -> {
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
            case W:
            case A:
            case S:
            case D:
                this.player.handleKeyPress(event);
                break;

            case ESCAPE:
                if (!isPaused) {
                    pauseGame();
                    showPauseMenu();
                } else {
                    hidePauseMenu();
                    resumeGame();
                }
                break;

            default:
                break;
        }
    }


    private void handleKeyRelease(KeyEvent event) {
        this.player.handleKeyRelease(event);
    }
}
