package com.emkave.pacman.handler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/com/emkave/pacman/Database/sqlite/players.db";
    private static Connection connection = null;


    public static Connection connectToDatabase() {
        try {
            DatabaseHandler.connection = DriverManager.getConnection(DatabaseHandler.DB_URL);
            return DatabaseHandler.connection;
        } catch (Exception e) {
            throw new RuntimeException("DatabaseHandler::connectToDatabase() -> " + e.getMessage());
        }
    }


    public static boolean stopConnection() {
        try {
            if (DatabaseHandler.connection != null) {
                DatabaseHandler.connection.close();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("DatabaseHandler::stopConnection() -> " + e.getMessage());
        }

        return false;
    }


    public static boolean saveFinalResult(final int level, final long score, final String username) {
        String sql = "INSERT INTO player_scores (username, score, level) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DatabaseHandler.connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setLong(2, score);
            pstmt.setInt(3, level);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("DatabaseHandler::saveFinalResult() -> " + e.getMessage());
        }

        return true;
    }


    public static boolean checkUsernameExists(final String username) {
        String sql = "SELECT * FROM player_scores WHERE username = ?";

        try (PreparedStatement pstmt = DatabaseHandler.connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException("DatabaseHandler::checkUsernameExists() -> " + e.getMessage());
        }
    }


    public static List<Map<String, Object>> getTopPlayers(int offset, int limit) {
        List<Map<String, Object>> players = new ArrayList<>();

        try {
            DatabaseHandler.connectToDatabase();
            PreparedStatement stmt = DatabaseHandler.connection.prepareStatement("SELECT username, score, level FROM player_scores ORDER BY score DESC LIMIT ? OFFSET ?");
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> player = new HashMap<>();
                player.put("username", rs.getString("username"));
                player.put("score", rs.getInt("score"));
                player.put("level", rs.getInt("level"));
                players.add(player);
            }
        } catch (Exception e) {
            throw new RuntimeException("DatabaseHandler::getTopPlayers() -> " + e.getMessage());
        }

        return players;
    }


    public static Connection isConnected() {
        return DatabaseHandler.connection;
    }
}
