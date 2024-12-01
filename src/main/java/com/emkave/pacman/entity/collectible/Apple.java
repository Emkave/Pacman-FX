package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;

public class Apple extends Collectible {
    Apple() {
        super('A');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");
    }
}
