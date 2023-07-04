package dev.fabby.com.kit.menu;

import dev.fabby.com.Core;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.MenuUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitMenu extends MenuUtil {

    private final ItemStack spacer = new ItemUtil(Material.RED_STAINED_GLASS_PANE, " ").getProduct();
    //player kits
    private final ItemStack player = Core.getCore().getKitManager().getKit("Player").getMenuItem();
    private final ItemStack playerPlus = Core.getCore().getKitManager().getKit("PlayerPlus").getMenuItem();
    private final ItemStack playerMax = Core.getCore().getKitManager().getKit("PlayerMax").getMenuItem();
    //food kits
    private final ItemStack food = Core.getCore().getKitManager().getKit("Food").getMenuItem();
    private final ItemStack foodPlus = Core.getCore().getKitManager().getKit("FoodPlus").getMenuItem();
    private final ItemStack foodMax = Core.getCore().getKitManager().getKit("FoodMax").getMenuItem();
    //mining kits
    private final ItemStack miner = Core.getCore().getKitManager().getKit("Miner").getMenuItem();
    private final ItemStack minerPlus = Core.getCore().getKitManager().getKit("MinerPlus").getMenuItem();
    private final ItemStack minerMax = Core.getCore().getKitManager().getKit("MinerMax").getMenuItem();
    //other kits
    private final ItemStack biohazard = Core.getCore().getKitManager().getKit("Biohazard").getMenuItem();
    private final ItemStack enchantment = Core.getCore().getKitManager().getKit("Enchantment").getMenuItem();
    private final ItemStack clandestine = Core.getCore().getKitManager().getKit("Clandestine").getMenuItem();
    //rank kits
    private final ItemStack vip = Core.getCore().getKitManager().getKit("Vip").getMenuItem();
    private final ItemStack mvp = Core.getCore().getKitManager().getKit("Mvp").getMenuItem();
    private final ItemStack elite = Core.getCore().getKitManager().getKit("Elite").getMenuItem();
    private final ItemStack immortal = Core.getCore().getKitManager().getKit("Immortal").getMenuItem();
    //special
    private final ItemStack thanks = Core.getCore().getKitManager().getKit("Thanks").getMenuItem();

    public KitMenu() {
        super("&b&lKit Menu", 54);
    }

    @Override
    public String[] getDesign() {
        return new String[] {
                "x", "x", "x", "x", "x", "x", "x", "x", "x",
                "x", "13", "", "1", "2", "3", "", "", "x",
                "x", "14", "", "4", "5", "6", "", "", "x",
                "x", "15", "", "7", "8", "9", "", "", "x",
                "x", "16", "", "10", "11", "12", "", "17", "x",
                "x", "x", "x", "x", "x", "x", "x", "x", "x"
        };
    }

    public void buildAndOpen(Player caller) {
        for (int i = 0; i < getDesign().length; i++) {
            switch (getDesign()[i]) {
                case "":
                    continue;
                case "x":
                    setItem(i, spacer);
                    continue;
                case "1":
                    setItem(i, player);
                    continue;
                case "2":
                    setItem(i, playerPlus);
                    continue;
                case "3":
                    setItem(i, playerMax);
                    continue;
                case "4":
                    setItem(i, food);
                    continue;
                case "5":
                    setItem(i, foodPlus);
                    continue;
                case "6":
                    setItem(i, foodMax);
                    continue;
                case "7":
                    setItem(i, miner);
                    continue;
                case "8":
                    setItem(i, minerPlus);
                    continue;
                case "9":
                    setItem(i, minerMax);
                    continue;
                case "10":
                    setItem(i, biohazard);
                    continue;
                case "11":
                    setItem(i, enchantment);
                    continue;
                case "12":
                    setItem(i, clandestine);
                    continue;
                case "13":
                    setItem(i, vip);
                    continue;
                case "14":
                    setItem(i, mvp);
                    continue;
                case "15":
                    setItem(i, elite);
                    continue;
                case "16":
                    setItem(i, immortal);
                    continue;
                case "17":
                    setItem(i, thanks);
                    continue;
            }
        }
        build(caller);
    }
}
