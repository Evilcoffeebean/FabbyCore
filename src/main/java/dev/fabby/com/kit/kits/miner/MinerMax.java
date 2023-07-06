package dev.fabby.com.kit.kits.miner;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MinerMax implements IKit {

    @Override
    public String getName() {
        return "MinerMax";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&9Miner&b++ &bKit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.minermax";
    }

    @Override
    public double getPrice() {
        return 5200;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&7* (1x) Supreme Miner Pickaxe"),
                StringUtil.color("  &b[Unbreaking III]"),
                StringUtil.color("  &b[Fortune III]"),
                StringUtil.color("  &b[Efficiency V]"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.NETHERITE_PICKAXE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack pick = new ItemUtil(Material.IRON_PICKAXE, "&9Supreme Miner Pickaxe").getProduct();
        Core.getCore().getKitManager().enchant(pick, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(pick, Enchantment.LOOT_BONUS_BLOCKS, 3);
        Core.getCore().getKitManager().enchant(pick, Enchantment.DIG_SPEED, 5);

        return new ItemStack[] {pick};
    }

    @Override
    public long getCooldown() {
        return 20*60*60; //1 hour
    }

    @Override
    public void execute(Player player) {
        if (!player.hasPermission(getPermission())) {
            player.closeInventory();
            player.playSound(player.getLocation(), error, 1f, 1f);
            player.sendMessage(ChatColor.GREEN + "You don't have permission to access this kit.");
            return;
        }

        double bal = Core.getCore().getEconomy().getBalance(player);
        if (bal < getPrice()) {
            player.closeInventory();
            player.sendMessage(ChatColor.GREEN + getName() + " costs $" + getPrice() + " to purchase.");
            player.playSound(player.getLocation(), error, 1f, 1f);
            return;
        }

        Core.getCore().getEconomy().withdrawPlayer(player, getPrice());
        player.closeInventory();
        player.playSound(player.getLocation(), okay, 1f, 1f);
        player.getInventory().addItem(getInventoryContents());
        return;
    }
}
