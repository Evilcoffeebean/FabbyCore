package dev.fabby.com.utils;

import dev.fabby.com.kit.IKit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public final class StringUtil {

    private final static int CENTER_PX = 154;

    //prevent instantiation
    private StringUtil() {}

    public static String json(String input) {
        return json(input, true);
    }

    public static String json(String input, boolean color) {
        return "{\"text\":\"" + (color ? color(input) : input) + "\"}";
    }

    public static String join(String... text) {
        final StringBuilder response = new StringBuilder();
        Stream.of(text).forEach(line -> response.append(line).append("\n"));
        return response.toString();
    }

    public static String join(int index, String... text) {
        final StringBuilder builder = new StringBuilder();
        for (int i = index; i < text.length; i++)
            builder.append(text[i]).append(i >= text.length - 1 ? "" : " ");

        return builder.toString();
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String formatBalance(double bal) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return "$" + decimalFormat.format(bal);
    }

    public static String getCenteredMessage(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }

        return sb.toString() + message;
    }

    public static void sendCenteredMessage(Player player, String message){
        player.sendMessage(getCenteredMessage(message));
    }

    public static String getScrambled(String word) {
        List<Character> chars = new ArrayList<>(word.length());
        for (char c : word.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars.subList(1, chars.size()-1));
        char[] shuffled = new char[chars.size()];

        for (int i = 0; i < shuffled.length; i++) {
            shuffled[i] = chars.get(i);
        }
        return new String(shuffled);
    }

    public static String formatKitCooldown(IKit kit) {
        long hours = kit.getCooldodwn()/(20*60*60);
        return (hours <= 1) ? hours + " Hour" : hours + " Hours";
    }
}
