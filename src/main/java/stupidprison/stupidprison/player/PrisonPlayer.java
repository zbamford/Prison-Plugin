package stupidprison.stupidprison.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import stupidprison.stupidprison.pickaxes.Pickaxe;
import stupidprison.stupidprison.pickaxes.PickaxeStandardIssue;
import stupidprison.stupidprison.pickaxes.PickaxeSuperBasic;

import java.util.UUID;

public class PrisonPlayer {
    private UUID id;
    private Pickaxe pickaxe;

    public PrisonPlayer(Player player) {
        id = player.getUniqueId();
        pickaxe = new PickaxeSuperBasic();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(id);
    }

    public void setPickaxe(Pickaxe pickaxe) {
        this.pickaxe = pickaxe;
    }

    public Pickaxe getPickaxe() {
        return pickaxe;
    }
}
