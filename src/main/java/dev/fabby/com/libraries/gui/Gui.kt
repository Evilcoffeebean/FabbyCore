package dev.fabby.com.libraries.gui

import dev.fabby.com.libraries.gui.components.InteractionModifier
import net.kyori.adventure.text.Component
import java.util.*

/**
 * Standard GUI implementation of [BaseGui]
 */
class Gui
/**
 * TarvosCore constructor for the GUI
 *
 * @param rows  The amount of rows the GUI should have
 * @param title The GUI's title [Component]
 */(rows: Int, title: Component) :
    BaseGui(rows, title, HashSet(Arrays.stream(InteractionModifier.entries.toTypedArray()).toList())) 