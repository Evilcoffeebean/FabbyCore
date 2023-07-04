package dev.fabby.com.fakeplayers.tasks;

import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.FakePlayerList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.Set;

public class DeathMessageTask extends BukkitRunnable {

    private final Random random = new Random();
    private final String[] deathMessages = {
            "{victim} was shot by arrow",
            "{victim} was shot by Skeleton",
            "{victim} was shot by {aggressor}",
            "{victim} was pricked to death",
            "{victim} hugged a cactus",
            "{victim} walked into a cactus while trying to escape {aggressor}",
            "{victim} drowned",
            "{victim} drowned while trying to escape {aggressor}",
            "{victim} experienced kinetic energy",
            "{victim} removed an Elytra while flying",
            "{victim} blew up",
            "{victim} was blown up by Creeper",
            "{victim} hit the ground too hard",
            "{victim} went up in flames",
            "{victim} burned to death",
            "{victim} was burnt to a crisp while fighting {aggressor}",
            "{victim} tried to swim in lava",
            "{victim} tried to swim in lava while trying to escape {aggressor}",
            "{victim} was slain by {aggressor}",
            "{victim} starved to death",
            "{victim} suffocated in a wall",
            "{victim} fell out of the world",
            "{victim} fell from a high place",
    };

    @Override
    public void run() {
        if (!Core.getCore().getNpcManager().getNpcMap().isEmpty()) {
            Set<String> savedNames = Core.getCore().getNpcManager().getNpcMap().keySet();
            int index = random.nextInt(savedNames.size()), selected = random.nextInt(deathMessages.length);
            FakePlayerList victim = FakePlayerList.valueOf((String) savedNames.toArray()[index]);
            FakePlayerList aggressor = FakePlayerList.valueOf((String) savedNames.toArray()[index-1 < 0 ? index + 1 : index - 1]);

            String msg = deathMessages[selected].replace("{victim}", victim.getName()).replace("{aggressor}", aggressor.getName());
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.WHITE + msg));
        }
    }
}