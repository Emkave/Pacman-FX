package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Grape extends Collectible {
    public Grape() {
        super('G');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
        Game.addScore(1000);
    }
}
