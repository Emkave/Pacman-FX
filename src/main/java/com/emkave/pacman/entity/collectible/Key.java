package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;

public class Key extends Collectible {
    public Key() {
        super('K');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
    }
}
