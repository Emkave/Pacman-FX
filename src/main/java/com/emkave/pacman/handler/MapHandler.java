package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.collectible.Collectible;
import com.emkave.pacman.entity.collectible.Dot;
import com.emkave.pacman.entity.mob.Mob;
import com.emkave.pacman.entity.mob.Pacman;
import com.emkave.pacman.scene.Game;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class MapHandler {
    private static StackPane gameMapPane;
    private static char[][] map;


    public static void loadGameMap() {
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
        if (!EntityHandler.getMobs().isEmpty()) {
            EntityHandler.getMobs().get('!').render();
        }
    }


    public static void loadGameEntities() {
        EntityHandler.loadMobs();

        final Map<Integer, Collectible> collectibles = EntityHandler.getCollectibleMap();
        final Map<Character, Mob> mobs = EntityHandler.getMobs();

        for (Collectible collectible : collectibles.values()) {
            collectible.getImageView().setTranslateX(collectible.getX() * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
            collectible.getImageView().setTranslateY(collectible.getY() * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
            MapHandler.getGameMapPane().getChildren().add(collectible.getImageView());
        }

        for (Mob mob : mobs.values()) {
            mob.getImageView().setTranslateX(mob.getX() * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
            mob.getImageView().setTranslateY(mob.getY() * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
            MapHandler.getGameMapPane().getChildren().add(mob.getImageView());
        }

        MapHandler.loadTheRest();
    }


    private static ImageView createTileImageView(final char tileType) {
        try {
            InputStream imageStream = Application.class.getResourceAsStream("Images/Tiles/"+tileType+".png");

            if (imageStream == null) {
                throw new RuntimeException("MapHandler::createTileImage() -> Tile image not found: " + tileType);
            }

            return new ImageView(new Image(imageStream));
        } catch (Exception e) {
            throw new RuntimeException("MapHandler::createTileImageView() -> " + e.getMessage());
        }
    }


    private static char[][] loadMapFile() {
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
        } catch (Exception e) {
            throw new RuntimeException("MapHandler::loadMapFile() -> " + e.getMessage());
        }

        return map;
    }


    private static void loadGameTiles() {
        for (int row = 0; row < REGISTRY_KEYS.GET_MAP_HEIGHT(); row++) {
            for (int column = 0; column < REGISTRY_KEYS.GET_MAP_WIDTH(); column++) {
                char tileType = MapHandler.map[row][column];

                if (tileType == '0' || tileType == 'X') {
                    continue;
                }

                if (tileType != '1' && tileType != '2' && tileType != '3' && tileType != '4' && tileType != '7' && tileType != '8') {
                    String colName = "com.emkave.pacman.entity.collectible." + REGISTRY_KEYS.GET_CLASS_NAME_RESOLVER().get(tileType).apply(tileType);

                    try {
                        Collectible instance = (Collectible)Class.forName(colName).getDeclaredConstructor().newInstance();

                        if (instance instanceof Dot) {
                            Game.setGameDots(Game.getGameDots()+1);
                        }

                        instance.setX(column);
                        instance.setY(row);

                        TileKey key = new TileKey(column, row);
                        EntityHandler.getCollectibleMap().put(key.hashCode(), instance);
                    } catch (Exception e) {
                        throw new RuntimeException("MapHandler::loadGameTiles() -> ", e);
                    }
                } else {
                    ImageView tileImage = MapHandler.createTileImageView(tileType);

                    tileImage.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
                    tileImage.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
                    tileImage.setTranslateX(column * REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
                    tileImage.setTranslateY(row * REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
                    MapHandler.gameMapPane.getChildren().add(tileImage);
                }
            }
        }
    }


    public static void loadTheRest() {
        ImageView bl1 = MapHandler.createTileImageView('v');
        bl1.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()*3);
        bl1.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()*3);
        bl1.setTranslateX(-10);
        bl1.setTranslateY(250);

        ImageView bl2 = MapHandler.createTileImageView('v');
        bl2.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH()*3);
        bl2.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT()*3);
        bl2.setTranslateX(430);
        bl2.setTranslateY(250);

        MapHandler.gameMapPane.getChildren().addAll(bl1, bl2);

        for (int i = 0; i<Pacman.getLivesCount(); i++) {
            Pacman.increaseLives();
        }

        for (Collectible collectible : Pacman.getCollected()) {
            MapHandler.getGameMapPane().getChildren().add(collectible.getImageView());
        }
    }


    public static synchronized StackPane getGameMapPane() {
        return MapHandler.gameMapPane;
    }


    public static synchronized char[][] getGameMap() {
        return MapHandler.map;
    }
}
