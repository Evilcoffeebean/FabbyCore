package dev.fabby.com.commands.staff;

import dev.fabby.com.Core;
import dev.fabby.com.crates.ICrate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCrateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("setcrate")) {
            if (!(sender instanceof Player)) {
                Bukkit.getLogger().info("Only a player can save a crate in the config.");
                return true;
            }

            final Player p = (Player) sender;
            if (!p.hasPermission("fabby.setcrate")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to set crate data.");
                return true;
            }

            if (args.length != 1) {
                p.sendMessage(ChatColor.GREEN + "Usage: /setcrate <crate name>");
                return true;
            }

            final ICrate crate = Core.getCore().getCrateManager().getCrate(args[0]);
            if (crate == null) {
                p.sendMessage(ChatColor.GREEN + "'" + args[0] + "' isn't a valid crate name.");
                return true;
            }

            if (!Core.getCore().getCrateManager().isEditing(p)) {
                Core.getCore().getCrateManager().addEditor(p, args[0]);
                p.sendMessage(ChatColor.GREEN + "Select the chest to specify it as the " + args[0] + " crate.");
            } else {
                p.sendMessage(ChatColor.GREEN + "You are already in crate editorial mode.");
            }
            return true;
        }
        return false;
    }
}
