package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Entity;
import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.TileKey;
import javafx.scene.image.Image;

import java.util.Objects;

public class Collectible extends Entity { // Every collectible is an entity
    protected char colType;


    Collectible(final char colName) {
        this.imageView.setImage(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/Tiles/"+colName+".png"))));
        this.colType = colName;
    }


    protected void deleteCollectible() {
        TileKey tk = new TileKey(this.x, this.y);
        EntityHandler.getCollectibleMap().remove(tk.hashCode());
        MapHandler.getGameMapPane().getChildren().remove(this.getImageView());
        MapHandler.getGameMap()[this.y][this.x] = '0';
    }


    public void effect() {
        throw new UnsupportedOperationException("Collectible::effect() -> Subclasses must implement effect().");
    }


    @Override public void render() {

    }


    public synchronized boolean isCollected() {
        throw new UnsupportedOperationException("Collectible::isCollected() -> Subclasses must implement isCollected().");
    }


    public synchronized void setCollected() {
        throw new UnsupportedOperationException("Collectible::setCollected() -> Subclasses must implement setCollected().");
    }
}
