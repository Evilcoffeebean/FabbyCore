package dev.fabby.com.commands;

import dev.fabby.com.Core;
import dev.fabby.com.kit.IKit;
import dev.fabby.com.kit.menu.KitMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KitCommand implements CommandExecutor {

    private final List<IKit> kits = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("kit")) {
            if (!(sender instanceof Player)) {
                Bukkit.getLogger().info("Only player can access the kit menu.");
                return true;
            }

            final Player p = (Player) sender;
            new KitMenu().buildAndOpen(p);
            Core.getCore().getKitManager().addKitList(p, kits);
            return true;
        }
        return false;
    }
}
