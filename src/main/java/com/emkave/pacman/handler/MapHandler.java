package com.emkave.pacman.handler;

import com.emkave.pacman.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class MapHandler {
    public static int[][] loadMap() throws IOException {
        int[][] map = new int[22][19];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Application.class.getResourceAsStream("Map/map.txt"))))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    map[row][col] = line.charAt(col) == '1' ? 1 : 0;
                }

                row++;
            }
        }

        return map;
    }
}
