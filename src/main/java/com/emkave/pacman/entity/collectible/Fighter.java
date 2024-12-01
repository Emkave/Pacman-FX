package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;

public class Fighter extends Collectible {
    public Fighter() {
        super('F');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
    }
}
