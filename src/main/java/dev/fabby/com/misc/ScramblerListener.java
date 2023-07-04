package dev.fabby.com.misc;

import dev.fabby.com.Core;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ScramblerListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onChat(final AsyncPlayerChatEvent e) {
        Scrambler scrambler = Core.getCore().getScrambler();
        //
    }
}
