package dev.fabby.com.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class AnnouncerUtil extends BukkitRunnable {

    private static final Random random = new Random();
    private final Sound sound = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;
    private final String prefix = StringUtil.color("&9Fabby&bSMP &9| ");
    private final String[] broadcasts = {
            "&bThank you for joining us, we hope you have a good time!",
            "&bUse &e/help &bfor general information regarding the Server.",
            "&bWe host events regularly, make sure to follow us on Discord!",
            "&bConsider donating to support our community &e/store",
            "&bPlease report any bugs you find on our Discord server.",
            "&bHave a helpful suggestion? Tell us at our Discord server!",
            "&bDon't hesitate to ask our helpful staff team regarding any questions you might have.",
            "&bStay tuned for giveaways on our Discord server!",
            "&bMake sure to read the rules at &e/rules"
    };

    @Override
    public void run() {
        int index = random.nextInt(broadcasts.length);
        String message = StringUtil.color(broadcasts[index]);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(prefix + message);
            player.playSound(player.getLocation(), sound, 1f, 1f);
        }
    }
}
