package com.emkave.pacman.entity.mob;


import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;

import java.util.concurrent.locks.ReentrantLock;


public class Pinky extends Mob {
    public Pinky() {
        super("pinky");
        this.x = 14;
        this.y = 14;
        this.mobSymbol = '@';
    }


    @Override protected void autopilot() {
        int[] step;

        if (this.chasing) {
            Pacman pacman = (Pacman) EntityHandler.getMobs().get('!');
            int[] direction = pacman.getDirection();
            int[] predictPos = {pacman.getX() + direction[0] * 5, pacman.getY() + direction[1] * 5};

            predictPos[0] = (predictPos[0] + REGISTRY_KEYS.GET_MAP_WIDTH()) % REGISTRY_KEYS.GET_MAP_WIDTH();
            predictPos[1] = (predictPos[1] + REGISTRY_KEYS.GET_MAP_HEIGHT()) % REGISTRY_KEYS.GET_MAP_HEIGHT();

            boolean validPrediction = false;

            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            try {
                char predictedCell = MapHandler.getGameMap()[predictPos[1]][predictPos[0]];
                if (predictedCell != '1' && predictedCell != '2' && predictedCell != '3' && predictedCell != '4' &&
                        predictedCell != '7' && predictedCell != '8') {
                    validPrediction = true;
                }
            } finally {
                lock.unlock();
            }

            if (validPrediction) {
                step = this.pathFind(predictPos[0], predictPos[1]);
            } else {
                step = this.pathFind(pacman.getX(), pacman.getY());
            }
        } else {
            step = this.pathFind(14, 14);
        }

        if (step != null) {
            this.d_x = step[0];
            this.d_y = step[1];
            this.x += this.d_x;
            this.y += this.d_y;
        }

        this.moveToCell();
    }
}
