package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SphereEffect implements IParticle {

    private final Effect effect = new Effect();
    private final Particle particle = Particle.SPELL_MOB;
    private double radius = 0.6;
    private final double yOffset = 0;
    private final int particles = 50;
    private final double radiusIncrease = 0;

    @Override
    public String getPermission() {
        return "fabby.effect.sphere";
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
                if (radiusIncrease != 0) {
                    radius += radiusIncrease;
                }

                location.add(0, yOffset, 0);
                for (int i = 0; i < particles; i++) {
                    Vector vector = effect.getRandomVector().multiply(radius);
                    location.add(vector);
                    effect.particle(location, particle);
                    location.subtract(vector);
                }

                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
