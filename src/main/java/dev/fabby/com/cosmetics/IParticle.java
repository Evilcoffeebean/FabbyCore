package dev.fabby.com.cosmetics;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IParticle {

    String getPermission();
    boolean hasPerm(Player player);
    void execute(Player player, Location location);
}
