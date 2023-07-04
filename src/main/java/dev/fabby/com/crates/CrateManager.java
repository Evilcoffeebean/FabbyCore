package dev.fabby.com.crates;

import com.google.common.collect.Maps;
import dev.fabby.com.crates.crate.*;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public final class CrateManager {

    private static final Map<String, ICrate> crateMap = Maps.newConcurrentMap();
    private final Map<UUID, String> crateEditorMap = Maps.newConcurrentMap();

    public CrateManager() {
        //TODO: Cache all crates
        crateMap.put("Coal", new CoalCrate());
        crateMap.put("Redstone", new RedstoneCrate());
        crateMap.put("Lapis", new LapisCrate());
        crateMap.put("Iron", new IronCrate());
        crateMap.put("Gold", new GoldCrate());
        crateMap.put("Emerald", new EmeraldCrate());
        crateMap.put("Diamond", new DiamondCrate());
        crateMap.put("Netherite", new NetheriteCrate());
    }

    public ICrate getCrate(String name) {
        return crateMap.get(name);
    }

    public void clearAllCrates() {
        crateMap.clear();
    }

    public void addEditor(Player player, String crate) {
        crateEditorMap.put(player.getUniqueId(), crate);
    }

    public void removeEditor(Player player) {
        crateEditorMap.remove(player.getUniqueId());
    }

    public String getEditingCrate(Player player) {
        return crateEditorMap.get(player.getUniqueId());
    }

    public boolean isEditing(Player player) {
        return crateEditorMap.containsKey(player.getUniqueId());
    }

    public void clearCrateEditor() {
        crateEditorMap.clear();
    }
}
