package stupidprison.stupidprison;

import net.milkbowl.vault.economy.Economy;
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
}
