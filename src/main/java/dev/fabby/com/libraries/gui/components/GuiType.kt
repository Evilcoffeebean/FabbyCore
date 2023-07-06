package dev.fabby.com.libraries.gui.components

import org.bukkit.event.inventory.InventoryType


/**
 * Credits: SecretX / Tarvos / TriumphMC (Whoever did it)
 */
enum class GuiType(val inventoryType: InventoryType, val limit: Int) {
    CHEST(InventoryType.CHEST, 9),
    WORKBENCH(InventoryType.WORKBENCH, 9),
    HOPPER(InventoryType.HOPPER, 4),
    DISPENSER(InventoryType.DISPENSER, 8),
    BREWING(InventoryType.BREWING, 4)

}