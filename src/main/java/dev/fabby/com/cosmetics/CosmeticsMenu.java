package dev.fabby.com.cosmetics;

import dev.fabby.com.Core;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.MenuUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CosmeticsMenu extends MenuUtil {

    private Player player;
    private final ItemStack spacer = new ItemUtil(Material.BLUE_STAINED_GLASS_PANE, " ").getProduct();

    public CosmeticsMenu(Player player) {
        super("&d&lCosmetics Menu", 54);
        this.player = player;
    }

    private String permCheck(String effect) {
        IParticle particle = Core.getCore().getParticleManager().getEffect(effect);
        return particle.hasPerm(player) || player.isOp() ? "&aTrue" : "&cFalse";
    }

    @Override
    public String[] getDesign() {
        return new String[] {
                "x", "x", "x", "x", "x", "x", "x", "x", "x",
                "x", "", "", "", "", "", "", "", "x",
                "x", "", "1", "2", "3", "4", "5", "", "x",
                "x", "", "6", "7", "8", "9", "10", "", "x",
                "x", "", "", "", "", "", "", "", "x",
                "x", "x", "x", "x", "x", "x", "x", "x", "x"
        };
    }

    public void buildAndOpen() {
        for (int i = 0; i < getDesign().length; i++) {
            switch (getDesign()[i]) {
                case "x":
                    setItem(i, spacer);
                    continue;
                case "1":
                    setItem(i, new ItemUtil(
                            Material.REDSTONE,
                            "&c&lHeart Effect",
                            "&7Permission: " + permCheck("Heart")
                    ).getProduct());
                    continue;
                case "2":
                    setItem(i, new ItemUtil(
                            Material.SMOKER,
                            "&c&lSmoke Effect",
                            "&7Permission: " + permCheck("Smoke")
                    ).getProduct());
                    continue;
                case "3":
                    setItem(i, new ItemUtil(
                            Material.FEATHER,
                            "&c&lWarp Effect",
                            "&7Permission: " + permCheck("Warp")
                    ).getProduct());
                    continue;
                case "4":
                    setItem(i, new ItemUtil(
                            Material.WRITTEN_BOOK,
                            "&c&lMusic Effect",
                            "&7Permission: " + permCheck("Music")
                    ).getProduct());
                    continue;
                case "5":
                    setItem(i, new ItemUtil(
                            Material.GLOWSTONE_DUST,
                            "&c&lHelix Effect",
                            "&7Permission: " + permCheck("Helix")
                    ).getProduct());
                    continue;
                case "6":
                    setItem(i, new ItemUtil(
                            Material.GLOW_INK_SAC,
                            "&c&lDisco Effect",
                            "&7Permission: " + permCheck("Disco")
                    ).getProduct());
                    continue;
                case "7":
                    setItem(i, new ItemUtil(
                            Material.WITHER_ROSE,
                            "&c&lSphere Effect",
                            "&7Permission: " + permCheck("Sphere")
                    ).getProduct());
                    continue;
                case "8":
                    setItem(i, new ItemUtil(
                            Material.NETHER_STAR,
                            "&c&lStar Effect",
                            "&7Permission: " + permCheck("Star")
                    ).getProduct());
                    continue;
                case "9":
                    setItem(i, new ItemUtil(
                            Material.DIAMOND,
                            "&c&lCone Effect",
                            "&7Permission: " + permCheck("Cone")
                    ).getProduct());
                    continue;
                case "10":
                    setItem(i, new ItemUtil(
                            Material.RAW_GOLD_BLOCK,
                            "&c&lCube Effect",
                            "&7Permission: " + permCheck("Cube")
                    ).getProduct());
                    continue;
            }
        }

        build(player);
    }
}
