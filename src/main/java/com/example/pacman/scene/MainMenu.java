package com.example.pacman.scene;

import com.example.pacman.Application;
import com.example.pacman.ui.MainMenuButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenu {
    public MainMenu(Stage stage) {
        Application.stage = stage;
        Application.root = new StackPane();
        Application.root.setAlignment(Pos.CENTER);
        this.initializeMenu();
    }


    private void initializeMenu() {
        MainMenuButton exitButton = new MainMenuButton("EXIT");
        exitButton.setOnAction(event -> {
            exitButton.getStyleClass().remove("main-menu-button");
            exitButton.getStyleClass().add("main-menu-exit-pressed");
            Application.stage.close();
        });
        exitButton.setTranslateY(260);

        MainMenuButton optionsButton = new MainMenuButton("OPTIONS");
        optionsButton.setOnAction(event -> {
            optionsButton.getStyleClass().add("main-menu-options-pressed");
        });
        optionsButton.setTranslateY(180);

        MainMenuButton playButton = new MainMenuButton("PLAY");
        playButton.setOnAction(event -> {
            playButton.getStyleClass().add("main-menu-play-pressed");
        });
        playButton.setTranslateY(100);



        Image blinkyGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/blinky_moves_right.gif")));
        ImageView blinkyGIFImageView = new ImageView(blinkyGIF);
        blinkyGIFImageView.setFitWidth(70);
        blinkyGIFImageView.setPreserveRatio(true);
        blinkyGIFImageView.setTranslateY(-210);
        blinkyGIFImageView.setTranslateX(-148);

        Image inkyGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/inky_moves_right.gif")));
        ImageView inkyGIFImageView = new ImageView(inkyGIF);
        inkyGIFImageView.setFitWidth(70);
        inkyGIFImageView.setPreserveRatio(true);
        inkyGIFImageView.setTranslateY(-210);
        inkyGIFImageView.setTranslateX(-50);

        Image pinkyGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pinky_moves_right.gif")));
        ImageView pinkyGIFImageView = new ImageView(pinkyGIF);
        pinkyGIFImageView.setFitWidth(70);
        pinkyGIFImageView.setPreserveRatio(true);
        pinkyGIFImageView.setTranslateY(-210);
        pinkyGIFImageView.setTranslateX(50);

        Image clydeGIF = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/clyde_moves_right.gif")));
        ImageView clydeGIFImageView = new ImageView(clydeGIF);
        clydeGIFImageView.setFitWidth(70);
        clydeGIFImageView.setPreserveRatio(true);
        clydeGIFImageView.setTranslateY(-210);
        clydeGIFImageView.setTranslateX(148);

        Image mainLabel = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Pacman-FX.png")));
        ImageView mainLabelView = new ImageView(mainLabel);
        mainLabelView.setFitWidth(450);
        mainLabelView.setPreserveRatio(true);
        mainLabelView.setTranslateY(-330);


        Application.root.getChildren().addAll(clydeGIFImageView, pinkyGIFImageView, inkyGIFImageView, blinkyGIFImageView, mainLabelView, exitButton, optionsButton
        , playButton);
    }


    public Scene createScene() {
        Scene scene = new Scene(Application.root, Application.SCREEN_WIDHT, Application.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(Application.class.getResource("black-theme.css")).toExternalForm());
        return scene;
    }
}
