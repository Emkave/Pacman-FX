package com.emkave.pacman.ui;

import com.emkave.pacman.Application;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Objects;

public class UILabel extends Text {

    public UILabel(String text, double fontSize) {
        super(text);
        this.setFont(Font.font(REGISTRY_KEYS.GET_UI_FONT().getFamily(), fontSize));
        this.setFill(Color.RED);
    }
}
