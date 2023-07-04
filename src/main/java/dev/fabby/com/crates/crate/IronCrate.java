package dev.fabby.com.crates.crate;

import dev.fabby.com.Core;
import dev.fabby.com.crates.ICrate;
import dev.fabby.com.crates.key.KeyItemBuilder;
import dev.fabby.com.crates.key.KeyType;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IronCrate implements ICrate {

    @Override
    public String getName() {
        return "Iron";
    }

    @Override
    public KeyType getKeyRequired() {
        return KeyType.IRON;
    }

    @Override
    public KeyType getKeyReward() {
        return KeyType.GOLD;
    }

    @Override
    public ItemStack[] getItemRewards() {
        return new ItemStack[] {
                new ItemStack(Material.IRON_BLOCK, 16),
                new ItemStack(Material.GOLD_BLOCK, 8),
                new ItemStack(Material.DIAMOND_BLOCK, 4),
        };
    }

    @Override
    public Integer[] getMoneyVouchers() {
        return new Integer[] {
                15000,
                25000,
                50000
        };
    }

    @Override
    public Integer[] getDirectPayouts() {
        return new Integer[] {};
    }

    @Override
    public String getRankVoucher() {
        return "mvp";
    }

    private ItemStack getRandomVipItem() {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 6);
        ItemStack godApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack diamond = KeyItemBuilder.getKey(KeyType.DIAMOND);
        ItemStack emerald = KeyItemBuilder.getKey(KeyType.EMERALD);

        Core.getCore().getKitManager().enchant(helmet, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Core.getCore().getKitManager().enchant(chest, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(chest, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Core.getCore().getKitManager().enchant(legs, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(legs, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Core.getCore().getKitManager().enchant(boots, Enchantment.DURABILITY, 2);
        Core.getCore().getKitManager().enchant(boots, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
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

        ItemStack[] items = {helmet, chest, legs, boots, sword, pickaxe, axe, shovel, goldenApple, godApple, steak, diamond, emerald};
        int index = random.nextInt(items.length);

        return items[index];
    }

    private ItemStack getRandomMvpItem() {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack axe = new ItemStack(Material.IRON_AXE);
        ItemStack shovel = new ItemStack(Material.IRON_SHOVEL);
        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 6);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack key = KeyItemBuilder.getKey(KeyType.GOLD);

        Core.getCore().getKitManager().enchant(helmet, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(helmet, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(chest, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(chest, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(legs, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(legs, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(boots, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(boots, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        Core.getCore().getKitManager().enchant(sword, Enchantment.DAMAGE_ALL, 3);
        Core.getCore().getKitManager().enchant(sword, Enchantment.KNOCKBACK, 1);
        Core.getCore().getKitManager().enchant(sword, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DIG_SPEED, 4);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(pickaxe, Enchantment.LOOT_BONUS_BLOCKS, 2);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DIG_SPEED, 3);
        Core.getCore().getKitManager().enchant(axe, Enchantment.DURABILITY, 3);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.DIG_SPEED, 3);
        Core.getCore().getKitManager().enchant(shovel, Enchantment.DURABILITY, 3);

        ItemStack[] items = {helmet, chest, legs, boots, sword, pickaxe, axe, shovel, goldenApple, steak, key};
        int index = random.nextInt(items.length);

        return items[index];
    }

    @Override
    public void execute(Player player) {
        int result = random.nextInt(100);

        if (result < 20) {
            if (result < 18) {
                if (result < 15) {
                    if (result < 12) {
                        if (result < 5) {
                            Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "rankvoucher " + player.getName() + " " + getRankVoucher());
                            return;
                        }
                        ItemStack reward = KeyItemBuilder.getKey(getKeyReward());
                        String rewardMsg = "&aYou've received a &6&lGOLD &aCrate Key.";

                        player.getInventory().addItem(reward);
                        player.playSound(player.getLocation(), win, 1f, 1f);
                        player.sendMessage(StringUtil.color(rewardMsg));
                        return;
                    }
                    player.getInventory().addItem(getRandomMvpItem());
                    player.playSound(player.getLocation(), win, 1f, 1f);
                    return;
                }
                player.getInventory().addItem(getRandomVipItem());
                player.playSound(player.getLocation(), win, 1f, 1f);
                return;
            }
            int size = getItemRewards().length;
            int index = random.nextInt(size);

            ItemStack reward = getItemRewards()[index];
            String rewardName = reward.getType().toString().replace("_", " ");
            String rewardMsg = "&aYou were given &e" + reward.getAmount() + "x " + rewardName;

            player.getInventory().addItem(reward);
            player.sendMessage(StringUtil.color(rewardMsg));
            player.playSound(player.getLocation(), win, 1f, 1f);
            return;
        }

        int size = getMoneyVouchers().length;
        int index = random.nextInt(size);

        int reward = getMoneyVouchers()[index];
        Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "moneyvoucher " + player.getName() + " " + reward);
        return;
    }
}
