package dev.fabby.com.commands.staff;

import dev.fabby.com.Core;
import dev.fabby.com.vouchers.KitVoucher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitVoucherCommand implements CommandExecutor {

    public static KitVoucher kitVoucher;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("kitvoucher")) {
            if (!(sender instanceof Player)) {
                if (args.length != 2) {
                    Bukkit.getLogger().info("Usage: /kitvoucher <player> <kit identifier>");
                    return true;
                }

                final Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Bukkit.getLogger().info(args[0] + " isn't currently online.");
                    return true;
                }

                if (Core.getCore().getKitManager().getKit(args[1]) == null) {
                    Bukkit.getLogger().info(args[1] + " isn't a valid kit identifier.");
                    return true;
                }

                kitVoucher = new KitVoucher(args[1]);
                kitVoucher.addVoucher(target);
                Bukkit.getLogger().info("You gave 1 " + args[1] + " Kit Voucher to " + target.getName());
                target.sendMessage(ChatColor.GREEN + "You were given 1 " + args[1] + " Kit Voucher by the Server.");
                return true;
            }

            final Player player = (Player) sender;
            if (!player.hasPermission("fabby.kitvoucher")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to give Kit Vouchers.");
                return true;
            }

            if (args.length != 2) {
                player.sendMessage(ChatColor.GREEN + "Usage: /kitvoucher <player> <kit identifier>");
                return true;
            }

            final Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.GREEN + args[0] + " isn't currently online.");
                return true;
            }

            if (Core.getCore().getKitManager().getKit(args[1]) == null) {
                player.sendMessage(ChatColor.GREEN + args[1] + " isn't a valid kit identifier.");
                return true;
            }

            kitVoucher = new KitVoucher(args[1]);
            kitVoucher.addVoucher(target);
            player.sendMessage(ChatColor.GREEN + "You gave 1 " + args[1] + " Kit Voucher to " + target.getName());
            target.sendMessage(ChatColor.GREEN + "You were given 1 " + args[1] + " Kit Voucher by " + player.getName());
            return true;
        }
        return false;
    }
}
