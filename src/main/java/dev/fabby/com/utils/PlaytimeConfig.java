package dev.fabby.com.utils;

import dev.fabby.com.Core;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaytimeConfig {

    public PlaytimeConfig() {
        if (!Core.getCore().getDataFolder().exists()) {
            Core.getCore().getDataFolder().mkdirs();
        }

        File data = new File(Core.getCore().getDataFolder() + "/playtime.json");
        if (!data.exists()) {
            try {
                FileWriter writer = new FileWriter(data.getAbsoluteFile());
                writer.write((new JSONArray()).toJSONString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void savePlayerData(Player player) {
        JSONObject target = new JSONObject();
        target.put("uuid", player.getUniqueId().toString());
        target.put("name", player.getName());
        target.put("time", Integer.toString(player.getStatistic(Statistic.PLAY_ONE_MINUTE)));
        toJson(target);
    }

    public String getPlayerData(String name) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(Core.getCore().getDataFolder() + "/playtime.json");
            JSONArray players = (JSONArray) jsonParser.parse(reader);
            for (Object o : players) {
                JSONObject player = (JSONObject) o;
                if (player.get("name").equals(name)) {
                    return player.get("time").toString();
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void toJson(JSONObject target) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(Core.getCore().getDataFolder() + "/playtime.json");
            JSONArray players = (JSONArray) jsonParser.parse(reader);

            List<JSONObject> list = new ArrayList<>();
            for (Object player : players) {
                JSONObject player_JSON = (JSONObject) player;
                if (!player_JSON.get("uuid").equals(target.get("uuid")))
                    list.add(player_JSON);
            }
            for (int i = 0; i < list.size(); i++) {
                if (Integer.parseInt(target.get("time").toString()) > Integer.parseInt(list.get(i).get("time").toString())) {
                    JSONObject temp = list.get(i);
                    list.set(i, target);
                    target = temp;
                }
            }
            list.add(target);

            JSONArray sortedPlayers = new JSONArray();
            sortedPlayers.addAll(list);

            FileWriter writer = new FileWriter(Core.getCore().getDataFolder() + "/playtime.json");
            writer.write(sortedPlayers.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
