package dev.fabby.com.fakeplayers.tasks;

import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.Dialogues;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.Set;

public class DialogueTask extends BukkitRunnable {

    private final Random random = new Random();

    @Override
    public void run() {
        if (!Core.getCore().getNpcManager().getNpcMap().isEmpty()) {
            Set<String> savedNames = Core.getCore().getNpcManager().getNpcMap().keySet();
            int index = random.nextInt(savedNames.size());
            FakePlayerList randomPlayer = FakePlayerList.valueOf((String) savedNames.toArray()[index]);
            String result = Dialogues.buildFinalDialogue();

            //problem with bots tagging themselves
            if (!result.contains(randomPlayer.getName())) {
                if (Core.getCore().getNpcManager().isSaved(randomPlayer.getName())) {
                    String rank = Core.getCore().getNpcManager().formatRank(randomPlayer.getRank());
                    String display = randomPlayer.getDisplayName();

                    Bukkit.getOnlinePlayers().forEach(online -> online.sendMessage(StringUtil.color(rank + " " + display + " &7»&f " + result)));
                }
            }
        }
    }
}
