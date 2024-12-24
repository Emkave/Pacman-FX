package com.emkave.pacman.handler;

import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.*;

import java.util.*;


public class EntityHandler {
    private static Map<Integer, Collectible> collectibleMap = new HashMap<>();
    private static Map<Character, Mob> mobs = new HashMap<>();
    private static List<Thread> mobThreads = new ArrayList<>();


    public static void loadMobs() {
        Mob pacman = new Pacman();
        Mob inky = new Inky();
        Mob pinky = new Pinky();
        Mob clyde = new Clyde();
        Mob blinky = new Blinky();

        EntityHandler.mobs.put('!', pacman);
        EntityHandler.mobs.put('%', blinky);
        EntityHandler.mobs.put('$', clyde);
        EntityHandler.mobs.put('#', inky);
        EntityHandler.mobs.put('@', pinky);

        for (Mob mob : EntityHandler.mobs.values()) {
            if (!(mob instanceof Pacman)) {
                EntityHandler.mobThreads.add(mob.getThread());
            }
        }
    }


    public static void stopAllThreads() {
        for (Mob mob : EntityHandler.mobs.values()) {
            if (!(mob instanceof Pacman)) {
                mob.stop();
            }
        }

        for (Thread thread : EntityHandler.mobThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public static synchronized Collectible getCollectible(int x, int y) {
        return EntityHandler.collectibleMap.values().stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().orElse(null);
    }


    public static synchronized void removeCollectible(Collectible collectible) {
        MapHandler.getGameMapPane().getChildren().remove(collectible.getImageView());
        EntityHandler.collectibleMap.remove(collectible.hashCode());
    }


    public static synchronized Map<Character, Mob> getMobs() {
        return EntityHandler.mobs;
    }


    public static synchronized Map<Integer, Collectible> getCollectibleMap() {
        return EntityHandler.collectibleMap;
    }


    public static synchronized List<Thread> getMobThreads() {
        return EntityHandler.mobThreads;
    }
}
