package stupidprison.stupidprison;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Globals {
    public static Plugin plugin;
    public static Economy econ = null;
    public static List<Material> materials = new ArrayList<>();

    public static World world;

    public static void log(String message) {
        Bukkit.getLogger().info("[StupidPrison] " + message);
    }
}
