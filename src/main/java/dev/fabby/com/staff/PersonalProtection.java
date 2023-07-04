package dev.fabby.com.staff;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class PersonalProtection implements Listener {

    private final UUID deathibring = UUID.fromString("5507bef2-cd33-4a66-8123-101ca4f73c31");
    private final UUID stipebno = UUID.fromString("ed97785d-2dc2-4106-abd1-2bc44f6d264f");

    @EventHandler (ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerAttack(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            final Player victim = (Player) event.getEntity();

            if (victim.getUniqueId().equals(deathibring) || victim.getUniqueId().equals(stipebno)) {
                if (victim.getHealth() <= 2) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
}
