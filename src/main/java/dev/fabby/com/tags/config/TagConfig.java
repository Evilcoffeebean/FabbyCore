package dev.fabby.com.tags.config;

import dev.fabby.com.Core;
import dev.fabby.com.utils.FileUtil;
import dev.fabby.com.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TagConfig extends FileUtil {

    private final String path = "tags", separate = ".", full = path + separate;

    public TagConfig(final UUID id) {
        super(new File(Core.getCore().getDataFolder() + File.separator + "tags" + File.separator, id.toString() + ".yml"));
    }

    public void saveTag(String identifier, String tag) {
        try {
            set(full + identifier, tag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTag(String identifier) {
        try {
            set(full + identifier, null, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDisplayTag(String identifier) {
        return getDisplayTag(identifier, true);
    }

    public String getDisplayTag(String identifier, boolean color) {
        return color ? StringUtil.color(getString(full + identifier)) : getString(full + identifier);
    }

    //test this
    public List<String> getAll() {
        return new ArrayList<>(getConfig().getConfigurationSection(path).getKeys(false));
    }
}
