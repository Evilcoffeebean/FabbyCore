package dev.fabby.com.bossbar;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DefaultServerBar extends FabbyBar implements Listener {

    public DefaultServerBar() {
        super("&a&lServer is officially released!", BarColor.GREEN, BarStyle.SEGMENTED_20);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onJoin(final PlayerJoinEvent e) {
        if (e.getPlayer().getWorld().getName().equalsIgnoreCase("spawn")) {
            send(e.getPlayer());
        }
    }
}
