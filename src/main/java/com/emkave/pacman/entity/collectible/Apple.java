package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Apple extends Collectible {
    private static boolean collected = false;


    public Apple() {
        super('A');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");

        if (!Apple.collected) {
            Pacman.addCollected(this);
            this.setCollected();
        }

        Game.addScore(700);
    }


    @Override public boolean isCollected() {
        return Apple.collected;
    }


    @Override public void setCollected() {
        Apple.collected = true;
    }
}
