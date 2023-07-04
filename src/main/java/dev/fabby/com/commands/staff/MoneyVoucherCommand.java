package dev.fabby.com.commands.staff;

import dev.fabby.com.utils.StringUtil;
import dev.fabby.com.vouchers.MoneyVoucher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyVoucherCommand implements CommandExecutor {

    public static MoneyVoucher voucher;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("moneyvoucher")) {
            if (!(sender instanceof Player)) {
                if (args.length != 2) {
                    Bukkit.getLogger().info("Usage: /moneyvoucher <player> <amount>");
                    return true;
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    Bukkit.getLogger().info(args[0] + " isn't currently online.");
                    return true;
                }

                final Player target = Bukkit.getPlayer(args[0]);

                try {
                    double amount = Integer.parseInt(args[1]);
                    voucher = new MoneyVoucher(amount);
                    voucher.addVoucher(target);

                    Bukkit.getLogger().info("You've given a money voucher of $" + amount + " to " + target.getName());
                    target.sendMessage(StringUtil.color("&aYou were given a money voucher of &e$" + amount + " &aby the Server."));
                    target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    return true;
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().info(args[1] + " isn't a valid amount of money.");
                    return true;
                }
            }

            final Player p = (Player) sender;

            if (!p.hasPermission("fabby.moneyvoucher")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to give money vouchers.");
                return true;
            }

            if (args.length != 2) {
                p.sendMessage(ChatColor.GREEN + "Usage: /moneyvoucher <player> <amount>");
                return true;
            }

            if (Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(ChatColor.GREEN + args[0] + " isn't currently online.");
                return true;
            }

            final Player target = Bukkit.getPlayer(args[0]);

            try {
                double amount = Integer.parseInt(args[1]);
                voucher = new MoneyVoucher(amount);
                voucher.addVoucher(target);

                p.sendMessage(StringUtil.color("&aYou've given a money voucher of &e$" + amount + " &ato &e" + target.getName()));
                target.sendMessage(StringUtil.color("&aYou were given a money voucher of &e$" + amount + " &aby &e" + p.getName()));
                target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                return true;
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.GREEN + args[1] + " isn't a valid amount of money.");
                return true;
            }
        }
        return false;
    }
}
