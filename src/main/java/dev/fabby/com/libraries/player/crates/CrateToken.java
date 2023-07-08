package dev.fabby.com.libraries.player.crates;

import dev.fabby.com.libraries.player.FabbyPlayer;

import java.util.UUID;

public class CrateToken {

    private long openedAt;
    private Key keyUsed;
    private String crateName;
    private FabbyPlayer openedBy;
    private UUID openedByUUID;

    public CrateToken(Key keyUsed, String crateName, FabbyPlayer openedBy) {
        this.openedAt = System.currentTimeMillis();
        this.keyUsed = keyUsed;
        this.crateName = crateName;
        this.openedBy = openedBy;
        this.openedByUUID = openedBy.getUuid();
    }

    public long getOpenedAt() {
        return openedAt;
    }

    public Key getKeyUsed() {
        return keyUsed;
    }

    public String getCrateName() {
        return crateName;
    }

    public FabbyPlayer getOpenedBy() {
        return openedBy;
    }

    public UUID getOpenedByUUID() {
        return openedByUUID;
    }


}
