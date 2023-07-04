package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import dev.fabby.com.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class ConeEffect implements IParticle {

    private final Effect effect = new Effect();
    private final Random random = new Random();
    private final Particle particle = Particle.FLAME;
    private final float lengthGrow = .05f;
    private final double angularVelocity = Math.PI / 16;
    private final int particles = 10;
    private final float radiusGrow = 0.006f;
    private final int particlesCone = 180;
    private double rotation = 0;
    private final boolean randomize = false;
    protected int step = 0;

    @Override
    public String getPermission() {
        return "fabby.effect.cone";
    }

    @Override
    public boolean hasPerm(Player player) {
        return player.hasPermission(getPermission());
    }

    private double getRandomAngle() {
        return random.nextDouble() * 2 * Math.PI;
    }

    @Override
    public void execute(Player player, Location location) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int x = 0; x < particles; x++) {
                    if (step > particlesCone) {
                        step = 0;
                    }
                    if (randomize && step == 0) {
                        rotation = getRandomAngle();
                    }
                    double angle = step * angularVelocity + rotation;
                    float radius = step * radiusGrow;
                    float length = step * lengthGrow;
                    Vector v = new Vector(Math.cos(angle) * radius, length, Math.sin(angle) * radius);
                    effect.rotateAroundAxisX(v, (location.getPitch() + 90) * MathUtil.degreesToRadians);
                    effect.rotateAroundAxisY(v, -location.getYaw() * MathUtil.degreesToRadians);

                    location.add(v);
                    effect.particle(location, particle);
                    location.subtract(v);
                    step++;
                }

                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
