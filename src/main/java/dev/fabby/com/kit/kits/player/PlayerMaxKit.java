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

public class PlayerMaxKit implements IKit {

    @Override
    public String getName() {
        return "PlayerPlus";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&7Player&b++ &7Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.playermax";
    }

    @Override
    public double getPrice() {
        return 10000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (1x) Iron Sword [&bSharpness II&7]"),
                StringUtil.color("&72. (1x) Iron Axe [&bUnbreaking II, Fortune II&7]"),
                StringUtil.color("&73. (1x) Iron Pickaxe [&bUnbreaking II, Fortune II&7]"),
                StringUtil.color("&74. (64x) Cooked Beef"),
                StringUtil.color("&75. (5x) Golden Apple"),
                StringUtil.color("&77. (1x) Enchanted Golden Apple"),
                StringUtil.color("&76. (16x) XP Bottle"),
                StringUtil.color("&78. (1x) &b&nDiamond Armor Set"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.COAL_BLOCK, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {
                new ItemStack(Material.DIAMOND_HELMET),
                new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_LEGGINGS),
                new ItemStack(Material.DIAMOND_BOOTS),
        };
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemStack pick = new ItemStack(Material.IRON_PICKAXE);
        ItemStack axe = new ItemStack(Material.IRON_AXE);

        Core.getCore().getKitManager().enchant(sword, Enchantment.DAMAGE_ALL, 2);
        Core.getCore().getKitManager().enchant(pick, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(pick, Enchantment.LOOT_BONUS_BLOCKS, 2);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(axe, Enchantment.LOOT_BONUS_BLOCKS, 2);

        return new ItemStack[] {
                sword,
                pick,
                axe,
                new ItemStack(Material.COOKED_BEEF, 64),
                new ItemStack(Material.GOLDEN_APPLE, 5),
                new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 16)
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
        player.getInventory().addItem(getArmorContents());
        return;
    }
}
