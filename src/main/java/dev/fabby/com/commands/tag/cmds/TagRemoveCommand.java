package dev.fabby.com.commands.tag.cmds;

import dev.fabby.com.commands.tag.ITagCommand;
import org.bukkit.command.CommandSender;

public class TagRemoveCommand implements ITagCommand {

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Removes an existing Tag.";
    }

    @Override
    public String getUsage() {
        return "/tag remove <identifier>";
    }

    @Override
    public String getPermission() {
        return "fabby.tag.remove";
    }

    @Override
    public int getLength() {
        return 2;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
}
