package dev.fabby.com.commands.donor;

import dev.fabby.com.Core;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AutographCommand implements CommandExecutor {

    //unused command

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("autograph")) {
            if (!(sender instanceof Player)) {
                Bukkit.getLogger().info("Only player can give autographed items.");
                return true;
            }
            final Player player = (Player) sender;
            if (!player.hasPermission("fabby.autograph")) {
                player.sendMessage(ChatColor.GREEN + "You don't have permission to sign this item.");
                return true;
            }

            if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                player.sendMessage(ChatColor.GREEN + "You must be holding an item to sign it.");
                return true;
            }

            String prefix = Core.getCore().getApi().getPlayerAdapter(Player.class).getUser(player).getCachedData().getMetaData().getPrefix();
            String name = prefix + player.getName();

            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(StringUtil.color("&bSigned by: " + name));
            item.setItemMeta(meta);

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            player.sendMessage(ChatColor.GREEN + "Successfully signed " + player.getInventory().getItemInMainHand().getType().toString().toLowerCase().replace("_", " "));
            return true;
        }
        return false;
    }
}
