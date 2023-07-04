package dev.fabby.com.commands.staff;

import dev.fabby.com.Core;
import dev.fabby.com.vouchers.EffectVoucher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EffectVoucherCommand implements CommandExecutor {

    public static EffectVoucher effectVoucher;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("effectvoucher")) {
            if (!(sender instanceof Player)) {
                if (args.length != 2) {
                    Bukkit.getLogger().info("Usage: /effectvoucher <player> <effect>");
                    return true;
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    Bukkit.getLogger().info(args[0] + " isn't currently online.");
                    return true;
                }

                if (Core.getCore().getParticleManager().getEffect(args[1]) == null) {
                    Bukkit.getLogger().info(args[1] + " isn't a valid cosmetic.");
                    return true;
                }

                effectVoucher = new EffectVoucher(args[1]);
                effectVoucher.addVoucher(Bukkit.getPlayer(args[0]));
                Bukkit.getLogger().info("You gave 1 Effect Voucher to " + args[0]);
                Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "You were given 1 Effect Voucher by the Server.");
                return true;
            }

            final Player p = (Player) sender;
            if (!p.hasPermission("fabby.voucher")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to give effect vouchers.");
                return true;
            }

            if (args.length != 2) {
                p.sendMessage(ChatColor.GREEN + "Usage: /effectvoucher <player> <effect>");
                return true;
            }

            if (Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(ChatColor.GREEN + args[0] + " isn't currently online.");
                return true;
            }

            if (Core.getCore().getParticleManager().getEffect(args[1]) == null) {
                p.sendMessage(ChatColor.GREEN + args[1] + " isn't a valid cosmetic.");
                return true;
            }

            effectVoucher = new EffectVoucher(args[1]);
            effectVoucher.addVoucher(Bukkit.getPlayer(args[0]));
            p.sendMessage(ChatColor.GREEN + "You gave 1 Effect Voucher to " + args[0]);
            Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "You were given 1 Effect Voucher by " + p.getName());
            return true;
        }
        return false;
    }
}
