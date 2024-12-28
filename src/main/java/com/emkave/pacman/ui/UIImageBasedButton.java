package com.emkave.pacman.ui;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.SoundHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import java.util.Objects;


public class UIImageBasedButton extends Button {
    private ImageView buttonImage;

    public UIImageBasedButton(String imagePath) {
        this.getStyleClass().add("image-button");

        initializeButtonImage(imagePath);
        setupHoverEffect();
        setupClickEffect();
        setButtonSize(50, 50);
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


    private void setupHoverEffect() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setRadius(5);
        dropShadow.setSpread(0.5);

        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            buttonImage.setEffect(dropShadow);
            SoundHandler.playSoundEffect("hover");
        });

        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            buttonImage.setEffect(null);
        });
    }


    private void setupClickEffect() {
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            SoundHandler.playSoundEffect("click");
            this.setScaleX(0.9);
            this.setScaleY(0.9);
        });

        this.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            this.setScaleX(1.0);
            this.setScaleY(1.0);
        });

        this.setOnAction(e -> {
            SoundHandler.playSoundEffect("click");
        });
    }


    public void setButtonSize(double width, double height) {
        this.setPrefSize(width, height);
        buttonImage.setFitWidth(width - 10);
    }


    public void changeImage(String imagePath) {
        Image image = new Image(Objects.requireNonNull(Application.class.getResourceAsStream(imagePath)));
        this.buttonImage.setImage(image);
    }
}
