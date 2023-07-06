package dev.fabby.com.commands;

import dev.fabby.com.Core;
import dev.fabby.com.commands.commandargs.NoArgTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements Listener {

    private final List<BaseCommand> activeCommands;
    private CommandMap commandMap;


    public CommandHandler() {
        activeCommands = new ArrayList<>();

        setCommandMap();
        registerCommands();
        System.out.println("CommandHandler loaded!");
    }


    public List<BaseCommand> getActiveCommands() {
        return activeCommands;
    }

    private void registerCommands() {
;
    }

    public void registerCommand(final BaseCommand command) {
        this.activeCommands.add(command);

        try {
            Class<?> pluginClass = PluginCommand.class;
            Constructor<?> constructor = pluginClass.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            PluginCommand pluginCommand = (PluginCommand) constructor.newInstance(command.name(), Core.getCore());
            pluginCommand.setExecutor(getExecutor(command));
            if (command.customTabCompleter()) {
                pluginCommand.setTabCompleter(command.tabCompleter());
            } else {
                pluginCommand.setTabCompleter(new NoArgTabCompleter());
            }

            if (command.aliases().length > 0) {
                pluginCommand.setAliases(Arrays.asList(command.aliases()));
            }

            pluginCommand.setDescription(command.description());
            this.commandMap.register("FortuneCore", pluginCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CommandExecutor getExecutor(final BaseCommand command) {
        return ((sender, command1, label, args) -> {
            if (sender instanceof Player player) {
                if(player.hasPermission(command.neededPermission())) {
                    if(args.length < command.minArgs()) {
                        player.sendMessage(ChatColor.RED + "Invalid arguments. Correct usage: " + command.usage());

                    } else {
                        command.execute(player, args);
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
                }

            } else {
                if (command.consoleAllowed()) {
                    command.executeAsConsole(sender, args);
                } else {
                    sender.sendMessage("You must be a player to execute this command.");
                }
            }
            return true;
        }
        );
    }

    private void setCommandMap() {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            this.commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}