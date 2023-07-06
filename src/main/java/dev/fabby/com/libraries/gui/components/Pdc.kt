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
package dev.fabby.com.libraries.gui.components

import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

/**
 * Wrapper for compatibility with [LegacyNbt].
 * This ideally wouldn't need exist, but legacy.
 */
class Pdc : NbtWrapper {
    /**
     * Sets a String NBT tag to the an [ItemStack].
     *
     * @param itemStack The current [ItemStack] to be set.
     * @param key       The NBT key to use.
     * @param value     The tag value to set.
     * @return An [ItemStack] that has NBT set.
     */
    override fun setString(itemStack: ItemStack, key: String, value: String): ItemStack {
        val meta = itemStack.itemMeta ?: return itemStack
        meta.persistentDataContainer.set(NamespacedKey(PLUGIN, key), PersistentDataType.STRING, value)
        itemStack.setItemMeta(meta)
        return itemStack
    }

    /**
     * Removes a tag from an [ItemStack].
     *
     * @param itemStack The current [ItemStack] to be removed.
     * @param key       The NBT key to remove.
     * @return An [ItemStack] that has the tag removed.
     */
    override fun removeTag(itemStack: ItemStack, key: String): ItemStack {
        val meta = itemStack.itemMeta ?: return itemStack
        meta.persistentDataContainer.remove(NamespacedKey(PLUGIN, key))
        itemStack.setItemMeta(meta)
        return itemStack
    }

    /**
     * Sets a boolean to the [ItemStack].
     * Mainly used for setting an item to be unbreakable on older versions.
     *
     * @param itemStack The [ItemStack] to set the boolean to.
     * @param key       The key to use.
     * @param value     The boolean value.
     * @return An [ItemStack] with a boolean value set.
     */
    override fun setBoolean(itemStack: ItemStack, key: String, value: Boolean): ItemStack {
        val meta = itemStack.itemMeta ?: return itemStack
        meta.persistentDataContainer.set(
            NamespacedKey(PLUGIN, key),
            PersistentDataType.BYTE,
            if (value) 1.toByte() else 0
        )
        itemStack.setItemMeta(meta)
        return itemStack
    }

    /**
     * Gets the NBT tag based on a given key.
     *
     * @param itemStack The [ItemStack] to get from.
     * @param key       The key to look for.
     * @return The tag that was stored in the [ItemStack].
     */
    override fun getString(itemStack: ItemStack, key: String): String? {
        val meta = itemStack.itemMeta ?: return null
        return meta.persistentDataContainer.get(NamespacedKey(PLUGIN, key), PersistentDataType.STRING)
    }

    companion object {
        /**
         * Plugin instance required for the [NamespacedKey].
         */
        private val PLUGIN: Plugin = JavaPlugin.getProvidingPlugin(Pdc::class.java)
    }
}
