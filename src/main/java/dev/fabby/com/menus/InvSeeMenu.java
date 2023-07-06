package dev.fabby.com.menus;

import dev.fabby.com.libraries.gui.GuiItem;
import dev.fabby.com.libraries.gui.manager.GUIPair;
import dev.fabby.com.libraries.gui.manager.Item;
import dev.fabby.com.libraries.gui.manager.TarvosGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class InvSeeMenu extends TarvosGUI {

    public InvSeeMenu(Player target) {
        super(6, Component.text(ChatColor.RED + target.getName() + "'s Inventory"), target, true, false);

        setItems(createItems(target));
        createAndOpen();
    }

    private HashMap<GUIPair, Integer> createItems(final Player target) {
        HashMap<GUIPair, Integer> items = new HashMap<>(); // 16 25 34 43 52 armour, 52 item in hand

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

        return items;
    }

    private ItemStack createNonExistingItem() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "No equipped item for this slot.");
        return Item.createItem(Material.BARRIER, "§cNo Item", lore);
    }
}
