package dev.fabby.com.fakeplayers.npc;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.fakeplayers.packets.FakePlayer;
import dev.fabby.com.utils.StringUtil;
import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;

@Getter
public final class NPCManager {

    private static final boolean live = true;
    private final Map<String, NPC.Global> npcMap = Maps.newConcurrentMap();
    private final Map<String, FakePlayer> packetMap = Maps.newConcurrentMap();
    private final List<String> completions = new ArrayList<>();

    public String formatRank(String input) {
        switch (input) {
            case "default": return "&7&lPLAYER&7";
            case "vip": return "&3&lVIP&3";
            case "mvp": return "&b&lMVP&b";
            case "elite": return "&d&lELITE&d";
            case "immortal": return "&c&lIMMORTAL&c";
            case "booster" : return "&d&lBOOSTER&d";
        }
        return "";
    }

    public String formatDiscordRank(String input) {
        switch (input) {
            case "default": return "";
            case "vip": return "Vip";
            case "mvp": return "Mvp";
            case "elite": return "Elite";
            case "immortal": return "Immortal";
            case "booster" : return "Booster";
        }
        return "";
    }

    public String[] getFromName(String name) {
        try {
            URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            String uuid = new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();

            URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());
            JsonObject textureProperty = new JsonParser().parse(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = textureProperty.get("value").getAsString();
            String signature = textureProperty.get("signature").getAsString();

            return new String[] {texture, signature};
        } catch (IOException e) {
            System.err.println("Could not get skin data from session servers!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean isSaved(String name) {
       return npcMap.containsKey(name);
    }

    public void saveNpc(World world, String name) {
        NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(Core.getCore(), name, NPC.Global.Visibility.SELECTED_PLAYERS, new Location(world, 0, 0, 0));

        try {
            String rankTag = FakePlayerList.valueOf(name).getRank();
            String formattedRank = formatRank(rankTag);
            String display = FakePlayerList.valueOf(name).getDisplayName();
            String result = (rankTag.equals("default") ? StringUtil.color("&7" + display) : StringUtil.color(formattedRank + " " + display));

            FakePlayer fakePlayer = new FakePlayer(name, result);

            Bukkit.getOnlinePlayers().forEach(player -> {
                if (!packetMap.containsKey(name))
                    packetMap.put(name, fakePlayer);
                fakePlayer.getTablistAddPacket().sendPacketOnce(player);
            });

            //npc.setSkin(getFromName(name)[0], getFromName(name)[1]);
            //npc.setTabListName((rankTag.equals("default") ? StringUtil.color("&7" + display) : formattedRank + " " + display));
        } catch (NullPointerException e) {
            Bukkit.getLogger().log(Level.WARNING, "NPC Skin value not found - skipping.");
        }
        npcMap.put(name, npc);
        completions.add(name);
        Bukkit.getOnlinePlayers().forEach(pl -> pl.addCustomChatCompletions(completions));
    }

    public void removeNpc(String name) {
        NPCLib.getInstance().removeGlobalNPC(npcMap.get(name));
        npcMap.remove(name);
        completions.remove(name);
        Bukkit.getOnlinePlayers().forEach(pl -> {
            pl.removeCustomChatCompletions(completions);
            packetMap.get(name).getTablistRemovePacket().sendPacketOnce(pl);
        });
        packetMap.remove(name);
    }

    public void clearNpc() {
        npcMap.values().forEach(instance -> NPCLib.getInstance().removeGlobalNPC(instance));
        npcMap.clear();
        completions.clear();
        Bukkit.getOnlinePlayers().forEach(pl -> {
            pl.removeCustomChatCompletions(completions);
            packetMap.values().forEach(packet -> packet.getTablistRemovePacket().sendPacketOnce(pl));
        });
        packetMap.clear();
    }
}
