package dev.fabby.com.commands.tag;

import dev.fabby.com.commands.tag.cmds.TagCreateCommand;
import dev.fabby.com.commands.tag.cmds.TagMenuCommand;
import dev.fabby.com.commands.tag.cmds.TagRemoveCommand;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TagCommandExecutor implements CommandExecutor {

    private final Map<String, ITagCommand> tagCommands = new HashMap<>();

    public TagCommandExecutor() {
        //TODO: cache all /tag commands
        /* this will be fixed in an updated after release
        tagCommands.put("create", new TagCreateCommand());
        tagCommands.put("remove", new TagRemoveCommand());
         */
        tagCommands.put("menu", new TagMenuCommand());
    }

    private String getViableCommands(CommandSender sender) {
        final StringBuilder response = new StringBuilder();
        response.append(ChatColor.BLUE).append("Available Tag Commands:\n");

        for (ITagCommand cmd : tagCommands.values()) {
            if (!cmd.isPlayer()) {
                response.append(ChatColor.AQUA).append(cmd.getUsage()).append("\n");
            } else {
                if (sender instanceof Player) {
                    if (((Player) sender).hasPermission(cmd.getPermission())) {
                        response.append(ChatColor.AQUA).append(cmd.getUsage()).append("\n");
                    }
                }
            }
        }

        return response.toString();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("tag")) {
            if (!(sender instanceof Player)) {
                if (args.length == 0) {
                    Bukkit.getLogger().info(StringUtil.getCenteredMessage(getViableCommands(sender)));
                    return true;
                }

                final ITagCommand command = tagCommands.get(args[0]);
                if (command == null) {
                    Bukkit.getLogger().info("'" + args[0] + "' isn't a valid Tag command.");
                    return true;
                }

                if (command.isPlayer()) {
                    Bukkit.getLogger().info("That command can only be executed by a player.");
                    return true;
                }

                if (args.length < command.getLength()) {
                    Bukkit.getLogger().info("Usage: " + command.getUsage());
                    return true;
                }

                command.execute(sender, args);
                return true;
            }

            final Player player = (Player) sender;
            if (args.length == 0) {
                StringUtil.sendCenteredMessage(player, getViableCommands(player));
                return true;
            }

            final ITagCommand command = tagCommands.get(args[0]);
            if (command == null || !tagCommands.containsKey(args[0])) {
                player.sendMessage(ChatColor.AQUA + "'" + args[0] + "' isn't a valid Tag command.");
                return true;
            }

            if (!player.hasPermission(command.getPermission())) {
                player.sendMessage(ChatColor.AQUA + "You don't have permission to execute this command.");
                return true;
            }

            if (args.length < command.getLength()) {
                player.sendMessage(ChatColor.AQUA + "Usage: " + command.getUsage());
                return true;
            }

            command.execute(player, args);
            return true;
        }
        return false;
    }
}
