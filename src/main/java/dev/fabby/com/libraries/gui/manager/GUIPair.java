package dev.fabby.com.libraries.gui.manager;

import dev.fabby.com.libraries.gui.GuiItem;
import dev.fabby.com.libraries.gui.components.GuiAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public record GUIPair(GuiItem item, GuiAction<InventoryClickEvent> action) {

}