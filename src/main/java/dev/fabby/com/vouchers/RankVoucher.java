package dev.fabby.com.vouchers;

import dev.fabby.com.Core;
import dev.fabby.com.utils.ItemUtil;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class RankVoucher {

    private final String rank;
    private final ItemStack voucher;
    private final String[] staff = {"builder", "media", "helper", "moderator", "admin", "sr-admin", "developer", "manager", "co-owner", "owner"};

    public RankVoucher(String rank) {
        this.rank = rank;

        final String display = Core.getCore().getLuckPermsApi().getGroupManager().getGroup(rank).getCachedData().getMetaData().getPrefix();
        voucher = new ItemUtil(
                Material.PAPER,
                "&6&nRank Voucher:&r " + display,
                "",
                "&aThis is a rank voucher.",
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
        String group = Core.getCore().getLuckPermsApi().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup();
        for (String s : staff) {
            if (group.equalsIgnoreCase(s)) {
                player.sendMessage(ChatColor.RED + "Staff members can't claim rank vouchers.");
                return;
            }
        }

        if (group.equalsIgnoreCase(rank)) {
            player.sendMessage(ChatColor.RED + "You already have the specified rank.");
            return;
        }

        Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), "lp user " + player.getName() + " parent add " + rank);
        player.getInventory().remove(voucher);
    }
}
