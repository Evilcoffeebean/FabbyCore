package dev.fabby.com.tags.menu;

import dev.fabby.com.Core;
import dev.fabby.com.tags.Tag;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TagMenuListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onTagSelect(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (!(e.getWhoClicked() instanceof Player))
            return;
        final Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Available Tags")) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {
                case 10:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.COOL_PLAYER.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.COOL_PLAYER);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 11:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.WARRIOR.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.WARRIOR);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 12:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.GARDENER.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.GARDENER);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 13:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.BOTANIST.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.BOTANIST);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 14:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.BREWER.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.BREWER);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 15:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.KINGPIN.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.KINGPIN);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 16:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.BEAUTY.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.BEAUTY);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 19:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.LATE_BLOOMER.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.LATE_BLOOMER);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 20:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.MYSTERIOUS.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.MYSTERIOUS);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 21:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.DAB.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.DAB);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 22:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.COOK.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.COOK);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 23:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.SWEET.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.SWEET);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 24:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.SALTY.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.SALTY);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 25:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.ANGRY.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.ANGRY);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 28:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.SICK.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.SICK);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 29:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.MALICIOUS.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.MALICIOUS);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 30:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.OPTIMISTIC.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.OPTIMISTIC);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 31:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.DEAD_INSIDE.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.DEAD_INSIDE);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 32:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.GRIEFER.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.GRIEFER);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 33:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.TOXIC.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.TOXIC);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 34:
                    player.closeInventory();
                    if (!player.hasPermission(Tag.BOSS.getId())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You don't have permission to equip this Tag.");
                        return;
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().setActiveTag(player, Tag.BOSS);
                    player.sendMessage(ChatColor.AQUA + "Successfully equipped Tag.");
                    return;
                case 40:
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    Core.getCore().getTagPlayerManager().removeActiveTag(player);
                    player.sendMessage(ChatColor.AQUA + "Removed active tag.");
                    return;
            }
        }
    }
}
