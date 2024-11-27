package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.mob.Mob;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;


public class MapHandler {
    private static Map<String, ImageView> tileImageMap = new HashMap<>();
    private static StackPane gameMapPane;
    private static char[][] map;



    public static void loadGameMap() throws IOException {
        MapHandler.map = MapHandler.loadMapFile();

        MapHandler.gameMapPane = new StackPane();
        MapHandler.gameMapPane.setAlignment(Pos.TOP_LEFT);
        MapHandler.gameMapPane.setMinWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
        MapHandler.gameMapPane.setMinHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());
        MapHandler.gameMapPane.setTranslateX(100);
        MapHandler.gameMapPane.setTranslateY(150);

        MapHandler.loadGameTiles();
    }


    public static void renderEntities() {
        final LinkedList<Collectible> collectibles = EntityHandler.getCollectibles();
        final LinkedList<Mob> mobs = EntityHandler.getMobs();

        for (Mob mob : mobs) {
            mob.render();
        }

        for (Collectible collectible : collectibles) {
            collectible.render();
        }
    }


    public static void loadEntities() {
        EntityHandler.loadEntities();

        final LinkedList<Collectible> collectibles = EntityHandler.getCollectibles();
        final LinkedList<Mob> mobs = EntityHandler.getMobs();

        for (Mob mob : mobs) {
            mob.getImageView().setTranslateX(mob.getX() * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
            mob.getImageView().setTranslateY(mob.getY() * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
            MapHandler.gameMapPane.getChildren().add(mob.getImageView());
        }

        for (Collectible collectible : collectibles) {
            collectible.getImageView().setTranslateX(collectible.getX() * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
            collectible.getImageView().setTranslateY(collectible.getY() * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
            MapHandler.gameMapPane.getChildren().add(collectible.getImageView());
        }

        ImageView bl1 = MapHandler.createTileImageView('0', true);
        assert bl1 != null;
        bl1.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()*3);
        bl1.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()*3);
        bl1.setTranslateX(-10);
        bl1.setTranslateY(250);

        ImageView bl2 = MapHandler.createTileImageView('0', true);
        assert bl2 != null;
        bl2.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()*3);
        bl2.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()*3);
        bl2.setTranslateX(430);
        bl2.setTranslateY(250);

        MapHandler.gameMapPane.getChildren().addAll(bl1, bl2);
    }


    public static StackPane getGameMapPane() {
        return MapHandler.gameMapPane;
    }


    public static char[][] getGameMap() {
        return MapHandler.map;
    }


    private static ImageView createTileImageView(final char tileType, final boolean __p) {
        if (tileType == 0 && !__p) {
            return null;
        }
        try {
            InputStream imageStream = Application.class.getResourceAsStream("Images/Tiles/"+tileType+".png");

            if (imageStream == null) {
                throw new IllegalArgumentException("MapHandler::createTileImage() -> Tile image not found: " + tileType);
            }

            return new ImageView(new Image(imageStream));
        } catch (Exception e) {
            System.err.println("MapHandler::createTileImageView() -> Error loading tile image for type: " + tileType);
            return null;
        }
    }


    private static char[][] loadMapFile() throws IOException {
        char[][] map = new char[(int)REGISTRY_KEYS.GET_MAP_HEIGHT()][(int)REGISTRY_KEYS.GET_MAP_WIDTH()];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Application.class.getResourceAsStream("Map/map.txt"))))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    map[row][col] = line.charAt(col);
                }

                row++;
            }
        }

        return map;
    }


    private static void loadGameTiles() {
        for (int row = 0; row < REGISTRY_KEYS.GET_MAP_HEIGHT(); row++) {
            for (int column = 0; column < REGISTRY_KEYS.GET_MAP_WIDTH(); column++) {
                char tileType = MapHandler.map[row][column];
                ImageView tileImage = MapHandler.createTileImageView(tileType, false);

                if (tileImage != null) {
                    tileImage.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
                    tileImage.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
                    tileImage.setTranslateX(column * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
                    tileImage.setTranslateY(row * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
                    MapHandler.gameMapPane.getChildren().add(tileImage);

                    String key = row + "," + column;
                    MapHandler.tileImageMap.put(key, tileImage);
                }
            }
        }
    }


    public static ImageView getTileImage(final int row, final int column) {
        return MapHandler.tileImageMap.get(row+","+column);
    }
}
