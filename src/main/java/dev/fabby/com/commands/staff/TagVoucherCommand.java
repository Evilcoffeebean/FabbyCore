package dev.fabby.com.commands.staff;

import dev.fabby.com.vouchers.TagVoucher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagVoucherCommand implements CommandExecutor {

    public static TagVoucher voucher;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("tagvoucher")) {
            if (!(sender instanceof Player)) {
                if (args.length != 2) {
                    Bukkit.getLogger().info("Usage: /tagvouher <player> <permission node>");
                    return true;
                }

                final Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Bukkit.getLogger().info("'" + args[0] + "' isn't currently online.");
                    return true;
                }

                try {
                    voucher = new TagVoucher(args[1]);
                    voucher.addVoucher(target);
                    Bukkit.getLogger().info("You gave 1 '" + args[1] + "' voucher to " + target.getName());
                    target.sendMessage(ChatColor.AQUA + "You were given 1 '" + args[1] + "' voucher by the Server.");
                    return true;
                } catch (IllegalArgumentException e) {
                    Bukkit.getLogger().info("'" + args[1] + "' isn't a valid Tag voucher.");
                    return true;
                }
            }

            final Player player = (Player) sender;
            if (!player.hasPermission("fabby.tagvoucher")) {
                player.sendMessage(ChatColor.AQUA + "You don't have permission to give Tag vouchers.");
                return true;
            }

            if (args.length != 2) {
                player.sendMessage(ChatColor.AQUA + "Usage: /tagvouher <player> <permission node>");
                return true;
            }

            final Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(ChatColor.AQUA + "'" + args[0] + "' isn't currently online.");
                return true;
            }

            try {
                voucher = new TagVoucher(args[1]);
                voucher.addVoucher(target);
                player.sendMessage(ChatColor.AQUA + "You gave 1 '" + args[1] + "' voucher to " + target.getName());
                target.sendMessage(ChatColor.AQUA + "You were given 1 '" + args[1] + "' voucher by " + player.getName());
                return true;
            } catch (IllegalArgumentException e) {
                player.sendMessage(ChatColor.AQUA + "'" + args[1] + "' isn't a valid Tag voucher.");
                return true;
            }
        }
        return false;
    }
}
