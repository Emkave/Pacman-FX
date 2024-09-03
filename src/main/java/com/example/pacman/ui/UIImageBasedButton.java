package com.example.pacman.ui;

import com.example.pacman.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Objects;

public class UIImageBasedButton extends Button {
    private ImageView buttonImage;
    private AudioClip hoverSound;
    private AudioClip clickSound;

    public UIImageBasedButton(String imagePath) {
        this.getStyleClass().add("image-button");
        this.setPrefSize(50, 50);

        initializeButtonImage(imagePath);
        initializeSounds();
        setupHoverEffect();
        setupClickEffect();
    }


    private void initializeButtonImage(String imagePath) {
        Image image = new Image(Objects.requireNonNull(Application.class.getResourceAsStream(imagePath)));
        buttonImage = new ImageView(image);
        buttonImage.setFitWidth(40);
        buttonImage.setPreserveRatio(true);


        StackPane stackPane = new StackPane(buttonImage);
        stackPane.setAlignment(Pos.CENTER);
        this.setGraphic(stackPane);
    }

    private void initializeSounds() {
        hoverSound = new AudioClip(Objects.requireNonNull(Application.class.getResource("Audio/hover.wav")).toExternalForm());
        clickSound = new AudioClip(Objects.requireNonNull(Application.class.getResource("Audio/click.mp3")).toExternalForm());
    }

    private void setupHoverEffect() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setRadius(5);
        dropShadow.setSpread(0.5);

        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            buttonImage.setEffect(dropShadow);
            hoverSound.play();
        });

        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            buttonImage.setEffect(null);
        });
    }

    private void setupClickEffect() {
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            clickSound.play();
            this.setScaleX(0.9);
            this.setScaleY(0.9);
        });

        this.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            this.setScaleX(1.0);
            this.setScaleY(1.0);
        });

        this.setOnAction(e -> {
            clickSound.play();
        });
    }
}
