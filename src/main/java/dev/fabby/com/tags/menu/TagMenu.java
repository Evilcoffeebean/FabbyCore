package dev.fabby.com.tags.menu;

import dev.fabby.com.tags.Tag;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.MenuUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TagMenu extends MenuUtil {

    private final ItemStack spacer = new ItemUtil(Material.RED_STAINED_GLASS_PANE, " ").getProduct();
    private final ItemStack cool = new ItemUtil(Material.NAME_TAG, Tag.COOL_PLAYER.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack warrior = new ItemUtil(Material.NAME_TAG, Tag.WARRIOR.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack gardener = new ItemUtil(Material.NAME_TAG, Tag.GARDENER.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack botanist = new ItemUtil(Material.NAME_TAG, Tag.BOTANIST.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack brewer = new ItemUtil(Material.NAME_TAG, Tag.BREWER.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack kingpin = new ItemUtil(Material.NAME_TAG, Tag.KINGPIN.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack beauty = new ItemUtil(Material.NAME_TAG, Tag.BEAUTY.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack lateBloomer = new ItemUtil(Material.NAME_TAG, Tag.LATE_BLOOMER.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack mysterious = new ItemUtil(Material.NAME_TAG, Tag.MYSTERIOUS.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack dab = new ItemUtil(Material.NAME_TAG, Tag.DAB.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack cook = new ItemUtil(Material.NAME_TAG, Tag.COOK.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack sweet = new ItemUtil(Material.NAME_TAG, Tag.SWEET.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack salty = new ItemUtil(Material.NAME_TAG, Tag.SALTY.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack angry = new ItemUtil(Material.NAME_TAG, Tag.ANGRY.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack sick = new ItemUtil(Material.NAME_TAG, Tag.SICK.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack malicious = new ItemUtil(Material.NAME_TAG, Tag.MALICIOUS.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack optimistic = new ItemUtil(Material.NAME_TAG, Tag.OPTIMISTIC.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack deadInside = new ItemUtil(Material.NAME_TAG, Tag.DEAD_INSIDE.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack griefer = new ItemUtil(Material.NAME_TAG, Tag.GRIEFER.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack toxic = new ItemUtil(Material.NAME_TAG, Tag.TOXIC.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack boss = new ItemUtil(Material.NAME_TAG, Tag.BOSS.getDisplay(), "&aRight-Click &7to select.").getProductWithGlow();
    private final ItemStack clear = new ItemUtil(Material.BARRIER, "&cRemove Tag").getProduct();

    public TagMenu() {
        super("&c&lAvailable Tags", 45);
    }

    @Override
    public String[] getDesign() {
        return new String[] {
                "x", "x", "x", "x", "x", "x", "x", "x", "x",
                "x", "1", "2", "3", "4", "5", "6", "7", "x",
                "x", "8", "9", "10", "11", "12", "13", "14", "x",
                "x", "15", "16", "17", "18", "19", "20", "21", "x",
                "x", "x", "x", "x", "clear", "x", "x", "x", "x"
        };
    }

    public void buildAndOpen(final Player player) {
        for (int i = 0; i < getDesign().length; i++) {
            switch (getDesign()[i]) {
                case "x":
                    setItem(i, spacer);
                    continue;
                case "clear":
                    setItem(i, clear);
                    continue;
                case "1":
                    setItem(i, cool);
                    continue;
                case "2":
                    setItem(i, warrior);
                    continue;
                case "3":
                    setItem(i, gardener);
                    continue;
                case "4":
                    setItem(i, botanist);
                    continue;
                case "5":
                    setItem(i, brewer);
                    continue;
                case "6":
                    setItem(i, kingpin);
                    continue;
                case "7":
                    setItem(i, beauty);
                    continue;
                case "8":
                    setItem(i, lateBloomer);
                    continue;
                case "9":
                    setItem(i, mysterious);
                    continue;
                case "10":
                    setItem(i, dab);
                    continue;
                case "11":
                    setItem(i, cook);
                    continue;
                case "12":
                    setItem(i, sweet);
                    continue;
                case "13":
                    setItem(i, salty);
                    continue;
                case "14":
                    setItem(i, angry);
                    continue;
                case "15":
                    setItem(i, sick);
                    continue;
                case "16":
                    setItem(i, malicious);
                    continue;
                case "17":
                    setItem(i, optimistic);
                    continue;
                case "18":
                    setItem(i, deadInside);
                    continue;
                case "19":
                    setItem(i, griefer);
                    continue;
                case "20":
                    setItem(i, toxic);
                    continue;
                case "21":
                    setItem(i, boss);
                    continue;
            }
        }

        build(player);
    }
}
