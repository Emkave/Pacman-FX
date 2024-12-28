package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Cherry extends Collectible {
    private static boolean collected = false;


    public Cherry() {
        super('C');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");

        if (!collected) {
            Pacman.addCollected(this);
            this.setCollected();
        }

        Game.addScore(100);
    }


    @Override public boolean isCollected() {
        return collected;
    }


    @Override public void setCollected() {
        collected = true;
    }
}
