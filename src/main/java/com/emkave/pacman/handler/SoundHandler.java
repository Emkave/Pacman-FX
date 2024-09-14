package com.emkave.pacman.handler;

import com.emkave.pacman.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

public class SoundHandler {
    private static boolean isMuted = false;
    private static Map<String, AudioClip> soundEffects = new HashMap<>();
    private static MediaPlayer backgroundMusic;

    public static void loadSoundEffect(String soundName, String filePath) {
        AudioClip audioClip = new AudioClip(Application.class.getResource(filePath).toExternalForm());
        SoundHandler.soundEffects.put(soundName, audioClip);
    }


    public static void playSoundEffect(String soundName) {
        if (!SoundHandler.isMuted && SoundHandler.soundEffects.containsKey(soundName)) {
            SoundHandler.soundEffects.get(soundName).play();
        }
    }


    public static void playBackgroundMusic(String filePath, boolean loop) {
        Media media = new Media(SoundHandler.class.getResource(filePath).toExternalForm());
        SoundHandler.backgroundMusic = new MediaPlayer(media);

        if (loop) {
            SoundHandler.backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        }

        if (!SoundHandler.isMuted) {
            SoundHandler.backgroundMusic.play();
        }
    }


    public static void stopBackgroundMusic() {
        if (SoundHandler.backgroundMusic != null) {
            SoundHandler.backgroundMusic.stop();
        }
    }


    public static void toggleMute() {
        SoundHandler.isMuted = !SoundHandler.isMuted;

        if (SoundHandler.isMuted && SoundHandler.backgroundMusic != null) {
            SoundHandler.backgroundMusic.pause();
        } else if (SoundHandler.backgroundMusic != null) {
            SoundHandler.backgroundMusic.play();
        }
    }


    public static boolean isMuted() {
        return isMuted;
    }
}
