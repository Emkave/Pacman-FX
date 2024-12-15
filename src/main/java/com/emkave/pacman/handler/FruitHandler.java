package com.emkave.pacman.handler;
import com.emkave.pacman.entity.collectible.Collectible;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FruitHandler {
    private static final Map<String, Integer> fruitProbabilities = Map.of(
            "Apple", 45,
            "Bell", 39,
            "Cherry", 90,
            "Grape", 30,
            "Heart", 15,
            "Key", 15,
            "Orange", 46,
            "Strawberry", 39,
            "Watermelon", 15,
            "Fighter", 7
    );


    public static Collectible spawnFruit() {
        for (Map.Entry<String, Integer> entry : fruitProbabilities.entrySet()) {
            if (RandomHandler.getRandomInt() <= entry.getValue()) {
                try {
                    return (Collectible)Class.forName("com.emkave.pacman.entity.collectible." + entry.getKey()).getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("FruitHandler::spawnFruits() -> " + e.getMessage());
                }
            }
        }

        return null;
    }


    public static void placeFruit() {
        Collectible fruit = FruitHandler.spawnFruit();

        if (fruit != null) {
            List<int[]> validPositions = FruitHandler.getValidPositions();

            if (validPositions.isEmpty()) {
                return;
            }

            int[] position = validPositions.get(RandomHandler.getRandomInt() % validPositions.size());

            fruit.setX(position[0]);
            fruit.setY(position[1]);

            EntityHandler.getCollectibleMap().put(new TileKey(position[0], position[1]).hashCode(), fruit);
            MapHandler.getGameMapPane().getChildren().add(478, fruit.getImageView());
        }
    }


    private static List<int[]> getValidPositions() {
        List<int[]> validPositions = new ArrayList<>();
        char[][] gameMap = MapHandler.getGameMap();

        for (int row=0; row<gameMap.length; row++) {
            for (int col=0; col<gameMap[row].length; col++) {
                char tile = gameMap[row][col];

                if (tile == '0') {
                    int finalCol = col;
                    int finalRow = row;
                    boolean isOccupiedByMob = EntityHandler.getMobs().values().stream().anyMatch(mob -> mob.getX() == finalCol && mob.getY() == finalRow);
                    boolean isOccupiedByCollectible = EntityHandler.getCollectibleMap().values().stream().anyMatch(collectible -> collectible.getX() == finalCol && collectible.getY() == finalRow);

                    if (!isOccupiedByCollectible && !isOccupiedByMob) {
                        validPositions.add(new int[]{col, row});
                    }
                }
            }
        }

        return validPositions;
    }
}
