package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class REGISTRY_KEYS {
    private static boolean IS_CONTINUED = false;
    private static boolean IS_PAUSED = false;
    private static boolean IS_PLAYED = false;
    private static boolean IS_HOSTER = false;
    private static boolean IS_MUTED = false;
    private static boolean IS_INTERMISSION = false;
    private static long LAST_GAME_SCORE = Long.parseLong(ConfigHandler.getScore());
    private static int LAST_GAME_LEVEL = Integer.parseInt(ConfigHandler.getLevel());
    private static Map<Character, Function<Character, String>> CLASS_NAME_RESOLVER = new HashMap<>();
    private static int GAME_DOTS = 0;
    private static int GAME_LEVEL = 1;
    private static Font UI_FONT;
    private static int PAC_LIVES = 3;

    private static final double SCREEN_WIDTH = 672.0;
    private static final double SCREEN_HEIGHT = 864.0;
    private static final int MAP_WIDTH = 28;
    private static final int MAP_HEIGHT = 31;
    private static final double GAME_MAP_WIDTH = REGISTRY_KEYS.SCREEN_WIDTH * 0.7;
    private static final double GAME_MAP_HEIGHT = REGISTRY_KEYS.SCREEN_HEIGHT * 0.7;
    private static final double GAME_MAP_CELL_WIDTH = GAME_MAP_WIDTH / REGISTRY_KEYS.MAP_WIDTH;
    private static final double GAME_MAP_CELL_HEIGHT = GAME_MAP_HEIGHT / REGISTRY_KEYS.MAP_HEIGHT;
    private static final long GAME_MOVE_INTERVAL = 400_000_000;

    static {
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('A', TILE -> "Apple");
        REGISTRY_KEYS.CLASS_NAME_RESOLVER.put('B', TILE -> "Berry");
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

    public static synchronized void SET_ISPLAYED(final boolean __b) {
        REGISTRY_KEYS.IS_PLAYED = __b;
    }

    public static synchronized void SET_PACLIVES(final int __l) {
        REGISTRY_KEYS.PAC_LIVES = __l;
    }

    public static synchronized void SET_UI_FONT(final String __font, final int __size) {
        REGISTRY_KEYS.UI_FONT = Font.loadFont(Objects.requireNonNull(Application.class.getResourceAsStream("Fonts/"+__font+".ttf")), __size);
    }

    public static synchronized void SET_UI_FONT() {
        String lang = Application.localeResourceBundle.getLocale().getLanguage();

        if (Objects.equals(lang, "cz") || Objects.equals(lang, "en") || Objects.equals(lang, "ru")) {
            REGISTRY_KEYS.SET_UI_FONT("arcade_font", 30);
        } else if (Objects.equals(lang, "ko")) {
            REGISTRY_KEYS.SET_UI_FONT("HBIOS-SYS", 35);
        }
    }

    public static synchronized void SET_GAME_LEVEL(final int __level) {
        REGISTRY_KEYS.GAME_LEVEL = __level;
    }

    public static synchronized void SET_AMOUNT_GAME_DOTS(final int __amount) {
        REGISTRY_KEYS.GAME_DOTS = __amount;
    }

    public static synchronized void SET_LAST_GAME_SCORE(final long __score) {
        REGISTRY_KEYS.LAST_GAME_SCORE = __score;
    }

    public static synchronized void SET_ISCONTINUED(final boolean __b) {
        REGISTRY_KEYS.IS_CONTINUED = __b;
    }

    public static synchronized void SET_LAST_GAME_LEVEL(final int __level) {
        REGISTRY_KEYS.LAST_GAME_LEVEL = __level;
    }

    public static synchronized void SET_CONTINUED(final boolean __B) {
        REGISTRY_KEYS.IS_CONTINUED = __B;
    }

    public static synchronized void SET_ISPAUSED(final boolean __B) {
        REGISTRY_KEYS.IS_PAUSED = __B;
    }

    public static synchronized void SET_ISHOSTER(final boolean __B) {
        REGISTRY_KEYS.IS_HOSTER = __B;
    }

    public static synchronized void SET_ISMUTED(final boolean __B) {
        REGISTRY_KEYS.IS_MUTED = __B;
    }

    public static synchronized void SET_ISINTERMISSION(final boolean __B) {
        REGISTRY_KEYS.IS_INTERMISSION = __B;
    }


    public static synchronized boolean GET_ISCONTINUED() {
        return REGISTRY_KEYS.IS_CONTINUED;
    }

    public static synchronized boolean GET_ISPAUSED() {
        return REGISTRY_KEYS.IS_PAUSED;
    }

    public static synchronized boolean GET_ISHOSTER() {
        return REGISTRY_KEYS.IS_HOSTER;
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

    public static synchronized boolean GET_ISINTERMISSION() {
        return REGISTRY_KEYS.IS_INTERMISSION;
    }

    public static synchronized long GET_GAME_MOVE_INTERVAL() {
        return REGISTRY_KEYS.GAME_MOVE_INTERVAL;
    }

    public static synchronized int GET_LAST_GAME_LEVEL() {
        return REGISTRY_KEYS.LAST_GAME_LEVEL;
    }

    public static synchronized long GET_LAST_GAME_SCORE() {
        return REGISTRY_KEYS.LAST_GAME_SCORE;
    }

    public static synchronized Map<Character, Function<Character, String>> GET_CLASS_NAME_RESOLVER() {
        return REGISTRY_KEYS.CLASS_NAME_RESOLVER;
    }

    public static synchronized int GET_AMOUNT_GAME_DOTS() {
        return REGISTRY_KEYS.GAME_DOTS;
    }

    public static synchronized int GET_GAME_LEVEL() {
        return REGISTRY_KEYS.GAME_LEVEL;
    }

    public static synchronized Font GET_UI_FONT() {
        return REGISTRY_KEYS.UI_FONT;
    }

    public static synchronized int GET_PACLIVES() {
        return REGISTRY_KEYS.PAC_LIVES;
    }

    public static synchronized boolean GET_ISPLAYED() {
        return REGISTRY_KEYS.IS_PLAYED;
    }
}
