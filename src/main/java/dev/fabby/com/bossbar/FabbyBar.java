package dev.fabby.com.bossbar;

import dev.fabby.com.utils.StringUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

@Getter
public class FabbyBar {

    private final BossBar bar;
    private final String text;
    private final BarColor color;
    private final BarStyle style;

    public FabbyBar(String text, BarColor color, BarStyle style) {
        this.text = text;
        this.color = color;
        this.style = style;

        this.bar = Bukkit.createBossBar(StringUtil.color(text), color, style);
        this.bar.setProgress(1.0);
    }

    public void sendAll() {
        Bukkit.getOnlinePlayers().forEach(bar::addPlayer);
    }

    public void send(Player... players) {
        Stream.of(players).forEach(bar::addPlayer);
    }
}
