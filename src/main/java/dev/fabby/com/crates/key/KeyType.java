package dev.fabby.com.crates.key;

import lombok.Getter;

@Getter
public enum KeyType {

    COAL("Coal"),
    REDSTONE("Redstone"),
    LAPIS("Lapis"),
    IRON("Iron"),
    GOLD("Gold"),
    EMERALD("Emerald"),
    DIAMOND("Diamond"),
    NETHERITE("Netherite");

    private final String name;

    KeyType(String name) {
        this.name = name;
    }

    public static KeyType byName(String input) {
        for (KeyType type : values()) {
            if (type.getName().equalsIgnoreCase(input))
                return type;
        }
        return null;
    }
}
