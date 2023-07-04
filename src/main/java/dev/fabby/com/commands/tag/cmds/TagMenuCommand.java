package dev.fabby.com.commands.tag.cmds;

import dev.fabby.com.commands.tag.ITagCommand;
import dev.fabby.com.tags.menu.TagMenu;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagMenuCommand implements ITagCommand {

    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public String getDescription() {
        return "Tag Menu with all your available tags.";
    }

    @Override
    public String getUsage() {
        return "/tag menu";
    }

    @Override
    public String getPermission() {
        return "fabby.tag.menu";
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        new TagMenu().buildAndOpen((Player) sender);
        ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }
}
