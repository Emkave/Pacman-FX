package com.example.pacman.ui;

import com.example.pacman.Application;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UILabel extends Label {
    private double fontSize;

    public UILabel(String text, double fontSize) {
        super(text);
        this.fontSize = fontSize;
        initializeLabel();
    }

    private void initializeLabel() {
        Font customFont = Font.loadFont(Application.class.getResourceAsStream("Fonts/arcade_font.ttf"), this.fontSize);
        this.setFont(customFont);
    }
}
