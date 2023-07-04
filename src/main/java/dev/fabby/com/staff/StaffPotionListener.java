package dev.fabby.com.staff;

import dev.fabby.com.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class StaffPotionListener implements Listener {

    private final StaffPotionEffectMenu menu = new StaffPotionEffectMenu();
    private final UUID deathibring = UUID.fromString("5507bef2-cd33-4a66-8123-101ca4f73c31"), stipebno = UUID.fromString("ed97785d-2dc2-4106-abd1-2bc44f6d264f");
    private final String[] activate = {"!staff", "!effects", "!potions"};

    @EventHandler (priority = EventPriority.LOW)
    public void onActivate(final AsyncPlayerChatEvent e) {
        for (String s : activate) {
            if (e.getMessage().equals(s)) {
                if (e.getPlayer().getUniqueId().equals(deathibring) || e.getPlayer().getUniqueId().equals(stipebno)) {
                    e.setCancelled(true);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getServer().getScheduler().scheduleSyncDelayedTask(Core.getCore(), () -> {
                        menu.buildAndOpen(e.getPlayer());
                    });
                    return;
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onClick(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
            return;

        if (!(e.getWhoClicked() instanceof Player))
            return;

        final Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Staff Effects Menu")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE)
                return;

            if (e.getCurrentItem().getType() != Material.BARRIER) {
                menu.applyEffect(p, e.getCurrentItem());
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                p.sendMessage(ChatColor.GREEN + "Applying specified potion effect.");
            } else {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                p.getActivePotionEffects().forEach(potion -> p.removePotionEffect(potion.getType()));
                p.sendMessage(ChatColor.GREEN + "Removing all active potion effects.");
            }
            return;
        }
    }
}
