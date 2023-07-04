package dev.fabby.com.commands;

import dev.fabby.com.Core;
import dev.fabby.com.vouchers.WithdrawVoucher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WithdrawCommand implements CommandExecutor {

    public static WithdrawVoucher voucher;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("withdraw")) {
            if (!(sender instanceof Player)) {
                Bukkit.getLogger().info("Only players can withdraw from their accounts.");
                return true;
            }

            final Player p = (Player) sender;
            if (args.length != 1) {
                p.sendMessage(ChatColor.GREEN + "Usage: /withdraw <amount>");
                return true;
            }

            try {
                double amount = Integer.parseInt(args[0]);
                double balance = Core.getCore().getEconomy().getBalance(p);

                if (amount > balance) {
                    p.sendMessage(ChatColor.GREEN + "You don't have enough money to make this withdrawal.");
                    return true;
                }

                Core.getCore().getEconomy().withdrawPlayer(p, amount);
                voucher = new WithdrawVoucher(p, amount);
                voucher.addVoucher(p);
                p.sendMessage(ChatColor.GREEN + "You've withdrawn $" + amount + " and have deposited into the designated voucher.");
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                return true;
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.GREEN + args[0] + " isn't a valid number.");
                return true;
            }
        }
        return false;
    }
}
