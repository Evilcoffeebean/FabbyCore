package dev.fabby.com.kit;

import com.google.common.collect.Maps;
import dev.fabby.com.kit.kits.food.FoodKit;
import dev.fabby.com.kit.kits.food.FoodMax;
import dev.fabby.com.kit.kits.food.FoodPlus;
import dev.fabby.com.kit.kits.miner.MinerKit;
import dev.fabby.com.kit.kits.miner.MinerMax;
import dev.fabby.com.kit.kits.miner.MinerPlus;
import dev.fabby.com.kit.kits.other.BiohazardKit;
import dev.fabby.com.kit.kits.other.ClandestineKit;
import dev.fabby.com.kit.kits.other.EnchantmentKit;
import dev.fabby.com.kit.kits.player.PlayerKit;
import dev.fabby.com.kit.kits.player.PlayerMaxKit;
import dev.fabby.com.kit.kits.player.PlayerPlusKit;
import dev.fabby.com.kit.kits.rank.EliteKit;
import dev.fabby.com.kit.kits.rank.ImmortalKit;
import dev.fabby.com.kit.kits.rank.MvpKit;
import dev.fabby.com.kit.kits.rank.VipKit;
import dev.fabby.com.kit.kits.special.ThanksKit;
import dev.fabby.com.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class KitManager {

    private final Map<String, IKit> kits = Maps.newConcurrentMap();
    private final Map<UUID, List<IKit>> cooldownMap = Maps.newConcurrentMap();

    public KitManager() {
        //TODO cache kits
        kits.put("Player", new PlayerKit());
        kits.put("PlayerPlus", new PlayerPlusKit());
        kits.put("PlayerMax", new PlayerMaxKit());
        kits.put("Food", new FoodKit());
        kits.put("FoodPlus", new FoodPlus());
        kits.put("FoodMax", new FoodMax());
        kits.put("Miner", new MinerKit());
        kits.put("MinerPlus", new MinerPlus());
        kits.put("MinerMax", new MinerMax());
        kits.put("Biohazard", new BiohazardKit());
        kits.put("Enchantment", new EnchantmentKit());
        kits.put("Clandestine", new ClandestineKit());
        kits.put("Vip", new VipKit());
        kits.put("Mvp", new MvpKit());
        kits.put("Elite", new EliteKit());
        kits.put("Immortal", new ImmortalKit());
        kits.put("Thanks", new ThanksKit());
    }

    public IKit getKit(String name) {
        return kits.get(name);
    }

    public void clearKits() {
        kits.clear();
    }

    public ItemStack getVoucher(String name) {
        IKit kit = getKit(name);
        return getVoucher(kit);
    }

    public ItemStack getVoucher(IKit kit) {
        return new ItemUtil(Material.PAPER, kit.getDisplayName(), kit.getDescription()).getProductWithGlow();
    }

    public void enchant(ItemStack item, Enchantment enchantment, int level) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
    }

    public ItemStack potion(PotionEffectType type, int level, int duration, int amount) {
        ItemStack result = new ItemStack(Material.SPLASH_POTION, amount);
        if (result.getItemMeta() instanceof PotionMeta) {
            PotionMeta meta = (PotionMeta) result.getItemMeta();
            meta.addCustomEffect(new PotionEffect(type, 20*duration, level), true);
            result.setItemMeta(meta);
        }
        return result;
    }

    public ItemStack book(Enchantment enchantment, int level, int amount) {
        ItemStack result = new ItemStack(Material.ENCHANTED_BOOK, amount);
        if (result.getItemMeta() instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();
            meta.addStoredEnchant(enchantment, level, false);
            result.setItemMeta(meta);
        }
        return result;
    }

    public void addKitList(Player player, List<IKit> kits) {
        if (!cooldownMap.containsKey(player.getUniqueId()))
            cooldownMap.put(player.getUniqueId(), kits);
    }

    public void addCooldown(Player player, IKit kit) {
        cooldownMap.get(player.getUniqueId()).add(kit);
    }

    public void removeCooldown(Player player, IKit kit) {
        cooldownMap.get(player.getUniqueId()).remove(kit);
    }

    public boolean isCooldown(Player player, IKit kit) {
        return cooldownMap.get(player.getUniqueId()).contains(kit);
    }

    public void clearCooldowns() {
        cooldownMap.clear();
    }
}
