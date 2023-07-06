package dev.fabby.com.libraries.gui.components

import org.bukkit.event.Event

fun interface GuiAction<T : Event> {
    /**
     * Executes the task passed to it
     *
     * @param event Inventory action
     */
    fun execute(event: T)
}