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

public class NetheriteCrate implements ICrate {

    @Override
    public String getName() {
        return "Netherite";
    }

    @Override
    public KeyType getKeyRequired() {
        return KeyType.NETHERITE;
    }

    @Override
    public KeyType getKeyReward() {
        return KeyType.NETHERITE;
    }

    @Override
    public ItemStack[] getItemRewards() {
        return new ItemStack[] {};
    }

    @Override
    public Integer[] getMoneyVouchers() {
        return new Integer[] {};
    }

    @Override
    public Integer[] getDirectPayouts() {
        return new Integer[] {200000};
    }

    //Since every rank voucher is obtainable here we will have a separate method for handing them out

    @Override
    public String getRankVoucher() {
        return null;
    }

    private String getVipVoucher() {
        return "vip";
    }

    private String getMvpVoucher() {
        return "vip";
    }

    private String getEliteVoucher() {
        return "elite";
    }

    private String getImmortalVoucher() {
        return "immortal";
    }

    private ItemStack getRandomImmortalItem() {
        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
        ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
        ItemStack shovel = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 32);
        ItemStack godApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 12);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);

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

        ItemStack[] items = {helmet, chest, legs, boots, sword, pickaxe, shovel, axe, goldenApple, godApple, steak};
        int index = random.nextInt(items.length);

        return items[index];
    }

    @Override
    public void execute(Player player) {
        int result = random.nextInt(100);

        if (result < 20) {
            if (result < 17) {
                if (result < 15) {
                    if (result < 11) {
                        if (result < 10) {
                            if (result < 5) {
                                Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "rankvoucher " + player.getName() + " " + getImmortalVoucher());
                                return;
                            }
                            Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "rankvoucher " + player.getName() + " " + getEliteVoucher());
                            return;
                        }
                        ItemStack reward = KeyItemBuilder.getKey(getKeyReward());
                        String rewardMsg = "&aYou've received a &c&lNETHERITE &aCrate Key.";

                        player.getInventory().addItem(reward);
                        player.playSound(player.getLocation(), win, 1f, 1f);
                        player.sendMessage(StringUtil.color(rewardMsg));
                        return;
                    }
                    Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "rankvoucher " + player.getName() + " " + getMvpVoucher());
                    return;
                }
                player.getInventory().addItem(getRandomImmortalItem());
                player.playSound(player.getLocation(), win, 1f, 1f);
                return;
            }
            Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "rankvoucher " + player.getName() + " " + getVipVoucher());
            return;
        }

        int payout = getDirectPayouts()[0];
        Core.getCore().getEconomy().depositPlayer(player, payout);
        player.sendMessage(StringUtil.color("&aYou received a direct payout reward of &e$" + payout + " &ato your account."));
        player.playSound(player.getLocation(), win, 1f, 1f);
        return;
    }
}
