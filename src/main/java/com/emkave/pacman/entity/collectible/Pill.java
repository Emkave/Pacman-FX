package com.emkave.pacman.entity.collectible;

import com.emkave.pacman.entity.mob.Mob;
import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.SoundHandler;


public class Pill extends Collectible {
    public Pill() {
        super('P');
    }


    @Override public void effect() {
        super.deleteCollectible();
        SoundHandler.playSoundEffect("interm");
        MapHandler.getGameMap()[this.y][this.x] = '0';

        for (Mob mob : EntityHandler.getMobs().values()) {
            if (!(mob instanceof Pacman)) {
                mob.setChasing(false);
            }
        }
    }
}
