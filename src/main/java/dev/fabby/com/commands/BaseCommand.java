package dev.fabby.com.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public abstract class BaseCommand {

    public abstract String name();

    public abstract String description();
    public abstract String[] aliases();

    public abstract String neededPermission();

    public abstract int minArgs();

    public abstract String usage();

    public abstract boolean consoleAllowed();

    public abstract void execute(final Player p, final String[] args);

    public abstract void executeAsConsole(final CommandSender sender, final String[] args);

    public abstract boolean customTabCompleter();

    public abstract TabCompleter tabCompleter();
}
