package com.emkave.pacman.handler;

import com.emkave.pacman.Application;

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
                throw new RuntimeException("ConfigLoader::loadLanguageSettings() -> Language or region is missing.");
            }
        } catch (IOException e) {
            throw new RuntimeException("ConfigHandler::loadLanguageSettings() -> ", e);
        }

        return locale;
    } 


    public static void setLanguageSettings(String language, String region) {
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("ConfigHandler::setLanguageSettings() -> ", e);
        }

        try (FileOutputStream out = new FileOutputStream("config.properties")) {
            properties.setProperty("language", language);
            properties.setProperty("region", region);
            properties.store(out, null);
        } catch (IOException e) {
            throw new RuntimeException("ConfigHandler::setLanguageSettings() -> ", e);
        }

        Locale.setDefault(ConfigHandler.loadLanguageSettings());
        Application.localeResourceBundle = ResourceBundle.getBundle("com.emkave.pacman.Langs.messages", Locale.getDefault());
    }
}
