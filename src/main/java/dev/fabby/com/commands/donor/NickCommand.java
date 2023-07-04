package dev.fabby.com.commands.donor;

import dev.fabby.com.Core;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("nick")) {
            if (!(sender instanceof Player)) {
                if (args.length < 2) {
                    Bukkit.getLogger().info("Usage: /nick <player> <nickname>");
                    return true;
                }

                if (args[1].equalsIgnoreCase("none") || args[1].equalsIgnoreCase("off")) {
                    Core.getCore().getNickConfig().removeNickname(args[0]);
                    Bukkit.getLogger().info("You removed " + args[0] + "'s nickname.");
                    return true;
                }

                final String nick = StringUtil.join(1, args);
                Core.getCore().getNickConfig().setNickname(args[0], nick);
                Bukkit.getLogger().info("You set " + args[0] + "'s nickname to " + StringUtil.color(nick));
                return true;
            }
        }

        final Player p = (Player) sender;
        if (!p.hasPermission("fabby.nick")) {
            p.sendMessage(ChatColor.RED + "You don't have permission to set a nickname.");
            return true;
        }

        if (args.length < 2) {
            p.sendMessage(ChatColor.YELLOW + "Usage: /nick <player> <nickname>");
            return true;
        }

        if (!p.getName().equals(args[0])) {
            if (!p.hasPermission("fabby.nick.others")) {
                p.sendMessage(ChatColor.RED + "You can only change your own nickname.");
                return true;
            }
        }

        if (args[1].equalsIgnoreCase("none") || args[1].equalsIgnoreCase("off")) {
            Core.getCore().getNickConfig().removeNickname(args[0]);
            p.sendMessage(ChatColor.YELLOW + "You removed " + args[0] + "'s nickname.");
            return true;
        }

        final String nick = StringUtil.join(1, args);
        Core.getCore().getNickConfig().setNickname(args[0], nick);
        p.sendMessage(StringUtil.color("&bYou set &e" + args[0] + "&b's nickname to " + nick));
        if (Bukkit.getPlayer(args[0]) != null)
            Bukkit.getPlayer(args[0]).sendMessage(StringUtil.color("&bYour nickname was set to " + nick + " &bby &e" + p.getName()));
        return true;
    }
}
