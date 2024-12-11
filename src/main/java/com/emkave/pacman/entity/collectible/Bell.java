package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Bell extends Collectible {
    public Bell() {
        super('B');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
        Game.addScore(3000);
    }
}
