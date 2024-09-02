package com.example.pacman;

import com.example.pacman.scene.MainMenu;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    public static final double SCREEN_WIDHT = 672.0;
    public static final double SCREEN_HEIGHT = 864.0;
    public static StackPane root;
    public static Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        MainMenu mainMenu = new MainMenu(stage);

        Scene scene = mainMenu.createScene();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Pacman FX");

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/icon.png")));
        stage.getIcons().add(icon);

        stage.setScene(scene);

        root.setOnMousePressed(event -> {
            this.xOffset = event.getSceneX();
            this.yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            Application.stage.setX(event.getScreenX() - this.xOffset);
            Application.stage.setY(event.getScreenY() - this.yOffset);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}