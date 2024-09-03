package com.example.pacman.ui;

import com.example.pacman.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class UITextBasedButton extends Button {
    private ImageView pacmanImageView;
    private Text buttonText;
    private AudioClip hoverSound;
    private AudioClip clickSound;

    public UITextBasedButton(String text) {
        this.getStyleClass().add("main-menu-button");
        this.setPrefSize(200, 50);

        initializePacmanImage();
        initializeButtonText(text);
        initializeSounds();
        setupHoverEffect();
        setupClickEffect();
    }


    private void initializeButtonText(String text) {
        Font arcadeFont = Font.loadFont(Objects.requireNonNull(Application.class.getResourceAsStream("Fonts/arcade_font.ttf")), 30);
        buttonText = new Text(text);
        buttonText.setFont(arcadeFont);
        buttonText.setFill(Color.BLUE);
        buttonText.setStroke(null);
        buttonText.setTranslateX(-20);

        HBox hBox = new HBox(pacmanImageView, buttonText);
        hBox.setAlignment(Pos.CENTER_LEFT);
        this.setGraphic(hBox);
    }


    private void initializePacmanImage() {
        Image pacManImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pacman_moves_right.gif")));
        pacmanImageView = new ImageView(pacManImage);
        pacmanImageView.setFitWidth(50);
        pacmanImageView.setPreserveRatio(true);
        pacmanImageView.setVisible(false);
        pacmanImageView.setTranslateX(-40);
    }


    private void initializeSounds() {
        hoverSound = new AudioClip(Objects.requireNonNull(Application.class.getResource("Audio/hover.wav")).toExternalForm());
        clickSound = new AudioClip(Objects.requireNonNull(Application.class.getResource("Audio/click.mp3")).toExternalForm());
    }


    private void setupHoverEffect() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.CYAN);
        dropShadow.setRadius(5);
        dropShadow.setSpread(0.5);

        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            buttonText.setStroke(Color.WHITE);
            buttonText.setStrokeWidth(1.3);
            pacmanImageView.setVisible(true);
            hoverSound.play();
        });

        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            buttonText.setEffect(null);
            buttonText.setStrokeWidth(0);
            pacmanImageView.setVisible(false);
        });
    }


    private void setupClickEffect() {
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            clickSound.play();
            buttonText.setFill(Color.DARKBLUE);
        });

        this.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            buttonText.setFill(Color.BLUE);
        });

        this.setOnAction(e -> {
            clickSound.play();
        });
    }
}
