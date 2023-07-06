package dev.fabby.com.kit.kits.food;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FoodPlus implements IKit {

    @Override
    public String getName() {
        return "FoodPlus";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&dFood&a+ &7Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.foodplus";
    }

    @Override
    public double getPrice() {
        return 1500;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (30x) Cooked Beef"),
                StringUtil.color("&72. (64x) Baked Potato"),
                StringUtil.color("&73. (30x) Apple"),
                StringUtil.color("&74. (64x) Bread"),
                StringUtil.color("&75. (15x) Cooked Salmon"),
                StringUtil.color("&76. (15x) Cooked Cod"),
                StringUtil.color("&77. (10x) Golden Carrots"),
                StringUtil.color("&78. (15x) Cooked Rabbit"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.GOLDEN_APPLE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        return new ItemStack[] {
                new ItemStack(Material.COOKED_BEEF, 30),
                new ItemStack(Material.BAKED_POTATO, 64),
                new ItemStack(Material.APPLE, 30),
                new ItemStack(Material.BREAD, 64),
                new ItemStack(Material.COOKED_SALMON, 15),
                new ItemStack(Material.COOKED_COD, 15),
                new ItemStack(Material.GOLDEN_CARROT, 10),
                new ItemStack(Material.COOKED_RABBIT, 15)
        };
    }

    @Override
    public long getCooldown() {
        return 20*60*60;
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
