package com.emkave.pacman.ui;

import com.emkave.pacman.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UILabel extends Text {

    public UILabel(String text, double fontSize) {
        super(text);
        Font customFont = Font.loadFont(Application.class.getResourceAsStream("Fonts/arcade_font.ttf"), fontSize);
        this.setFont(customFont);
        this.setFill(Color.RED);
    }
}
