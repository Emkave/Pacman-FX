package com.emkave.pacman;

import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.MainMenu;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public static StackPane frameLayerPane = new StackPane();
    public static StackPane uiLayerPane = new StackPane();
    public static Stage window;
    public static ResourceBundle resourceBundle;

    private double xOffset = 0;
    private double yOffset = 0;


    @Override public void start(Stage stage) throws IOException {
        Application.window = stage;
        Application.window.initStyle(StageStyle.TRANSPARENT);
        Application.window.setTitle("Pacman FX");
        Application.window.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/icon.png"))));

        Locale.setDefault(ConfigHandler.loadLanguageSettings());
        Application.resourceBundle = ResourceBundle.getBundle("com.emkave.pacman.Langs.messages", Locale.getDefault()); // Pick the words from langs messages to render

        SoundHandler.loadSoundEffect("click", "Audio/click.mp3");
        SoundHandler.loadSoundEffect("hover", "Audio/hover.wav");

        Image frameImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/appframe.png"))); // Load the image frame
        ImageView frameView = new ImageView(frameImage);
        frameView.setFitWidth(Application.SCREEN_WIDHT);
        frameView.setFitHeight(Application.SCREEN_HEIGHT);
        frameView.fitWidthProperty().bind(frameLayerPane.widthProperty());
        frameView.fitHeightProperty().bind(frameLayerPane.heightProperty());
        Application.frameLayerPane.getChildren().add(frameView); // Place frame of the application

        Application.frameLayerPane.getChildren().add(Application.uiLayerPane); // Place another stack layer on top for UI and other game elements

        Application.frameLayerPane.setOnMousePressed(event -> { // If you press left mouse button, get the coordinates of the press
            this.xOffset = event.getSceneX();
            this.yOffset = event.getSceneY();
        });

        Application.frameLayerPane.setOnMouseDragged(event -> { // When mouse if hold, drag the window
            Application.window.setX(event.getScreenX() - this.xOffset);
            Application.window.setY(event.getScreenY() - this.yOffset);
        });

        Scene frameLayerScene = new Scene(Application.frameLayerPane, Application.SCREEN_WIDHT, Application.SCREEN_HEIGHT); // Initialize the scene
        frameLayerScene.setFill(Color.TRANSPARENT); // Set transparent layer scene
        frameLayerScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm()); // Load styles to the scene
        Application.window.setScene(frameLayerScene);
        Application.window.setWidth(Application.SCREEN_WIDHT); // Set window size
        Application.window.setHeight(Application.SCREEN_HEIGHT);

        new MainMenu(); // Create Main Menu scene

        Application.window.show(); // Render the window
    }


    public static void main(String[] args) {
        launch();
    }
}