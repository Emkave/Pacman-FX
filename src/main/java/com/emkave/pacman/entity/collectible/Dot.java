package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.handler.TileKey;
import com.emkave.pacman.scene.Game;

public class Dot extends Collectible {
    public Dot() {
        super('D');
    }


    @Override public void effect() {
        super.deleteCollectible();
        Game.addScore(1);
        SoundHandler.playSoundEffect("eatdot");
    }
}
