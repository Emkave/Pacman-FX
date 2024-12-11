package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Fighter extends Collectible {
    public Fighter() {
        super('F');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
        Game.addScore(2000);
    }
}
