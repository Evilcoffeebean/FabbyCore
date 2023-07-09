package dev.fabby.com.libraries.player;

import com.google.gson.JsonObject;
import dev.fabby.com.Core;
import dev.fabby.com.libraries.player.callback.LoadReason;
import dev.fabby.com.utils.Task;
import lombok.Getter;
import lombok.Setter;
import net.luckperms.api.model.group.Group;
import org.json.JSONObject;

import java.util.UUID;

@Getter
@Setter
public class FabbyPlayer {

    private UUID uuid;
    private String name;
    private Group rank;

    private boolean online;
    private boolean loaded;

    private JSONObject staffSettings = null;

    private final Task task = Core.getCore().getTaskManager();

    public FabbyPlayer(JSONObject object, LoadReason reason) {
        load(object, reason);
    }

    private void load(JSONObject obj, LoadReason reason) {
        task.runAsync(() -> {
            this.uuid = UUID.fromString(obj.getString("uuid"));
            this.name = obj.getString("name");
            this.rank = Core.getCore().getLuckPermsApi().getGroupManager().getGroup(obj.getString("rank"));

            if(obj.has("staff_settings")) {
                this.staffSettings = obj.getJSONObject("staff_settings");
            }

            this.online = true;
            this.loaded = true;
        });



    }

    public void updateStaffSetting(final StaffSettings setting, final Object update) {
        staffSettings.put(setting.getDbName(), update);
        JSONObject updateObj = new JSONObject();
        updateObj.put(setting.getDbName(), update);
        // task.runAsync(() -> api.updateField(this.uuid, "staff_settings", updateObj));
    }


    public String getStaffSetting(final StaffSettings setting) {
        return staffSettings.getString(setting.getDbName());
    }

    public boolean checkStaffSetting(final StaffSettings setting) {
        return staffSettings.getBoolean(setting.getDbName());
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Group getRank() {
        return rank;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setRank(Group rank) {
        this.rank = rank;
    }

}
