package dev.fabby.com.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class MenuUtil {

    private Inventory inventory;
    private String name;
    private int slots;

    public MenuUtil(String name, int slots) {
        this.name = name;
        this.slots = (slots % 9) == 0 ? slots : 54;
        this.inventory = Bukkit.createInventory(null, slots, StringUtil.color(name));
    }

    public abstract String[] getDesign();

    public String getName() {
        return this.name;
    }

    public int getSlots() {
        return this.slots;
    }

    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    public void build(Player caller) {
        caller.openInventory(inventory);
    }

    public void close(Player caller) {
        caller.closeInventory();
    }
}
