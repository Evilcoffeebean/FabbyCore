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

public class DiamondCrate implements ICrate {

    @Override
    public String getName() {
        return "Diamond";
    }

    @Override
    public KeyType getKeyRequired() {
        return KeyType.DIAMOND;
    }

    @Override
    public KeyType getKeyReward() {
        return KeyType.NETHERITE;
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
        return new Integer[] {};
    }

    @Override
    public Integer[] getDirectPayouts() {
        return new Integer[] {150000};
    }

    @Override
    public String getRankVoucher() {
        return "elite";
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

        if (result < 22) {
            if (result < 15) {
                if (result < 10) {
                    if (result < 5) {
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

                    Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "rankvoucher " + player.getName() + " " + getRankVoucher());
                    return;
                }

                ItemStack reward = KeyItemBuilder.getKey(getKeyReward());
                String rewardMsg = "&aYou've received a &c&lNETHERITE &aCrate Key.";

                player.getInventory().addItem(reward);
                player.playSound(player.getLocation(), win, 1f, 1f);
                player.sendMessage(StringUtil.color(rewardMsg));
                return;
            }

            int payout = getDirectPayouts()[0];
            Core.getCore().getEconomy().depositPlayer(player, payout);
            player.sendMessage(StringUtil.color("&aYou received a direct payout reward of &e$" + payout + " &ato your account."));
            player.playSound(player.getLocation(), win, 1f, 1f);
            return;
        }

        player.getInventory().addItem(getRandomMvpItem());
        player.playSound(player.getLocation(), win, 1f, 1f);
        return;
    }
}
