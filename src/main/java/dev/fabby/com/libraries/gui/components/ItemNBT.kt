package dev.fabby.com.libraries.gui.components

import org.bukkit.inventory.ItemStack

/**
 * Class to set / get NBT tags from items
 */
object ItemNBT {
    private val nbt = selectNbt()

    /**
     * Sets an NBT tag to the an [ItemStack].
     *
     * @param itemStack The current [ItemStack] to be set.
     * @param key       The NBT key to use.
     * @param value     The tag value to set.
     * @return An [ItemStack] that has NBT set.
     */
    @JvmStatic
    fun setString(itemStack: ItemStack, key: String, value: String): ItemStack {
        return nbt.setString(itemStack, key, value)
    }

    /**
     * Gets the NBT tag based on a given key.
     *
     * @param itemStack The [ItemStack] to get from.
     * @param key       The key to look for.
     * @return The tag that was stored in the [ItemStack].
     */
    @JvmStatic
    fun getString(itemStack: ItemStack, key: String): String? {
        return nbt.getString(itemStack, key)
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
    fun setBoolean(itemStack: ItemStack, key: String, value: Boolean): ItemStack {
        return nbt.setBoolean(itemStack, key, value)
    }

    /**
     * Removes a tag from an [ItemStack].
     *
     * @param itemStack The current [ItemStack] to be remove.
     * @param key       The NBT key to remove.
     * @return An [ItemStack] that has the tag removed.
     */
    fun removeTag(itemStack: ItemStack, key: String): ItemStack {
        return nbt.removeTag(itemStack, key)
    }

    /**
     * Selects which [NbtWrapper] to use based on server version.
     *
     * @return A [NbtWrapper] implementation, [Pdc] if version is higher than 1.14 and [LegacyNbt] if not.
     */
    private fun selectNbt(): NbtWrapper {
        return Pdc()
    }
}