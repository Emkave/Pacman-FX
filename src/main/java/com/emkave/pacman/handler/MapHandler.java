package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import com.emkave.pacman.entity.Entity;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Vector;


public class MapHandler {
    private static StackPane gameMapFramePane;
    private static StackPane gameMapPane;
    private static ImageView mapImage;
    private static int[][] map;
    private static Vector<Entity> entities = new Vector<Entity>();
    private static final double ImgXcellSize = 470.4 / 19;
    private static final double ImgYcellSize = 604.8 / 22;


    public static void loadGameMap() throws IOException {
        MapHandler.loadMapFile();

        MapHandler.gameMapFramePane = new StackPane();
        MapHandler.gameMapFramePane.setAlignment(Pos.CENTER);
        MapHandler.gameMapFramePane.setMaxWidth(550);
        MapHandler.gameMapFramePane.setMaxHeight(550);

        MapHandler.gameMapPane = new StackPane();
        MapHandler.gameMapPane.setAlignment(Pos.TOP_LEFT);
        MapHandler.gameMapPane.setMaxWidth(470.4);
        MapHandler.gameMapPane.setMaxHeight(604.8);

        MapHandler.mapImage = new ImageView(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("Images/UI/map.jpg"))));
        MapHandler.mapImage.setFitWidth(470.4);
        MapHandler.mapImage.setFitHeight(604.8);

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


    public static double GetImgXcellSize() {
        return MapHandler.ImgXcellSize;
    }


    public static double GetImgYcellSize() {
        return MapHandler.ImgYcellSize;
    }


    public static void emplaceEntity(Entity entity) {
        MapHandler.gameMapPane.getChildren().add(entity.getImageView());
        MapHandler.entities.add(entity);
    }


    public static StackPane getGameMapFramePane() {
        return MapHandler.gameMapFramePane;
    }
}
