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
import stupidprison.stupidprison.util.DelayedTask;

import net.milkbowl.vault.economy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL;
import static stupidprison.stupidprison.Globals.econ;

public class PlayerHandler implements Listener {

    private Plugin plugin;
    private Map<Material, Double> sellPrices;
    public PlayerHandler(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    private void setupSellPrices() {
        sellPrices = new HashMap<>();
        sellPrices.put(Material.STONE, Math.pow(3.0, 0.0));
        sellPrices.put(Material.COAL_ORE, Math.pow(3.0, 0.33333));
        sellPrices.put(Material.DEEPSLATE_COAL_ORE, Math.pow(3.0, 0.66666));
        sellPrices.put(Material.COAL_BLOCK, Math.pow(3.0, 1.0));
        sellPrices.put(Material.COPPER_ORE, Math.pow(3.0, 1.33333));
        sellPrices.put(Material.DEEPSLATE_COAL_ORE, Math.pow(3.0, 1.66666));
        sellPrices.put(Material.COPPER_BLOCK, Math.pow(3.0, 2.0));
        sellPrices.put(Material.IRON_ORE, Math.pow(3.0, 2.33333));
        sellPrices.put(Material.DEEPSLATE_IRON_ORE, Math.pow(3.0, 2.66666));
        sellPrices.put(Material.IRON_BLOCK, Math.pow(3.0, 3.0));
        sellPrices.put(Material.REDSTONE_ORE, Math.pow(3.0, 3.33333));
        sellPrices.put(Material.DEEPSLATE_REDSTONE_ORE, Math.pow(3.0, 3.66666));
        sellPrices.put(Material.REDSTONE_BLOCK, Math.pow(3.0, 4.0));
        sellPrices.put(Material.LAPIS_ORE, Math.pow(3.0, 4.33333));
        sellPrices.put(Material.DEEPSLATE_LAPIS_ORE, Math.pow(3.0, 4.66666));
        sellPrices.put(Material.LAPIS_BLOCK, Math.pow(3.0, 5.0));
        sellPrices.put(Material.GOLD_ORE, Math.pow(3.0, 5.33333));
        sellPrices.put(Material.DEEPSLATE_GOLD_ORE, Math.pow(3.0, 5.66666));
        sellPrices.put(Material.GOLD_BLOCK, Math.pow(3.0, 6.0));
        sellPrices.put(Material.DIAMOND_ORE, Math.pow(3.0, 6.33333));
        sellPrices.put(Material.DEEPSLATE_DIAMOND_ORE, Math.pow(3.0, 6.66666));
        sellPrices.put(Material.DIAMOND_BLOCK, Math.pow(3.0, 7.0));
        sellPrices.put(Material.EMERALD_ORE, Math.pow(3.0, 7.33333));
        sellPrices.put(Material.DEEPSLATE_EMERALD_ORE, Math.pow(3.0, 7.66666));
        sellPrices.put(Material.EMERALD_BLOCK, Math.pow(3.0, 8.0));
        sellPrices.put(Material.ANCIENT_DEBRIS, Math.pow(3.0, 8.33333));
        sellPrices.put(Material.NETHERITE_BLOCK, Math.pow(3.0, 8.66666));
        sellPrices.put(Material.BEACON, Math.pow(3.0, 9.0));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

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
        switch (block.getType()) {

            case DIAMOND_BLOCK:
                econ.depositPlayer(player, 1000 * (fortune + 1));
                break;
            default:
                break;
        }
    }
}
