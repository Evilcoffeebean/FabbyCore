package dev.fabby.com.libraries.gui.components

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import dev.fabby.com.libraries.gui.GuiItem
import org.apache.commons.lang.Validate
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import java.lang.reflect.Field
import java.util.*

@Suppress("unused")
class ItemBuilder @Deprecated("Use {@link ItemBuilder#from(ItemStack)} instead, it's more idiomatic for a builder") constructor(
    itemStack: ItemStack
) {
    private var itemStack: ItemStack
    private var meta: ItemMeta?

    /**
     * Constructor of the item builder
     *
     * @param itemStack The [ItemStack] of the item
     */
    init {
        Validate.notNull(itemStack, "Item can't be null!")
        this.itemStack = itemStack
        meta = if (itemStack.hasItemMeta()) itemStack.itemMeta else Bukkit.getItemFactory().getItemMeta(itemStack.type)
    }

    /**
     * Alternative constructor with [Material]
     *
     * @param material The [Material] of the [ItemStack]
     */
    @Deprecated("Use {@link ItemBuilder#from(Material)} instead, it's more idiomatic for a builder")
    constructor(material: Material?) : this(ItemStack(material!!))

    /**
     * Builds the item into [ItemStack]
     *
     * @return The fully built [ItemStack]
     */
    fun build(): ItemStack {
        itemStack.setItemMeta(meta)
        return itemStack
    }

    fun asGuiItem(): GuiItem {
        return GuiItem(build())
    }

    fun asGuiItem(action: GuiAction<InventoryClickEvent>): GuiItem {
        return GuiItem(build(), action)
    }

    /**
     * Set display name of the item
     *
     * @param name the display name of the item
     * @return [ItemBuilder]
     */
    fun setName(name: String): ItemBuilder {
        meta!!.setDisplayName(name)
        return this
    }

    /**
     * Sets the amount of items
     *
     * @param amount the amount of items
     * @return [ItemBuilder]
     */
    fun setAmount(amount: Int): ItemBuilder {
        itemStack.amount = amount
        return this
    }

    /**
     * Set the lore lines of an item
     *
     * @param lore the lore lines to set
     * @return [ItemBuilder]
     */
    fun setLore(vararg lore: String): ItemBuilder {
        meta!!.lore = Arrays.asList(*lore)
        return this
    }

    /**
     * Set the lore lines of an item
     *
     * @param lore A [List] with the lore lines
     * @return [ItemBuilder]
     */
    fun setLore(lore: List<String>): ItemBuilder {
        meta!!.lore = lore
        return this
    }
    /**
     * Add enchantment to an item
     *
     * @param enchantment            the [Enchantment] to add
     * @param level                  the level of the [Enchantment]
     * @param ignoreLevelRestriction If should or not ignore it
     * @return [ItemBuilder]
     */
    /**
     * Add enchantment to an item
     *
     * @param enchantment the [Enchantment] to add
     * @return [ItemBuilder]
     */
    /**
     * Add enchantment to an item
     *
     * @param enchantment the [Enchantment] to add
     * @param level       the level of the [Enchantment]
     * @return [ItemBuilder]
     */
    @JvmOverloads
    fun addEnchantment(enchantment: Enchantment, level: Int = 1, ignoreLevelRestriction: Boolean = true): ItemBuilder {
        meta!!.addEnchant(enchantment, level, ignoreLevelRestriction)
        return this
    }

    /**
     * Removes a certain [Enchantment] from the item
     *
     * @param enchantment The [Enchantment] to remove
     * @return [ItemBuilder]
     */
    fun removeEnchantment(enchantment: Enchantment): ItemBuilder {
        itemStack.removeEnchantment(enchantment)
        return this
    }

    /**
     * Add a custom [ItemFlag] to the item
     *
     * @param flags the [ItemFlag] to add
     * @return [ItemBuilder]
     */
    fun addItemFlags(vararg flags: ItemFlag): ItemBuilder {
        meta!!.addItemFlags(*flags)
        return this
    }

    /**
     * Sets the item as unbreakable
     *
     * @param unbreakable If should or not be unbreakable
     * @return [ItemBuilder]
     */
    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        if (ServerVersion.CURRENT_VERSION.isOlderThan(ServerVersion.V1_12_R1)) {
            throw GuiException("setUnbreakable is not supported on versions below 1.12!")
        }
        meta!!.isUnbreakable = true
        return this
    }

    /**
     * Makes the Item glow
     *
     * @param glow Should the item glow
     * @return [ItemBuilder]
     */
    fun glow(glow: Boolean): ItemBuilder {
        if (glow) {
            meta!!.addEnchant(Enchantment.LURE, 1, false)
            meta!!.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            return this
        }
        for (enchantment in meta!!.enchants.keys) {
            meta!!.removeEnchant(enchantment!!)
        }
        return this
    }

    /**
     * Sets the skull texture
     *
     * @param texture The base64 texture
     * @return [ItemBuilder]
     */
    fun setSkullTexture(texture: String): ItemBuilder {
        if (itemStack.type != XMaterial.PLAYER_HEAD.parseMaterial()) return this
        val skullMeta = meta as SkullMeta?
        val profile = GameProfile(UUID.randomUUID(), null)
        profile.properties.put("textures", Property("textures", texture))
        val profileField: Field
        try {
            profileField = skullMeta!!.javaClass.getDeclaredField("profile")
            profileField.isAccessible = true
            profileField[skullMeta] = profile
        } catch (ex: NoSuchFieldException) {
            ex.printStackTrace()
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        } catch (ex: IllegalAccessException) {
            ex.printStackTrace()
        }
        meta = skullMeta
        return this
    }

    /**
     * Sets skull owner via bukkit methods
     *
     * @param player [OfflinePlayer] to set skull of
     * @return [ItemBuilder]
     */
    fun setSkullOwner(player: OfflinePlayer): ItemBuilder {
        if (itemStack.type != XMaterial.PLAYER_HEAD.parseMaterial()) return this
        val skullMeta = meta as SkullMeta?
        skullMeta!!.setOwningPlayer(player)
        meta = skullMeta
        return this
    }

    /**
     * Sets NBT tag to the [ItemStack]
     *
     * @param key   The NBT key
     * @param value The NBT value
     * @return [ItemBuilder]
     */
    fun setNbt(key: String, value: String): ItemBuilder {
        itemStack = ItemNBT.setString(itemStack, key, value)
        return this
    }

    companion object {
        /**
         * TarvosCore method to create [ItemBuilder]
         *
         * @param itemStack The [ItemStack] you want to edit
         * @return A new [ItemBuilder]
         */
        fun from(itemStack: ItemStack): ItemBuilder {
            return ItemBuilder(itemStack)
        }

        /**
         * Alternative method to create [ItemBuilder]
         *
         * @param material The [Material] you want to create an item from
         * @return A new [ItemBuilder]
         */
        fun from(material: Material): ItemBuilder {
            return ItemBuilder(material)
        }
    }
}