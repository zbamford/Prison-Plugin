package stupidprison.stupidprison;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import stupidprison.stupidprison.mines.Mine;
import stupidprison.stupidprison.mines.MineBuilder;
import stupidprison.stupidprison.handlers.PlayerHandler;
import stupidprison.stupidprison.handlers.TorchHandler;
import stupidprison.stupidprison.mines.MineSetup;
import stupidprison.stupidprison.util.Configuration;
import stupidprison.stupidprison.util.DelayedTask;

import static stupidprison.stupidprison.Globals.econ;

public class StupidPrison extends JavaPlugin {

    private Configuration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("hello world!!");

        Globals.plugin = this;

        saveDefaultConfig();

        config = new Configuration(this, "mines");
        config.getConfig().set("goodbye", "world");
        config.save();

        new TorchHandler(this);
        new PlayerHandler(this);

        new DelayedTask(this);

        new MineSetup();

        if (!setupEconomy()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("shutting down!!");
    }

    public Configuration getConfiguration() {
        return config;
    }

    public static void log(String message) {
        Bukkit.getLogger().info("[StupidPrison] " + message);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
