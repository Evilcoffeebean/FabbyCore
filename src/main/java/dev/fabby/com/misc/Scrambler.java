package dev.fabby.com.misc;

import dev.fabby.com.Core;
import dev.fabby.com.utils.StringUtil;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Getter
public class Scrambler {

    enum RewardType {
        DIRECT_PAYOUT, //49% chance
        MONEY_VOUCHER, //47% chance
        COSMETIC_VOUCHER, //3%
        RANK_VOUCHER // 1%
    }

    private final Set<String> active = new HashSet<>();
    private final String[] defaultWords = {"fabby", "minecraft", "diamond", "apple", "emerald"};
    private final Integer[] monetaryPrizes = {100, 200, 300, 400, 500}; //works for money vouchers as well
    private final Random random = new Random();
    private final String message = "&c&lWhat am I: &d{scramble}";
    private final int maxDuration = 20*10, startAgain = 20*30; //seconds
    private final Sound win = Sound.ENTITY_PLAYER_LEVELUP;
    private BukkitRunnable scramblerTask;
    private double timer = 0;

    public Scrambler() {
        this.scramblerTask = new BukkitRunnable() {

            final int index = random.nextInt(defaultWords.length);
            final String word = defaultWords[index];

            @Override
            public void run() {
                //
            }
        };
    }

    public void startScrambler() {
        if (scramblerTask != null)
            scramblerTask.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L); //TODO: change this back
    }

    public void playWinSound() {
        Bukkit.getOnlinePlayers().forEach(pl -> pl.playSound(pl.getLocation(), win, 1f, 1f));
    }

    public RewardType getRewardType() {
        int result = random.nextInt(100);

        if (result < 47) {
            if (result < 3) {
                if (result == 1) {
                    return RewardType.RANK_VOUCHER;
                }
                return RewardType.COSMETIC_VOUCHER;
            }
            return RewardType.MONEY_VOUCHER;
        }
        return RewardType.DIRECT_PAYOUT;
    }

    //sync
    private void sendCommand(String command) {
        Core.getCore().getServer().getScheduler().runTask(Core.getCore(), () -> {
            Core.getCore().getServer().dispatchCommand(Core.getCore().getServer().getConsoleSender(), command);
        });
    }

    public void giveReward(final Player winner) {
        final RewardType rewardType = getRewardType();
        switch (rewardType) {
            case RANK_VOUCHER:
                sendCommand("rankvoucher " + winner.getName() + " vip");
                return;
            case MONEY_VOUCHER:
                int index = random.nextInt(monetaryPrizes.length);
                int reward = monetaryPrizes[index];
                sendCommand("moneyvoucher " + winner.getName() + " " + reward);
                return;
            case COSMETIC_VOUCHER:
                String node = Core.getCore().getParticleManager().getRandomCosmetic(random);
                sendCommand("effectvoucher " + winner.getName() + " " + node);
                return;
            case DIRECT_PAYOUT:
                Core.getCore().getEconomy().depositPlayer(winner, 100);
                return;
        }
    }
}
