package dev.fabby.com.crates.menu;

import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.MenuUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainCratesMenu extends MenuUtil {

    private final ItemStack spacer = new ItemUtil(Material.PURPLE_STAINED_GLASS_PANE, "&k&la").getProduct();
    private final ItemStack coal = new ItemUtil(Material.CHEST,
            "&8&lCoal Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e16x Iron Block &e(&a50%&e)",
            "&e&l* &e8x Gold Blocks &e(&a50%&e)",
            "&e&l* &e4x Diamond Blocks &e(&a50%&e)",
            "&e&l* &e$15k, $25k, $50k Vouchers &e(&a35%&e)",
            "&e&l* &eVIP Kit random item &e(&a10%&e)",
            "&e&l* &eRedstone Crate Key &e(&a4%&e)",
            "&e&l* &eVIP Rank Voucher &e(&a1%&e)").getProduct();

    private final ItemStack redstone = new ItemUtil(Material.CHEST,
            "&c&lRedstone Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e16x Iron Block &e(&a40%&e)",
            "&e&l* &e8x Gold Blocks &e(&a40%&e)",
            "&e&l* &e4x Diamond Blocks &e(&a40%&e)",
            "&e&l* &e$15k, $25k, $50k Vouchers &e(&a30%&e)",
            "&e&l* &eVIP Kit random item &e(&a15%&e)",
            "&e&l* &eLapis Crate Key &e(&a10%&e)",
            "&e&l* &eVIP Rank Voucher &e(&a5%&e)").getProduct();

    private final ItemStack lapis = new ItemUtil(Material.CHEST,
            "&9&lLapis Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e16x Iron Block &e(&a30%&e)",
            "&e&l* &e8x Gold Blocks &e(&a30%&e)",
            "&e&l* &e4x Diamond Blocks &e(&a30%&e)",
            "&e&l* &e$15k, $25k, $50k Vouchers &e(&a35%&e)",
            "&e&l* &eVIP Kit random item &e(&a20%&e)",
            "&e&l* &eIron Crate Key &e(&a10%&e)",
            "&e&l* &eVIP Rank Voucher &e(&a5%&e)").getProduct();

    private final ItemStack iron = new ItemUtil(Material.CHEST,
            "&f&lIron Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e16x Iron Block &e(&a20%&e)",
            "&e&l* &e8x Gold Blocks &e(&a20%&e)",
            "&e&l* &e4x Diamond Blocks &e(&a20%&e)",
            "&e&l* &e$15k, $25k, $50k Vouchers &e(&a30%&e)",
            "&e&l* &eVIP Kit random item &e(&a18%&e)",
            "&e&l* &eMVP Kit random item &e(&a15%&e)",
            "&e&l* &eGold Crate Key &e(&a12%&e)",
            "&e&l* &eMVP Rank Voucher &e(&a5%&e)").getProduct();

    private final ItemStack gold = new ItemUtil(Material.CHEST,
            "&6&lGold Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e16x Iron Block &e(&a20%&e)",
            "&e&l* &e8x Gold Blocks &e(&a20%&e)",
            "&e&l* &e4x Diamond Blocks &e(&a20%&e)",
            "&e&l* &e$50k, $75k, $100k Vouchers &e(&a35%&e)",
            "&e&l* &eMVP Kit random item &e(&a22%&e)",
            "&e&l* &eEmerald Crate Key &e(&a13%&e)",
            "&e&l* &eMVP Rank Voucher &e(&a10%&e)").getProduct();

    private final ItemStack emerald = new ItemUtil(Material.CHEST,
            "&a&lEmerald Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e16x Iron Block &e(&a18%&e)",
            "&e&l* &e8x Gold Blocks &e(&a18%&e)",
            "&e&l* &e4x Diamond Blocks &e(&a18%&e)",
            "&e&l* &e$120k Direct Payout &e(&a38%&e)",
            "&e&l* &eElite Kit random item &e(&a22%&e)",
            "&e&l* &eDiamond Crate Key &e(&a15%&e)",
            "&e&l* &eElite Rank Voucher &e(&a5%&e)").getProduct();

    private final ItemStack diamond = new ItemUtil(Material.CHEST,
            "&b&lDiamond Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e16x Iron Block &e(&a5%&e)",
            "&e&l* &e8x Gold Blocks &e(&a5%&e)",
            "&e&l* &e4x Diamond Blocks &e(&a5%&e)",
            "&e&l* &e$150k Direct Payout &e(&a22%&e)",
            "&e&l* &eElite Kit random item &e(&a35%&e)",
            "&e&l* &eNetherite Crate Key &e(&a15%&e)",
            "&e&l* &eElite Rank Voucher &e(&a10%&e)").getProductWithGlow();

    private final ItemStack netherite = new ItemUtil(Material.CHEST,
            "&4&lNetherite Crate",
            "",
            "&e&lProbability table listed below:",
            "",
            "&e&l* &e$200k Direct Payout &e(&a22%&e)",
            "&e&l* &eImmortal Kit random item &e(&a17%&e)",
            "&e&l* &eNetherite Crate Key &e(&a11%&e)",
            "&e&l* &eVIP Rank Voucher &e(&a20%&e)",
            "&e&l* &eMVP Rank Voucher &e(&a15%&e)",
            "&e&l* &eElite Rank Voucher &e(&a10%&e)",
            "&e&l* &eImmortal Rank Voucher &e(&a5%&e)").getProductWithGlow();

    public MainCratesMenu() {
        super("&b&lSelect a crate", 45);
    }

    @Override
    public String[] getDesign() {
        return new String[] {
                "x", "x", "x", "x", "x", "x", "x", "x", "x",
                "x", "", "1", "", "2", "", "3", "", "x",
                "x", "", "4", "", "5", "", "6", "", "x",
                "x", "", "", "7", "", "8", "", "", "x",
                "x", "x", "x", "x", "x", "x", "x", "x", "x"
        };
    }

    public void buildAndOpen(Player player) {
        for (int i = 0; i < getDesign().length; i++) {
            switch (getDesign()[i]) {
                case "":
                    continue;
                case "x":
                    setItem(i, spacer);
                    continue;
                case "1":
                    setItem(i, coal);
                    continue;
                case "2":
                    setItem(i, redstone);
                    continue;
                case "3":
                    setItem(i, lapis);
                    continue;
                case "4":
                    setItem(i, iron);
                    continue;
                case "5":
                    setItem(i, gold);
                    continue;
                case "6":
                    setItem(i, emerald);
                    continue;
                case "7":
                    setItem(i, diamond);
                    continue;
                case "8":
                    setItem(i, netherite);
                    continue;
            }
        }
        build(player);
    }
}
