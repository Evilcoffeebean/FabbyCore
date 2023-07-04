package dev.fabby.com.commands.staff;

import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnounceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("announce")) {
            if (!(sender instanceof Player)) {
                if (args.length < 1) {
                    Bukkit.getLogger().info("Usage: /announce <message>");
                    return true;
                }

                final String msg = StringUtil.join(0, args);
                Bukkit.getOnlinePlayers().forEach(pl -> {
                    pl.playSound(pl.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                    pl.sendTitle(
                            StringUtil.color("&b&lANNOUNCEMENT"),
                            StringUtil.color(msg),
                            20,
                            20 * 7,
                            20

                    );
                });
                return true;
            }

            final Player p = (Player) sender;
            if (!p.hasPermission("fabby.announce")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to make an announcement.");
                return true;
            }

            if (args.length < 1) {
                p.sendMessage(ChatColor.YELLOW + "/announce <message>");
                return true;
            }

            final String msg = StringUtil.join(0, args);
            Bukkit.getOnlinePlayers().forEach(pl -> {
                pl.playSound(pl.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                pl.sendTitle(
                        StringUtil.color("&b&lANNOUNCEMENT"),
                        StringUtil.color(msg),
                        20,
                        20*7,
                        20
                );
            });
            return true;
        }
        return false;
    }
}
