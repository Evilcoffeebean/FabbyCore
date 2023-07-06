package dev.fabby.com.commands.staff.moderation;

import dev.fabby.com.commands.BaseCommand;
import dev.fabby.com.menus.InvSeeMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class InvSeeCommand extends BaseCommand {
    @Override
    public String name() {
        return "invsee";
    }

    @Override
    public String description() {
        return "View another player's inventory.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public String neededPermission() {
        return "fabby.moderation.invsee";
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public String usage() {
        return "/invsee <player>";
    }

    @Override
    public boolean consoleAllowed() {
        return false;
    }

    @Override
    public void execute(Player p, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            p.sendMessage(ChatColor.RED + "That player is not online!");
            return;
        }

        new InvSeeMenu(p, target);
    }

    @Override
    public void executeAsConsole(CommandSender sender, String[] args) {

    }

    @Override
    public boolean customTabCompleter() {
        return false;
    }

    @Override
    public TabCompleter tabCompleter() {
        return null;
    }
}
