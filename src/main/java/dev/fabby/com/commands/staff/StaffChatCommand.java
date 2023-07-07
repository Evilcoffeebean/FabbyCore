package dev.fabby.com.commands.staff;

import dev.fabby.com.commands.BaseCommand;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffChatCommand extends BaseCommand {


    public static final List<UUID> staffChat = new ArrayList<>();

    @Override
    public String name() {
        return "staffchat";
    }

    @Override
    public String description() {
        return "Send a message to all staff members.";
    }

    @Override
    public String[] aliases() {
        return new String[]{"sc"};
    }

    @Override
    public String neededPermission() {
        return "fabby.staff.staffchat";
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public String usage() {
        return "/staffchat <message>";
    }

    @Override
    public boolean consoleAllowed() {
        return false;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("toggle")) {
            // TODO: Create a FabbyPlayer, make all of this more clean.
            if (staffChat.contains(p.getUniqueId())) {
                staffChat.remove(p.getUniqueId());
                p.sendMessage("§cYou have disabled staff chat.");
            } else {
                staffChat.add(p.getUniqueId());
                p.sendMessage("§aYou have enabled staff chat.");
            }
            return;
        }

        if(!staffChat.contains(p.getUniqueId()) && !args[0].equalsIgnoreCase("toggle")) {
            p.sendMessage("§cYou have disabled staff chat. To enable it, type §6/staffchat toggle§c.");
            return;
        }



        if (staffChat.contains(p.getUniqueId()) || !args[0].equalsIgnoreCase("toggle")) {
            StringBuilder builder = new StringBuilder();
            for (String arg : args) {
                builder.append(arg).append(" ");
            }

            for (Player staff : p.getServer().getOnlinePlayers()) {
                if (staff.hasPermission("fabby.staff.staffchat") && staffChat.contains(staff.getUniqueId())) {
                    if (staffChat.contains(p.getUniqueId())) {
                        staff.sendMessage("§8[§bSTAFF§8] §6" + p.getName() + "§8: §f" + builder);
                    }
                }
            }
        } else {
            p.sendMessage("§bYou have disabled staff chat. To enable it, type §6/staffchat toggle§b.");
        }


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
