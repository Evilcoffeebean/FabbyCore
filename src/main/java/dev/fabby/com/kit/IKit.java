package dev.fabby.com.kit;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IKit {

    String getName();
    String getDisplayName();
    String getPermission();
    String[] getDescription();
    ItemStack getMenuItem();
    ItemStack[] getArmorContents();
    ItemStack[] getInventoryContents();
    long getCooldown();
    double getPrice();
    void execute(Player player);
    Sound error = Sound.BLOCK_NOTE_BLOCK_BASS, okay = Sound.ENTITY_PLAYER_LEVELUP;
}
