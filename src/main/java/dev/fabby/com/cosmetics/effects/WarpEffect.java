package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WarpEffect implements IParticle {

    private final Effect effect = new Effect();
    private final float radius = 1;
    private final int particles = 20;
    private final Particle particle = Particle.FIREWORKS_SPARK;
    private final float grow = .2f;
    private final int rings = 12;
    protected int step = 0;

    @Override
    public String getPermission() {
        return "fabby.effect.warp";
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
                if (step > rings) {
                    step = 0;
                }
                double x, y, z;
                y = step * grow;
                location.add(0, y, 0);
                for (int i = 0; i < particles; i++) {
                    double angle = (double) 2 * Math.PI * i / particles;
                    x = Math.cos(angle) * radius;
                    z = Math.sin(angle) * radius;
                    location.add(x, 0, z);
                    effect.particle(location, particle);
                    location.subtract(x, 0, z);
                }
                location.subtract(0, y, 0);
                step++;

                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
