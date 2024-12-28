package com.emkave.pacman.handler;

import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.*;
import java.util.*;


public class EntityHandler {
    private static Map<Integer, Collectible> collectibleMap = new HashMap<>();
    private static Map<Character, Mob> mobs = new HashMap<>();


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
    }


    public static void stopAllThreads() {
        for (Mob mob : EntityHandler.mobs.values()) {
            if (!(mob instanceof Pacman)) {
                mob.stop();
                try {
                    mob.getThread().join();
                } catch (Exception e) {
                    throw new RuntimeException("EntityHandler::stopAllThreads() -> " + e.getMessage());
                }

            }
        }
    }


    public static synchronized Collectible getCollectible(int x, int y) {
        return EntityHandler.collectibleMap.values().stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().orElse(null);
    }


    public static synchronized Map<Character, Mob> getMobs() {
        return EntityHandler.mobs;
    }


    public static synchronized Map<Integer, Collectible> getCollectibleMap() {
        return EntityHandler.collectibleMap;
    }
}
