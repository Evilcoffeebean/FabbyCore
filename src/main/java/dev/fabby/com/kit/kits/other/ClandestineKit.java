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
import org.bukkit.potion.PotionEffectType;

public class ClandestineKit implements IKit {

    @Override
    public String getName() {
        return "Clandestine";
    }

    @Override
    public String getDisplayName() {
        return StringUtil.color("&6Clandestine Kit");
    }

    @Override
    public String getPermission() {
        return "fabby.kit.clandestine";
    }

    @Override
    public double getPrice() {
        return 20000;
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                StringUtil.color("&7Are you sure you want"),
                StringUtil.color("&7to purchase this kit?"),
                "",
                StringUtil.color("&7As the rarest collection of"),
                StringUtil.color("&7rewards specific to each kit, even"),
                StringUtil.color("&7the Staff are afraid of this one..."),
                "",
                StringUtil.color("&7[DISCLAIMER]"),
                StringUtil.color("&7&nPurchase at your own risk"),
                "",
                StringUtil.color("&7Cost: &e$" + getPrice()),
                "",
                StringUtil.color("&7Cooldown duration: &a" + StringUtil.formatKitCooldown(this))
        };
    }

    @Override
    public ItemStack getMenuItem() {
        return new ItemUtil(Material.PHANTOM_MEMBRANE, getDisplayName(), getDescription()).getProduct();
    }

    @Override
    public ItemStack[] getArmorContents() {
        return new ItemStack[] {};
    }

    @Override
    public ItemStack[] getInventoryContents() {
        ItemStack clandestineElytra = new ItemUtil(Material.ELYTRA, "&4&lCLANDESTINE ELYTRA").getProduct();
        Core.getCore().getKitManager().enchant(clandestineElytra, Enchantment.MENDING, Enchantment.MENDING.getMaxLevel());

        ItemStack crystals = new ItemStack(Material.END_CRYSTAL, 16);
        ItemStack crying = new ItemStack(Material.CRYING_OBSIDIAN, 32);
        ItemStack totems = new ItemStack(Material.TOTEM_OF_UNDYING, 6);
        ItemStack health = Core.getCore().getKitManager().potion(PotionEffectType.HEAL, 2, 90, 6);
        ItemStack tnt = new ItemStack(Material.TNT, 32);
        ItemStack flintSteel = new ItemStack(Material.FLINT_AND_STEEL);

        return new ItemStack[] {
                clandestineElytra,
                crystals,
                crying,
                totems,
                health,
                tnt,
                flintSteel
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
        return;
    }
}
