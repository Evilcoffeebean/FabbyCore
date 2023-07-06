package dev.fabby.com.libraries.gui

import dev.fabby.com.libraries.gui.components.InteractionModifier
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.math.ceil

/**
 * GUI that allows you to have multiple pages
 */
@Suppress("unused")
class PaginatedGui
/**
 * Main constructor to provide a way to create PaginatedGui
 *
 * @param rows                 The amount of rows the GUI should have
 * @param pageSize             The page size.
 * @param title                The GUI's title using [Component]
 * @author James
 * @since Tarvos
 */(
    rows: Int,
    /**
     * Sets the page size
     *
     * @param pageSize The new page size
     */
    private var pageSize: Int, title: Component
) : BaseGui(rows, title, HashSet(Arrays.stream(InteractionModifier.entries.toTypedArray()).toList())) {
    // List with all the page items
    private val pageItems: MutableList<GuiItem?> = ArrayList()

    // Saves the current page items and it's slot
    private val currentPage: MutableMap<Int, GuiItem?> = LinkedHashMap()
    /**
     * Gets the page size
     *
     * @return The page size
     */
    /**
     * Gets the page number
     *
     * @return The current page number
     */
    /**
     * Sets the page number
     *
     * @param pageNum Sets the current page to be the specified number
     */
    /**
     * Gets the current page number
     *
     * @return The current page number
     */
    private var currentPageNum = 1

    /**
     * Adds an [GuiItem] to the next available slot in the page area
     *
     * @param item The [GuiItem] to add to the page
     */
    fun addItem(item: GuiItem) {
        pageItems.add(item)
    }

    /**
     * Overridden [BaseGui.addItem] to add the items to the page instead
     *
     * @param items Varargs for specifying the [GuiItem]s
     */
    override fun addItem(vararg items: GuiItem) {
        pageItems.addAll(Arrays.asList(*items))
    }

    /**
     * Overridden [BaseGui.update] to use the paginated open
     */
    override fun update() {
        inventory.clear()
        populateGui()
        updatePage()
    }

    /**
     * Updates the page [GuiItem] on the slot in the page
     * Can get the slot from [InventoryClickEvent.getSlot]
     *
     * @param slot      The slot of the item to update
     * @param itemStack The new [ItemStack]
     */
    fun updatePageItem(slot: Int, itemStack: ItemStack) {
        if (!currentPage.containsKey(slot)) return
        val guiItem = currentPage[slot]
        guiItem!!.setItemStack(itemStack)
        inventory.setItem(slot, guiItem.getItemStack())
    }

    /**
     * Alternative [.updatePageItem] that uses *ROWS* and *COLUMNS* instead
     *
     * @param row       The row of the slot
     * @param col       The columns of the slot
     * @param itemStack The new [ItemStack]
     */
    fun updatePageItem(row: Int, col: Int, itemStack: ItemStack) {
        updateItem(getSlotFromRowCol(row, col), itemStack)
    }

    /**
     * Alternative [.updatePageItem] that uses [GuiItem] instead
     *
     * @param slot The slot of the item to update
     * @param item The new ItemStack
     */
    fun updatePageItem(slot: Int, item: GuiItem) {
        if (!currentPage.containsKey(slot)) return
        // Gets the old item and its index on the main items list
        currentPage[slot]
        val index = pageItems.indexOf(currentPage[slot])

        // Updates both lists and inventory
        currentPage[slot] = item
        pageItems[index] = item
        inventory.setItem(slot, item.getItemStack())
    }

    /**
     * Alternative [.updatePageItem] that uses *ROWS* and *COLUMNS* instead
     *
     * @param row  The row of the slot
     * @param col  The columns of the slot
     * @param item The new [GuiItem]
     */
    fun updatePageItem(row: Int, col: Int, item: GuiItem) {
        updateItem(getSlotFromRowCol(row, col), item)
    }

    /**
     * Overrides [BaseGui.open] to use the paginated populator instead
     *
     * @param player The [HumanEntity] to open the GUI to
     */
    override fun open(player: HumanEntity) {
        open(player, 1)
    }

    /**
     * Specific open method for the Paginated GUI
     * Uses [.populatePage]
     *
     * @param player   The [HumanEntity] to open it to
     * @param openPage The specific page to open at
     */
    private fun open(player: HumanEntity, openPage: Int) {
        if (player.isSleeping) return
        if (openPage <= pagesNum || openPage > 0) currentPageNum = openPage
        inventory.clear()
        currentPage.clear()
        populateGui()
        if (pageSize == 0) pageSize = calculatePageSize()
        populatePage()
        player.openInventory(inventory)
    }

    /**
     * Overrides [BaseGui.updateTitle] to use the paginated populator instead
     * Updates the title of the GUI
     * *This method may cause LAG if used on a loop*
     *
     * @param title The title to set
     * @return The GUI for easier use when declaring, works like a builder
     */
    override fun updateTitle(title: Component): BaseGui {
        isUpdating = true
        val viewers: List<HumanEntity> = ArrayList(
            inventory.viewers
        )
        inventory = Bukkit.createInventory(this, inventory.size, title.toString())
        for (player in viewers) {
            open(player, currentPageNum)
        }
        isUpdating = false
        return this
    }

    override fun updateTitle(title: String): BaseGui {
        return updateTitle(Component.text(title))
    }

    val currentPageItems: MutableMap<Int, GuiItem?>
        /**
         * Gets an immutable [Map] with all the current pages items
         *
         * @return The [Map] with all the [.currentPage]
         */
        get() = Collections.unmodifiableMap(currentPage)

    /**
     * Gets an immutable [List] with all the page items added to the GUI
     *
     * @return The  [List] with all the [.pageItems]
     */
    fun getPageItems(): List<GuiItem?> {
        return Collections.unmodifiableList(pageItems)
    }

    val nextPageNum: Int
        /**
         * Gets the next page number
         *
         * @return The next page number or [.pageNum] if no next is present
         */
        get() = if (currentPageNum + 1 > pagesNum) currentPageNum else currentPageNum + 1
    val prevPageNum: Int
        /**
         * Gets the previous page number
         *
         * @return The previous page number or [.pageNum] if no previous is present
         */
        get() = if (currentPageNum - 1 == 0) currentPageNum else currentPageNum - 1

    /**
     * Goes to the next page
     *
     * @return False if there is no next page.
     */
    operator fun next(): Boolean {
        if (currentPageNum + 1 > pagesNum) return false
        currentPageNum++
        updatePage()
        return true
    }

    /**
     * Goes to the previous page if possible
     *
     * @return False if there is no previous page.
     */
    fun previous(): Boolean {
        if (currentPageNum - 1 == 0) return false
        currentPageNum--
        updatePage()
        return true
    }

    /**
     * Gets the page item for the GUI listener
     *
     * @param slot The slot to get
     * @return The GuiItem on that slot
     */
    fun getPageItem(slot: Int): GuiItem? {
        return currentPage[slot]
    }

    /**
     * Gets the items in the page
     *
     * @param givenPage The page to get
     * @return A list with all the page items
     */
    private fun getPageNum(givenPage: Int): List<GuiItem?> {
        val page = givenPage - 1
        val guiPage: MutableList<GuiItem?> = ArrayList()
        var max = page * pageSize + pageSize
        if (max > pageItems.size) max = pageItems.size
        for (i in page * pageSize until max) {
            guiPage.add(pageItems[i])
        }
        return guiPage
    }

    private val pagesNum: Int
        /**
         * Gets the number of pages the GUI has
         *
         * @return The pages number
         */
        get() = ceil(pageItems.size.toDouble() / pageSize).toInt()

    /**
     * Populates the inventory with the page items
     */
    private fun populatePage() {
        // Adds the paginated items to the page
        for (guiItem in getPageNum(currentPageNum)) {
            for (slot in 0 until rows * 9) {
                if (inventory.getItem(slot) != null) continue
                currentPage[slot] = guiItem
                inventory.setItem(slot, guiItem!!.getItemStack())
                break
            }
        }
    }

    val mutableCurrentPageItems: Map<Int, GuiItem?>
        /**
         * Gets the current page items to be used on other gui types
         *
         * @return The [Map] with all the [.currentPage]
         */
        get() = currentPage

    /**
     * Clears the page content
     */
    private fun clearPage() {
        for ((key) in currentPage) {
            inventory.setItem(key, null)
        }
    }

    fun clear() {
        inventory.clear()
        currentPage.clear()
        pageItems.clear()
    }

    /**
     * Updates the page content
     */
    private fun updatePage() {
        clearPage()
        populatePage()
    }

    /**
     * Calculates the size of the give page
     *
     * @return The page size
     */
    private fun calculatePageSize(): Int {
        var counter = 0
        for (slot in 0 until rows * 9) {
            if (inventory.getItem(slot) == null) counter++
        }
        return counter
    }
}