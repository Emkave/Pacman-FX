package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.TileKey;
import com.emkave.pacman.scene.Game;

public class Dot extends Collectible {
    public Dot() {
        super('D');
    }


    @Override public void effect() {
        TileKey tk = new TileKey('D', this.x, this.y);
        EntityHandler.getCollectibleMap().remove(tk.hashCode());
        MapHandler.getGameMapPane().getChildren().remove(this.getImageView());
        Game.addScore(1);
    }
}
