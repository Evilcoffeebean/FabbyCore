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

public class ImmortalKit implements IKit {

    @Override
    public String getName() {
        return "Immortal";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&c&lIMMORTAL Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.immortal";
    }

    @Override
    public double getPrice() {
        return 15000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&71. (1x) Netherite Helmet"),
                StringUtil.color("  &7[&bUnbreaking III, Protection IV, Mending&7]"),
                StringUtil.color("  &7[&bRespiration III, Aqua Affinity&7]"),
                StringUtil.color("&72. (1x) Netherite Chestplate"),
                StringUtil.color("  &7[&bUnbreaking III, Protection IV, Mending&7]"),
                StringUtil.color("&73. (1x) Netherite Leggings"),
                StringUtil.color("  &7[&bUnbreaking III, Protection IV&7]"),
                StringUtil.color("  &7[&bMending, Swift Sneak III&7]"),
                StringUtil.color("&74. (1x) Netherite Boots"),
                StringUtil.color("  &7[&bUnbreaking III, Protection IV, Mending&7]"),
                StringUtil.color("  &7[&bDepth Strider III, Soul Speed III, Feather Falling III&7]"),
                StringUtil.color("&75. (1x) Netherite Sword"),
                StringUtil.color("  &7[&bSharpness V, Knockback II, Unbreaking III&7]"),
                StringUtil.color("  &7[&bSweeping Edge III, Mending&7]"),
                StringUtil.color("&76. (1x) Netherite Pickaxe"),
                StringUtil.color("  &7[&bEfficiency V, Unbreaking III&7]"),
                StringUtil.color("  &7[&bMending, Fortune III&7]"),
                StringUtil.color("&77. (1x) Netherite Axe"),
                StringUtil.color("  &7[&bEfficiency V, Unbreaking III&7]"),
                StringUtil.color("  &7[&bSharpness V, Mending&7]"),
                StringUtil.color("&78. (1x) Netherite Shovel"),
                StringUtil.color("  &7[&bEfficiency V, Unbreaking III&7]"),
                StringUtil.color("  &7[&bSilk Touch, Mending&7]"),
                StringUtil.color("&79. (32x) Golden Apple"),
                StringUtil.color("&710. (64x) Cooked Beef"),
                StringUtil.color("&711. (12x) Enchanted Golden Apple"),
                StringUtil.color("&712. &7&n(1x) EVERY CRATE KEY"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.RED_DYE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
        ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);

        Core.getCore().getKitManager().enchant(helmet, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.MENDING, 1);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.OXYGEN, 3);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.WATER_WORKER, 1);

        Core.getCore().getKitManager().enchant(chest, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(chest, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        Core.getCore().getKitManager().enchant(chest, Enchantment.MENDING, 1);

        Core.getCore().getKitManager().enchant(legs, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(legs, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        Core.getCore().getKitManager().enchant(legs, Enchantment.MENDING, 1);
        Core.getCore().getKitManager().enchant(legs, Enchantment.SWIFT_SNEAK, 3);

        Core.getCore().getKitManager().enchant(boots, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(boots, Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        Core.getCore().getKitManager().enchant(boots, Enchantment.MENDING, 1);
        Core.getCore().getKitManager().enchant(boots, Enchantment.DEPTH_STRIDER, 3);
        Core.getCore().getKitManager().enchant(boots, Enchantment.SOUL_SPEED, 3);
        Core.getCore().getKitManager().enchant(boots, Enchantment.PROTECTION_FALL, 3);

        return new ItemStack[] {helmet, chest, legs, boots};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
        ItemStack shovel = new ItemStack(Material.NETHERITE_SHOVEL);

        Core.getCore().getKitManager().enchant(sword, Enchantment.DAMAGE_ALL, 5);
        Core.getCore().getKitManager().enchant(sword, Enchantment.KNOCKBACK, 2);
        Core.getCore().getKitManager().enchant(sword, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(sword, Enchantment.SWEEPING_EDGE, 3);
        Core.getCore().getKitManager().enchant(sword, Enchantment.MENDING, 1);

        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DIG_SPEED, 5);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.MENDING, 1);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.LOOT_BONUS_BLOCKS, 3);

        Core.getCore().getKitManager().enchant(axe, Enchantment.DIG_SPEED, 5);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DAMAGE_ALL, 5);
        Core.getCore().getKitManager().enchant(axe, Enchantment.MENDING, 1);

        Core.getCore().getKitManager().enchant(shovel, Enchantment.DIG_SPEED, 5);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.SILK_TOUCH, 1);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.MENDING, 1);

        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 32);
        ItemStack godApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 12);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);

        ItemStack coal = KeyItemBuilder.getKey(KeyType.COAL);
        ItemStack redstone = KeyItemBuilder.getKey(KeyType.REDSTONE);
        ItemStack lapis = KeyItemBuilder.getKey(KeyType.LAPIS);
        ItemStack iron = KeyItemBuilder.getKey(KeyType.IRON);
        ItemStack gold = KeyItemBuilder.getKey(KeyType.GOLD);
        ItemStack emerald = KeyItemBuilder.getKey(KeyType.EMERALD);
        ItemStack diamond = KeyItemBuilder.getKey(KeyType.DIAMOND);
        ItemStack netherite = KeyItemBuilder.getKey(KeyType.NETHERITE);

        return new ItemStack[] {sword, pickaxe, axe, shovel, goldenApple, godApple, steak, coal, redstone, lapis, iron, gold, emerald, diamond, netherite};
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
