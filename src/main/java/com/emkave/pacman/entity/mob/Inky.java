package com.emkave.pacman.entity.mob;


import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


public class Inky extends Mob {
    int[] destination = new int[2];


    public Inky() {
        super("inky");
        this.x = 11;
        this.y = 14;
        this.destination[0] = 11;
        this.destination[1] = 11;
        this.mobSymbol = '#';
    }


    @Override public void autopilot() {
        int[] step;

        if (!this.respawning && this.chasing) {
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int[] pacmanPos = {EntityHandler.getMobs().get('!').getX(), EntityHandler.getMobs().get('!').getY()};
            boolean found = false;

            out:
            for (int[] direction : directions) {
                int[] currCell = new int[2];
                currCell[0] = this.x;
                currCell[1] = this.y;

                while (true) {
                    currCell[0] = (currCell[0] + direction[0] + REGISTRY_KEYS.GET_MAP_WIDTH()) % REGISTRY_KEYS.GET_MAP_WIDTH();
                    currCell[1] = (currCell[1] + direction[1] + REGISTRY_KEYS.GET_MAP_HEIGHT()) % REGISTRY_KEYS.GET_MAP_HEIGHT();

                    if (pacmanPos[0] == currCell[0] && pacmanPos[1] == currCell[1]) {
                        this.destination[0] = pacmanPos[0];
                        this.destination[1] = pacmanPos[1];
                        found = true;
                        break out;
                    }

                    {
                        ReentrantLock unique_lock = new ReentrantLock();
                        unique_lock.lock();
                        try {
                            var cell = MapHandler.getGameMap()[currCell[1]][currCell[0]];

                            if (cell == '1' || cell == '2' || cell == '3' || cell == '4' || cell == '7' || cell == '8') {
                                break;
                            }
                        } finally {
                            unique_lock.unlock();
                        }
                    }
                }
            }

            if (this.destination[0] == this.x && this.destination[1] == this.y) {
                if (!found) {
                    out:
                    for (int y = 0; y < REGISTRY_KEYS.GET_MAP_HEIGHT(); y++) {
                        for (int x = 0; x < REGISTRY_KEYS.GET_MAP_WIDTH(); x++) {
                            ReentrantLock unique_lock = new ReentrantLock();
                            unique_lock.lock();
                            try {
                                var cell = MapHandler.getGameMap()[y][x];

                                if (!(cell == '1' || cell == '2' || cell == '3' || cell == '4' || cell == '7' || cell == '8') && new Random().nextInt(10) == 2) {
                                    this.destination[0] = x;
                                    this.destination[1] = y;
                                    break out;
                                }
                            } finally {
                                unique_lock.unlock();
                            }
                        }
                    }
                }
            }
        } else {
            this.destination[0] = 14;
            this.destination[1] = 14;
        }

        step = this.pathFind(this.destination[0], this.destination[1]);

        if (step != null) {
            this.d_x = step[0];
            this.d_y = step[1];
            this.x += this.d_x;
            this.y += this.d_y;
        }

        this.moveToCell();
    }
}
