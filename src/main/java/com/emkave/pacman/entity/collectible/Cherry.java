package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Cherry extends Collectible {
    public Cherry() {
        super('C');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
        Game.addScore(100);
    }
}
