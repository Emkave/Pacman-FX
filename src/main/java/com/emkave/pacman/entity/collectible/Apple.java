package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Apple extends Collectible {
    public Apple() {
        super('A');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
        Game.addScore(700);
    }
}
