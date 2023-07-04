package dev.fabby.com.commands.staff;

import dev.fabby.com.Core;
import dev.fabby.com.vouchers.RankVoucher;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

@Getter
public class RankVoucherCommand implements CommandExecutor, Listener {

    public static RankVoucher rankVoucher;
    private final String[] available = {"vip", "mvp", "elite", "immortal"};

    private boolean isDonor(String rank) {
        for (String s : available) {
            if (rank.equals(s))
                return true;
        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("rankvoucher")) {
            if (!(sender instanceof Player)) {
                if (args.length != 2) {
                    Bukkit.getLogger().info("Usage: /rankvoucher <player> <rank>");
                    return true;
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    Bukkit.getLogger().info(args[0] + " isn't currently online.");
                    return true;
                }

                if (Core.getCore().getApi().getGroupManager().getGroup(args[1]) == null) {
                    Bukkit.getLogger().info(args[1] + " isn't a valid rank.");
                    return true;
                }

                if (!isDonor(args[1])) {
                    Bukkit.getLogger().info("You need to specify a purchasable rank.");
                    return true;
                }

                final String rank = Core.getCore().getApi().getGroupManager().getGroup(args[1]).getName();

                rankVoucher = new RankVoucher(rank);
                rankVoucher.addVoucher(Bukkit.getPlayer(args[0]));

                Bukkit.getLogger().info("You gave 1 " + rank + " voucher to " + Bukkit.getPlayer(args[0]).getName());
                Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "You gave been given 1 " + rank + " voucher by the Server.");
                return true;
            }

            Player p = (Player) sender;
            if (!p.hasPermission("fabby.voucher")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to give rank vouchers.");
                return true;
            }

            if (args.length != 2) {
                p.sendMessage(ChatColor.GREEN + "Usage: /rankvoucher <player> <rank>");
                return true;
            }

            if (Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(ChatColor.RED + args[0] + " isn't currently online.");
                return true;
            }

            if (Core.getCore().getApi().getGroupManager().getGroup(args[1]) == null) {
                p.sendMessage(ChatColor.RED + args[1] + " isn't a valid rank.");
                return true;
            }

            if (!isDonor(args[1])) {
                p.sendMessage(ChatColor.RED + "You need to specify a purchasable rank.");
                return true;
            }

            final String rank = Core.getCore().getApi().getGroupManager().getGroup(args[1]).getName();

            rankVoucher = new RankVoucher(rank);
            rankVoucher.addVoucher(Bukkit.getPlayer(args[0]));

            p.sendMessage(ChatColor.GREEN + "You gave 1 " + rank + " voucher to " + Bukkit.getPlayer(args[0]).getName());
            Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "You have been given 1 " + rank + " voucher by " + p.getName());
            return true;
        }
        return false;
    }
}
