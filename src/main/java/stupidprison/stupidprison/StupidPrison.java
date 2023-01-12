package stupidprison.stupidprison;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;
import stupidprison.stupidprison.mines.CommandMineSetup;
import stupidprison.stupidprison.handlers.PlayerHandler;
import stupidprison.stupidprison.handlers.TorchHandler;
import stupidprison.stupidprison.util.Configuration;
import stupidprison.stupidprison.util.DelayedTask;

import java.util.ArrayList;
import java.util.List;

import static stupidprison.stupidprison.Globals.*;

public class StupidPrison extends JavaPlugin {

    private Configuration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("hello world!!");

        plugin = this;
        Globals.world = getServer().getWorld("world");

        saveDefaultConfig();

        config = new Configuration(this, "mines");
        config.getConfig().set("goodbye", "world");
        config.save();

        new TorchHandler(this);

        new DelayedTask(this);

        if (!setupEconomy()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        materials.add(Material.STONE);                  // Tutorial
        materials.add(Material.COAL_ORE);               // A
        materials.add(Material.DEEPSLATE_COAL_ORE);     // B
        materials.add(Material.COAL_BLOCK);             // C
        materials.add(Material.COPPER_ORE);             // D
        materials.add(Material.DEEPSLATE_COPPER_ORE);   // E
        materials.add(Material.COPPER_BLOCK);           // F
        materials.add(Material.IRON_ORE);               // G
        materials.add(Material.DEEPSLATE_IRON_ORE);     // H
        materials.add(Material.IRON_BLOCK);             // I
        materials.add(Material.REDSTONE_ORE);           // J
        materials.add(Material.DEEPSLATE_REDSTONE_ORE); // K
        materials.add(Material.REDSTONE_BLOCK);         // L
        materials.add(Material.LAPIS_ORE);              // M
        materials.add(Material.DEEPSLATE_LAPIS_ORE);    // N
        materials.add(Material.LAPIS_BLOCK);            // O
        materials.add(Material.GOLD_ORE);               // P
        materials.add(Material.DEEPSLATE_GOLD_ORE);     // Q
        materials.add(Material.GOLD_BLOCK);             // R
        materials.add(Material.DIAMOND_ORE);            // S
        materials.add(Material.DEEPSLATE_DIAMOND_ORE);  // T
        materials.add(Material.DIAMOND_BLOCK);          // U
        materials.add(Material.EMERALD_ORE);            // V
        materials.add(Material.DEEPSLATE_EMERALD_ORE);  // W
        materials.add(Material.EMERALD_BLOCK);          // X
        materials.add(Material.ANCIENT_DEBRIS);         // Y
        materials.add(Material.NETHERITE_BLOCK);        // Z
        materials.add(Material.BEACON);                 // Free

        new PlayerHandler(this);

        setupCommands();

        Bukkit.getPluginManager().registerEvents(new MenuFunctionListener(), plugin);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("shutting down!!");
    }

    public Configuration getConfiguration() {
        return config;
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

    private void setupCommands() {
        List<String> commands = new ArrayList<>();
        commands.add("setupmines");
        commands.add("resetmines");

        CommandMineSetup cms = new CommandMineSetup();
        for (String cmd : commands) {
            this.getCommand(cmd).setExecutor(cms);
        }
    }

}
