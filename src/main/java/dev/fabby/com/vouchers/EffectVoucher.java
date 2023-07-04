package dev.fabby.com.vouchers;

import dev.fabby.com.Core;
import dev.fabby.com.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class EffectVoucher {

    private final String effect;
    private final ItemStack voucher;

    public EffectVoucher(String effect) {
        this.effect = effect;
        this.voucher = new ItemUtil(
                Material.PAPER,
                "&d&nEffect Voucher:&r &e" + effect,
                "",
                "&aThis is an effect voucher.",
                "&aYou can use it for yourself or",
                "&ayou can sell/trade it with others.",
                "",
                "&aBy claiming this voucher you will",
                "&areceive the corresponding cosmetic effect.",
                "",
                "&aYou can equip it by using &e/effects&a.",
                "",
                "&b&nClick Anywhere&r &ato claim (one time only)."
        ).getProductWithGlow();
    }

    public void addVoucher(Player player) {
        player.getInventory().addItem(voucher);
    }

    public boolean hasVoucher(Player player) {
        return player.getInventory().getItemInMainHand().isSimilar(voucher) || player.getInventory().getItemInMainHand() == voucher;
    }

    private String getPermission() {
        switch (effect) {
            case "Cone":
                return "fabby.effect.cone";
            case "Cube":
                return "fabby.effect.cube";
            case "Disco":
                return "fabby.effect.disco";
            case "Heart":
                return "fabby.effect.heart";
            case "Helix":
                return "fabby.effect.helix";
            case "Music":
                return "fabby.effect.music";
            case "Smoke":
                return "fabby.effect.smoke";
            case "Sphere":
                return "fabby.effect.sphere";
            case "Star":
                return "fabby.effect.star";
            case "Warp":
                return "fabby.effect.warp";
        }
        return null;
    }

    public void execute(Player player) {
        Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(),
                "lp user " + player.getName() + " permission set " + getPermission() + " true");
        player.getInventory().remove(voucher);
    }
}
