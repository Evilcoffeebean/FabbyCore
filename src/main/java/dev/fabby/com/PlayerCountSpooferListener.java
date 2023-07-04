package dev.fabby.com;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;

public class PlayerCountSpooferListener extends PacketAdapter {

    public PlayerCountSpooferListener() {
        super(Core.getCore(), ListenerPriority.HIGH, PacketType.Status.Server.SERVER_INFO);
    }

    @Override
    public void onPacketSending(PacketEvent e) {
        if (e.getPacketType().equals(PacketType.Status.Server.SERVER_INFO) &&
                e.getPacket() != null &&
                e.getPacket().getServerPings() != null) {

            WrappedServerPing ping = e.getPacket().getServerPings().read(0);
            JsonObject newPing = new JsonObject();
            JsonParser parser = new JsonParser();
            JsonObject pingObj = parser.parse(ping.toJson()).getAsJsonObject();
            if (pingObj.has("favicon"))
                newPing.addProperty("favicon", pingObj.get("favicon").getAsString());
            JsonObject descriptionObject = pingObj.get("description").getAsJsonObject();
            JsonObject protocolObject = pingObj.get("version").getAsJsonObject();
            newPing.add("version", protocolObject);
            newPing.add("description", descriptionObject);
            JsonObject playerObj = new JsonObject();
            int playersMax = pingObj.get("players").getAsJsonObject().get("max").getAsInt();
            playerObj.addProperty("max", playersMax);
            playerObj.addProperty("online", Bukkit.getOnlinePlayers().size() + Core.getCore().getNpcManager().getNpcMap().size());
            newPing.add("players", playerObj);
            e.getPacket().getServerPings().write(0, WrappedServerPing.fromJson(newPing.toString()));
        }
    }
}
