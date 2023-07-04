package dev.fabby.com.commands.staff;

import dev.fabby.com.crates.key.KeyItemBuilder;
import dev.fabby.com.crates.key.KeyType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KeyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("key")) {
            if (!(sender instanceof Player)) {
                if (args.length != 3) {
                    Bukkit.getLogger().info("Usage: /key <player> <crate key> <amount>");
                    return true;
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    Bukkit.getLogger().info(args[0] + " isn't currently online.");
                    return true;
                }

                final KeyType type = KeyType.byName(args[1]);

                if (type == null) {
                    Bukkit.getLogger().info(args[1] + " isn't a valid key.");
                    return true;
                }

                try {
                    int amount = Integer.parseInt(args[2]);

                    for (int i = 0; i < amount; i++)
                        Bukkit.getPlayer(args[0]).getInventory().addItem(KeyItemBuilder.getKey(type));

                    Bukkit.getLogger().info("You gave " + amount + " " + args[1].toUpperCase() + " Crate Key(s) to " + args[0]);
                    Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "You were given " + amount + " " + args[1].toUpperCase() + " Crate Key(s) by the Server.");
                    return true;
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().info(args[2] + " isn't a valid amount.");
                    return true;
                }
            }

            final Player p = (Player) sender;

            if (!p.hasPermission("fabby.crates.key")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to give crate keys.");
                return true;
            }

            if (args.length != 3) {
                p.sendMessage(ChatColor.GREEN + "Usage: /key <player> <crate key> <amount>");
                return true;
            }

            if (Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(ChatColor.GREEN + " isn't currently online.");
                return true;
            }

            final KeyType type = KeyType.byName(args[1]);

            if (type == null) {
                p.sendMessage(ChatColor.GREEN + args[1] + " isn't a valid key.");
                return true;
            }

            try {
                int amount = Integer.parseInt(args[2]);

                for (int i = 0; i < amount; i++)
                    Bukkit.getPlayer(args[0]).getInventory().addItem(KeyItemBuilder.getKey(type));

                p.sendMessage(ChatColor.GREEN + "You gave " + amount + " " + args[1].toUpperCase() + " Crate Key(s) to " + args[0]);
                Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "You were given " + amount + " " + args[1].toUpperCase() + " Crate Key(s) by " + p.getName());
                return true;
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.GREEN + args[2] + " isn't a valid amount.");
                return true;
            }
        }
        return false;
    }
}
