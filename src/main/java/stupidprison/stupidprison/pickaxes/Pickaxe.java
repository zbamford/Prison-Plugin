package stupidprison.stupidprison.pickaxes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ipvp.canvas.Menu;

import java.util.ArrayList;
import java.util.List;

public abstract class Pickaxe {

    protected List<String> lore;
    protected int fortune;

    public Pickaxe() {
        lore = new ArrayList<>();
    }

    public abstract Menu createMenu();
    public ItemStack toItem() {
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DIG_SPEED, 100, true);
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, fortune, true);
        meta.setUnbreakable(true);
        meta.setLore(lore);
        return item;
    }
}
