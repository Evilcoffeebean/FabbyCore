package dev.fabby.com.vouchers;

import dev.fabby.com.Core;
import dev.fabby.com.tags.Tag;
import dev.fabby.com.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TagVoucher {

    private final String id;
    private final ItemStack voucher;

    public TagVoucher(String id) {
        this.id = id;

        final String display = Tag.valueOf(id.toUpperCase()).getDisplay();
        voucher = new ItemUtil(
                Material.PAPER,
                "&6&nTag Voucher:&r " + display,
                "",
                "&aThis is a Tag voucher.",
                "&aYou can use it for yourself or",
                "&ayou can sell/trade it to others.",
                "",
                "&b&nClick Anywhere&r &ato claim (one time only)."
        ).getProductWithGlow();
    }

    public void addVoucher(Player player) {
        player.getInventory().addItem(voucher);
    }

    public boolean hasVoucher(Player player) {
        return player.getInventory().getItemInMainHand().isSimilar(voucher) || player.getInventory().getItemInMainHand() == voucher;
    }

    public void execute(Player player) {
        if (player.hasPermission(Tag.valueOf(id.toUpperCase()).getId())) {
            player.sendMessage(ChatColor.RED + "You already have the required Tag permission.");
            return;
        }

        Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "lp user " + player.getName() + " permission set fabby.tag." + id + " true");
        player.getInventory().remove(voucher);
        return;
    }
}
