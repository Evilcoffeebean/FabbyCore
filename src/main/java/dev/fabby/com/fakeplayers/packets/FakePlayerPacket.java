package dev.fabby.com.fakeplayers.packets;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import dev.fabby.com.Core;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FakePlayerPacket implements PacketSender {

    private final UUID uuid;
    private final String playerName;
    private final String displayText;
    private PacketContainer packet;
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    protected FakePlayerPacket(UUID uuid, String playerName, String displayText) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.displayText = displayText;
    }

    public void setPacket(PacketContainer packet) {
        this.packet = packet;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    @Override
    public void sendPacketOnce(Player player) {
        this.protocolManager.sendServerPacket(player, this.packet);
    }

    public static WrappedGameProfile changeGameProfileSkin(WrappedGameProfile profile) {
        String texture = Core.getCore().getNpcManager().getFromName(profile.getName())[0];
        String signature = Core.getCore().getNpcManager().getFromName(profile.getName())[1];
        profile.getProperties().put("textures", new WrappedSignedProperty("textures", texture, signature));
        return profile;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getDisplayText() {
        return displayText;
    }
}
