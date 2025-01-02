package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import javafx.scene.text.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


public abstract class REGISTRY_KEYS {
    private static boolean IS_PAUSED = false;
    private static boolean IS_MUTED = false;
    private static Map<Character, Function<Character, String>> CLASS_NAME_RESOLVER = new HashMap<>();
    private static Font UI_FONT;

    private static final double SCREEN_WIDTH = 672.0;
    private static final double SCREEN_HEIGHT = 864.0;
    private static final int MAP_WIDTH = 28;
    private static final int MAP_HEIGHT = 31;
    private static final double GAME_MAP_WIDTH = REGISTRY_KEYS.SCREEN_WIDTH * 0.7;
    private static final double GAME_MAP_HEIGHT = REGISTRY_KEYS.SCREEN_HEIGHT * 0.7;
    private static final double GAME_MAP_CELL_WIDTH = GAME_MAP_WIDTH / REGISTRY_KEYS.MAP_WIDTH;
    private static final double GAME_MAP_CELL_HEIGHT = GAME_MAP_HEIGHT / REGISTRY_KEYS.MAP_HEIGHT;

    static {
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('A', TILE -> "Apple");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('B', TILE -> "Bell");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('C', TILE -> "Cherry");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('D', TILE -> "Dot");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('F', TILE -> "Fighter");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('G', TILE -> "Grape");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('H', TILE -> "Heart");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('K', TILE -> "Key");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('O', TILE -> "Orange");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('P', TILE -> "Pill");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('S', TILE -> "Strawberry");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('W', TILE -> "Watermelon");
    }

    public static synchronized void SET_UI_FONT(final String __font, final int __size) {
        REGISTRY_KEYS.UI_FONT = Font.loadFont(Objects.requireNonNull(Application.class.getResourceAsStream("Fonts/"+__font+".ttf")), __size);
    }

    public static synchronized void SET_UI_FONT() {
        String lang = Application.localeResourceBundle.getLocale().getLanguage();

        if (Objects.equals(lang, "cz") || Objects.equals(lang, "en") || Objects.equals(lang, "ru")) {
            REGISTRY_KEYS.SET_UI_FONT("arcade_font", 30);
        }
    }

    public static synchronized void SET_ISPAUSED(final boolean __B) {
        REGISTRY_KEYS.IS_PAUSED = __B;
    }

    public static synchronized void SET_ISMUTED(final boolean __B) {
        REGISTRY_KEYS.IS_MUTED = __B;
    }

    public static synchronized boolean GET_ISPAUSED() {
        return REGISTRY_KEYS.IS_PAUSED;
    }

    public static synchronized double GET_SCREEN_WIDTH() {
        return REGISTRY_KEYS.SCREEN_WIDTH;
    }

    public static synchronized double GET_SCREEN_HEIGHT() {
        return REGISTRY_KEYS.SCREEN_HEIGHT;
    }

    public static synchronized double GET_GAME_MAP_WIDTH() {
        return REGISTRY_KEYS.GAME_MAP_WIDTH;
    }

    public static synchronized double GET_GAME_MAP_HEIGHT() {
        return REGISTRY_KEYS.GAME_MAP_HEIGHT;
    }

    public static synchronized double GET_GAME_MAP_CELL_WIDTH() {
        return REGISTRY_KEYS.GAME_MAP_CELL_WIDTH;
    }

    public static synchronized double GET_GAME_MAP_CELL_HEIGHT() {
        return REGISTRY_KEYS.GAME_MAP_CELL_HEIGHT;
    }

    public static synchronized int GET_MAP_WIDTH() {
        return REGISTRY_KEYS.MAP_WIDTH;
    }

    public static synchronized int GET_MAP_HEIGHT() {
        return REGISTRY_KEYS.MAP_HEIGHT;
    }

    public static synchronized boolean GET_ISMUTED() {
        return REGISTRY_KEYS.IS_MUTED;
    }

    public static synchronized Map<Character, Function<Character, String>> GET_CLASS_NAME_RESOLVER() {
        return REGISTRY_KEYS.CLASS_NAME_RESOLVER;
    }

    public static synchronized Font GET_UI_FONT() {
        return REGISTRY_KEYS.UI_FONT;
    }
}
