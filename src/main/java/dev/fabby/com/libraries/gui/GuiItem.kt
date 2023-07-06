package dev.fabby.com.libraries.gui

import dev.fabby.com.libraries.gui.components.GuiAction
import dev.fabby.com.libraries.gui.components.ItemNBT.setString
import org.apache.commons.lang.Validate
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * GuiItem represents the [ItemStack] on the [Inventory]
 */
@Suppress("unused")
class GuiItem @JvmOverloads constructor(itemStack: ItemStack, action: GuiAction<InventoryClickEvent>? = null) {
    /**
     * Gets the [GuiAction] to do when the player clicks on it
     */
    //lateinit var action: GuiAction<InventoryClickEvent>


    /**
     * Replaces the [GuiAction] of the current GUI Item
     *
     * @param action The new [GuiAction] to set
     */
    // Action to do when clicking on the item
    var action: GuiAction<InventoryClickEvent>? = null

    // The ItemStack of the GuiItem
    private var itemStack: ItemStack

    /**
     * Gets the random [UUID] that was generated when the GuiItem was made
     */
    // Random UUID to identify the item when clicking
    @JvmField
    val uuid = UUID.randomUUID()
    /**
     * Fabby constructor of the GuiItem
     * @author Tarvos(?)
     * @param itemStack The [ItemStack] to be used
     * @param action    The [GuiAction] to run when clicking on the Item
     */
    /**
     * Secondary constructor with no action
     *
     * @param itemStack The ItemStack to be used
     */
    init {
        Validate.notNull(itemStack, "The ItemStack for the GUI Item cannot be null!")
        this.action = action

        // Sets the UUID to an NBT tag to be identifiable later
        this.itemStack = setString(itemStack, "fabby-gui", uuid.toString())
    }

    /**
     * Alternate constructor that takes [Material] instead of an [ItemStack] but without a [GuiAction]
     *
     * @param material The [Material] to be used when invoking class
     */
    constructor(material: Material) : this(ItemStack(material), null)

    /**
     * Alternate constructor that takes [Material] instead of an [ItemStack]
     *
     * @param material The `Material` to be used when invoking class
     * @param action   The [GuiAction] should be passed on [InventoryClickEvent]
     */
    constructor(material: Material, action: GuiAction<InventoryClickEvent>?) : this(ItemStack(material), action)

    /**
     * Replaces the [ItemStack] of the GUI Item
     *
     * @param itemStack The new [ItemStack]
     */
    fun setItemStack(itemStack: ItemStack) {
        Validate.notNull(itemStack, "The ItemStack for the GUI Item cannot be null!")
        this.itemStack = setString(itemStack, "fabby-gui", uuid.toString())
    }

    /**
     * Gets the GuiItem's [ItemStack]
     *
     * @return The [ItemStack]
     */
    fun getItemStack(): ItemStack {
        return itemStack
    }
}