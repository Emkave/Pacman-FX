package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;

public class Cherry extends Collectible {
    Cherry() {
        super('C');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
    }
}
