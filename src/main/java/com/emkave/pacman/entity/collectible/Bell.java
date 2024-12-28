package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Bell extends Collectible {
    private static boolean collected = false;


    public Bell() {
        super('B');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");

        if (!collected) {
            Pacman.addCollected(this);
            this.setCollected();
        }

        Game.addScore(3000);
    }


    @Override public synchronized boolean isCollected() {
        return collected;
    }


    @Override public synchronized void setCollected() {
        collected = true;
    }
}
