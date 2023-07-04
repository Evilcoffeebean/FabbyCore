package dev.fabby.com.crates;

import dev.fabby.com.Core;
import dev.fabby.com.utils.FileUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CratesConfig extends FileUtil {

    public CratesConfig() {
        super(new File(Core.getCore().getDataFolder(), "crates-locations.yml"));
    }

    public void saveCrate(Player player, Location location, String name) {
        final ICrate crate = Core.getCore().getCrateManager().getCrate(name);
        if (crate == null) {
            player.sendMessage(ChatColor.GREEN + "'" + name + "' isn't a valid crate name.");
            return;
        }

        try {
            set("crates." + name + ".pos-x", location.getX(), true);
            set("crates." + name + ".pos-y", location.getY(), true);
            set("crates." + name + ".pos-z", location.getZ(), true);
            set("crates." + name + ".world-name", location.getWorld().getName(), true);

            player.sendMessage(ChatColor.GREEN + "Saving " + name + " Crate at specified location.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Location> getSavedLocations() {
        List<Location> result = new ArrayList<>();
        for (String path : getConfig().getConfigurationSection("crates").getKeys(false)) {
            double x = getDouble("crates." + path + ".pos-x");
            double y = getDouble("crates." + path + ".pos-y");
            double z = getDouble("crates." + path + ".pos-z");
            World world = Core.getCore().getServer().getWorld(getString("crates." + path + ".world-name"));

            result.add(new Location(world, x, y, z));
        }
        return result;
    }

    public boolean isCrate(Block block) {
        for (Location l : getSavedLocations()) {
            if (block.getLocation().equals(l))
                return true;
        }
        return false;
    }

    public void removeCrate(Player player, String name) {
        final ICrate crate = Core.getCore().getCrateManager().getCrate(name);
        if (crate == null) {
            player.sendMessage(ChatColor.GREEN + "'" + name + "' doesn't exist in the config.");
            return;
        }

        try {
            set("crates." + name, null, true);
            player.sendMessage(ChatColor.GREEN + "Removed " + name + " from config.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ICrate getCrateByLocation(Location location) {
        for (String key : getConfig().getConfigurationSection("crates").getKeys(false)) {
            double x = getDouble("crates." + key + ".pos-x");
            double y = getDouble("crates." + key + ".pos-y");
            double z = getDouble("crates." + key + ".pos-z");
            World world = Core.getCore().getServer().getWorld(getString("crates." + key + ".world-name"));
            Location saved = new Location(world, x, y, z);

            if (location.equals(saved))
                return Core.getCore().getCrateManager().getCrate(key);
        }
        return null;
    }

    public Location getCrateByName(String name) {
        if (get("crates." + name) == null)
            throw new NullPointerException("No crate saved by specified name '" + name + "'.");

        double x = getDouble("crates." + name + ".pos-x");
        double y = getDouble("crates." + name + ".pos-y");
        double z = getDouble("crates." + name + ".pos-z");
        World world = Core.getCore().getServer().getWorld(getString("crates." + name + ".world-name"));

        return new Location(world, x, y, z);
    }
}
