package dev.fabby.com.commands;

import dev.fabby.com.utils.StringUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("rules")) {
            final StringBuilder response = new StringBuilder();
            response.append("               &7[&9&lRules&7]          ").append("\n");
            response.append("   &b● &9No advertising of other servers (of any variety)").append("\n");
            response.append("   &b● &9The primary language is English").append("\n");
            response.append("   &b● &9No DDOS/DOX Threats (Unappealable)").append("\n");
            response.append("   &b● &9Don't post walls of texts or spam images or copypastas").append("\n");
            response.append("   &b● &9No phishing or IP grabbing links/scam links").append("\n");
            response.append("   &b● &9No political/controversial topics").append("\n");
            response.append("   &b● &9You can appeal any punishment besides Dox/Ddos threats").append("\n");
            response.append("   &b● &9Griefing &ois &9allowed").append("\n");
            response.append("   &b● &9Swearing &ois &9allowed (don't overdo it though)").append("\n");

            sender.sendMessage(StringUtil.color(StringUtil.getCenteredMessage(response.toString())));
            if (sender instanceof Player)
                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            return true;
        }
        return false;
    }
}
