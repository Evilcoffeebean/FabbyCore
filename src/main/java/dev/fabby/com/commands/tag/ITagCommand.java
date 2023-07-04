package dev.fabby.com.commands.tag;

import org.bukkit.command.CommandSender;

public interface ITagCommand {

    String getName();
    String getDescription();
    String getUsage();
    String getPermission();
    int getLength();
    boolean isPlayer();
    void execute(CommandSender sender, String[] args);
}
