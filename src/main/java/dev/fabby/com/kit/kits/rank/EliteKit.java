package dev.fabby.com.kit.kits.rank;

import dev.fabby.com.Core;
import dev.fabby.com.crates.key.KeyItemBuilder;
import dev.fabby.com.crates.key.KeyType;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EliteKit implements IKit {

    @Override
    public String getName() {
        return "Elite";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&d&lELITE Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.elite";
    }

    @Override
    public double getPrice() {
        return 15000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (1x) Diamond Helmet [&bUnbreaking III, Protection III, Mending&7]"),
                StringUtil.color("&72. (1x) Diamond Chestplate [&bUnbreaking III, Protection III, Mending&7]"),
                StringUtil.color("&73. (1x) Diamond Leggings [&bUnbreaking III, Protection III, Mending&7]"),
                StringUtil.color("&74. (1x) Diamond Boots [&bUnbreaking III, Protection III, Mending&7]"),
                StringUtil.color("&75. (1x) Diamond Sword"),
                StringUtil.color("  &7[&bSharpness IV, Knockback II, Unbreaking III, Sweeping Edge III&7]"),
                StringUtil.color("&76. (1x) Diamond Pickaxe"),
                StringUtil.color("  &7[&bEfficiency IV, Unbreaking II, Mending, Fortune II&7]"),
                StringUtil.color("&77. (1x) Diamond Axe"),
                StringUtil.color("  &7[&bEfficiency III, Unbreaking III, Sharpness III&7]"),
                StringUtil.color("&78. (1x) Diamond Shovel"),
                StringUtil.color("  &7[&bEfficiency III, Unbreaking III, Silk Touch&7]"),
                StringUtil.color("&79. (6x) Golden Apple"),
                StringUtil.color("&710. (64x) Cooked Beef"),
                StringUtil.color("&711. (1x) Enchanted Golden Apple"),
                StringUtil.color("&712. &7&n(1x) Diamond + (1x) Emerald Key"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.BLUE_DYE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

        Core.getCore().getKitManager().enchant(helmet, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.MENDING, 1);

        Core.getCore().getKitManager().enchant(chest, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(chest, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(chest, Enchantment.MENDING, 1);

        Core.getCore().getKitManager().enchant(legs, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(legs, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(legs, Enchantment.MENDING, 1);

        Core.getCore().getKitManager().enchant(boots, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(boots, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(boots, Enchantment.MENDING, 1);

        return new ItemStack[] {helmet, chest, legs, boots};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);

        Core.getCore().getKitManager().enchant(sword, Enchantment.DAMAGE_ALL, 4);
        Core.getCore().getKitManager().enchant(sword, Enchantment.KNOCKBACK, 2);
        Core.getCore().getKitManager().enchant(sword, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(sword, Enchantment.SWEEPING_EDGE, 3);

        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DIG_SPEED, 4);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.MENDING, 1);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.LOOT_BONUS_BLOCKS, 2);

        Core.getCore().getKitManager().enchant(axe, Enchantment.DIG_SPEED, 3);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DAMAGE_ALL, 3);

        Core.getCore().getKitManager().enchant(shovel, Enchantment.DIG_SPEED, 3);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.SILK_TOUCH, 1);

        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 6);
        ItemStack godApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);

        ItemStack diamond = KeyItemBuilder.getKey(KeyType.DIAMOND);
        ItemStack emerald = KeyItemBuilder.getKey(KeyType.EMERALD);

        return new ItemStack[] {sword, pickaxe, axe, shovel, goldenApple, godApple, steak, diamond, emerald};
    }

    @Override
    public long getCooldown() {
        return 20*60*60*5; //5 hours
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
        player.getInventory().addItem(getArmorContents());
        player.getInventory().addItem(getInventoryContents());
        return;
    }
}
