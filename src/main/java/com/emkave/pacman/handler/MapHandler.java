package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Collectible;
import com.emkave.pacman.entity.Mob;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;


public class MapHandler {
    private static StackPane gameMapFramePane;
    private static StackPane gameMapPane;
    private static ImageView mapImage;
    private static int[][] map;


    public static void loadGameMap() throws IOException {
        MapHandler.loadMapFile();

        MapHandler.gameMapFramePane = new StackPane();
        MapHandler.gameMapFramePane.setAlignment(Pos.CENTER);
        MapHandler.gameMapFramePane.setMaxWidth(550);
        MapHandler.gameMapFramePane.setMaxHeight(550);

        MapHandler.gameMapPane = new StackPane();
        MapHandler.gameMapPane.setAlignment(Pos.TOP_LEFT);
        MapHandler.gameMapPane.setMaxWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
        MapHandler.gameMapPane.setMaxHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());

        MapHandler.mapImage = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/UI/map.jpg"))));
        MapHandler.mapImage.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
        MapHandler.mapImage.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());

        MapHandler.gameMapPane.getChildren().addAll(MapHandler.mapImage);
        MapHandler.gameMapFramePane.getChildren().addAll(MapHandler.gameMapPane);
    }


    public static void loadMapFile() throws IOException {
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

        MapHandler.map = map;
    }


    public static void renderEntities() {
        final LinkedList<Collectible> collectibles = EntityHandler.getCollectibles();
        final LinkedList<Mob> mobs = EntityHandler.getMobs();

        for (Mob mob : mobs) {
            mob.render();
        }
    }


    public static void loadEntities() {
        EntityHandler.loadEntities();

        final LinkedList<Collectible> collectibles = EntityHandler.getCollectibles();
        final LinkedList<Mob> mobs = EntityHandler.getMobs();

        for (Mob mob : mobs) {
            MapHandler.gameMapPane.getChildren().add(mob.getImageView());
        }

        for (Collectible collectible : collectibles) {
            MapHandler.gameMapPane.getChildren().add(collectible.getImageView());
        }
    }


    public static StackPane getGameMapFramePane() {
        return MapHandler.gameMapFramePane;
    }
}
