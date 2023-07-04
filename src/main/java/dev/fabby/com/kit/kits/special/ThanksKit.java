package dev.fabby.com.kit.kits.special;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ThanksKit implements IKit {

    @Override
    public String getName() {
        return "Thanks";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&4&lGratitude Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.thanks";
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&dAs a token of the Staff"),
                StringUtil.color("&dTeam's appreciation of"),
                StringUtil.color("&danyone boosting the Discord"),
                StringUtil.color("&dServer, we are handing out"),
                StringUtil.color("&da free kit to anyone who"),
                StringUtil.color("&dsupports us."),
                "",
                StringUtil.color("&71. (10x) Enchanted Golden Apple"),
                StringUtil.color("&72. (5x) Totem of Undying"),
                StringUtil.color("&73. (3x) Diamond Block"),
                StringUtil.color("&74. (3x) Emerald Block"),
                StringUtil.color("&75. (6x) Gold Block"),
                StringUtil.color("&76. (10x) Iron Block"),
                StringUtil.color("&77. (1x) Beacon"),
                StringUtil.color("&78. (1x) Nether Star"),
                StringUtil.color("&79. (3x) Shulker Box"),
                StringUtil.color("&710. (10x) End Crystal"),
                "",
                StringUtil.color("&7Cost: &e" + (getPrice() <= 0 ? "Free!" : "$" + getCooldodwn())),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.RED_TULIP, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        return new ItemStack[] {
                new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 10),
                new ItemStack(Material.TOTEM_OF_UNDYING, 5),
                new ItemStack(Material.DIAMOND_BLOCK, 3),
                new ItemStack(Material.EMERALD_BLOCK, 3),
                new ItemStack(Material.GOLD_BLOCK, 6),
                new ItemStack(Material.IRON_BLOCK, 10),
                new ItemStack(Material.BEACON, 1),
                new ItemStack(Material.NETHER_STAR, 1),
                new ItemStack(Material.SHULKER_BOX, 3),
                new ItemStack(Material.END_CRYSTAL, 10)
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
