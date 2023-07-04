package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class DiscoEffect implements IParticle {

    public enum Direction {
        UP, DOWN;
    }

    private final Effect effect = new Effect();
    private final Random random = new Random();
    private final float sphereRadius = .6f;
    private final int max = 15;
    private final Particle sphereParticle = Particle.FLAME;
    private final int maxLines = 7;
    private final int lineParticles = 100, sphereParticles = 50;
    private final Direction direction = Direction.DOWN;

    @Override
    public String getPermission() {
        return "fabby.effect.disco";
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
                //Lines
                int mL = random.nextInt(maxLines - 2) + 2;
                for (int m = 0; m < mL * 2; m++) {
                    double x = random.nextInt(max - max * (-1)) + max * (-1);
                    double y = random.nextInt(max - max * (-1)) + max * (-1);
                    double z = random.nextInt(max - max * (-1)) + max * (-1);
                    if (direction == Direction.DOWN) {
                        y = random.nextInt(max * 2 - max) + max;
                    } else if (direction == Direction.UP) {
                        y = random.nextInt(max * (-1) - max * (-2)) + max * (-2);
                    }
                    Location target = location.clone().subtract(x, y, z);
                    if (target == null) {
                        cancel();
                        return;
                    }
                    Vector link = target.toVector().subtract(location.toVector());
                    float length = (float) link.length();
                    link.normalize();

                    float ratio = length / lineParticles;
                    Vector v = link.multiply(ratio);
                    Location loc = location.clone().subtract(v);
                    for (int i = 0; i < lineParticles; i++) {
                        loc.add(v);
                        effect.particle(location, sphereParticle);
                    }
                }

                //Sphere
                for (int i = 0; i < sphereParticles; i++) {
                    Vector vector = effect.getRandomVector().multiply(sphereRadius);
                    location.add(vector);
                    effect.particle(location, sphereParticle);
                    location.subtract(vector);
                }

                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
