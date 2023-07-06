package dev.fabby.com.libraries.gui.components

import java.util.*

enum class InteractionModifier {

    PREVENT_ITEM_PLACE,
    PREVENT_ITEM_TAKE,
    PREVENT_ITEM_SWAP;

    companion object {
        val modifiers = Collections.unmodifiableSet(
            EnumSet.allOf(
                InteractionModifier::class.java
            )
        )
    }
}
