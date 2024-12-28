package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Grape extends Collectible {
    private static boolean collected = false;


    public Grape() {
        super('G');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");

        if (!collected) {
            Pacman.addCollected(this);
            this.setCollected();
        }

        Game.addScore(1000);
    }


    @Override public boolean isCollected() {
        return collected;
    }


    @Override public void setCollected() {
        collected = true;
    }
}
