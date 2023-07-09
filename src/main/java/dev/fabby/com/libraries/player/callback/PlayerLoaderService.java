package dev.fabby.com.libraries.player.callback;

import dev.fabby.com.Core;
import dev.fabby.com.libraries.player.FabbyPlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class PlayerLoaderService {

    public static void loadPlayer(UUID uuid, LoadReason reason, PlayerLoader loader) {
        new BukkitRunnable() {
            @Override
            public void run() {
                final FabbyPlayer player = new FabbyPlayer(Core.getCore().getApi().getPlayerInfo(uuid), reason);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.isLoaded()) {
                            loader.onPlayerLoad(player);
                            this.cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(Core.getCore(), 0L, 2L);
            }
        }.runTask(Core.getCore());
    }
}
