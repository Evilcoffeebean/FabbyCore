package dev.fabby.com.cosmetics;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dev.fabby.com.cosmetics.effects.*;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public final class ParticleManager {

    private static final Map<UUID, IParticle> activeEffects = Maps.newConcurrentMap();
    private static final Map<String, IParticle> effectsMap = Maps.newConcurrentMap();
    private final List<IParticle> allEffects = Lists.newArrayList();

    public ParticleManager() {
        effectsMap.put("Heart", new HeartEffect());
        effectsMap.put("Helix", new HelixEffect());
        effectsMap.put("Smoke", new SmokeEffect());
        effectsMap.put("Warp", new WarpEffect());
        effectsMap.put("Music", new MusicEffect());
        effectsMap.put("Sphere", new SphereEffect());
        effectsMap.put("Star", new StarEffect());
        effectsMap.put("Disco", new DiscoEffect());
        effectsMap.put("Cone", new ConeEffect());
        effectsMap.put("Cube", new CubeEffect());

        allEffects.add(new HeartEffect());
        allEffects.add(new HelixEffect());
        allEffects.add(new SmokeEffect());
        allEffects.add(new WarpEffect());
        allEffects.add(new MusicEffect());
        allEffects.add(new SphereEffect());
        allEffects.add(new StarEffect());
        allEffects.add(new DiscoEffect());
        allEffects.add(new ConeEffect());
        allEffects.add(new CubeEffect());
    }

    public IParticle getEffect(String name) {
        return effectsMap.get(name);
    }

    public boolean hasActiveEffect(Player player) {
        return activeEffects.containsKey(player.getUniqueId());
    }

    public void addActiveEffect(Player player, IParticle effect) {
        activeEffects.put(player.getUniqueId(), effect);
    }

    public void removeActiveEffect(Player player) {
        activeEffects.remove(player.getUniqueId());
    }

    public void clearActiveEffects() {
        activeEffects.clear();
    }

    public String getRandomCosmetic(Random random) {
        int index = random.nextInt(allEffects.size());
        IParticle particle = allEffects.get(index);

        String node = particle.getPermission();
        switch (node) {
            case "fabby.effect.heart":
                return "Heart";
            case "fabby.effect.helix":
                return "Helix";
            case "fabby.effect.smoke":
                return "Smoke";
            case "fabby.effect.warp":
                return "Warp";
            case "fabby.effect.music":
                return "Music";
            case "fabby.effect.sphere":
                return "Sphere";
            case "fabby.effect.star":
                return "Star";
            case "fabby.effect.disco":
                return "Disco";
            case "fabby.effect.cone":
                return "Cone";
            case "fabby.effect.cube":
                return "Cube";
        }
        return null;
    }
}
