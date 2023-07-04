package dev.fabby.com.cosmetics.effects;

import dev.fabby.com.Core;
import dev.fabby.com.cosmetics.Effect;
import dev.fabby.com.cosmetics.IParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class SmokeEffect implements IParticle {

    private final Effect effect = new Effect();
    private final Random random = new Random();
    private final Particle particle = Particle.SMOKE_NORMAL;
    private final int particles = 20;

    @Override
    public String getPermission() {
        return "fabby.effect.smoke";
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
                for (int i = 0; i < particles; i++) {
                    location.add(effect.getRandomCircleVector().multiply(random.nextDouble() * 0.6d));
                    location.add(0, random.nextFloat() * 2, 0);
                    effect.particle(location, particle);
                }
                if (!Core.getCore().getParticleManager().hasActiveEffect(player))
                    cancel();
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0L, 3L);
    }
}
