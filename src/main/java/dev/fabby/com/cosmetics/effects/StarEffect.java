package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import dev.fabby.com.utils.MathUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class StarEffect implements IParticle {

    private final Effect effect = new Effect();
    private final Random random = new Random();
    private final Particle particle = Particle.FLAME;
    private final int particles = 50;
    private final float spikeHeight = 3.5f;
    private final int spikesHalf = 3;
    private final float innerRadius = 0.5f;

    @Override
    public String getPermission() {
        return "fabby.effect.star";
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
                float radius = 3 * innerRadius / MathUtil.SQRT_3;
                for (int i = 0; i < spikesHalf * 2; i++) {
                    double xRotation = i * Math.PI / spikesHalf;
                    for (int x = 0; x < particles; x++) {
                        double angle = 2 * Math.PI * x / particles;
                        float height = random.nextFloat() * spikeHeight;
                        Vector v = new Vector(Math.cos(angle), 0, Math.sin(angle));
                        v.multiply((spikeHeight - height) * radius / spikeHeight);
                        v.setY(innerRadius + height);
                        effect.rotateAroundAxisX(v, xRotation);
                        location.add(v);
                        effect.particle(location, particle);
                        location.subtract(v);
                        effect.rotateAroundAxisX(v, Math.PI);
                        effect.rotateAroundAxisY(v, Math.PI / 2);
                        location.add(v);
                        effect.particle(location, particle);
                        location.subtract(v);
                    }
                }

                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
