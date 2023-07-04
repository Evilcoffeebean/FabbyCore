package dev.fabby.com.kit.menu;

import dev.fabby.com.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KitMenuListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onKitSelect(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getCurrentItem() == null)
            return;
        if (!(e.getWhoClicked() instanceof Player))
            return;

        final Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Kit Menu")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                //player kits
                case CHARCOAL:
                    Core.getCore().getKitManager().getKit("Player").execute(player);
                    return;
                case COAL:
                    Core.getCore().getKitManager().getKit("PlayerPlus").execute(player);
                    return;
                case COAL_BLOCK:
                    Core.getCore().getKitManager().getKit("PlayerMax").execute(player);
                    return;
                //food kits
                case APPLE:
                    Core.getCore().getKitManager().getKit("Food").execute(player);
                    return;
                case GOLDEN_APPLE:
                    Core.getCore().getKitManager().getKit("FoodPlus").execute(player);
                    return;
                case ENCHANTED_GOLDEN_APPLE:
                    Core.getCore().getKitManager().getKit("FoodMax").execute(player);
                    return;
                //miner kits
                case IRON_PICKAXE:
                    Core.getCore().getKitManager().getKit("Miner").execute(player);
                    return;
                case DIAMOND_PICKAXE:
                    Core.getCore().getKitManager().getKit("MinerPlus").execute(player);
                    return;
                case NETHERITE_PICKAXE:
                    Core.getCore().getKitManager().getKit("MinerMax").execute(player);
                    return;
                //other kits
                case BREWING_STAND:
                    Core.getCore().getKitManager().getKit("Biohazard").execute(player);
                    return;
                case ENCHANTING_TABLE:
                    Core.getCore().getKitManager().getKit("Enchantment").execute(player);
                    return;
                case PHANTOM_MEMBRANE:
                    Core.getCore().getKitManager().getKit("Clandestine").execute(player);
                    return;
                //rank kits
                case GREEN_DYE:
                    Core.getCore().getKitManager().getKit("Vip").execute(player);
                    return;
                case LIGHT_BLUE_DYE:
                    Core.getCore().getKitManager().getKit("Mvp").execute(player);
                    return;
                case BLUE_DYE:
                    Core.getCore().getKitManager().getKit("Elite").execute(player);
                    return;
                case RED_DYE:
                    Core.getCore().getKitManager().getKit("Immortal").execute(player);
                    return;
                    //special
                case RED_TULIP:
                    Core.getCore().getKitManager().getKit("Thanks").execute(player);
                    return;
            }
        }
    }
}
