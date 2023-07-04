package dev.fabby.com.commands;

import dev.fabby.com.kit.menu.KitMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("kit")) {
            if (!(sender instanceof Player)) {
                Bukkit.getLogger().info("Only player can access the kit menu.");
                return true;
            }

            final Player p = (Player) sender;
            //new KitMenu().buildAndOpen(p);
            p.sendMessage(ChatColor.AQUA + "Temporarily disabled due to bugfixes.");
            return true;
        }
        return false;
    }
}
