package dev.fabby.com.libraries.gui;

import dev.fabby.com.libraries.gui.components.InteractionModifier;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * GUI that allows you to have multiple pages
 */
@SuppressWarnings("unused")
public class PaginatedGui extends BaseGui {

    // List with all the page items
    private final List<GuiItem> pageItems = new ArrayList<>();
    // Saves the current page items and it's slot
    private final Map<Integer, GuiItem> currentPage = new LinkedHashMap<>();

    private int pageSize;
    private int pageNum = 1;

    /**
     * Main constructor to provide a way to create PaginatedGui
     *
     * @param rows                 The amount of rows the GUI should have
     * @param pageSize             The page size.
     * @param title                The GUI's title using {@link Component}
     * @author James
     * @since Tarvos
     */
    public PaginatedGui(final int rows, final int pageSize, @NotNull final Component title) {
        super(rows, title, new HashSet<>(Arrays.stream(InteractionModifier.values()).toList()));
        this.pageSize = pageSize;
    }

    /**
     * Sets the page size
     *
     * @param pageSize The new page size
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Adds an {@link GuiItem} to the next available slot in the page area
     *
     * @param item The {@link GuiItem} to add to the page
     */
    public void addItem(@NotNull final GuiItem item) {
        pageItems.add(item);
    }

    /**
     * Overridden {@link BaseGui#addItem(GuiItem...)} to add the items to the page instead
     *
     * @param items Varargs for specifying the {@link GuiItem}s
     */
    @Override
    public void addItem(@NotNull final GuiItem... items) {
        pageItems.addAll(Arrays.asList(items));
    }

    /**
     * Overridden {@link BaseGui#update()} to use the paginated open
     */
    @Override
    public void update() {
        getInventory().clear();
        populateGui();

        updatePage();
    }

    /**
     * Updates the page {@link GuiItem} on the slot in the page
     * Can get the slot from {@link InventoryClickEvent#getSlot()}
     *
     * @param slot      The slot of the item to update
     * @param itemStack The new {@link ItemStack}
     */
    public void updatePageItem(final int slot, @NotNull final ItemStack itemStack) {
        if (!currentPage.containsKey(slot)) return;
        final GuiItem guiItem = currentPage.get(slot);
        guiItem.setItemStack(itemStack);
        getInventory().setItem(slot, guiItem.getItemStack());
    }

    /**
     * Alternative {@link #updatePageItem(int, ItemStack)} that uses <i>ROWS</i> and <i>COLUMNS</i> instead
     *
     * @param row       The row of the slot
     * @param col       The columns of the slot
     * @param itemStack The new {@link ItemStack}
     */
    public void updatePageItem(final int row, final int col, @NotNull final ItemStack itemStack) {
        updateItem(getSlotFromRowCol(row, col), itemStack);
    }

    /**
     * Alternative {@link #updatePageItem(int, ItemStack)} that uses {@link GuiItem} instead
     *
     * @param slot The slot of the item to update
     * @param item The new ItemStack
     */
    public void updatePageItem(final int slot, @NotNull final GuiItem item) {
        if (!currentPage.containsKey(slot)) return;
        // Gets the old item and its index on the main items list
        final GuiItem oldItem = currentPage.get(slot);
        final int index = pageItems.indexOf(currentPage.get(slot));

        // Updates both lists and inventory
        currentPage.put(slot, item);
        pageItems.set(index, item);
        getInventory().setItem(slot, item.getItemStack());
    }

    /**
     * Alternative {@link #updatePageItem(int, GuiItem)} that uses <i>ROWS</i> and <i>COLUMNS</i> instead
     *
     * @param row  The row of the slot
     * @param col  The columns of the slot
     * @param item The new {@link GuiItem}
     */
    public void updatePageItem(final int row, final int col, @NotNull final GuiItem item) {
        updateItem(getSlotFromRowCol(row, col), item);
    }

    /**
     * Overrides {@link BaseGui#open(HumanEntity)} to use the paginated populator instead
     *
     * @param player The {@link HumanEntity} to open the GUI to
     */
    @Override
    public void open(@NotNull final HumanEntity player) {
        open(player, 1);
    }

    /**
     * Specific open method for the Paginated GUI
     * Uses {@link #populatePage()}
     *
     * @param player   The {@link HumanEntity} to open it to
     * @param openPage The specific page to open at
     */
    public void open(@NotNull final HumanEntity player, final int openPage) {
        if (player.isSleeping()) return;
        if (openPage <= getPagesNum() || openPage > 0) pageNum = openPage;

        getInventory().clear();
        currentPage.clear();

        populateGui();

        if (pageSize == 0) pageSize = calculatePageSize();

        populatePage();

        player.openInventory(getInventory());
    }

    /**
     * Overrides {@link BaseGui#updateTitle(Component)} to use the paginated populator instead
     * Updates the title of the GUI
     * <i>This method may cause LAG if used on a loop</i>
     *
     * @param title The title to set
     * @return The GUI for easier use when declaring, works like a builder
     */
    @Override
    public BaseGui updateTitle(@NotNull final Component title) {
        setUpdating(true);

        final List<HumanEntity> viewers = new ArrayList<>(getInventory().getViewers());

        setInventory(Bukkit.createInventory(this, getInventory().getSize(), String.valueOf(title)));

        for (final HumanEntity player : viewers) {
            open(player, getPageNum());
        }

        setUpdating(false);

        return this;
    }

    /**
     * Gets an immutable {@link Map} with all the current pages items
     *
     * @return The {@link Map} with all the {@link #currentPage}
     */
    public Map<Integer, GuiItem> getCurrentPageItems() {
        return Collections.unmodifiableMap(currentPage);
    }

    /**
     * Gets an immutable {@link List} with all the page items added to the GUI
     *
     * @return The  {@link List} with all the {@link #pageItems}
     */
    public List<GuiItem> getPageItems() {
        return Collections.unmodifiableList(pageItems);
    }


    /**
     * Gets the current page number
     *
     * @return The current page number
     */
    public int getCurrentPageNum() {
        return pageNum;
    }

    /**
     * Gets the next page number
     *
     * @return The next page number or {@link #pageNum} if no next is present
     */
    public int getNextPageNum() {
        if (pageNum + 1 > getPagesNum()) return pageNum;
        return pageNum + 1;
    }

    /**
     * Gets the previous page number
     *
     * @return The previous page number or {@link #pageNum} if no previous is present
     */
    public int getPrevPageNum() {
        if (pageNum - 1 == 0) return pageNum;
        return pageNum - 1;
    }

    /**
     * Goes to the next page
     *
     * @return False if there is no next page.
     */
    public boolean next() {
        if (pageNum + 1 > getPagesNum()) return false;

        pageNum++;
        updatePage();
        return true;
    }

    /**
     * Goes to the previous page if possible
     *
     * @return False if there is no previous page.
     */
    public boolean previous() {
        if (pageNum - 1 == 0) return false;

        pageNum--;
        updatePage();
        return true;
    }

    /**
     * Gets the page item for the GUI listener
     *
     * @param slot The slot to get
     * @return The GuiItem on that slot
     */
    GuiItem getPageItem(final int slot) {
        return currentPage.get(slot);
    }

    /**
     * Gets the items in the page
     *
     * @param givenPage The page to get
     * @return A list with all the page items
     */
    private List<GuiItem> getPageNum(final int givenPage) {
        final int page = givenPage - 1;

        final List<GuiItem> guiPage = new ArrayList<>();

        int max = ((page * pageSize) + pageSize);
        if (max > pageItems.size()) max = pageItems.size();

        for (int i = page * pageSize; i < max; i++) {
            guiPage.add(pageItems.get(i));
        }

        return guiPage;
    }

    /**
     * Gets the number of pages the GUI has
     *
     * @return The pages number
     */
    public int getPagesNum() {
        return (int) Math.ceil((double) pageItems.size() / pageSize);
    }

    /**
     * Populates the inventory with the page items
     */
    private void populatePage() {
        // Adds the paginated items to the page
        for (final GuiItem guiItem : getPageNum(pageNum)) {
            for (int slot = 0; slot < getRows() * 9; slot++) {
                if (getInventory().getItem(slot) != null) continue;
                currentPage.put(slot, guiItem);
                getInventory().setItem(slot, guiItem.getItemStack());
                break;
            }
        }
    }

    /**
     * Gets the current page items to be used on other gui types
     *
     * @return The {@link Map} with all the {@link #currentPage}
     */
    Map<Integer, GuiItem> getMutableCurrentPageItems() {
        return currentPage;
    }

    /**
     * Clears the page content
     */
    void clearPage() {
        for (Map.Entry<Integer, GuiItem> entry : currentPage.entrySet()) {
            getInventory().setItem(entry.getKey(), null);
        }
    }

    public void clear() {
        getInventory().clear();
        currentPage.clear();
        pageItems.clear();
    }

    /**
     * Gets the page size
     *
     * @return The page size
     */
    int getPageSize() {
        return pageSize;
    }

    /**
     * Gets the page number
     *
     * @return The current page number
     */
    int getPageNum() {
        return pageNum;
    }

    /**
     * Sets the page number
     *
     * @param pageNum Sets the current page to be the specified number
     */
    void setPageNum(final int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Updates the page content
     */
    void updatePage() {
        clearPage();
        populatePage();
    }

    /**
     * Calculates the size of the give page
     *
     * @return The page size
     */
    int calculatePageSize() {
        int counter = 0;

        for (int slot = 0; slot < getRows() * 9; slot++) {
            if (getInventory().getItem(slot) == null) counter++;
        }

        return counter;
    }

}
