package dev.fabby.com.listeners;

import dev.fabby.com.Core;
import dev.fabby.com.commands.staff.StaffChatCommand;
import dev.fabby.com.libraries.api.API;
import dev.fabby.com.libraries.player.callback.LoadReason;
import dev.fabby.com.libraries.player.callback.PlayerLoaderService;
import dev.fabby.com.utils.Task;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {


    private final API api = Core.getCore().getApi();
    private final Task task = Core.getCore().getTaskManager();

    @EventHandler
    public void onPlayerPreJoin(AsyncPlayerPreLoginEvent e) {
        var playerExists = api.playerExists(e.getUniqueId());
        if (!playerExists) {
            api.createPlayer(e.getUniqueId(), e.getAddress(), e.getName());
        }

        if (e.getLoginResult() == AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            PlayerLoaderService.loadPlayer(e.getUniqueId(), LoadReason.PLAYER_CONNECTED, fabbyPlayer -> {
                if (fabbyPlayer.isLoaded()) {
                    fabbyPlayer.setOnline(true);
                    fabbyPlayer.setLoaded(true);
                    task.runSync(() -> Core.getCore().addFabbyPlayer(fabbyPlayer));
                    e.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
                }
            });

        }

    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("fabby.staff.staffchat")) {
            StaffChatCommand.staffChat.add(e.getPlayer().getUniqueId());
            e.getPlayer().sendMessage("§aEnabled staff chat for you. To disable it, type §b/staffchat toggle§a.");
        }
    }

}
