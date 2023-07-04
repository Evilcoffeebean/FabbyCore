package dev.fabby.com.crates.menu;

import dev.fabby.com.Core;
import dev.fabby.com.crates.CrateInventory;
import dev.fabby.com.crates.ICrate;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ShuffleMenu {

    private final ItemStack[] contents = {
            new ItemStack(Material.DIAMOND),
            new ItemStack(Material.DIAMOND_BLOCK),
            new ItemStack(Material.DIAMOND_SWORD),
            new ItemStack(Material.DIAMOND_AXE),
            new ItemStack(Material.DIAMOND_PICKAXE),
            new ItemStack(Material.EMERALD),
            new ItemStack(Material.EMERALD_BLOCK),
            new ItemStack(Material.NETHER_STAR),
            new ItemStack(Material.NETHERITE_SCRAP),
            new ItemStack(Material.NETHERITE_SCRAP, 3),
            new ItemStack(Material.NETHERITE_SCRAP, 5),
            new ItemStack(Material.NETHERITE_SCRAP, 10),
            new ItemStack(Material.NETHERITE_INGOT),
            new ItemStack(Material.NETHERITE_SWORD),
            new ItemStack(Material.NETHERITE_AXE),
            new ItemStack(Material.NETHERITE_PICKAXE),
            new ItemStack(Material.GOLDEN_APPLE),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
            new ItemStack(Material.SPECTRAL_ARROW, 16),
            new ItemStack(Material.ARROW, 16),
            new ItemStack(Material.OAK_LOG, 64),
            new ItemStack(Material.GOLD_INGOT, 32),
            new ItemStack(Material.IRON_INGOT, 32),
            new ItemStack(Material.GOLDEN_CARROT, 16),
            new ItemStack(Material.DIAMOND_BOOTS),
            new ItemStack(Material.DIAMOND_CHESTPLATE),
            new ItemStack(Material.DIAMOND_LEGGINGS),
            new ItemStack(Material.DIAMOND_HELMET),
            new ItemStack(Material.GOLDEN_HELMET),
            new ItemStack(Material.GOLDEN_CHESTPLATE),
            new ItemStack(Material.GOLDEN_LEGGINGS),
            new ItemStack(Material.GOLDEN_BOOTS),
            new ItemStack(Material.NETHERITE_HELMET),
            new ItemStack(Material.NETHERITE_CHESTPLATE),
            new ItemStack(Material.NETHERITE_LEGGINGS),
            new ItemStack(Material.NETHERITE_BOOTS),
            new ItemUtil(Material.NETHERITE_HELMET, "&bMystery Netherite Helmet").getProductWithGlow(),
            new ItemUtil(Material.NETHERITE_CHESTPLATE, "&bMystery Netherite Chestplate").getProductWithGlow(),
            new ItemUtil(Material.NETHERITE_LEGGINGS, "&bMystery Netherite Leggings").getProductWithGlow(),
            new ItemUtil(Material.NETHERITE_BOOTS, "&bMystery Netherite Boots").getProductWithGlow(),
            new ItemUtil(Material.DIAMOND_HELMET, "&bMystery Diamond Helmet").getProductWithGlow(),
            new ItemUtil(Material.DIAMOND_CHESTPLATE, "&bMystery Diamond Chestplate").getProductWithGlow(),
            new ItemUtil(Material.DIAMOND_LEGGINGS, "&bMystery Diamond Leggings").getProductWithGlow(),
            new ItemUtil(Material.DIAMOND_BOOTS, "&bMystery Diamond Boots").getProductWithGlow(),
            new ItemUtil(Material.GOLDEN_HELMET, "&bMystery Gold Helmet").getProductWithGlow(),
            new ItemUtil(Material.GOLDEN_CHESTPLATE, "&bMystery Gold Chestplate").getProductWithGlow(),
            new ItemUtil(Material.GOLDEN_LEGGINGS, "&bMystery Gold Leggings").getProductWithGlow(),
            new ItemUtil(Material.GOLDEN_BOOTS, "&bMystery Gold Boots").getProductWithGlow(),
            new ItemUtil(Material.IRON_HELMET, "&bMystery Iron Helmet").getProductWithGlow(),
            new ItemUtil(Material.IRON_CHESTPLATE, "&bMystery Iron Chestplate").getProductWithGlow(),
            new ItemUtil(Material.IRON_LEGGINGS, "&bMystery Iron Leggings").getProductWithGlow(),
            new ItemUtil(Material.IRON_BOOTS, "&bMystery Iron Boots").getProductWithGlow(),
            new ItemUtil(Material.PAPER, "&c&l(?) &d&nMystery Rank Voucher &c&l(?)").getProductWithGlow(),
            new ItemUtil(Material.PAPER, "&c&l(?) &d&nMystery Money Voucher &c&l(?)").getProductWithGlow(),
            new ItemUtil(Material.PAPER, "&c&l(?) &d&nMystery Cosmetics &c&l(?)").getProductWithGlow(),
            new ItemUtil(Material.DIAMOND_PICKAXE, "&bMystery Diamond Pickaxe").getProductWithGlow(),
            new ItemUtil(Material.NETHERITE_PICKAXE, "&bMystery Netherite Pickaxe").getProductWithGlow(),
            new ItemUtil(Material.GOLDEN_PICKAXE, "&bMystery Gold Pickaxe").getProductWithGlow(),
            new ItemUtil(Material.IRON_PICKAXE, "&bMystery Iron Pickaxe").getProductWithGlow(),
            new ItemUtil(Material.DIAMOND_AXE, "&bMystery Diamond Axe").getProductWithGlow(),
            new ItemUtil(Material.NETHERITE_AXE, "&bMystery Netherite Axe").getProductWithGlow(),
            new ItemUtil(Material.GOLDEN_AXE, "&bMystery Gold Axe").getProductWithGlow(),
            new ItemUtil(Material.IRON_AXE, "&bMystery Iron Axe").getProductWithGlow(),
            new ItemUtil(Material.DIAMOND_SWORD, "&bMystery Diamond Sword").getProductWithGlow(),
            new ItemUtil(Material.NETHERITE_SWORD, "&bMystery Netherite Sword").getProductWithGlow(),
            new ItemUtil(Material.GOLDEN_SWORD, "&bMystery Gold Sword").getProductWithGlow(),
            new ItemUtil(Material.IRON_SWORD, "&bMystery Iron Sword").getProductWithGlow(),
            new ItemUtil(Material.HEART_OF_THE_SEA, "&4&lSPECIAL MYSTERY PRIZE").getProductWithGlow()
    };

    public ShuffleMenu(final Player player, final ICrate crate) {
        new CrateInventory(27, contents, StringUtil.color("&c&lShuffling prize..."), Core.getCore(), crate).spin(10, player);
    }
}
