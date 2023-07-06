package dev.fabby.com.libraries.gui.manager;

import dev.fabby.com.Core;
import dev.fabby.com.libraries.gui.Gui;
import dev.fabby.com.libraries.gui.GuiItem;
import dev.fabby.com.utils.Task;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class FabbyGUI {

    public Gui gui;
    public final int rows;
    public String title;
    private final Player player;
    private HashMap<GUIPair, Integer> menuItems;
    private final boolean updates;

    public final boolean borders;

    private static final String customUISpacer = "\uF80C\uF80A\uF809\uF825\uF821";

    private final Task task = Core.getCore().getTaskManager();

    public FabbyGUI(final int rows, final String title, final Player player, final boolean updates, final boolean borders) {
        this.rows = rows;
        this.title = title;
        this.player = player;
        this.updates = updates;
        this.borders = borders;
    }

    public FabbyGUI(final int rows, final String title, final Player player, final boolean updates) {
        this.rows = rows;
        this.title = title;
        this.player = player;
        this.updates = updates;
        this.borders = true;
    }

    public void setItems(HashMap<GUIPair, Integer> menuItems) {
        this.menuItems = menuItems;
    }

    public void updateItems(HashMap<GUIPair, Integer> menuItems) {
        this.menuItems = menuItems;
    }

    public Player getPlayer() {
        return player;
    }

    public void create() {
        gui = new Gui(this.rows, String.valueOf(this.title));
        gui.setDefaultClickAction(event -> event.setCancelled(true));
        if (borders) gui.getFiller().fill(new GuiItem(Item.glass(Material.BLUE_STAINED_GLASS_PANE)));

        for (GUIPair pair : menuItems.keySet()) {
            GuiItem item = pair.item();
            if (pair.action() != null) item.setAction(pair.action());
            gui.setItem(menuItems.get(pair), item);
        }
    }

    public void createAndOpen() {
        create();
        open();
    }

    public void open() {
        if (player.isOnline()) {
            gui.open(player);
            if (updates) startUpdating();
        }
    }

    public void close() {
        if (player.isOnline()) {
            gui.close(player);
        }
    }

    public Gui get() {
        return gui;
    }

    public Task getTask() {
        return task;
    }

    public boolean updates() {
        return this.updates;
    }

    public void update() {
        for (GUIPair pair : menuItems.keySet()) {
            GuiItem item = pair.item();
            if (pair.action() != null) item.setAction(pair.action());
            gui.updateItem(menuItems.get(pair), item);
        }
    }

    private void startUpdating() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getPlayer() == null || !getPlayer().isOnline()) {
                    this.cancel();
                    return;
                }

                if (!getPlayer().getPlayer().getOpenInventory().getTitle().equals(title)) {
                    this.cancel();
                    return;
                }

                update();
            }
        }.runTaskTimer(Core.getCore(), 0L, 20L);
    }

    public void updateItem(final GUIPair pair, final int slot) {
        GuiItem item = pair.item();
        if (pair.action() != null) item.setAction(pair.action());
        gui.updateItem(slot, item);
    }

    public FabbyLoadingGUI createAndDisplayLoader(final int rows, final Component title, final Player fortunePlayer, final String whatAmILoading) {
        FabbyLoadingGUI gui = new FabbyLoadingGUI(rows, title, fortunePlayer, whatAmILoading);
        gui.display();
        return gui;
    }

}