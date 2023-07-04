package dev.fabby.com.vouchers;

import dev.fabby.com.Core;
import dev.fabby.com.utils.ItemUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MoneyVoucher {

    private final Economy economy = Core.getCore().getEconomy();
    private final double amount;
    private final ItemStack voucher;

    public MoneyVoucher(double amount) {
        this.amount = amount;
        this.voucher = new ItemUtil(
                Material.PAPER,
                "&a&lReward Voucher &a(&e$" + amount + "&a)",
                "",
                "&aThis is a reward voucher.",
                "&aObtainable by using the Crates.",
                "",
                "&aAnyone with access to this voucher can",
                "&aclaim this amount to their own balance.",
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
        economy.depositPlayer(player, amount);
        int current = player.getInventory().getItemInMainHand().getAmount();
        player.getInventory().getItemInMainHand().setAmount(current - 1);
    }
}
