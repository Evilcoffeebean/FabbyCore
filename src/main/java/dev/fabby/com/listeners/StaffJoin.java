package dev.fabby.com.listeners;

import dev.fabby.com.commands.staff.StaffChatCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StaffJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(e.getPlayer().hasPermission("fabby.staff.staffchat")) {
            StaffChatCommand.staffChat.add(e.getPlayer().getUniqueId());
            e.getPlayer().sendMessage("§aEnabled staff chat for you. To disable it, type §b/staffchat toggle§a.");
        }
    }

}
