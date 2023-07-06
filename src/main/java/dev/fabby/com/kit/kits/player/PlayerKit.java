package dev.fabby.com.kit.kits.player;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerKit implements IKit {

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&7Player Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.player";
    }

    @Override
    public double getPrice() {
        return 1500;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (1x) Iron Sword"),
                StringUtil.color("&72. (1x) Iron Axe"),
                StringUtil.color("&73. (1x) Iron Pickaxe [&bUnbreaking I&7]"),
                StringUtil.color("&74. (32x) Cooked Beef"),
                StringUtil.color("&75. (16x) Torch"),
                StringUtil.color("&76. (64x) Cobblestone"),
                StringUtil.color("&77. (32x) Oak Log"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.CHARCOAL, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack pick = new ItemStack(Material.IRON_PICKAXE);
        Core.getCore().getKitManager().enchant(pick, Enchantment.DURABILITY, 1);

        return new ItemStack[] {
                new ItemStack(Material.IRON_SWORD),
                new ItemStack(Material.IRON_AXE),
                pick,
                new ItemStack(Material.COOKED_BEEF, 32),
                new ItemStack(Material.TORCH, 16),
                new ItemStack(Material.COBBLESTONE, 64),
                new ItemStack(Material.OAK_LOG, 32)
        };
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
