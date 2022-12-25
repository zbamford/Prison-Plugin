package stupidprison.stupidprison.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import stupidprison.stupidprison.Globals;
import stupidprison.stupidprison.util.DelayedTask;

import net.milkbowl.vault.economy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL;
import static stupidprison.stupidprison.Globals.econ;
import static stupidprison.stupidprison.Globals.materials;

public class PlayerHandler implements Listener {

    private Plugin plugin;
    private Map<Material, Double> sellPrices;
    public PlayerHandler(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        setupSellPrices();
    }

    private void setupSellPrices() {
        sellPrices = new HashMap<>();
        for (int i = 0; i < materials.size(); i++) {
            sellPrices.put(materials.get(i), Math.pow(3.0, (double) i / 3));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            ItemStack item;
            Inventory inv;
            ItemMeta meta;

            // Sword
            item = new ItemStack(Material.NETHERITE_SWORD, 1);
            inv = player.getInventory();
            meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 20, true);
            meta.setUnbreakable(true);
            meta.setDisplayName("Standard Issue Sword");
            item.setItemMeta(meta);
            inv.addItem(item);

            // Bow
            item = new ItemStack(Material.BOW, 1);
            inv = player.getInventory();
            meta = item.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 20, true);
            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            meta.setUnbreakable(true);
            meta.setDisplayName("Standard Issue Bow");
            item.setItemMeta(meta);
            inv.addItem(item);

            // Pickaxe
            item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
            inv = player.getInventory();
            meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, 100, true);
            meta.setUnbreakable(true);
            List<String> lore = new ArrayList<>();
            lore.add("A basic pickaxe issued to new prisoners.");
            lore.add("Can't be upgraded or leveled up.");
            meta.setLore(lore);
            meta.setDisplayName("Standard Issue Pickaxe");
            item.setItemMeta(meta);
            inv.addItem(item);

            // Helmet
            item = new ItemStack(Material.NETHERITE_HELMET, 1);
            inv = player.getInventory();
            meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.setUnbreakable(true);
            meta.setDisplayName("Standard Issue Helmet");
            item.setItemMeta(meta);
            inv.addItem(item);

            // Chestplate
            item = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
            inv = player.getInventory();
            meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.setUnbreakable(true);
            meta.setDisplayName("Standard Issue Chestplate");
            item.setItemMeta(meta);
            inv.addItem(item);

            // Leggings
            item = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
            inv = player.getInventory();
            meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.setUnbreakable(true);
            meta.setDisplayName("Standard Issue Leggings");
            item.setItemMeta(meta);
            inv.addItem(item);

            // Boots
            item = new ItemStack(Material.NETHERITE_BOOTS, 1);
            inv = player.getInventory();
            meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.setUnbreakable(true);
            meta.setDisplayName("Standard Issue Boots");
            item.setItemMeta(meta);
            inv.addItem(item);
        } else {
            player.sendMessage("Welcome to the Insanity Prison Server!!!");
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player && event.getCause() == FALL)) {
            return;
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setDropItems(false);
        Player player = event.getPlayer();
        int fortune = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);
        Block block = event.getBlock();
        econ.depositPlayer(player, sellPrices.get(block.getType()) * (fortune + 1));
    }
}
