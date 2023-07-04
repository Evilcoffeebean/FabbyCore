package dev.fabby.com.tags;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TagPlayerManager {

    private static final Map<UUID, Tag> playerTags = new HashMap<>();

    public void setActiveTag(Player player, Tag tag) {
        playerTags.put(player.getUniqueId(), tag);
    }

    public void removeActiveTag(Player player) {
        playerTags.remove(player.getUniqueId());
    }

    public void clearActiveTags() {
        playerTags.clear();
    }

    public boolean hasActiveTag(Player player) {
        return playerTags.containsKey(player.getUniqueId());
    }

    public Tag getActiveTag(Player player) {
        return playerTags.get(player.getUniqueId());
    }
}
