package dev.fabby.com.cosmetics;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public final class Effect {

    private static final Random r = new Random();
    static public final float degreesToRadians = (float) (Math.PI / 180);

    public Effect() {}

    public void particle(Location location, Particle particle) {
        particle(location, particle, null);
    }

    public void particle(Location location, Material material) {
        particle(location, null, material);
    }

    public void particle(Location location, Particle particle, Material material) {
        if (particle == null) {
            location.getWorld().spawnParticle(Particle.BLOCK_CRACK, location, 0, 0, 0, 0, material.createBlockData());
        } else {
            location.getWorld().spawnParticle(particle, location, 0, 0, 0, 0);
        }
    }

    public Vector rotate(Vector toRotate, Vector around, double angle) {
        if (angle == 0)
            return toRotate;

        /*
        v = around;

        x1 = x * ((vx * vx) * (1 - cos a) + cos a) + y * ((vx * vy) * (1 - cos a) - vz * sin a) + ((vx * vz) * (1 - cos a) + vy * sin a)
        x2 = x * (())

         */

        double vx = around.getX(), vy = around.getY(), vz = around.getZ();
        double x = toRotate.getX(), y = toRotate.getY(), z = toRotate.getZ();
        double sinA = Math.sin(Math.toRadians(angle)), cosA = Math.cos(Math.toRadians(angle));

        double x1 = x * ((vx * vx) * (1 - cosA) + cosA) + y * ((vx * vy) * (1 - cosA) - vz * sinA) + z * ((vx * vz) * (1 - cosA) + vy * sinA);
        double y1 = x * ((vy * vx) * (1 - cosA) + vz * sinA) + y * ((vy * vy) * (1 - cosA) + cosA) + z * ((vy * vz) * (1 - cosA) - vx * sinA);
        double z1 = x * ((vz * vx) * (1 - cosA) - vy * sinA) + y * ((vz * vy) * (1 - cosA) + vx * sinA) + z * ((vz * vz) * (1 - cosA) + cosA);

        return new Vector(x1, y1, z1);
    }

    public Vector getBackVector(Location loc) {
        final float newZ = (float) (loc.getZ() + (1 * Math.sin(Math.toRadians(loc.getYaw() + 90))));
        final float newX = (float) (loc.getX() + (1 * Math.cos(Math.toRadians(loc.getYaw() + 90))));
        return new Vector(newX - loc.getX(), 0, newZ - loc.getZ());
    }

    public final Vector rotateAroundAxisX(Vector v, double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public final Vector rotateAroundAxisY(Vector v, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public final Vector rotateAroundAxisZ(Vector v, double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos - v.getY() * sin;
        y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    public final Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
        // double x = v.getX(), y = v.getY(), z = v.getZ();
        // double cosX = Math.cos(angleX), sinX = Math.sin(angleX), cosY =
        // Math.cos(angleY), sinY = Math.sin(angleY), cosZ = Math.cos(angleZ),
        // sinZ = Math.sin(angleZ);
        // double nx, ny, nz;
        // nx = (x * cosY + z * sinY) * (x * cosZ - y * sinZ);
        // ny = (y * cosX - z * sinX) * (x * sinZ + y * cosZ);
        // nz = (y * sinX + z * cosX) * (-x * sinY + z * cosY);
        // return v.setX(nx).setY(ny).setZ(nz);
        // Having some strange behavior up there.. Have to look in it later. TODO
        rotateAroundAxisX(v, angleX);
        rotateAroundAxisY(v, angleY);
        rotateAroundAxisZ(v, angleZ);
        return v;
    }

    public final Vector rotateVector(Vector v, Location location) {
        return rotateVector(v, location.getYaw(), location.getPitch());
    }

    public final Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;

        // Z_Axis rotation (Pitch)
        initialX = v.getX();
        initialY = v.getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        // Y_Axis rotation (Yaw)
        initialZ = v.getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector(x, y, z);
    }

    public final double angleToXAxis(Vector vector) {
        return Math.atan2(vector.getX(), vector.getY());
    }

    public Vector getRandomCircleVector() {
        double rnd, x, z;
        rnd = r.nextDouble() * 2 * Math.PI;
        x = Math.cos(rnd);
        z = Math.sin(rnd);

        return new Vector(x, 0, z);
    }

    public Vector getRandomVector() {
        double x, y, z;
        x = r.nextDouble() * 2 - 1;
        y = r.nextDouble() * 2 - 1;
        z = r.nextDouble() * 2 - 1;

        return new Vector(x, y, z).normalize();
    }

    public ArrayList<Vector> createCircle(double y, double radius, int circleParticles) {
        double amount = radius * circleParticles;
        double inc = (2 * Math.PI) / amount;
        ArrayList<Vector> vecs = new ArrayList<Vector>();
        for (int i = 0; i < amount; i++) {
            double angle = i * inc;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            Vector v = new Vector(x, y, z);
            vecs.add(v);
        }
        return vecs;
    }
}
