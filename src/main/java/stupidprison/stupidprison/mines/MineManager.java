package stupidprison.stupidprison.mines;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

import static stupidprison.stupidprison.Globals.plugin;

public class MineManager {
    List<Mine> mines;

    public MineManager(int refillTimer) {
        mines = new ArrayList<>();

        Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            refillMines();
        }, 200, refillTimer * 20);

        Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            broadcastTimeToRefill(5);
        }, 100, refillTimer * 20);
    }

    public void registerMine(Mine mine) {
        mines.add(mine);
    }

    public void broadcastTimeToRefill(int time) {
        if (time > 60) {
            Bukkit.broadcastMessage("Mines resetting in " + (time / 60) + " minutes!");
        } else {
            Bukkit.broadcastMessage("Mines resetting in " + time + " seconds!");
        }
    }

    public void refillMines() {
        for (Mine mine : mines) {
            mine.refill();
        }
    }
}
