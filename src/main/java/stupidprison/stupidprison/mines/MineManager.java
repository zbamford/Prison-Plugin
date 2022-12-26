package stupidprison.stupidprison.mines;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static stupidprison.stupidprison.Globals.plugin;

public class MineManager {
    Map<String, Mine> mines;
    int offset;
    int refillTimer;

    public MineManager(int refillTimer) {
        mines = new HashMap<>();
        offset = 0;
        this.refillTimer = refillTimer;
    }

    public void registerMine(Mine mine, String id) {
        mines.put(id, mine);
        Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            refillMine(id);
        }, 200 + (offset * 2), refillTimer * 20);

        Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            broadcastTimeToRefill(0, id);
        }, 200 + (offset * 2), refillTimer * 20);

        offset++;
    }

    public void broadcastTimeToRefill(int time, String id) {
        if (time > 60) {
            Bukkit.broadcastMessage("Mine " + id + " resetting in " + (time / 60) + " minutes!");
        } else if (time == 0) {
            Bukkit.broadcastMessage("Mine " + id + " resetting!");
        } else {
            Bukkit.broadcastMessage("Mine " + id + " resetting in " + time + " seconds!");
        }
    }

    public void refillMine(String id) {
        mines.get(id).refill();
    }

    public void refillAll() {
        for (Map.Entry mine : mines.entrySet()) {
            ((Mine) mine.getValue()).refill();
        }
    }
}
