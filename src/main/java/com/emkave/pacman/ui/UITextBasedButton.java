package com.emkave.pacman.ui;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.SoundHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Objects;


public class UITextBasedButton extends Button {
    private ImageView pacmanImageView;
    private Text buttonText;

    public UITextBasedButton(String text) {
        this.getStyleClass().add("main-menu-button");
        this.setPrefSize(200, 50);

        initializePacmanImage();
        setButtonText(text);
        setupHoverEffect();
        setupClickEffect();
    }


    public void setButtonText(String text) {
        Font arcadeFont = Font.loadFont(Objects.requireNonNull(Application.class.getResourceAsStream("Fonts/arcade_font.ttf")), 30);
        this.buttonText = new Text(text);
        this.buttonText.setFont(arcadeFont);
        this.buttonText.setFill(Color.BLUE);
        this.buttonText.setStroke(null);
        this.buttonText.setTranslateX(-20);

        HBox hBox = new HBox(this.pacmanImageView, this.buttonText);
        hBox.setAlignment(Pos.CENTER);
        this.setGraphic(hBox);
    }


    private void initializePacmanImage() {
        Image pacManImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Characters/pacman_moves_right.gif")));
        this.pacmanImageView = new ImageView(pacManImage);
        this.pacmanImageView.setFitWidth(50);
        this.pacmanImageView.setPreserveRatio(true);
        this.pacmanImageView.setVisible(false);
        this.pacmanImageView.setTranslateX(-40);
    }


    private void setupHoverEffect() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.CYAN);
        dropShadow.setRadius(5);
        dropShadow.setSpread(0.5);

        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            this.buttonText.setStroke(Color.WHITE);
            this.buttonText.setStrokeWidth(1.3);
            this.pacmanImageView.setVisible(true);
            SoundHandler.playSoundEffect("hover");
        });

        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            this.buttonText.setEffect(null);
            this.buttonText.setStrokeWidth(0);
            this.pacmanImageView.setVisible(false);
        });
    }


    private void setupClickEffect() {
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            SoundHandler.playSoundEffect("click");
            this.buttonText.setFill(Color.DARKBLUE);
        });

        this.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            this.buttonText.setFill(Color.BLUE);
        });

        this.setOnAction(e -> {
            SoundHandler.playSoundEffect("click");
        });
    }
}
