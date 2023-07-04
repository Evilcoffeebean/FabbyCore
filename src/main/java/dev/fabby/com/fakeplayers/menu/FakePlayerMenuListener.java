package dev.fabby.com.fakeplayers.menu;

import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class FakePlayerMenuListener implements Listener {

    private final Sound JOIN_SOUND = Sound.BLOCK_NOTE_BLOCK_PLING;
    private final String JOIN_FORMAT = "&7[&a+&7] {rank} {name}", QUIT_FORMAT = "&7[&c-&7] {rank} {name}";

    private void addGlow(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.addEnchant(Enchantment.LURE, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);
    }

    private void removeGlow(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.removeEnchant(Enchantment.LURE);
        meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onClick(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        if (!(e.getWhoClicked() instanceof Player)) return;

        final Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().contains("Spoofer Module")) {
            e.setCancelled(true);

            //bulk join
            if (e.getRawSlot() == 43) {
                player.closeInventory();
                for (FakePlayerList fake : FakePlayerList.values()) {
                    if (!Core.getCore().getNpcManager().isSaved(fake.getName())) {
                        Core.getCore().getNpcManager().saveNpc(player.getWorld(), fake.getName());
                        Bukkit.getOnlinePlayers().forEach(online -> {
                            String rank = Core.getCore().getNpcManager().formatRank(fake.getRank());
                            String display = fake.getDisplayName();
                            online.playSound(online.getLocation(), JOIN_SOUND, 1f, 1f);
                            online.sendMessage(StringUtil.color(JOIN_FORMAT.replace("{rank}", rank).replace("{name}", display)));
                        });
                    } else {
                        player.sendMessage(ChatColor.AQUA + fake.getName() + " is already saved, skipping...");
                    }
                }
                return;
            }

            //bulk leave
            if (e.getRawSlot() == 44) {
                player.closeInventory();
                for (String name : Core.getCore().getNpcManager().getNpcMap().keySet()) {
                    FakePlayerList fake = FakePlayerList.valueOf(name);
                    String rank = Core.getCore().getNpcManager().formatRank(fake.getRank());
                    String display = fake.getDisplayName();
                    Bukkit.getOnlinePlayers().forEach(online -> {
                        online.sendMessage(StringUtil.color(QUIT_FORMAT.replace("{rank}", rank).replace("{name}", display)));
                    });
                }
                Core.getCore().getNpcManager().clearNpc();
                return;
            }

            FakePlayerList selected;
            try {
                selected = FakePlayerList.values()[e.getRawSlot()];

                if (selected != null) {
                    if (!Core.getCore().getNpcManager().isSaved(selected.getName())) {
                        addGlow(Objects.requireNonNull(e.getCurrentItem()));
                        Core.getCore().getNpcManager().saveNpc(player.getWorld(), selected.getName());
                        Bukkit.getOnlinePlayers().forEach(online -> {
                            String rank = Core.getCore().getNpcManager().formatRank(selected.getRank());
                            String display = selected.getDisplayName();
                            online.playSound(online.getLocation(), JOIN_SOUND, 1f, 1f);
                            online.sendMessage(StringUtil.color(JOIN_FORMAT.replace("{rank}", rank).replace("{name}", display)));
                        });
                    } else {
                        removeGlow(Objects.requireNonNull(e.getCurrentItem()));
                        Core.getCore().getNpcManager().removeNpc(selected.getName());
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        Bukkit.getOnlinePlayers().forEach(online -> {
                            String rank = Core.getCore().getNpcManager().formatRank(selected.getRank());
                            String display = selected.getDisplayName();
                            online.sendMessage(StringUtil.color(QUIT_FORMAT.replace("{rank}", rank).replace("{name}", display)));
                        });
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                Bukkit.getLogger().warning("ArrayIndexOutOfBounds exception noted - message for stockfish.");
            }
        }
    }
}
