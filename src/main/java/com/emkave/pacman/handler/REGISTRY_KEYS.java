package com.emkave.pacman.handler;

public abstract class REGISTRY_KEYS {
    private static boolean IS_CONTINUED = false;
    private static boolean IS_PAUSED = false;
    private static boolean IS_HOSTER = false;
    private static boolean IS_MUTED = false;
    private static long GAME_SCORE = Long.parseLong(ConfigHandler.getScore());
    private static byte GAME_LEVEL = Byte.parseByte(ConfigHandler.getLevel());

    private static final double SCREEN_WIDTH = 672.0;
    private static final double SCREEN_HEIGHT = 864.0;
    private static final int MAP_WIDTH = 28;
    private static final int MAP_HEIGHT = 31;
    private static final double GAME_MAP_WIDTH = REGISTRY_KEYS.SCREEN_WIDTH * 0.7;
    private static final double GAME_MAP_HEIGHT = REGISTRY_KEYS.SCREEN_HEIGHT * 0.7;
    private static final double GAME_MAP_CELL_WIDTH = GAME_MAP_WIDTH / REGISTRY_KEYS.MAP_WIDTH;
    private static final double GAME_MAP_CELL_HEIGHT = GAME_MAP_HEIGHT / REGISTRY_KEYS.MAP_HEIGHT;
    private static final long GAME_MOVE_INTERVAL = 400_000_000;

    public static void SET_GAME_SCORE(final long __score) {
        REGISTRY_KEYS.GAME_SCORE = __score;
    }

    public static void SET_GAME_LEVEL(final byte __level) {
        REGISTRY_KEYS.GAME_LEVEL = __level;
    }

    public static void SET_CONTINUED(final boolean __B) {
        REGISTRY_KEYS.IS_CONTINUED = __B;
    }

    public static void SET_ISPAUSED(final boolean __B) {
        REGISTRY_KEYS.IS_PAUSED = __B;
    }

    public static void SET_ISHOSTER(final boolean __B) {
        REGISTRY_KEYS.IS_HOSTER = __B;
    }

    public static void SET_ISMUTED(final boolean __B) {
        REGISTRY_KEYS.IS_MUTED = __B;
    }

    public static boolean GET_ISCONTINUED() {
        return REGISTRY_KEYS.IS_CONTINUED;
    }

    public static boolean GET_ISPAUSED() {
        return REGISTRY_KEYS.IS_PAUSED;
    }

    public static boolean GET_ISHOSTER() {
        return REGISTRY_KEYS.IS_HOSTER;
    }

    public static double GET_SCREEN_WIDTH() {
        return REGISTRY_KEYS.SCREEN_WIDTH;
    }

    public static double GET_SCREEN_HEIGHT() {
        return REGISTRY_KEYS.SCREEN_HEIGHT;
    }

    public static double GET_GAME_MAP_WIDTH() {
        return REGISTRY_KEYS.GAME_MAP_WIDTH;
    }

    public static double GET_GAME_MAP_HEIGHT() {
        return REGISTRY_KEYS.GAME_MAP_HEIGHT;
    }

    public static double GET_GAME_MAP_CELL_WIDTH() {
        return REGISTRY_KEYS.GAME_MAP_CELL_WIDTH;
    }

    public static double GET_GAME_MAP_CELL_HEIGHT() {
        return REGISTRY_KEYS.GAME_MAP_CELL_HEIGHT;
    }

    public static int GET_MAP_WIDTH() {
        return REGISTRY_KEYS.MAP_WIDTH;
    }

    public static int GET_MAP_HEIGHT() {
        return REGISTRY_KEYS.MAP_HEIGHT;
    }

    public static boolean GET_ISMUTED() {
        return REGISTRY_KEYS.IS_MUTED;
    }

    public static long GET_GAME_MOVE_INTERVAL() {
        return REGISTRY_KEYS.GAME_MOVE_INTERVAL;
    }

    public static byte GET_GAME_LEVEL() {
        return REGISTRY_KEYS.GAME_LEVEL;
    }

    public static long GET_GAME_SCORE() {
        return REGISTRY_KEYS.GAME_SCORE;
    }
}
