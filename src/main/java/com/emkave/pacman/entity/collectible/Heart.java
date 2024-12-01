package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.SoundHandler;

public class Heart extends Collectible {
    public Heart() {
        super('H');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("extrapac");
    }
}
