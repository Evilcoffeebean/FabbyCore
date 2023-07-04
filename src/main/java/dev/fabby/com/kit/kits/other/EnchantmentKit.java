package dev.fabby.com.kit.kits.other;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class EnchantmentKit implements IKit {

    @Override
    public String getName() {
        return "Enchantment";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&2Enchantment Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.enchantment";
    }

    @Override
    public double getPrice() {
        return 15000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&7* (3x) &7&nEnchanted Book Bundle"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.ENCHANTING_TABLE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        return new ItemStack[] {
                Core.getCore().getKitManager().book(Enchantment.DIG_SPEED, Enchantment.DIG_SPEED.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ALL.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.DAMAGE_UNDEAD, Enchantment.DAMAGE_UNDEAD.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.DAMAGE_ARTHROPODS, Enchantment.DAMAGE_ARTHROPODS.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.ARROW_DAMAGE, Enchantment.ARROW_DAMAGE.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.MENDING, Enchantment.MENDING.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.LOOT_BONUS_BLOCKS, Enchantment.LOOT_BONUS_BLOCKS.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.LOOT_BONUS_MOBS, Enchantment.LOOT_BONUS_MOBS.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.ARROW_INFINITE, Enchantment.ARROW_INFINITE.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_KNOCKBACK.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.CHANNELING, Enchantment.CHANNELING.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.DEPTH_STRIDER, Enchantment.DEPTH_STRIDER.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.FIRE_ASPECT, Enchantment.FIRE_ASPECT.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.KNOCKBACK, Enchantment.KNOCKBACK.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.MULTISHOT, Enchantment.MULTISHOT.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.THORNS, Enchantment.THORNS.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.LOYALTY, Enchantment.LOYALTY.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.IMPALING, Enchantment.IMPALING.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_ENVIRONMENTAL.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_EXPLOSIONS.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_FALL.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_FIRE.getMaxLevel(), 1),
                Core.getCore().getKitManager().book(Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_PROJECTILE.getMaxLevel(), 1)
        };
    }

    @Override
    public long getCooldodwn() {
        return 20*60*60; //1 hour
    }

    public ItemStack random() {
        Random r = new Random();
        int index = r.nextInt(getInventoryContents().length);
        return getInventoryContents()[index];
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
        player.getInventory().addItem(random());
        player.getInventory().addItem(random());
        player.getInventory().addItem(random());
        return;
    }
}
