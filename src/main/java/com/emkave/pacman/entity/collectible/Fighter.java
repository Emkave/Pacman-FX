package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.SoundHandler;
import com.emkave.pacman.scene.Game;

public class Fighter extends Collectible {
    private static boolean collected = false;


    public Fighter() {
        super('F');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatfruit");

        if (!collected) {
            ((Pacman) EntityHandler.getMobs().get('!')).addCollected(this);
            collected = true;
        }

        Game.addScore(2000);
    }


    @Override public boolean isCollected() {
        return collected;
    }


    @Override public void setCollected() {
        collected = true;
    }
}
