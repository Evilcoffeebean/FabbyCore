package dev.fabby.com.staff;

import dev.fabby.com.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class Locator {

    private Locator() {}

    public static void sendInformation(String ip, Player player, String targetName) {
        String ipinfo = getHttp("http://ip-api.com/line/" + ip);
        if (ipinfo == null || !ipinfo.startsWith("success")) {
            player.sendMessage(StringUtil.color("&cAn error occurred while trying to GeoLocate the IP: &e" + ip));
            return;
        }
        String[] lines = ipinfo.split("\n");
        StringBuilder response = new StringBuilder();
        response.append(StringUtil.color("      &aResults for " + "&a&l" + targetName + "&a's IP:\n"));
        response.append(" \n");
        response.append(StringUtil.color("      &bIP &b&l» " + ChatColor.GRAY + lines[13] + "\n"));
        response.append(StringUtil.color("      &bCity &b&l» " + ChatColor.GRAY + lines[5] + "\n"));
        response.append(StringUtil.color("      &bLatitude &b&l» " + ChatColor.GRAY + lines[7] + "\n"));
        response.append(StringUtil.color("      &bLongitude &b&l» " + ChatColor.GRAY + lines[8] + "\n"));
        response.append(StringUtil.color("      &bPostal Code &b&l» " + ChatColor.GRAY + lines[6] + "\n"));
        response.append(StringUtil.color("      &bRegion &b&l» " + ChatColor.GRAY + lines[4] + "\n"));
        response.append(StringUtil.color("      &bCountry &b&l» " + ChatColor.GRAY + lines[1] + "\n"));
        response.append(StringUtil.color("      &bTimezone &b&l» " + ChatColor.GRAY + lines[9] + "\n"));
        response.append(StringUtil.color("      &bISP &b&l» " + ChatColor.GRAY + lines[11]));
        StringUtil.sendCenteredMessage(player, response.toString());
    }

    private static String getHttp(String url) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
