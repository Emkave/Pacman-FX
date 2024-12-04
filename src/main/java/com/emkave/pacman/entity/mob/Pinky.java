package com.emkave.pacman.entity.mob;


import com.emkave.pacman.handler.MapHandler;

public class Pinky extends Mob {
    public Pinky() {
        super("pinky");
        this.x = 14;
        this.y = 14;
        this.mobSymbol = '@';
    }


    @Override protected void autopilot() {
        synchronized (MapHandler.getGameMap()) {

        }
    }
}
