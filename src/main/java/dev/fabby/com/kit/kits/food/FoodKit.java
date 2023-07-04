package dev.fabby.com.kit.kits.food;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FoodKit implements IKit {

    @Override
    public String getName() {
        return "Food";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&dFood Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.food";
    }

    @Override
    public double getPrice() {
        return 500;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (10x) Cooked Beef"),
                StringUtil.color("&72. (32x) Baked Potato"),
                StringUtil.color("&73. (15x) Apple"),
                StringUtil.color("&74. (30x) Bread"),
                StringUtil.color("&75. (10x) Cooked Salmon"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.APPLE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        return new ItemStack[] {
                new ItemStack(Material.COOKED_BEEF, 10),
                new ItemStack(Material.BAKED_POTATO, 32),
                new ItemStack(Material.APPLE, 15),
                new ItemStack(Material.BREAD, 30),
                new ItemStack(Material.COOKED_SALMON, 10)
        };
    }

    @Override
    public long getCooldodwn() {
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
