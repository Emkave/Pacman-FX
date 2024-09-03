package com.example.pacman;

import com.example.pacman.scene.MainMenu;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application {
    public static final double SCREEN_WIDHT = 672.0;
    public static final double SCREEN_HEIGHT = 864.0;
    public static StackPane root = new StackPane();
    public static StackPane uiLayer = new StackPane();
    public static Stage stage;
    public static ResourceBundle resourceBundle;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("ru", "RU"));
        Application.resourceBundle = ResourceBundle.getBundle("com.example.pacman.Langs.messages", Locale.getDefault());

        Image frameImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/appframe.png")));
        ImageView frameView = new ImageView(frameImage);
        frameView.setFitWidth(Application.SCREEN_WIDHT);
        frameView.setFitHeight(Application.SCREEN_HEIGHT);
        frameView.fitWidthProperty().bind(root.widthProperty());
        frameView.fitHeightProperty().bind(root.heightProperty());

        Application.root.getChildren().add(frameView);
        Application.root.getChildren().add(Application.uiLayer);

        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.initializeMenu();

        Scene scene = new Scene(Application.root, Application.SCREEN_WIDHT, Application.SCREEN_HEIGHT);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Pacman FX");

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/icon.png")));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.setWidth(Application.SCREEN_WIDHT);
        stage.setHeight(Application.SCREEN_HEIGHT);

        Application.root.setOnMousePressed(event -> {
            this.xOffset = event.getSceneX();
            this.yOffset = event.getSceneY();
        });

        Application.root.setOnMouseDragged(event -> {
            Application.stage.setX(event.getScreenX() - this.xOffset);
            Application.stage.setY(event.getScreenY() - this.yOffset);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}