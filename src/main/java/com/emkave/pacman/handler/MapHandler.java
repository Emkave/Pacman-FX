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
import java.util.*;


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
        MapHandler.loadGameMobs();

        ImageView bl1 = MapHandler.createTileImageView('v');
        assert bl1 != null;
        bl1.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()*3);
        bl1.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()*3);
        bl1.setTranslateX(-10);
        bl1.setTranslateY(250);

        ImageView bl2 = MapHandler.createTileImageView('v');
        assert bl2 != null;
        bl2.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()*3);
        bl2.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()*3);
        bl2.setTranslateX(430);
        bl2.setTranslateY(250);

        MapHandler.gameMapPane.getChildren().addAll(bl1, bl2);
    }


    public static void renderEntities() {
        final Map<TileKey, Collectible> collectibles = EntityHandler.getCollectibleMap();
        final LinkedList<Mob> mobs = EntityHandler.getMobs();

        for (Mob mob : mobs) {
            mob.render();
        }

        for (Map.Entry<TileKey, Collectible> entry : collectibles.entrySet()) {
            entry.getValue().render();
        }
    }


    public static void loadGameMobs() {
        EntityHandler.loadMobs();

        final Map<TileKey, Collectible> collectibles = EntityHandler.getCollectibleMap();
        final LinkedList<Mob> mobs = EntityHandler.getMobs();

        for (Map.Entry<TileKey, Collectible> entry : collectibles.entrySet()) {
            Collectible collectible = entry.getValue();
            collectible.getImageView().setTranslateX(collectible.getX() * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
            collectible.getImageView().setTranslateY(collectible.getY() * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
            MapHandler.gameMapPane.getChildren().add(collectible.getImageView());
        }

        for (Mob mob : mobs) {
            mob.getImageView().setTranslateX(mob.getX() * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
            mob.getImageView().setTranslateY(mob.getY() * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
            MapHandler.gameMapPane.getChildren().add(mob.getImageView());
        }
    }


    public static StackPane getGameMapPane() {
        return MapHandler.gameMapPane;
    }


    public static char[][] getGameMap() {
        return MapHandler.map;
    }


    private static ImageView createTileImageView(final char tileType) {
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

                if (tileType == '0') {
                    continue;
                }

                if (tileType != '1' && tileType != '2' && tileType != '3' && tileType != '4' && tileType != '7' && tileType != '8') {
                    String colName = "com.emkave.pacman.entity.collectible." + REGISTRY_KEYS.GET_CLASS_NAME_RESOLVER().get(tileType).apply(tileType);

                    try {
                        Collectible instance = (Collectible)Class.forName(colName).getDeclaredConstructor().newInstance();
                        instance.setX(column);
                        instance.setY(row);

                        TileKey key = new TileKey(tileType, column, row);
                        EntityHandler.getCollectibleMap().put(key, instance);
                    } catch (Exception e) {
                        System.err.println("Failed to create instance for tileType '" + tileType + "': " + e.getMessage());
                    }
                } else {
                    ImageView tileImage = MapHandler.createTileImageView(tileType);

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
    }


    public static Collectible getCollectible(char tileType, int x, int y) {
        return EntityHandler.getCollectibleMap().get(new TileKey(tileType, x, y));
    }
}
