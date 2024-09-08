package com.example.pacman.handler;

import com.example.pacman.Application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class ConfigHandler {
    public static Locale loadLanguageSettings() {
        Properties properties = new Properties();
        Locale locale = Locale.getDefault();

        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);

            String language = properties.getProperty("language");
            String region = properties.getProperty("region");

            if (language != null && region != null) {
                locale = new Locale(language, region);
            } else {
                System.out.println("ConfigLoader::loadLanguageSettings() -> Language or region is missing.");
            }
        } catch (IOException e) {
            System.out.println("ConfigLoader::loadLanguageSettings() -> Could not read config.properties.");
        }

        return locale;
    }


    public static void setLanguageSettings(String language, String region) {
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("ConfigHandler::setLanguageSettings() -> config.properties file not found.");
        }

        try (FileOutputStream out = new FileOutputStream("config.properties")) {
            properties.setProperty("language", language);
            properties.setProperty("region", region);
            properties.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Locale.setDefault(ConfigHandler.loadLanguageSettings());
        Application.resourceBundle = ResourceBundle.getBundle("com.example.pacman.Langs.messages", Locale.getDefault());
    }


    public static String getTopScore() {
        Properties properties = new Properties();
        String score = null;

        try (FileInputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
            score = properties.getProperty("top_score");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (score != null) ? score : "";
    }


    public static String getLevel() {
        Properties properties = new Properties();
        String level = null;

        try (FileInputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
            level = properties.getProperty("level");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (level != null) ? level : "";
    }
}
