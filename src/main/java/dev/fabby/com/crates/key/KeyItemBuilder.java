package dev.fabby.com.crates.key;

import dev.fabby.com.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class KeyItemBuilder {

    //prevent instantiation
    private KeyItemBuilder() {}

    public static ItemStack getKey(final KeyType type) {
        switch (type) {
            case COAL:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                    "&8&lCoal Key").getProductWithGlow();
            case REDSTONE:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                        "&c&lRedstone Key").getProductWithGlow();
            case LAPIS:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                        "&9&lLapis Key").getProductWithGlow();
            case IRON:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                        "&f&lIron Key").getProductWithGlow();
            case GOLD:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                        "&6&lGold Key").getProductWithGlow();
            case EMERALD:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                        "&a&lEmerald Key").getProductWithGlow();
            case DIAMOND:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                        "&b&lDiamond Key").getProductWithGlow();
            case NETHERITE:
                return new ItemUtil(Material.TRIPWIRE_HOOK,
                        "&4&lNetherite Key").getProductWithGlow();
        }
        return null;
    }
}
