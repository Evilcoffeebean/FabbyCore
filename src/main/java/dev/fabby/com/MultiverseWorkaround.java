package dev.fabby.com;

import dev.fabby.com.events.CropTrampleEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MultiverseWorkaround implements Listener {

    private final String spawn = "spawn";

    private boolean isSpawn(final Player player) {
        return player.getWorld().getName().equalsIgnoreCase(spawn);
    }

    private boolean isFarmlandsBlock(Material material) {
        return material == Material.FARMLAND;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onVanish(AsyncPlayerChatEvent e) {
        if (e.getMessage().startsWith("/vanish") || e.getMessage().equalsIgnoreCase("/v")) {
            if (Core.getCore().isVanished(e.getPlayer())) {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
            } else e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onLobbyJoin(final PlayerJoinEvent e) {
        if (isSpawn(e.getPlayer())) {
            e.getPlayer().setHealth(20f);
            e.getPlayer().setFoodLevel(20);
            e.getPlayer().setSaturation(20f);
            return;
        }
    }

    //LOBBY EVENTS

    @EventHandler (priority = EventPriority.LOW)
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntity().getLocation().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onDamageTake(EntityDamageEvent e) {
        if (e.getEntity().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onHungerEvent(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player player = (Player) e.getEntity();
            if (isSpawn(player))
                e.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent e) {
        if (isSpawn(e.getPlayer())) {
            if (!e.getPlayer().isOp())
                e.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBlockPlace(BlockPlaceEvent e) {
        if (isSpawn(e.getPlayer())) {
            if (!e.getPlayer().isOp())
                e.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBlockFormEvent(BlockFormEvent e) {
        if (e.getBlock().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBlockFromToPlace(BlockFromToEvent e) {
        if (e.getBlock().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBlockSpread(BlockSpreadEvent e) {
        if (e.getSource().getWorld().getName().equalsIgnoreCase(spawn) || e.getBlock().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBurnEvent(BlockBurnEvent e) {
        if (e.getBlock().getWorld().getName().equalsIgnoreCase(spawn) || e.getIgnitingBlock().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onGrowEvent(BlockGrowEvent e) {
        if (e.getBlock().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onExplodeEvent(BlockExplodeEvent e) {
        if (e.getBlock().getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onFertilizeEvent(BlockFertilizeEvent e) {
        if (isSpawn(e.getPlayer())) {
            if (!e.getPlayer().isOp())
                e.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPortalCreateEvent(PortalCreateEvent e) {
        if (e.getWorld().getName().equalsIgnoreCase(spawn))
            e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onMobSpawn(PlayerInteractEvent e) {
        if (isSpawn(e.getPlayer())) {
            ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
            if (hand.getType().toString().contains("SPAWN_EGG") || hand.getType() == Material.LAVA_BUCKET || hand.getType() == Material.WATER_BUCKET) {
                if (!e.getPlayer().isOp())
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMobTrample(EntityInteractEvent event) {
        if (event.getEntity() instanceof Player)
            return;

        if (isFarmlandsBlock(event.getBlock().getType())) {
            if (!isSpawn((Player) event.getEntity()))
                return;

            CropTrampleEvent cropTrampleEvent = new CropTrampleEvent(event.getEntity(), CropTrampleEvent.TrampleCause.MOB, event.getBlock());
            Bukkit.getPluginManager().callEvent(cropTrampleEvent);
            if (cropTrampleEvent.isCancelled())
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTrample(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL && isFarmlandsBlock(event.getClickedBlock().getType())) {
            if (!isSpawn(event.getPlayer()))
                return;

            CropTrampleEvent cropTrampleEvent = new CropTrampleEvent(event.getPlayer(), CropTrampleEvent.TrampleCause.PLAYER, event.getClickedBlock());
            Bukkit.getPluginManager().callEvent(cropTrampleEvent);
            if (cropTrampleEvent.isCancelled())
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onTrample(CropTrampleEvent event) {
        if ((event.getCause() == CropTrampleEvent.TrampleCause.MOB) || (event.getCause() == CropTrampleEvent.TrampleCause.PLAYER))
            event.setCancelled(true);
    }
}
