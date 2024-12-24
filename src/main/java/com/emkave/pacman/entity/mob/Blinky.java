package com.emkave.pacman.entity.mob;


import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Blinky extends Mob {
    public Blinky() {
        super("blinky");
        this.x = 15;
        this.y = 14;
        this.mobSymbol = '%';
    }


    @Override public void autopilot() {
        int[] path = this.pathFind(EntityHandler.getMobs().get('!').getX(), EntityHandler.getMobs().get('!').getY());

        if (path != null) {
            this.d_x = path[0];
            this.d_y = path[1];
            this.x += this.d_x;
            this.y += this.d_y;
        }

        this.moveToCell();
    }
}
