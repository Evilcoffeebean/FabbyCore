package dev.fabby.com.commands;

import com.google.common.collect.Maps;
import dev.fabby.com.Core;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class RtpCommand implements CommandExecutor {

    private static final HashSet<Material> blacklist = new HashSet<>();
    private final World rtpWorld = Bukkit.getWorld("world");
    private final int bound = 1500;
    private final Map<UUID, Integer> cooldown = Maps.newConcurrentMap();
    private final int cooldownDuration = 60;
    private final Random random = new Random();

    static {
        blacklist.add(Material.LAVA);
        blacklist.add(Material.CACTUS);
        blacklist.add(Material.WATER);
        blacklist.add(Material.VOID_AIR);
        blacklist.add(Material.FIRE);
    }

    private Location findSafeLocation(){
        Location randomLocation = generateLocation();

        while (!isLocationSafe(randomLocation))
            randomLocation = generateLocation();
        return randomLocation;
    }

    private Location generateLocation() {
        int x = 0;
        int z = 0;
        int y = 0;

        x = random.nextInt(bound);
        z = random.nextInt(bound);
        y = 150;

        Location randomLocation = new Location(rtpWorld, x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        return randomLocation;
    }

    private boolean isLocationSafe(Location location){
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        return !(blacklist.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
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
                player.teleport(findSafeLocation());
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
