package dev.fabby.com.commands;

import com.google.common.collect.Maps;
import dev.fabby.com.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class RtpCommand implements CommandExecutor {

    private final World rtpWorld = Bukkit.getWorld("world");
    private final Map<UUID, Integer> cooldown = Maps.newConcurrentMap();
    private final int cooldownDuration = 60;
    private final Random random = new Random();

    private void teleport(final Player player, final int bound) {
        int x = random.nextInt(bound);
        int z = random.nextInt(bound);
        int y = player.getWorld().getHighestBlockYAt(x, z) + 1;

        final Location l = new Location(rtpWorld, x, y, z);
        player.teleport(l);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("rtp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be executed by players.");
                return true;
            }

            final Player player = (Player) sender;

            if (player.getWorld().getEnvironment() != World.Environment.NORMAL) {
                player.sendMessage(ChatColor.AQUA + "RTP is only enabled in the overworld.");
                return true;
            }

            if (!cooldown.containsKey(player.getUniqueId())) {
                teleport(player, 1500);
                cooldown.put(player.getUniqueId(), cooldownDuration);
                player.sendMessage(ChatColor.AQUA + "Teleported to a random location.");

                new BukkitRunnable() {
                    int t = cooldownDuration;
                    @Override
                    public void run() {
                        t--;
                        cooldown.replace(player.getUniqueId(), t);

                        if (t <= 0) {
                            this.cancel();
                            cooldown.remove(player.getUniqueId());
                            player.sendMessage(ChatColor.AQUA + "You can use /rtp again.");
                        }
                    }
                }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
            } else {
                player.sendMessage(ChatColor.AQUA + "You can't use /rtp for another " + cooldown.get(player.getUniqueId()) + " seconds.");
                return true;
            }
            return true;
        }
        return false;
    }
}
