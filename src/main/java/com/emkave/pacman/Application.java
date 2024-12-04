package com.emkave.pacman;

import com.almasb.fxgl.audio.Sound;
import com.emkave.pacman.handler.ConfigHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import com.emkave.pacman.handler.SceneHandler;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.MainMenu;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
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
import java.util.Stack;


public class Application extends javafx.application.Application {
    public static StackPane frameLayerPane = new StackPane();
    public static Stage window;
    public static ResourceBundle localeResourceBundle;

    private double xOffset = 0;
    private double yOffset = 0;


    @Override public void start(Stage stage) throws IOException {
        Application.window = stage;
        Application.window.initStyle(StageStyle.TRANSPARENT);
        Application.window.setTitle("Pacman FX");
        Application.window.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/icon.png"))));

        Locale.setDefault(ConfigHandler.loadLanguageSettings());
        Application.localeResourceBundle = ResourceBundle.getBundle("com.emkave.pacman.Langs.messages", Locale.getDefault()); // Pick the words from langs messages to render

        SoundHandler.loadSounds();
        REGISTRY_KEYS.SET_UI_FONT();

        ImageView frameView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/appframe.png")))); // Load the image frame
        frameView.setFitWidth(REGISTRY_KEYS.GET_SCREEN_WIDTH());
        frameView.setFitHeight(REGISTRY_KEYS.GET_SCREEN_HEIGHT());
        frameView.fitWidthProperty().bind(frameLayerPane.widthProperty());
        frameView.fitHeightProperty().bind(frameLayerPane.heightProperty());

        Application.frameLayerPane.getChildren().addAll(frameView); // Place frame of the application

        Application.frameLayerPane.setOnMousePressed(event -> { // If you press left mouse button, get the coordinates of the press
            this.xOffset = event.getSceneX();
            this.yOffset = event.getSceneY();
        });

        Application.frameLayerPane.setOnMouseDragged(event -> { // When mouse if hold, drag the window
            Application.window.setX(event.getScreenX() - this.xOffset);
            Application.window.setY(event.getScreenY() - this.yOffset);
        });

        Scene frameLayerScene = new Scene(Application.frameLayerPane, REGISTRY_KEYS.GET_SCREEN_WIDTH(), REGISTRY_KEYS.GET_SCREEN_HEIGHT()); // Initialize the scene
        frameLayerScene.setFill(Color.TRANSPARENT); // Set transparent layer scene
        frameLayerScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm()); // Load styles to the scene

        Application.window.setScene(frameLayerScene);
        Application.window.setWidth(REGISTRY_KEYS.GET_SCREEN_WIDTH()); // Set window size
        Application.window.setHeight(REGISTRY_KEYS.GET_SCREEN_HEIGHT());

        SceneHandler.loadMainMenu();
        Application.window.show(); // Render the window
    }


    public static void main(String[] args) {
        launch();
    }
}