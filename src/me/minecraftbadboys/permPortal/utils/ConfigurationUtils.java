package me.minecraftbadboys.permPortal.utils;

import me.minecraftbadboys.permPortal.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.io.InputStream;

public class ConfigurationUtils {

    private static final Main plugin = Main.getInstance();

    private static File configF;
    private static Configuration configC;

    public static void registerConfiguration() {
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdirs();

        configF = new File(dataFolder, "config.yml");
        if (!configF.exists()) {
            try (InputStream in = plugin.getResourceAsStream("config.yml")) {
                Files.copy(in, configF.toPath());
            } catch (IOException ignored) {
            }
        }
        try {
            configC = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configF);
        } catch (IOException ignored) {
        }
    }

    public static void saveReloadConfiguration() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configC, configF);
        } catch (IOException ignored) {
        }

        try {
            configC = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configF);
        } catch (IOException ignored) {
        }
    }

    public static Configuration getConfig() {
        return configC;
    }

}
