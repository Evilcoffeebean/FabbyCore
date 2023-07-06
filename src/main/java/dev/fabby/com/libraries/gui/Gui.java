package dev.fabby.com.libraries.gui;

import dev.fabby.com.libraries.gui.components.InteractionModifier;
import net.kyori.adventure.text.Component;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Standard GUI implementation of {@link BaseGui}
 */
public final class Gui extends BaseGui {

    /**
     * TarvosCore constructor for the GUI
     *
     * @param rows  The amount of rows the GUI should have
     * @param title The GUI's title {@link Component}
     */
    public Gui(final int rows, final String title) {
        super(rows, title, new HashSet<>(Arrays.stream(InteractionModifier.values()).toList()));
    }

}
