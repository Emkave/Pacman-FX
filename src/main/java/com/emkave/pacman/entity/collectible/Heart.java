package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;
import com.emkave.pacman.handler.SoundHandler;

public class Heart extends Collectible {
    public Heart() {
        super('H');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("extrapac");
        REGISTRY_KEYS.SET_PACLIVES(REGISTRY_KEYS.GET_PACLIVES() + 1);
        ((Pacman)EntityHandler.getMobs().get('!')).increaseLives();
    }
}
