package dev.fabby.com.commands;

import dev.fabby.com.utils.StringUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("help")) {
            final StringBuilder response = new StringBuilder();
            response.append("&b&m----------&7[&9&lInfo Center&7]&b&m----------&r").append("\n");
            response.append(" ").append("\n");
            response.append("   &b● &9Server IP: &bplay.fabbysmp.com").append("\n");
            response.append("   &b● &9Server Instance: &bSurvival").append("\n");
            response.append("   &b● &9Website: &bstore.fabbysmp.com").append("\n");
            response.append("   &b● &9Discord: &bdiscord.gg/fabbysmp").append("\n");
            response.append(" ").append("\n");
            response.append("   &9&oWe stand at your disposal for any").append("\n");
            response.append("   &9&oquestions you may have - just ask!").append("\n");
            response.append("&b&m----------&7[&9&lInfo Center&7]&b&m----------");

            sender.sendMessage(StringUtil.color(StringUtil.getCenteredMessage(response.toString())));
            if (sender instanceof Player)
                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            return true;
        }
        return false;
    }
}
