package dev.fabby.com.fakeplayers.packets;

import dev.fabby.com.fakeplayers.FakePlayerList;

import java.util.UUID;

public class FakePlayer {

    private final TablistAddFakePlayerPacket tablistAddPacket;
    private final TablistRemoveFakePlayerPacket tablistRemovePacket;

    public FakePlayer(String name, String displayName) {
        UUID fakeUUID = UUID.randomUUID();
        this.tablistAddPacket = new TablistAddFakePlayerPacket(fakeUUID, name, displayName);
        this.tablistRemovePacket = new TablistRemoveFakePlayerPacket(fakeUUID, name, displayName);
    }

    public FakePlayer(String name) {
        this(name, " ");
    }

    public TablistAddFakePlayerPacket getTablistAddPacket() {
        return tablistAddPacket;
    }

    public TablistRemoveFakePlayerPacket getTablistRemovePacket() {
        return tablistRemovePacket;
    }

    public static FakePlayer randomFakePlayer() {
        return new FakePlayer(FakePlayerList.getRandomPlayer().getName());
    }
}
