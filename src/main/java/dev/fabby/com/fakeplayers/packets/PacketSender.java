package dev.fabby.com.fakeplayers.packets;

import org.bukkit.entity.Player;

public interface PacketSender {

    void sendPacketOnce(Player player);
}
