package dev.fabby.com.crates;

import dev.fabby.com.Core;
import dev.fabby.com.crates.key.KeyItemBuilder;
import dev.fabby.com.crates.key.KeyType;
import dev.fabby.com.crates.menu.ShuffleMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CrateListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onClick(final PlayerInteractEvent e) {
        if (Core.getCore().getCrateManager().isEditing(e.getPlayer())) {
            if (e.getClickedBlock() == null || e.getClickedBlock().getType() != Material.CHEST)
                return;

            e.setCancelled(true);
            String name = Core.getCore().getCrateManager().getEditingCrate(e.getPlayer());
            Core.getCore().getCratesConfig().saveCrate(e.getPlayer(), e.getClickedBlock().getLocation(), name);
            Core.getCore().getCrateManager().removeEditor(e.getPlayer());
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
            return;
        }

        if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CHEST) {
            if (Core.getCore().getCratesConfig().isCrate(e.getClickedBlock())) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    final ICrate crate = Core.getCore().getCratesConfig().getCrateByLocation(e.getClickedBlock().getLocation());
                    if (crate != null) {
                        e.setCancelled(true);
                        final ItemStack key = KeyItemBuilder.getKey(crate.getKeyRequired());

                        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(key)) {
                            e.getPlayer().sendMessage(ChatColor.GREEN + "You must be holding the required crate key.");
                            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().normalize().setY(0.5).multiply(-1));
                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                            return;
                        }

                        e.getPlayer().getInventory().removeItem(key);
                        e.getPlayer().sendMessage(ChatColor.GREEN + crate.getName() + " selected, shuffling prize...");
                        new ShuffleMenu(e.getPlayer(), crate);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBreak(final BlockBreakEvent e) {
        try {
            if (Core.getCore().getCratesConfig().isCrate(e.getBlock())) {
                if (e.getPlayer().hasPermission("fabby.removecrate") || e.getPlayer().isOp())  {
                    final ICrate crate = Core.getCore().getCratesConfig().getCrateByLocation(e.getBlock().getLocation());
                    if (crate != null) {
                        Core.getCore().getCratesConfig().removeCrate(e.getPlayer(), crate.getName());
                        return;
                    }
                }
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlace(final BlockPlaceEvent e) {
        final ItemStack[] keys = {
                KeyItemBuilder.getKey(KeyType.COAL),
                KeyItemBuilder.getKey(KeyType.REDSTONE),
                KeyItemBuilder.getKey(KeyType.LAPIS),
                KeyItemBuilder.getKey(KeyType.IRON),
                KeyItemBuilder.getKey(KeyType.GOLD),
                KeyItemBuilder.getKey(KeyType.EMERALD),
                KeyItemBuilder.getKey(KeyType.DIAMOND),
                KeyItemBuilder.getKey(KeyType.NETHERITE),
        };

        if (e.getItemInHand().getType() == Material.TRIPWIRE_HOOK) {
            for (ItemStack item : keys) {
                if (e.getItemInHand().isSimilar(item))
                    e.setCancelled(true);
            }
        }
    }
}
