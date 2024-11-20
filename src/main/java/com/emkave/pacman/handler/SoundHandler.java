package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SoundHandler {
    private static Map<String, AudioClip> soundEffects = new HashMap<>();
    private static MediaPlayer backgroundMusic;


    public static void loadSoundEffect(String soundName, String filePath) {
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(Application.class.getResource(filePath)).toExternalForm());
        SoundHandler.soundEffects.put(soundName, audioClip);
    }


    public static void playSoundEffect(String soundName) {
        if (!REGISTRY_KEYS.GET_ISMUTED() && SoundHandler.soundEffects.containsKey(soundName)) {
            SoundHandler.soundEffects.get(soundName).play();
        }
    }


    public static void playBackgroundMusic(String filePath, boolean loop) {
        Media media = new Media(Objects.requireNonNull(SoundHandler.class.getResource(filePath)).toExternalForm());
        SoundHandler.backgroundMusic = new MediaPlayer(media);

        if (loop) {
            SoundHandler.backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        }

        if (!REGISTRY_KEYS.GET_ISMUTED()) {
            SoundHandler.backgroundMusic.play();
        }
    }


    public static void stopBackgroundMusic() {
        if (SoundHandler.backgroundMusic != null) {
            SoundHandler.backgroundMusic.stop();
        }
    }


    public static void toggleMute() {
        REGISTRY_KEYS.SET_ISMUTED(!REGISTRY_KEYS.GET_ISMUTED());

        if (REGISTRY_KEYS.GET_ISMUTED() && SoundHandler.backgroundMusic != null) {
            SoundHandler.backgroundMusic.pause();
        } else if (SoundHandler.backgroundMusic != null) {
            SoundHandler.backgroundMusic.play();
        }
    }
}
