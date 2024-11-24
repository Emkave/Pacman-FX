package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Collectible;
import com.emkave.pacman.entity.Mob;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;


public class MapHandler {
    private static StackPane gameMapFramePane;
    private static GridPane gameMapPane;
    private static StackPane gameMapEntityPane;
    private static int[][] map;


    public static void loadGameMap() throws IOException {
        MapHandler.map = MapHandler.loadMapFile();

        MapHandler.gameMapFramePane = new StackPane();
        MapHandler.gameMapFramePane.setAlignment(Pos.CENTER);
        MapHandler.gameMapFramePane.setMaxWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
        MapHandler.gameMapFramePane.setMaxHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());

        MapHandler.gameMapPane = MapHandler.loadGameTiles();
        MapHandler.gameMapPane.setTranslateY(20);

        MapHandler.gameMapEntityPane = new StackPane();
        MapHandler.gameMapEntityPane.setPickOnBounds(false);
        MapHandler.gameMapEntityPane.setAlignment(Pos.TOP_LEFT);
        MapHandler.gameMapEntityPane.setMinWidth(REGISTRY_KEYS.GET_GAME_MAP_WIDTH());
        MapHandler.gameMapEntityPane.setMinHeight(REGISTRY_KEYS.GET_GAME_MAP_HEIGHT());

        MapHandler.gameMapFramePane.getChildren().addAll(MapHandler.gameMapPane, MapHandler.gameMapEntityPane);
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
            MapHandler.gameMapEntityPane.getChildren().add(mob.getImageView());
        }

        for (Collectible collectible : collectibles) {
            MapHandler.gameMapEntityPane.getChildren().add(collectible.getImageView());
        }
    }


    public static StackPane getGameMapFramePane() {
        return MapHandler.gameMapFramePane;
    }


    public static int[][] getGameMap() {
        return MapHandler.map;
    }


    private static ImageView createTileImageView(final int tileType) {
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


    private static int[][] loadMapFile() throws IOException {
        int[][] map = new int[(int)REGISTRY_KEYS.GET_MAP_HEIGHT()][(int)REGISTRY_KEYS.GET_MAP_WIDTH()];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Application.class.getResourceAsStream("Map/map.txt"))))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    map[row][col] = line.charAt(col) - '0';
                }

                row++;
            }
        }

        return map;
    }


    private static GridPane loadGameTiles() {
        GridPane gridPane = new GridPane();

        for (int row = 0; row < REGISTRY_KEYS.GET_MAP_HEIGHT(); row++) {
            for (int column = 0; column < REGISTRY_KEYS.GET_MAP_WIDTH(); column++) {
                int tileType = MapHandler.map[row][column];
                ImageView tileImage = MapHandler.createTileImageView(tileType);

                if (tileImage != null) {
                    tileImage.setFitWidth(REGISTRY_KEYS.GET_GAME_MAP_CELL_WIDTH());
                    tileImage.setFitHeight(REGISTRY_KEYS.GET_GAME_MAP_CELL_HEIGHT());
                    gridPane.add(tileImage, column, row);
                }
            }
        }

        return gridPane;
    }
}
