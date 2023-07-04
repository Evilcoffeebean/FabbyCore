package dev.fabby.com.commands;

import dev.fabby.com.cosmetics.CosmeticsMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CosmeticsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("cosmetics")) {
            if (!(sender instanceof Player)) {
                Bukkit.getLogger().info("Only a player can access server cosmetics.");
                return true;
            }

            new CosmeticsMenu((Player) sender).buildAndOpen();
            return true;
        }
        return false;
    }
}
