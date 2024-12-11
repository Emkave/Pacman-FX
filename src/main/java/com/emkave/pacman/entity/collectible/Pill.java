package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.handler.TileKey;

public class Pill extends Collectible {
    public Pill() {
        super('P');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("interm");
        MapHandler.getGameMap()[this.y][this.x] = '0';
    }
}
