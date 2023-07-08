package dev.fabby.com.libraries.player;

public enum StaffSettings {


    VANISHED("vanished", "Vanished"),
    VANISH_PICKUP("vanishpickup", "Vanish Pickup"),
    VANISH_DROP("vanishdrop", "Vanish Drop"),
    STAFF_CHAT("staffchat", "Staff Chat"),
    SUPER_VANISH("supervanish", "Super Vanish"); // Hide from staff below admin as well

    private final String dbName;
    private final String format;

    StaffSettings(String dbName, String format) {
        this.dbName = dbName;
        this.format = format;
    }

    public String getDbName() {
        return dbName;
    }

    public String getFormat() {
        return format;
    }
}
