package dev.fabby.com.crates;

import dev.fabby.com.crates.key.KeyType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public interface ICrate {

    Random random = new Random();
    Sound spin = Sound.BLOCK_NOTE_BLOCK_PLING, win = Sound.ENTITY_PLAYER_LEVELUP, error = Sound.BLOCK_NOTE_BLOCK_BASS;

    String getName();
    KeyType getKeyRequired(); //TODO: check for key in player inv
    KeyType getKeyReward();
    ItemStack[] getItemRewards();
    Integer[] getMoneyVouchers();
    Integer[] getDirectPayouts();
    String getRankVoucher();

    //TODO: add random kit item reward - Essentials
    void execute(Player player);
}
