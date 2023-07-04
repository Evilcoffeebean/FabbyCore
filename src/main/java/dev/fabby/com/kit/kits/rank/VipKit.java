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

public class VipKit implements IKit {

    @Override
    public String getName() {
        return "Vip";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&3&lVIP Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.vip";
    }

    @Override
    public double getPrice() {
        return 5000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (1x) Iron Helmet [&bUnbreaking II, Protection II&7]"),
                StringUtil.color("&72. (1x) Iron Chestplate [&bUnbreaking II, Protection II&7]"),
                StringUtil.color("&73. (1x) Iron Leggings [&bUnbreaking II, Protection II&7]"),
                StringUtil.color("&74. (1x) Iron Boots [&bUnbreaking II, Protection II&7]"),
                StringUtil.color("&75. (1x) Iron Sword [&bSharpness II&7]"),
                StringUtil.color("&76. (1x) Iron Pickaxe [&bEfficiency II, Unbreaking II&7]"),
                StringUtil.color("&77. (1x) Iron Shovel [&bUnbreaking II, Unbreaking II&7]"),
                StringUtil.color("&78. (1x) Iron Axe [&bUnbreaking II, Unbreaking II&7]"),
                StringUtil.color("&79. (2x) Golden Apple"),
                StringUtil.color("&710. (32x) Cooked Beef"),
                StringUtil.color("&711. (3x) &7&nIron Key"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.GREEN_DYE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);

        Core.getCore().getKitManager().enchant(helmet, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        Core.getCore().getKitManager().enchant(chest, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(chest, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        Core.getCore().getKitManager().enchant(legs, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(legs, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        Core.getCore().getKitManager().enchant(boots, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(boots, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        return new ItemStack[] {helmet, chest, legs, boots};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemStack axe = new ItemStack(Material.IRON_AXE);
        ItemStack shovel = new ItemStack(Material.IRON_SHOVEL);

        Core.getCore().getKitManager().enchant(sword, Enchantment.DAMAGE_ALL, 2);

        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DIG_SPEED, 2);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DURABILITY, 2);

        Core.getCore().getKitManager().enchant(axe, Enchantment.DIG_SPEED, 2);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DURABILITY, 2);

        Core.getCore().getKitManager().enchant(shovel, Enchantment.DIG_SPEED, 2);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.DURABILITY, 2);

        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 32);
        ItemStack key = KeyItemBuilder.getKey(KeyType.IRON);

        return new ItemStack[] {sword, pickaxe, axe, shovel, goldenApple, steak, key, key, key};
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
        player.getInventory().addItem(getArmorContents());
        player.getInventory().addItem(getInventoryContents());
        return;
    }
}
