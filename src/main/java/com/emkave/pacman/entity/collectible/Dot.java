package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.handler.*;
import com.emkave.pacman.scene.Game;

public class Dot extends Collectible {
    public Dot() {
        super('D');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("eatdot");
        Game.addScore(1);
        Game.setGameDots(Game.getGameDots() - 1);
    }
}
