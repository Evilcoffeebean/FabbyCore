package dev.fabby.com.commands.tag.cmds;

import dev.fabby.com.commands.tag.ITagCommand;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TagCreateCommand implements ITagCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create a new Tag.";
    }

    @Override
    public String getUsage() {
        return "/tag create [identifier] <tag display name>";
    }

    @Override
    public String getPermission() {
        return "fabby.tag.create";
    }

    @Override
    public int getLength() {
        return 3;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final String text = StringUtil.join(2, args);
        //
        sender.sendMessage(ChatColor.AQUA + "Saved identifier " + args[1] + " as a Tag.");
        return;
    }
}
