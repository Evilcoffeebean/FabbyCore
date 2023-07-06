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

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

/**
 * Class to set / get NBT tags from items.
 * I hate this class.
 */
class LegacyNbt : NbtWrapper {
    /**
     * Sets an NBT tag to the an [ItemStack].
     *
     * @param itemStack The current [ItemStack] to be set.
     * @param key       The NBT key to use.
     * @param value     The tag value to set.
     * @return An [ItemStack] that has NBT set.
     */
    override fun setString(itemStack: ItemStack, key: String, value: String): ItemStack {
        if (itemStack.type == Material.AIR) return itemStack
        val nmsItemStack = asNMSCopy(itemStack)
        val itemCompound = if (hasTag(nmsItemStack)) getTag(nmsItemStack) else newNBTTagCompound()
        setString(itemCompound, key, value)
        setTag(nmsItemStack, itemCompound)
        return asBukkitCopy(nmsItemStack)!!
    }

    /**
     * Removes a tag from an [ItemStack].
     *
     * @param itemStack The current [ItemStack] to be remove.
     * @param key       The NBT key to remove.
     * @return An [ItemStack] that has the tag removed.
     */
    override fun removeTag(itemStack: ItemStack, key: String): ItemStack {
        if (itemStack.type == Material.AIR) return itemStack
        val nmsItemStack = asNMSCopy(itemStack)
        val itemCompound = if (hasTag(nmsItemStack)) getTag(nmsItemStack) else newNBTTagCompound()
        remove(itemCompound, key)
        setTag(nmsItemStack, itemCompound)
        return asBukkitCopy(nmsItemStack)!!
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
        if (itemStack.type == Material.AIR) return itemStack
        val nmsItemStack = asNMSCopy(itemStack)
        val itemCompound = if (hasTag(nmsItemStack)) getTag(nmsItemStack) else newNBTTagCompound()
        setBoolean(itemCompound, key, value)
        setTag(nmsItemStack, itemCompound)
        return asBukkitCopy(nmsItemStack)!!
    }

    /**
     * Gets the NBT tag based on a given key.
     *
     * @param itemStack The [ItemStack] to get from.
     * @param key       The key to look for.
     * @return The tag that was stored in the [ItemStack].
     */
    override fun getString(itemStack: ItemStack, key: String): String? {
        if (itemStack.type == Material.AIR) return null
        val nmsItemStack = asNMSCopy(itemStack)
        val itemCompound = if (hasTag(nmsItemStack)) getTag(nmsItemStack) else newNBTTagCompound()
        return getString(itemCompound, key)
    }

    companion object {
        val PACKAGE_NAME = Bukkit.getServer().javaClass.getPackage().name
        val NMS_VERSION = PACKAGE_NAME.substring(PACKAGE_NAME.lastIndexOf(46.toChar()) + 1)
        private var getStringMethod: Method? = null
        private var setStringMethod: Method? = null
        private var setBooleanMethod: Method? = null
        private var hasTagMethod: Method? = null
        private var getTagMethod: Method? = null
        private var setTagMethod: Method? = null
        private var removeTagMethod: Method? = null
        private var asNMSCopyMethod: Method? = null
        private var asBukkitCopyMethod: Method? = null
        private var nbtCompoundConstructor: Constructor<*>? = null

        init {
            try {
                getStringMethod = Objects.requireNonNull(getNMSClass("NBTTagCompound"))!!
                    .getMethod("getString", String::class.java)
                removeTagMethod = Objects.requireNonNull(getNMSClass("NBTTagCompound"))!!
                    .getMethod("remove", String::class.java)
                setStringMethod = Objects.requireNonNull(getNMSClass("NBTTagCompound"))!!
                    .getMethod("setString", String::class.java, String::class.java)
                setBooleanMethod = Objects.requireNonNull(getNMSClass("NBTTagCompound"))!!
                    .getMethod("setBoolean", String::class.java, Boolean::class.javaPrimitiveType)
                hasTagMethod = Objects.requireNonNull(getNMSClass("ItemStack"))!!
                    .getMethod("hasTag")
                getTagMethod = Objects.requireNonNull(getNMSClass("ItemStack"))!!
                    .getMethod("getTag")
                setTagMethod = Objects.requireNonNull(getNMSClass("ItemStack"))!!
                    .getMethod("setTag", getNMSClass("NBTTagCompound"))
                nbtCompoundConstructor = Objects.requireNonNull(getNMSClass("NBTTagCompound"))!!
                    .getDeclaredConstructor()
                asNMSCopyMethod = Objects.requireNonNull(craftItemStackClass)!!
                    .getMethod("asNMSCopy", ItemStack::class.java)
                asBukkitCopyMethod = Objects.requireNonNull(craftItemStackClass)!!
                    .getMethod("asBukkitCopy", getNMSClass("ItemStack"))
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }
        }

        /**
         * Mimics the itemCompound#setString method.
         *
         * @param itemCompound The ItemCompound.
         * @param key          The key to add.
         * @param value        The value to add.
         */
        private fun setString(itemCompound: Any?, key: String, value: String) {
            try {
                setStringMethod!!.invoke(itemCompound, key, value)
            } catch (ignored: IllegalAccessException) {
            } catch (ignored: InvocationTargetException) {
            }
        }

        private fun setBoolean(itemCompound: Any?, key: String, value: Boolean) {
            try {
                setBooleanMethod!!.invoke(itemCompound, key, value)
            } catch (ignored: IllegalAccessException) {
            } catch (ignored: InvocationTargetException) {
            }
        }

        /**
         * Mimics the itemCompound#remove method.
         *
         * @param itemCompound The ItemCompound.
         * @param key          The key to remove.
         */
        private fun remove(itemCompound: Any?, key: String) {
            try {
                removeTagMethod!!.invoke(itemCompound, key)
            } catch (ignored: IllegalAccessException) {
            } catch (ignored: InvocationTargetException) {
            }
        }

        /**
         * Mimics the itemCompound#getString method.
         *
         * @param itemCompound The ItemCompound.
         * @param key          The key to get from.
         * @return A string with the value from the key.
         */
        private fun getString(itemCompound: Any?, key: String): String? {
            return try {
                getStringMethod!!.invoke(itemCompound, key) as String
            } catch (e: IllegalAccessException) {
                null
            } catch (e: InvocationTargetException) {
                null
            }
        }

        /**
         * Mimics the nmsItemStack#hasTag method.
         *
         * @param nmsItemStack the NMS ItemStack to check from.
         * @return True or false depending if it has tag or not.
         */
        private fun hasTag(nmsItemStack: Any?): Boolean {
            return try {
                hasTagMethod!!.invoke(nmsItemStack) as Boolean
            } catch (e: IllegalAccessException) {
                false
            } catch (e: InvocationTargetException) {
                false
            }
        }

        /**
         * Mimics the nmsItemStack#getTag method.
         *
         * @param nmsItemStack The NMS ItemStack to get from.
         * @return The tag compound.
         */
        fun getTag(nmsItemStack: Any?): Any? {
            return try {
                getTagMethod!!.invoke(nmsItemStack)
            } catch (e: IllegalAccessException) {
                null
            } catch (e: InvocationTargetException) {
                null
            }
        }

        /**
         * Mimics the nmsItemStack#setTag method.
         *
         * @param nmsItemStack the NMS ItemStack to set the tag to.
         * @param itemCompound The item compound to set.
         */
        private fun setTag(nmsItemStack: Any?, itemCompound: Any?) {
            try {
                setTagMethod!!.invoke(nmsItemStack, itemCompound)
            } catch (ignored: IllegalAccessException) {
            } catch (ignored: InvocationTargetException) {
            }
        }

        /**
         * Mimics the new NBTTagCompound instantiation.
         *
         * @return The new NBTTagCompound.
         */
        private fun newNBTTagCompound(): Any? {
            return try {
                nbtCompoundConstructor!!.newInstance()
            } catch (e: IllegalAccessException) {
                null
            } catch (e: InstantiationException) {
                null
            } catch (e: InvocationTargetException) {
                null
            }
        }

        /**
         * Mimics the CraftItemStack#asNMSCopy method.
         *
         * @param itemStack The ItemStack to make NMS copy.
         * @return An NMS copy of the ItemStack.
         */
        fun asNMSCopy(itemStack: ItemStack?): Any? {
            return try {
                asNMSCopyMethod!!.invoke(null, itemStack)
            } catch (e: IllegalAccessException) {
                null
            } catch (e: InvocationTargetException) {
                null
            }
        }

        /**
         * Mimics the CraftItemStack#asBukkitCopy method.
         *
         * @param nmsItemStack The NMS ItemStack to turn into [ItemStack].
         * @return The new [ItemStack].
         */
        fun asBukkitCopy(nmsItemStack: Any?): ItemStack? {
            return try {
                asBukkitCopyMethod!!.invoke(null, nmsItemStack) as ItemStack
            } catch (e: IllegalAccessException) {
                null
            } catch (e: InvocationTargetException) {
                null
            }
        }

        /**
         * Gets the NMS class from class name.
         *
         * @return The NMS class.
         */
        private fun getNMSClass(className: String): Class<*>? {
            return try {
                Class.forName("net.minecraft.server.$NMS_VERSION.$className")
            } catch (e: ClassNotFoundException) {
                null
            }
        }

        private val craftItemStackClass: Class<*>?
            /**
             * Gets the NMS craft [ItemStack] class from class name.
             *
             * @return The NMS craft [ItemStack] class.
             */
            get() = try {
                Class.forName("org.bukkit.craftbukkit.$NMS_VERSION.inventory.CraftItemStack")
            } catch (e: ClassNotFoundException) {
                null
            }
    }
}