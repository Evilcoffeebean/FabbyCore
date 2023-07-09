package dev.fabby.com.libraries.player;

public enum Rank {

    MEMBER("", ""),
    VIP ("", ""),
    MVP("", ""),
    ELITE("", ""),
    IMMORTAL("", ""),
    HELPER("", ""),
    MODERATOR("", ""),
    BUILDER("", ""),
    DEVELOPER("", ""),
    ADMIN("", ""),
    SENIOR_ADMIN("", ""),
    MANAGER("", ""),
    CO_OWNER("", ""),
    OWNER("", "");


    private final String prefix;
    private final String chatColour;

    // not important yet
    Rank(final String prefix, final String chatColour) {
        this.prefix = prefix;
        this.chatColour = chatColour;
    }

}
