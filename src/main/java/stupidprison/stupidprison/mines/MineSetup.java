package stupidprison.stupidprison.mines;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import stupidprison.stupidprison.Globals;

import static org.bukkit.Bukkit.getServer;

public class MineSetup {
    public MineSetup() {
        MineBuilder aBuilder = new MineBuilder();
        aBuilder.addMaterial(Material.STONE, 30);
        aBuilder.addMaterial(Material.COAL_ORE, 30);
        aBuilder.addMaterial(Material.IRON_ORE, 40);
        aBuilder.setBoundary(66, 4, -57, 49, 35, -77);
        aBuilder.setWorld(getServer().getWorld("world"));
        Mine a = aBuilder.getMine();

        MineBuilder bBuilder = new MineBuilder();
        bBuilder.addMaterial(Material.DIAMOND_BLOCK, 30);
        bBuilder.addMaterial(Material.DIAMOND_ORE, 30);
        bBuilder.addMaterial(Material.NETHERITE_BLOCK, 40);
        bBuilder.setBoundary(36, 4, -57, 19, 35, -77);
        bBuilder.setWorld(getServer().getWorld("world"));
        Mine b = bBuilder.getMine();

        MineManager manager = new MineManager();
        manager.registerMine(a);
        manager.registerMine(b);

        Bukkit.getServer().getScheduler().runTaskTimer(Globals.plugin, () -> {
            manager.refillMines();
        }, 200, 500);

        Bukkit.getServer().getScheduler().runTaskTimer(Globals.plugin, () -> {
            manager.broadcastTimeToRefill(5);
        }, 100, 500);
    }
}
