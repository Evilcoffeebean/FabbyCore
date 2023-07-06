package dev.fabby.com.libraries.gui.manager;

import dev.fabby.com.Core;
import dev.fabby.com.libraries.gui.Gui;
import dev.fabby.com.libraries.gui.GuiItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class FabbyLoadingGUI {

    private Gui gui;
    private final String title;
    private final int rows;
    private final Player player;
    private final String whatAmILoading;
    private BukkitTask runnable;

    @Deprecated
    public FabbyLoadingGUI(final int rows, final String title, final Player player, final String whatAmILoading) {
        this.rows = rows;
        this.title = title;
        this.player = player;
        this.whatAmILoading = whatAmILoading;
    }

    public FabbyLoadingGUI(final int rows, final Component title, final Player player, final String whatAmILoading) {
        this.rows = rows;
        this.title = String.valueOf(title);
        this.player = player;
        this.whatAmILoading = whatAmILoading;
    }

    public void display() {
        gui = new Gui(rows, title);
        gui.getFiller().fill(new GuiItem(Item.glass(Material.BLUE_STAINED_GLASS_PANE)));
        gui.setDefaultClickAction(event -> event.setCancelled(true));

        if (player != null) gui.open(player);

        ArrayList<Integer> slots = new ArrayList<>();

        int start = (9 * (Math.floorDiv(rows, 2))) + 2;
        int end = (9 * (Math.floorDiv(rows, 2))) + 6;

        for (int i = start; i <= end; i++) {
            slots.add(i);
        }

        runnable = new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count == 5) {
                    count = 0;
                    for (int i : slots) {
                        gui.updateItem(i, new GuiItem(Item.glass(Material.BLUE_STAINED_GLASS_PANE)));
                    }
                }
                gui.updateItem(slots.get(count), new GuiItem(Item.createItem(Material.ORANGE_STAINED_GLASS_PANE, String.valueOf(Component.text( "Loading " + whatAmILoading + "...", NamedTextColor.GOLD)), null)));
                count++;
            }
        }.runTaskTimer(Core.getCore(), 0L, 3L);
    }

    public void stop() {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.cancel();
            }
        }.runTask(Core.getCore());
    }
}