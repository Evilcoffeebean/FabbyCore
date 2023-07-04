package dev.fabby.com.vouchers;

import dev.fabby.com.Core;
import dev.fabby.com.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class KitVoucher {

    private final String kit;
    private final ItemStack voucher;

    public KitVoucher(String kit) {
        this.kit = kit;
        this.voucher = new ItemUtil(
                Material.PAPER,
                "&d&nKit Voucher:&r &e" + kit,
                "",
                "&aThis is a Kit voucher.",
                "&aYou can use it for yourself or",
                "&ayou can sell/trade it with others.",
                "",
                "&aBy claiming this voucher you will",
                "&areceive the corresponding Kit with",
                "&aall of its benefits and contents.",
                "",
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
        switch (kit) {
            case "Player":
                return "fabby.kit.player";
            case "PlayerPlus":
                return "fabby.kit.playerplus";
            case "PlayerMax":
                return "fabby.kit.playermax";
            case "Food":
                return "fabby.kit.food";
            case "FoodPlus":
                return "fabby.kit.foodplus";
            case "FoodMax":
                return "fabby.kit.foodmax";
            case "Miner":
                return "fabby.kit.miner";
            case "MinerPlus":
                return "fabby.kit.minerplus";
            case "MinerMax":
                return "fabby.kit.minermax";
            case "Biohazard":
                return "fabby.kit.biohazard";
            case "Enchantment":
                return "fabby.kit.enchantment";
            case "Clandestine":
                return "fabby.kit.clandestine";
            case "Vip":
                return "fabby.kit.vip";
            case "Mvp":
                return "fabby.kit.mvp";
            case "Elite":
                return "fabby.kit.elite";
            case "Immortal":
                return "fabby.kit.immortal";
            case "Thanks":
                return "fabby.kit.thanks";
        }
        return null;
    }

    public void execute(Player player) {
        Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(),
                "lp user " + player.getName() + " permission set " + getPermission() + " true");
        player.getInventory().remove(voucher);
    }
}
