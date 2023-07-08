package dev.fabby.com.commands.admin;

import dev.fabby.com.Core;
import dev.fabby.com.commands.BaseCommand;
import dev.fabby.com.utils.Task;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class RestartServerCommand extends BaseCommand {


    private final Task task = Core.getCore().getTaskManager();

    @Override
    public String name() {
        return "reboot";
    }

    @Override
    public String description() {
        return "Sends an announcement to all players that the server is restarting.";
    }

    @Override
    public String[] aliases() {
        return new String[]{"shutdown"};
    }

    @Override
    public String neededPermission() {
        return "fabby.admin.restart";
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public String usage() {
        return "/reboot";
    }

    @Override
    public boolean consoleAllowed() {
        return true;
    }

    @Override
    public void execute(Player p, String[] args) {
        restart();
    }

    @Override
    public void executeAsConsole(CommandSender sender, String[] args) {
        restart();
    }

    private void restart() {
        StringBuilder sb = new StringBuilder();
        sb.append("§a§m---------------------§r§8[§bFabbySMP§8]§a---------------------§r\n");
        sb.append("§aThe Server is restarting for an update.\n");
        sb.append("§aIt will be back in just a moment!\n");
        sb.append("§a§m---------------------§r§8[§bFabbySMP§8]§a---------------------§r\n");

        AtomicInteger seconds = new AtomicInteger(3);

        task.runSyncTimer(() -> {
            task.runSyncTimer(() -> {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendTitle("§aServer Restart", "§bThe server is restarting in " + seconds.get() + " " + (seconds.get() == 1 ? "second" : "seconds"), 0, 20, 0);
                    seconds.getAndDecrement();
                });
            }, 0L, 20L);

        }, 0L, 70L);
        task.runSyncTimer(() -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.kickPlayer(sb.toString());
            });
            Bukkit.shutdown();
        }, 60L, 20L);
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
