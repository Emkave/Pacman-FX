package com.emkave.pacman.ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;


public class UILineSeparator extends Line {
    public UILineSeparator(double startX, double startY, double endX, double endY, Color color, double width) {
        super(startX, startY, endX, endY); // Call the superclass constructor
        setStroke(color);
        setStrokeWidth(width);
        applyEffects();
    }


    private void applyEffects() {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(1);
        shadow.setOffsetX(1);
        shadow.setColor(Color.DARKBLUE);
        shadow.setRadius(0);
        shadow.setSpread(1);
        this.setEffect(shadow);
    }
}
