package com.emkave.pacman.handler;

import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.Mob;
import com.emkave.pacman.entity.mob.Pacman;

import java.util.Collections;
import java.util.LinkedList;


public class EntityHandler {
    private static LinkedList<Collectible> collectibles = new LinkedList<>();
    private static LinkedList<Mob> mobs = new LinkedList<>();


    public static void loadEntities() {
        Mob pacman = new Pacman();


        //Mob inky = new Inky();
        //Mob pinky = new Pinky();
        //Mob clyde = new Clyde();
        //Mob blinky = new Blinky();

        Collections.addAll(EntityHandler.mobs, pacman/*, inky, pinky, clyde, */);
    }


    public static LinkedList<Collectible> getCollectibles() {
        return EntityHandler.collectibles;
    }


    public static LinkedList<Mob> getMobs() {
        return EntityHandler.mobs;
    }
}
