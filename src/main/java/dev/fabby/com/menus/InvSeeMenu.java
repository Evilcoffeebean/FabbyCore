package dev.fabby.com.menus;

import dev.fabby.com.libraries.gui.GuiItem;
import dev.fabby.com.libraries.gui.components.ItemBuilder;
import dev.fabby.com.libraries.gui.manager.GUIPair;
import dev.fabby.com.libraries.gui.manager.Item;
import dev.fabby.com.libraries.gui.manager.FabbyGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class InvSeeMenu extends FabbyGUI {

    public InvSeeMenu(Player p, Player target) {
        super(6, "§aInventory of player §b" + ChatColor.stripColor(target.getName()), p, true, true);

        setItems(createItems(target));
        createAndOpen();
    }

    private HashMap<GUIPair, Integer> createItems(final Player target) {
        HashMap<GUIPair, Integer> items = new HashMap<>(); // 16 25 34 43 52 armour, 52 item in hand
        final Inventory inventory = target.getInventory();

        for (int i = 0; i < 6; i++) {
            items.put(new GUIPair(new GuiItem(inventory.getItem(i) != null ? inventory.getItem(i) : createNonExistingItem()), event -> {

            }), i);
        }

        for (int i = 9; i < 15; i++) {
            items.put(new GUIPair(new GuiItem(inventory.getItem(i - 3) != null ? inventory.getItem(i - 3) : createNonExistingItem()), event -> {

            }), i);
        }

        for (int i = 18; i < 24; i++) {
            items.put(new GUIPair(new GuiItem(inventory.getItem(i - 6) != null ? inventory.getItem(i - 6) : createNonExistingItem()), event -> {

            }), i);
        }

        for (int i = 27; i < 33; i++) {
            items.put(new GUIPair(new GuiItem(inventory.getItem(i - 9) != null ? inventory.getItem(i - 9) : createNonExistingItem()), event -> {

            }), i);
        }

        for (int i = 36; i < 42; i++) {
            items.put(new GUIPair(new GuiItem(inventory.getItem(i - 12) != null ? inventory.getItem(i - 12) : createNonExistingItem()), event -> {

            }), i);
        }

        for (int i = 45; i < 51; i++) {
            items.put(new GUIPair(new GuiItem(inventory.getItem(i - 15) != null ? inventory.getItem(i - 15) : createNonExistingItem()), event -> {

            }), i);
        }



        items.put(new GUIPair(new GuiItem(createPlayerInfoItem(target)), event -> {
            getPlayer().teleport(target);
            getPlayer().sendMessage(ChatColor.GREEN + "You have teleported to §a" + target.getName() + "§a!");

        }), 7);

        items.put(new GUIPair(new GuiItem(target.getInventory().getHelmet() != null ? target.getInventory().getHelmet() : createNonExistingItem()), event -> {

        }), 16);

        items.put(new GUIPair(new GuiItem(target.getInventory().getChestplate() != null ? target.getInventory().getChestplate() : createNonExistingItem()), event -> {

        }), 25);

        items.put(new GUIPair(new GuiItem(target.getInventory().getLeggings() != null ? target.getInventory().getLeggings() : createNonExistingItem()), event -> {

        }), 34);

        items.put(new GUIPair(new GuiItem(target.getInventory().getBoots() != null ? target.getInventory().getBoots() : createNonExistingItem()), event -> {

        }), 43);

        items.put(new GUIPair(new GuiItem(target.getInventory().getItemInMainHand().getType() != Material.AIR ? target.getInventory().getItemInMainHand() : createNonExistingItem()), event -> {

        }), 52);

        items.put(new GUIPair(new GuiItem(target.getInventory().getItemInOffHand().getType() != Material.AIR ? target.getInventory().getItemInOffHand() : createNonExistingItem()), event -> {

        }), 53);

        return items;
    }

    private ItemStack createNonExistingItem() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "No equipped item for this slot.");
        return Item.createItem(Material.BARRIER, "§aNo Item", lore);
    }

    private ItemStack createPlayerInfoItem(Player target) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "§aName: §b" + ChatColor.stripColor(target.getDisplayName()));
        lore.add(ChatColor.GRAY + "§aHealth: §b" + target.getHealth());
        lore.add(ChatColor.GRAY + "§aFood: §b" + target.getFoodLevel());
        lore.add(ChatColor.GRAY + "§aGamemode: §b" + target.getGameMode().name().substring(0, 1).toUpperCase() + target.getGameMode().name().substring(1).toLowerCase());
        lore.add(ChatColor.GRAY + "§aLocation: §b" + target.getLocation().getBlockX() + "§a, §b" + target.getLocation().getBlockY() + "§a, §b" + target.getLocation().getBlockZ());
        lore.add(ChatColor.GRAY + "§aWorld: §b" + target.getWorld().getName());
        lore.add("");
        lore.add(ChatColor.GRAY + "§aClick to teleport to this player.");
        return ItemBuilder.from(Material.PLAYER_HEAD).setSkullOwner(target).setName("§aPlayer Info").setLore(lore).build();

    }
}
