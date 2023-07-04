package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HelixEffect implements IParticle {

    private final Effect effect = new Effect();
    private final Particle particle = Particle.VILLAGER_HAPPY;
    private final int strands = 8;
    private final int particles = 80;
    private final float radius = 10;
    private final float curve = 10;
    private final double rotation = Math.PI / 4;

    @Override
    public String getPermission() {
        return "fabby.effect.helix";
    }

    @Override
    public boolean hasPerm(Player player) {
        return player.hasPermission(getPermission());
    }

    @Override
    public void execute(Player player, Location location) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 1; i <= strands; i++) {
                    for (int j = 1; j <= particles; j++) {
                        float ratio = (float) j / particles;
                        double angle = curve * ratio * 2 * Math.PI / strands + (2 * Math.PI * i / strands) + rotation;
                        double x = Math.cos(angle) * ratio * radius;
                        double z = Math.sin(angle) * ratio * radius;
                        location.add(x, 0, z);
                        effect.particle(location, particle);
                        location.subtract(x, 0, z);
                    }
                }

                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
