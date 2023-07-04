package dev.fabby.com.crates.menu;

import dev.fabby.com.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainCratesMenuListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onCrateSelect(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
            return;

        if (!(e.getWhoClicked() instanceof Player))
            return;

        final Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Select a crate")) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {
                case 11:
                    p.sendMessage(ChatColor.GREEN + "Coal Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Coal"));
                    return;
                case 13:
                    p.sendMessage(ChatColor.GREEN + "Redstone Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Redstone"));
                    return;
                case 15:
                    p.sendMessage(ChatColor.GREEN + "Lapis Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Lapis"));
                    return;
                case 20:
                    p.sendMessage(ChatColor.GREEN + "Iron Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Iron"));
                    return;
                case 22:
                    p.sendMessage(ChatColor.GREEN + "Gold Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Gold"));
                    return;
                case 24:
                    p.sendMessage(ChatColor.GREEN + "Emerald Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Emerald"));
                    return;
                case 30:
                    p.sendMessage(ChatColor.GREEN + "Diamond Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Diamond"));
                    return;
                case 32:
                    p.sendMessage(ChatColor.GREEN + "Netherite Crate selected, selecting prize...");
                    new ShuffleMenu(p, Core.getCore().getCrateManager().getCrate("Netherite"));
                    return;
            }
        }
    }
}
