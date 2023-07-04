package dev.fabby.com.staff;

import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.MenuUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class StaffPotionEffectMenu extends MenuUtil {

    private final ItemStack speed = new ItemUtil(Material.FEATHER, ChatColor.RED + "Speed").getProduct();
    private final ItemStack haste = new ItemUtil(Material.DIAMOND_PICKAXE, ChatColor.RED + "Haste").getProduct();
    private final ItemStack strength = new ItemUtil(Material.GOLD_BLOCK, ChatColor.RED + "Strength").getProduct();
    private final ItemStack jumpBoost = new ItemUtil(Material.SPECTRAL_ARROW, ChatColor.RED + "Jump Boost").getProduct();
    private final ItemStack regeneration = new ItemUtil(Material.SHEARS, ChatColor.RED + "Regeneration").getProduct();
    private final ItemStack resistance = new ItemUtil(Material.SHIELD, ChatColor.RED + "Resistance").getProduct();
    private final ItemStack fireResistance = new ItemUtil(Material.FIRE_CHARGE, ChatColor.RED + "Fire Resistance").getProduct();
    private final ItemStack waterBreathing = new ItemUtil(Material.HEART_OF_THE_SEA, ChatColor.RED + "Water Breathing").getProduct();
    private final ItemStack invisibility = new ItemUtil(Material.GLASS_BOTTLE, ChatColor.RED + "Invisibility").getProduct();
    private final ItemStack nightVision = new ItemUtil(Material.WITHER_ROSE, ChatColor.RED + "Night Vision").getProduct();
    private final ItemStack healthBoost = new ItemUtil(Material.GOLDEN_APPLE, ChatColor.RED + "Health Boost").getProduct();
    private final ItemStack absorption = new ItemUtil(Material.GOLDEN_CARROT, ChatColor.RED + "Absorption").getProduct();
    private final ItemStack saturation = new ItemUtil(Material.COOKED_BEEF, ChatColor.RED + "Saturation").getProduct();
    private final ItemStack glowing = new ItemUtil(Material.GLOWSTONE_DUST, ChatColor.RED + "Glowing").getProduct();
    private final ItemStack luck = new ItemUtil(Material.RABBIT_FOOT, ChatColor.RED + "Luck").getProduct();
    private final ItemStack slowFalling = new ItemUtil(Material.LEATHER_BOOTS, ChatColor.RED + "Slow Falling").getProduct();
    private final ItemStack conduitPower = new ItemUtil(Material.CONDUIT, ChatColor.RED + "Conduit Power").getProduct();
    private final ItemStack dolphinsGrace = new ItemUtil(Material.DOLPHIN_SPAWN_EGG, ChatColor.RED + "Dolphin's Grace").getProduct();
    private final ItemStack villageHero = new ItemUtil(Material.ENCHANTED_BOOK, ChatColor.RED + "Hero of the Village").getProduct();
    private final ItemStack clear = new ItemUtil(Material.BARRIER, ChatColor.DARK_RED + "Clear all effects").getProduct();
    private final ItemStack spacer = new ItemUtil(Material.RED_STAINED_GLASS_PANE, " ").getProduct();

    public StaffPotionEffectMenu() {
        super("&4&lStaff Effects Menu", 45);
    }

    @Override
    public String[] getDesign() {
        return new String[]{
                "x", "x", "x", "x", "x", "x", "x", "x", "x",
                "x", "1", "2", "3", "4", "5", "6", "7", "x",
                "x", "8", "9", "10", "11", "12", "13", "14", "x",
                "x", "15", "16", "17", "18", "19", "", "clear", "x",
                "x", "x", "x", "x", "x", "x", "x", "x", "x"
        };
    }

    public void applyEffect(Player player, ItemStack item) {
        switch (item.getType()) {
            case FEATHER:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
                return;
            case DIAMOND_PICKAXE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2));
                return;
            case GOLD_BLOCK:
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
                return;
            case SPECTRAL_ARROW:
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
                return;
            case SHEARS:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 2));
                return;
            case SHIELD:
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
                return;
            case FIRE_CHARGE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 2));
                return;
            case HEART_OF_THE_SEA:
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 2));
                return;
            case GLASS_BOTTLE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));
                return;
            case WITHER_ROSE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 2));
                return;
            case GOLDEN_APPLE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 2));
                return;
            case GOLDEN_CARROT:
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 2));
                return;
            case COOKED_BEEF:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 2));
                return;
            case GLOWSTONE_DUST:
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 2));
                return;
            case RABBIT_FOOT:
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 2));
                return;
            case LEATHER_BOOTS:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 2));
                return;
            case CONDUIT:
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, Integer.MAX_VALUE, 2));
                return;
            case DOLPHIN_SPAWN_EGG:
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, 2));
                return;
            case ENCHANTED_BOOK:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, Integer.MAX_VALUE, 2));
                return;
        }
    }

    public void buildAndOpen(Player player){
        for (int i = 0; i < getDesign().length; i++) {
            switch (getDesign()[i]) {
                case "":
                    continue;
                case "x":
                    setItem(i, spacer);
                    continue;
                case "1":
                    setItem(i, speed);
                    continue;
                case "2":
                    setItem(i, haste);
                    continue;
                case "3":
                    setItem(i, strength);
                    continue;
                case "4":
                    setItem(i, jumpBoost);
                    continue;
                case "5":
                    setItem(i, regeneration);
                    continue;
                case "6":
                    setItem(i, resistance);
                    continue;
                case "7":
                    setItem(i, fireResistance);
                    continue;
                case "8":
                    setItem(i, waterBreathing);
                    continue;
                case "9":
                    setItem(i, invisibility);
                    continue;
                case "10":
                    setItem(i, nightVision);
                    continue;
                case "11":
                    setItem(i, healthBoost);
                    continue;
                case "12":
                    setItem(i, absorption);
                    continue;
                case "13":
                    setItem(i, saturation);
                    continue;
                case "14":
                    setItem(i, glowing);
                    continue;
                case "15":
                    setItem(i, luck);
                    continue;
                case "16":
                    setItem(i, slowFalling);
                    continue;
                case "17":
                    setItem(i, conduitPower);
                    continue;
                case "18":
                    setItem(i, dolphinsGrace);
                    continue;
                case "19":
                    setItem(i, villageHero);
                    continue;
                case "clear":
                    setItem(i, clear);
                    continue;
            }
        }
        build(player);
    }
}
