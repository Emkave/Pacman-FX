package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Orange extends Collectible {
    private static boolean collected = false;


    public Orange() {
        super('O');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");

        if (!collected) {
            Pacman.addCollected(this);
            this.setCollected();
        }

        Game.addScore(500);
    }


    @Override public boolean isCollected() {
        return collected;
    }


    @Override public void setCollected() {
        collected = true;
    }
}
