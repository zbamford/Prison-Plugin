package stupidprison.stupidprison.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Configuration {

    private File file;
    private FileConfiguration config;
    public Configuration(Plugin plugin, String path) {
        this(plugin.getDataFolder().getAbsolutePath() + "/" + path + ".yml");
    }

    public Configuration(String path) {
        this.file = new File(path);
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
