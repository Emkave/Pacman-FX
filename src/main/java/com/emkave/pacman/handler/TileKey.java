package com.emkave.pacman.handler;

public class TileKey {
    public char tileType;
    public int x;
    public int y;

    public TileKey(char tileType, int x, int y) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
    }
}
