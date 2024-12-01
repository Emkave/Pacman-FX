package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;

public class Bell extends Collectible {
    Bell() {
        super('B');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
    }
}
