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

public class HeartEffect implements IParticle {

    private final Effect effect = new Effect();
    private final Particle particle = Particle.CRIT_MAGIC;
    private int particles = 50;
    private double xRotation, yRotation, zRotation = 0;
    private double yFactor = 1, xFactor = 1;
    private double factorInnerSpike = 0.8;
    private double compressYFactorTotal = 2;
    private float compilaction = 2F;

    @Override
    public String getPermission() {
        return "fabby.effect.heart";
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
                Vector vector = new Vector();
                for (int i = 0; i < particles; i++) {
                    float alpha = ((MathUtil.PI / compilaction) / particles) * i;
                    double phi = Math.pow(Math.abs(MathUtil.sin(2 * compilaction * alpha)) + factorInnerSpike * Math.abs(MathUtil.sin(compilaction * alpha)), 1 / compressYFactorTotal);

                    vector.setY(phi * (MathUtil.sin(alpha) + MathUtil.cos(alpha)) * yFactor);
                    vector.setZ(phi * (MathUtil.cos(alpha) - MathUtil.sin(alpha)) * xFactor);

                    effect.particle(player.getLocation().add(0,1.5,0).add(effect.rotateVector(vector, xRotation, yRotation, zRotation)), particle);
                    location.subtract(vector);
                }

                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
