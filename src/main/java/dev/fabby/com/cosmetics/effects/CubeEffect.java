package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CubeEffect implements IParticle {

    private final Effect effect = new Effect();
    private final Particle particle = Particle.FLAME;
    private final float edgeLength = 3;
    private final double angularVelocityX = Math.PI / 200;
    private final double angularVelocityY = Math.PI / 170;
    private final double angularVelocityZ = Math.PI / 155;
    private final int particles = 8;
    private final boolean enableRotation = true;
    private final boolean outlineOnly = true;
    protected int step = 0;

    @Override
    public String getPermission() {
        return "fabby.effect.cube";
    }

    @Override
    public boolean hasPerm(Player player) {
        return player.hasPermission(getPermission());
    }

    private void drawCubeOutline(Location location) {
        double xRotation = 0, yRotation = 0, zRotation = 0;
        if (enableRotation) {
            xRotation = step * angularVelocityX;
            yRotation = step * angularVelocityY;
            zRotation = step * angularVelocityZ;
        }
        float a = edgeLength / 2;
        // top and bottom
        double angleX, angleY;
        Vector v = new Vector();
        for (int i = 0; i < 4; i++) {
            angleY = i * Math.PI / 2;
            for (int j = 0; j < 2; j++) {
                angleX = j * Math.PI;
                for (int p = 0; p <= particles; p++) {
                    v.setX(a).setY(a);
                    v.setZ(edgeLength * p / particles - a);
                    effect.rotateAroundAxisX(v, angleX);
                    effect.rotateAroundAxisY(v, angleY);

                    if (enableRotation) {
                        effect.rotateVector(v, xRotation, yRotation, zRotation);
                    }
                    effect.particle(location.add(v), particle);
                    location.subtract(v);
                }
            }
            // pillars
            for (int p = 0; p <= particles; p++) {
                v.setX(a).setZ(a);
                v.setY(edgeLength * p / particles - a);
                effect.rotateAroundAxisY(v, angleY);

                if (enableRotation) {
                    effect.rotateVector(v, xRotation, yRotation, zRotation);
                }
                effect.particle(location.add(v), particle);
                location.subtract(v);
            }
        }
    }

    private void drawCubeWalls(Location location) {
        double xRotation = 0, yRotation = 0, zRotation = 0;
        if (enableRotation) {
            xRotation = step * angularVelocityX;
            yRotation = step * angularVelocityY;
            zRotation = step * angularVelocityZ;
        }
        float a = edgeLength / 2;
        for (int x = 0; x <= particles; x++) {
            float posX = edgeLength * ((float) x / particles) - a;
            for (int y = 0; y <= particles; y++) {
                float posY = edgeLength * ((float) y / particles) - a;
                for (int z = 0; z <= particles; z++) {
                    if (x != 0 && x != particles && y != 0 && y != particles && z != 0 && z != particles) {
                        continue;
                    }
                    float posZ = edgeLength * ((float) z / particles) - a;
                    Vector v = new Vector(posX, posY, posZ);
                    if (enableRotation) {
                        effect.rotateVector(v, xRotation, yRotation, zRotation);
                    }
                    effect.particle(location.add(v), particle);
                    location.subtract(v);
                }
            }
        }
    }


    @Override
    public void execute(Player player, Location location) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (outlineOnly) {
                    drawCubeOutline(location);
                } else {
                    drawCubeWalls(location);
                }
                step++;
                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
