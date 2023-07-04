package dev.fabby.com.config;

import dev.fabby.com.Core;
import dev.fabby.com.utils.FileUtil;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class NickConfig extends FileUtil {

    public NickConfig(Core core) {
        super(new File(core.getDataFolder(), "nicks.yml"));
    }

    public void setNickname(String name, String nickname) {
        try {
            set(name + ".nickname", nickname, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname(Player player) {
        return get(player.getName() + ".nickname");
    }

    public boolean noNickname(Player player) {
        return get(player.getName() + ".nickname") == null || get(player.getName() + ".nickname").equals("none");
    }

    public void removeNickname(String player) {
        try {
            set(player + ".nickname", null, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
