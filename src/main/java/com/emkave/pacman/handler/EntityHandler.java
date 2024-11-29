package com.emkave.pacman.handler;

import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.Mob;
import com.emkave.pacman.entity.mob.Pacman;

import java.util.*;


public class EntityHandler {
    private static Map<Integer, Collectible> collectibleMap = new HashMap<>();
    private static LinkedList<Mob> mobs = new LinkedList<>();


    public static void loadMobs() {
        Mob pacman = new Pacman();


        //Mob inky = new Inky();
        //Mob pinky = new Pinky();
        //Mob clyde = new Clyde();
        //Mob blinky = new Blinky();

        Collections.addAll(EntityHandler.mobs, pacman/*, inky, pinky, clyde, */);
    }


    public static LinkedList<Mob> getMobs() {
        return EntityHandler.mobs;
    }


    public static Map<Integer, Collectible> getCollectibleMap() {
        return EntityHandler.collectibleMap;
    }
}
