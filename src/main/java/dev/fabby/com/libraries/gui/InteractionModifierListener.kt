/**
 * MIT License
 *
 * Copyright (c) 2021 TriumphTeam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.fabby.com.libraries.gui

import com.google.common.base.Preconditions
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryType
import java.util.*

/**
 * Listener that apply default GUI [InteractionModifier]s to all GUIs
 *
 * @since 3.0.0
 * @author SecretX
 */
class InteractionModifierListener : Listener {
    /**
     * Handles any click on GUIs, applying all [InteractionModifier] as required
     *
     * @param event The InventoryClickEvent
     * @since 3.0.0
     * @author SecretX
     */
    @EventHandler
    fun onGuiClick(event: InventoryClickEvent) {
        if (event.inventory.holder !is BaseGui) return

        // Gui
        val gui = event.inventory.holder as BaseGui

        // if player is trying to do a disabled action, cancel it
        if (!gui.canPlaceItems() && isPlaceItemEvent(event) || !gui.canTakeItems() && isTakeItemEvent(event) || !gui.canSwapItems() && isSwapItemEvent(
                event
            )
        ) {
            event.isCancelled = true
            event.result = Event.Result.DENY
        }
    }

    /**
     * Handles any item drag on GUIs, applying all [InteractionModifier] as required
     *
     * @param event The InventoryDragEvent
     * @since 3.0.0
     * @author SecretX
     */
    @EventHandler
    fun onGuiDrag(event: InventoryDragEvent) {
        if (event.inventory.holder !is BaseGui) return

        // Gui
        val gui = event.inventory.holder as BaseGui

        // if players are allowed to place items on the GUI, or player is not dragging on GUI, return
        if (gui.canPlaceItems() || !isDraggingOnGui(event)) return

        // cancel the interaction
        event.isCancelled = true
        event.result = Event.Result.DENY
    }

    /**
     * Checks if what is happening on the [InventoryClickEvent] is take an item from the GUI
     *
     * @param event The InventoryClickEvent
     * @return True if the [InventoryClickEvent] is for taking an item from the GUI
     * @since 3.0.0
     * @author SecretX
     */
    private fun isTakeItemEvent(event: InventoryClickEvent): Boolean {
        Preconditions.checkNotNull(event, "event cannot be null")
        val inventory = event.inventory
        val clickedInventory = event.clickedInventory
        val action = event.action

        // magic logic, simplified version of https://paste.helpch.at/tizivomeco.cpp
        return if (clickedInventory != null && clickedInventory.type == InventoryType.PLAYER || inventory.type == InventoryType.PLAYER) false else action == InventoryAction.MOVE_TO_OTHER_INVENTORY || isTakeAction(
            action
        )
    }

    /**
     * Checks if what is happening on the [InventoryClickEvent] is place an item on the GUI
     *
     * @param event The InventoryClickEvent
     * @return True if the [InventoryClickEvent] is for placing an item from the GUI
     * @since 3.0.0
     * @author SecretX
     */
    private fun isPlaceItemEvent(event: InventoryClickEvent): Boolean {
        Preconditions.checkNotNull(event, "event cannot be null")
        val inventory = event.inventory
        val clickedInventory = event.clickedInventory
        val action = event.action

        // shift click on item in player inventory
        return if (action == InventoryAction.MOVE_TO_OTHER_INVENTORY && clickedInventory != null && clickedInventory.type == InventoryType.PLAYER && inventory.type != clickedInventory.type) true else (isPlaceAction(
            action
        )
                && (clickedInventory == null || clickedInventory.type != InventoryType.PLAYER) && inventory.type != InventoryType.PLAYER)

        // normal click on gui empty slot with item on cursor
    }

    /**
     * Checks if what is happening on the [InventoryClickEvent] is swap any item with an item from the GUI
     *
     * @param event The InventoryClickEvent
     * @return True if the [InventoryClickEvent] is for swapping any item with an item from the GUI
     * @since 3.0.0
     * @author SecretX
     */
    private fun isSwapItemEvent(event: InventoryClickEvent): Boolean {
        Preconditions.checkNotNull(event, "event cannot be null")
        val inventory = event.inventory
        val clickedInventory = event.clickedInventory
        val action = event.action
        return (isSwapAction(action)
                && (clickedInventory == null || clickedInventory.type != InventoryType.PLAYER) && inventory.type != InventoryType.PLAYER)
    }

    /**
     * Checks if any item is being dragged on the GUI
     *
     * @param event The InventoryDragEvent
     * @return True if the [InventoryDragEvent] is for dragging an item inside the GUI
     * @since 3.0.0
     * @author SecretX
     */
    private fun isDraggingOnGui(event: InventoryDragEvent): Boolean {
        Preconditions.checkNotNull(event, "event cannot be null")
        val topSlots = event.view.topInventory.size
        // is dragging on any top inventory slot
        return event.rawSlots.stream().anyMatch { slot: Int -> slot < topSlots }
    }

    private fun isTakeAction(action: InventoryAction): Boolean {
        Preconditions.checkNotNull(action, "action cannot be null")
        return ITEM_TAKE_ACTIONS.contains(action)
    }

    private fun isPlaceAction(action: InventoryAction): Boolean {
        Preconditions.checkNotNull(action, "action cannot be null")
        return ITEM_PLACE_ACTIONS.contains(action)
    }

    private fun isSwapAction(action: InventoryAction): Boolean {
        Preconditions.checkNotNull(action, "action cannot be null")
        return action == InventoryAction.SWAP_WITH_CURSOR
    }

    companion object {
        /**
         * Holds all the actions that should be considered "take" actions
         */
        private val ITEM_TAKE_ACTIONS = Collections.unmodifiableSet(
            EnumSet.of(
                InventoryAction.PICKUP_ONE,
                InventoryAction.PICKUP_SOME,
                InventoryAction.PICKUP_HALF,
                InventoryAction.PICKUP_ALL,
                InventoryAction.COLLECT_TO_CURSOR,
                InventoryAction.HOTBAR_SWAP
            )
        )

        /**
         * Holds all the actions that should be considered "place" actions
         */
        private val ITEM_PLACE_ACTIONS = Collections.unmodifiableSet(
            EnumSet.of(
                InventoryAction.PLACE_ONE,
                InventoryAction.PLACE_SOME,
                InventoryAction.PLACE_ALL
            )
        )
    }
}
