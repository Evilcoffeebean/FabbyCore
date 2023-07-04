package dev.fabby.com.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    private File file;
    private FileConfiguration config;

    public FileUtil(File file) {
        this.file = file;
        this.config = YamlConfiguration.loadConfiguration(file);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    private void init() throws IOException {
        if (!file.exists()) {
            config.save(file);
        } else {
            config = YamlConfiguration.loadConfiguration(file);
        }
    }

    public <T> void set(String path, T value) throws IOException {
        set(path, value, false);
    }

    public <T> void set(String path, T value, boolean overrideExisting) throws IOException {
        if (config != null && file.exists()) {
            if (config.get(path) != null) {
                if (overrideExisting) {
                    config.set(path, value);
                    config.save(file);
                    return;
                } else
                    return;
            }
            config.set(path, value);
            config.save(file);
        }
    }

    public <T> T get(String path) {
        try {
            return (T) config.get(path);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getBoolean(String path) {
        try {
            return config.getBoolean(path);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getInteger(String path) {
        try {
            return config.getInt(path);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public double getDouble(String path) {
        try {
            return config.getDouble(path);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public String getString(String path) {
        try {
            return config.getString(path);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    public static List<File> getFiles(final String path) {
        try {
            return Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
