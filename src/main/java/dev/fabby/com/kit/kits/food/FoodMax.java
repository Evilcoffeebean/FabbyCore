package dev.fabby.com.kit.kits.food;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FoodMax implements IKit {

    @Override
    public String getName() {
        return "FoodMax";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&dFood&b++ &dKit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.foodmax";
    }

    @Override
    public double getPrice() {
        return 3000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (30x) Cooked Beef"),
                StringUtil.color("&72. (64x) Baked Potato"),
                StringUtil.color("&73. (30x) Apple"),
                StringUtil.color("&74. (64x) Bread"),
                StringUtil.color("&75. (30x) Cooked Salmon"),
                StringUtil.color("&76. (30x) Cooked Cod"),
                StringUtil.color("&77. (20x) Golden Carrots"),
                StringUtil.color("&78. (30x) Cooked Rabbit"),
                StringUtil.color("&79. (5x) &aMagic Cake"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.ENCHANTED_GOLDEN_APPLE, getDisplayName(), getDescription()).getProduct();
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
                new ItemStack(Material.COOKED_SALMON, 30),
                new ItemStack(Material.COOKED_COD, 30),
                new ItemStack(Material.GOLDEN_CARROT, 20),
                new ItemStack(Material.COOKED_RABBIT, 30),
                new ItemUtil(Material.RED_CANDLE_CAKE, "&aMagic Cake", "&dCompletely satiates your", "&dhunger when eaten (Right-Click)").getProduct(5)
        };
    }

    @Override
    public long getCooldodwn() {
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
