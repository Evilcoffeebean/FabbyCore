package dev.fabby.com.commands.staff.fakeplayers;

import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.fakeplayers.menu.FakePlayerMenu;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class FakePlayerCommand implements CommandExecutor {

    private final String mcchat = "https://discord.com/api/webhooks/1126083131263418458/2L-50yxv1Flr8o1ekHS1cWVDWeEcxiodSYc8WU_ec_gps_DOeNrpLzgjmvqMdbKNGPRN";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("fakeplayer")) {
            if (!(sender instanceof Player)) {
                if (args.length < 2) {
                    sender.sendMessage("/fp <npc> <message>");
                    return true;
                }

                String fakePlayerName = args[0];
                Set<String> savedPlayers = Core.getCore().getNpcManager().getNpcMap().keySet();

                if (!savedPlayers.contains(fakePlayerName)) {
                    Bukkit.getLogger().info(ChatColor.AQUA + "'" + args[0] + "' isn't an active FakePlayer instance.");
                    return true;
                }

                String msg = StringUtil.join(1, args);
                String rank = Core.getCore().getNpcManager().formatRank(FakePlayerList.valueOf(args[0]).getRank());
                String display = FakePlayerList.valueOf(args[0]).getDisplayName();
                String format = "{rank} {name} &7»&f {msg}";
                String result = format.replace("{rank}", rank).replace("{name}", display).replace("{msg}", msg);

                Bukkit.getOnlinePlayers().forEach(online -> online.sendMessage(StringUtil.color(result)));
                return true;
            }

            final Player player = (Player) sender;

            if (!player.hasPermission("fabby.fp")) {
                player.sendMessage(ChatColor.GREEN + "Unknown command, please use /help");
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.AQUA + "/fp menu - open the spoofer module");
                player.sendMessage(ChatColor.AQUA + "/fp <npc> <message> - say something as an npc");
                return true;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("menu")) {
                    new FakePlayerMenu().buildAndOpen(player);
                } else {
                    player.sendMessage(ChatColor.AQUA + "Unknown FakePlayer command.");
                }
                return true;
            }

            String fakePlayerName = args[0];
            Set<String> savedPlayers = Core.getCore().getNpcManager().getNpcMap().keySet();

            if (!savedPlayers.contains(fakePlayerName)) {
                player.sendMessage(ChatColor.AQUA + "'" + args[0] + "' isn't an active FakePlayer instance.");
                return true;
            }

            String msg = StringUtil.join(1, args);
            String rank = Core.getCore().getNpcManager().formatRank(FakePlayerList.valueOf(args[0]).getRank());
            String display = FakePlayerList.valueOf(args[0]).getDisplayName();
            String format = "{rank} {name} &7»&f {msg}";
            String result = format.replace("{rank}", rank).replace("{name}", display).replace("{msg}", msg);

            Bukkit.getOnlinePlayers().forEach(online -> online.sendMessage(StringUtil.color(result)));

            String discordRank = Core.getCore().getNpcManager().formatDiscordRank(FakePlayerList.valueOf(args[0]).getRank());
            Core.getCore().discordHook(mcchat, discordRank, display, msg);
            return true;
        }
        return false;
    }
}
