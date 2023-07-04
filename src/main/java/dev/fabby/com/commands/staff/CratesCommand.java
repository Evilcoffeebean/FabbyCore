package dev.fabby.com.commands.staff;

import dev.fabby.com.crates.menu.MainCratesMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CratesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("crates")) {
            if (!(sender instanceof Player)) {
                Bukkit.getLogger().info("Only player can select crates to open.");
                return true;
            }

            final Player player = (Player) sender;
            if (!player.hasPermission("fabby.cratet.dev")) {
                player.sendMessage(ChatColor.AQUA + "Restricted to staff only.");
                return true;
            }
            new MainCratesMenu().buildAndOpen(player);
            return true;
        }
        return false;
    }
}
