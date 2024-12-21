package com.emkave.pacman.entity.mob;


import com.emkave.pacman.handler.EntityHandler;
import com.emkave.pacman.handler.MapHandler;
import com.emkave.pacman.handler.REGISTRY_KEYS;

import java.util.*;

public class Blinky extends Mob {
    public Blinky() {
        super("blinky");
        this.x = 15;
        this.y = 14;
        this.mobSymbol = '%';
    }


    @Override public void autopilot() {
        int[] path = this.getPath(EntityHandler.getMobs().get('!').getX(), EntityHandler.getMobs().get('!').getY());

        if (path != null) {
            this.d_x = path[0];
            this.d_y = path[1];
            this.x += this.d_x;
            this.y += this.d_y;
        }

        this.moveToCell();
    }


    private int[] getPath(int targetX, int targetY) {
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};


        boolean[][] visited = new boolean[REGISTRY_KEYS.GET_MAP_HEIGHT()][REGISTRY_KEYS.GET_MAP_WIDTH()];
        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> track = new HashMap<>();

        queue.add(new int[]{this.y, this.x});
        visited[this.y][this.x] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currY = current[0];
            int currX = current[1];


            if (currX == targetX && currY == targetY) {
                int[] backtrack = current;

                while (track.containsKey(this.coordKey(backtrack)) && !Arrays.equals(track.get(this.coordKey(backtrack)), new int[]{this.y, this.x})) {
                    backtrack = track.get(this.coordKey(backtrack));
                }

                int dirX = backtrack[1] - this.x;
                int dirY = backtrack[0] - this.y;

                return new int[]{dirX, dirY};
            }


            for (int[] direction : directions) {
                int newY = (currY + direction[0] + REGISTRY_KEYS.GET_MAP_HEIGHT()) % REGISTRY_KEYS.GET_MAP_HEIGHT();
                int newX = (currX + direction[1] + REGISTRY_KEYS.GET_MAP_WIDTH()) % REGISTRY_KEYS.GET_MAP_WIDTH();

                Mob.unique_lock.lock();
                try {
                    var cell = MapHandler.getGameMap()[newY][newX];

                    if (!visited[newY][newX] && cell != '1' && cell != '2' && cell != '3' && cell != '4' && cell != '7' && cell != '8') {
                        visited[newY][newX] = true;
                        int[] next = new int[]{newY, newX};
                        queue.add(next);
                        track.put(this.coordKey(next), current);
                    }
                } finally {
                    Mob.unique_lock.unlock();
                }
            }
        }

        return null;
    }


    private String coordKey(int[] coord) {
        return coord[0] + "x" + coord[1];
    }
}
