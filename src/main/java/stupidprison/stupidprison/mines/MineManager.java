package stupidprison.stupidprison.mines;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class MineManager {
    List<Mine> mines;

    public MineManager() {
        mines = new ArrayList<>();
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
