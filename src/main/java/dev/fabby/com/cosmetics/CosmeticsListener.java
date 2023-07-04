package dev.fabby.com.cosmetics;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.effects.*;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CosmeticsListener implements Listener {

    private final Sound perm = Sound.BLOCK_NOTE_BLOCK_PLING, activate = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

    @EventHandler (priority = EventPriority.LOW)
    public void onClick(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getCurrentItem() == null)
            return;
        if (!(e.getWhoClicked() instanceof Player))
            return;

        final Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Cosmetics Menu")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case REDSTONE:
                    final HeartEffect heart = (HeartEffect) Core.getCore().getParticleManager().getEffect("Heart");

                    if (!heart.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, heart);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        heart.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case GLOWSTONE_DUST:
                    final HelixEffect helix = (HelixEffect) Core.getCore().getParticleManager().getEffect("Helix");

                    if (!helix.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, helix);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        helix.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case SMOKER:
                    final SmokeEffect smoke = (SmokeEffect) Core.getCore().getParticleManager().getEffect("Smoke");

                    if (!smoke.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, smoke);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        smoke.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case FEATHER:
                    final WarpEffect warp = (WarpEffect) Core.getCore().getParticleManager().getEffect("Warp");

                    if (!warp.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, warp);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        warp.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case WRITTEN_BOOK:
                    final MusicEffect music = (MusicEffect) Core.getCore().getParticleManager().getEffect("Music");

                    if (!music.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, music);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        music.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case WITHER_ROSE:
                    final SphereEffect sphere = (SphereEffect) Core.getCore().getParticleManager().getEffect("Sphere");

                    if (!sphere.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, sphere);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        sphere.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case NETHER_STAR:
                    final StarEffect star = (StarEffect) Core.getCore().getParticleManager().getEffect("Star");

                    if (!star.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, star);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        star.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case GLOW_INK_SAC:
                    final DiscoEffect disco = (DiscoEffect) Core.getCore().getParticleManager().getEffect("Disco");

                    if (!disco.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, disco);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        disco.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case DIAMOND:
                    final ConeEffect cone = (ConeEffect) Core.getCore().getParticleManager().getEffect("Cone");

                    if (!cone.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, cone);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        cone.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;

                case RAW_GOLD_BLOCK:
                    final CubeEffect cube = (CubeEffect) Core.getCore().getParticleManager().getEffect("Cube");

                    if (!cube.hasPerm(p)) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.RED + "You don't have permission to activate that particle effect.");
                        return;
                    }

                    if (!Core.getCore().getParticleManager().hasActiveEffect(p)) {
                        Core.getCore().getParticleManager().addActiveEffect(p, cube);
                        p.closeInventory();
                        p.playSound(p.getLocation(), activate, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect activated.");
                        cube.execute(p, p.getLocation());
                    } else {
                        Core.getCore().getParticleManager().removeActiveEffect(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), perm, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Particle effect disabled.");
                    }
                    return;
            }
        }
    }
}
