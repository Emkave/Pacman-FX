package com.emkave.pacman.handler;

import java.util.Objects;

public class TileKey {
    public char tileType;
    public int x;
    public int y;

    public TileKey(char tileType, int x, int y) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TileKey tileKey = (TileKey)o;
        
        return tileType == tileKey.tileType && x == tileKey.x && y == tileKey.y;
    }

    @Override public int hashCode() {
        return Objects.hash(tileType, x, y);
    }
}
