package dev.fabby.com.fakeplayers.menu;

import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.utils.ItemUtil;
import dev.fabby.com.utils.MenuUtil;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FakePlayerMenu extends MenuUtil {

    public FakePlayerMenu() {
        super("&4&lSpoofer Module", 45);
    }

    //redundant
    @Override
    public String[] getDesign() {
        return new String[] {
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", ""
        };
    }

    private List<String> getNames() {
        List<String> result = new ArrayList<>();
        for (FakePlayerList fake : FakePlayerList.values()) {
            result.add(fake.getName());
        }
        return result;
    }

    private ItemStack player(String name) {
        final FakePlayerList fakePlayer = FakePlayerList.valueOf(name);
        final String formattedRank = Core.getCore().getNpcManager().formatRank(fakePlayer.getRank());
        final String formattedName = fakePlayer.getDisplayName();
        boolean isSaved = Core.getCore().getNpcManager().isSaved(name);

        final Material material = Material.BOOK;
        final String itemDisplay = formattedRank + " " + formattedName;
        final String[] lore = {
                "&6&m-----------------------&r",
                "&a&nFirst Click&e to activate",
                " ",
                "&c&nSecond Click&e to deactivate",
                "&6&m-----------------------&r"
        };

        return isSaved ? new ItemUtil(material, itemDisplay, lore).getProductWithGlow() : new ItemUtil(material, itemDisplay, lore).getProduct();
    }

    public void buildAndOpen(final Player player) {
        for (int i = 0; i < getNames().size(); i++) {
            String name = getNames().get(i);
            setItem(i, player(name)); //not optimal, but faster?
        }

        setItem(43, new ItemUtil(Material.OAK_SIGN, "&a&lBulk Join").getProduct());
        setItem(44, new ItemUtil(Material.BARRIER, "&c&lBulk Leave").getProduct());

        build(player);
    }
}
