package com.emkave.pacman.handler;

public abstract class REGISTRY_KEYS {
    private static boolean IS_MULTIPLAYER = false;
    private static boolean IS_CONTINUED = false;
    private static boolean IS_PAUSED = false;
    private static boolean IS_HOSTER = false;
    private static boolean IS_MUTED = false;

    private static final double SCREEN_WIDTH = 672.0;
    private static final double SCREEN_HEIGHT = 864.0;
    private static final double GAME_MAP_WIDTH = REGISTRY_KEYS.SCREEN_WIDTH * 0.7;
    private static final double GAME_MAP_HEIGHT = REGISTRY_KEYS.SCREEN_HEIGHT * 0.7;

    public static void SET_ISMULTIPLAYER(final boolean __B) {
        REGISTRY_KEYS.IS_MULTIPLAYER = __B;
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

    public static boolean GET_ISMULTIPLAYER() {
        return REGISTRY_KEYS.IS_MULTIPLAYER;
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

    public static boolean GET_ISMUTED() {
        return REGISTRY_KEYS.IS_MUTED;
    }

}
