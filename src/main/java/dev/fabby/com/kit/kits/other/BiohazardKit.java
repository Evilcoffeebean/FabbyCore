package dev.fabby.com.kit.kits.other;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class BiohazardKit implements IKit {

    @Override
    public String getName() {
        return "Biohazard";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&5Biohazard Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.biohazard";
    }

    @Override
    public double getPrice() {
        return 8000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (5x) Splash potion of harming"),
                StringUtil.color("&72. (5x) Splash potion of weakness"),
                StringUtil.color("&73. (5x) Splash potion of decay"),
                StringUtil.color("&74. (5x) Splash potion of poison"),
                StringUtil.color("&75. (1x) &7&nImmunity potion"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.BREWING_STAND, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack immunity = new ItemUtil(Material.HONEY_BOTTLE, "&6Immunity Potion").getProductWithGlow();
        return new ItemStack[] {
                Core.getCore().getKitManager().potion(PotionEffectType.HARM, 1, 10, 5),
                Core.getCore().getKitManager().potion(PotionEffectType.WEAKNESS, 1, 10, 5),
                Core.getCore().getKitManager().potion(PotionEffectType.WITHER, 1, 10, 5),
                Core.getCore().getKitManager().potion(PotionEffectType.POISON, 1, 10, 5),
                immunity
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
