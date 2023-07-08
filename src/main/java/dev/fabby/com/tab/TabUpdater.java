package dev.fabby.com.tab;

import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.tags.Tag;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

public class TabUpdater extends BukkitRunnable {

    //donors: VIP, MVP, ELITE, IMMORTAL

    private int counter = 0;
    private static final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

    private final String OWNER = "0000Owner";
    private final String CO_OWNER = "0001CoOwner";
    private final String MANAGER = "0002Manager";
    private final String DEVELOPER = "0003Developer";
    private final String SNR_ADMIN = "0004SnrAdmin";
    private final String ADMIN = "0005Admin";
    private final String MOD = "0006Mod";
    private final String HELPER = "0007Helper";
    private final String BUILDER = "0008Builder";
    private final String MEDIA = "0009Media";
    private final String IMMORTAL = "0010Immortal";
    private final String ELITE = "0011Elite";
    private final String MVP = "0012MVP";
    private final String VIP = "0013Vip";
    private final String BOOSTER = "0013Booster";
    private final String DEFAULT = "0015Default";

    public TabUpdater() {
        board.registerNewTeam(OWNER);
        board.registerNewTeam(CO_OWNER);
        board.registerNewTeam(MANAGER);
        board.registerNewTeam(DEVELOPER);
        board.registerNewTeam(SNR_ADMIN);
        board.registerNewTeam(ADMIN);
        board.registerNewTeam(MOD);
        board.registerNewTeam(HELPER);
        board.registerNewTeam(BUILDER);
        board.registerNewTeam(MEDIA);
        board.registerNewTeam(IMMORTAL);
        board.registerNewTeam(ELITE);
        board.registerNewTeam(MVP);
        board.registerNewTeam(VIP);
        board.registerNewTeam(BOOSTER);
        board.registerNewTeam(DEFAULT);

        board.getTeam(OWNER).setPrefix("§4§lOWNER§4 ");
        board.getTeam(CO_OWNER).setPrefix("§6§lCO-OWNER§6 ");
        board.getTeam(MANAGER).setPrefix("§4§lMANAGER§4 ");
        board.getTeam(DEVELOPER).setPrefix("§4§lDEV§4 ");
        board.getTeam(SNR_ADMIN).setPrefix("§3§lSR-ADMIN§3 ");
        board.getTeam(ADMIN).setPrefix("§c§lADMIN§c ");
        board.getTeam(MOD).setPrefix("§6§lMOD§6 ");
        board.getTeam(HELPER).setPrefix("§a§lHELPER§a ");
        board.getTeam(BUILDER).setPrefix("§2§lBUILDER§2 ");
        board.getTeam(MEDIA).setPrefix("§5§lMEDIA§5 ");
        board.getTeam(IMMORTAL).setPrefix("§c§lIMMORTAL§c ");
        board.getTeam(ELITE).setPrefix("§d§lELITE§d ");
        board.getTeam(MVP).setPrefix("§b§lMVP§b ");
        board.getTeam(VIP).setPrefix("§3§lVIP§3 ");
        board.getTeam(BOOSTER).setPrefix("§d§lBOOSTER§d ");
        board.getTeam(DEFAULT).setPrefix("§7");
    }

    private void setTeams(Player player) {
        String playerTeam = null;
        String playerRank = Core.getCore().getLuckPermsApi().getPlayerAdapter(Player.class).getUser(player).getPrimaryGroup();

        switch (playerRank.toLowerCase()) {
            case "owner":
                playerTeam = OWNER;
                break;
            case "co-owner":
            case "coowner":
                playerTeam = CO_OWNER;
                break;
            case "manager":
                playerTeam = MANAGER;
                break;
            case "developer":
            case "dev":
                playerTeam = DEVELOPER;
                break;
            case "sr-admin":
            case "sradmin":
            case "senioradmin":
                playerTeam = SNR_ADMIN;
                break;
            case "administrator":
            case "admin":
                playerTeam = ADMIN;
                break;
            case "moderator":
            case "mod":
                playerTeam = MOD;
                break;
            case "helper":
                playerTeam = HELPER;
                break;
            case "builder":
                playerTeam = BUILDER;
                break;
            case "media":
                playerTeam = MEDIA;
                break;
            case "immortal":
                playerTeam = IMMORTAL;
                break;
            case "elite":
                playerTeam = ELITE;
                break;
            case "mvp":
                playerTeam = MVP;
                break;
            case "vip":
                playerTeam = VIP;
                break;
            case "booster":
                playerTeam = BOOSTER;
                break;
            case "default":
            case "member":
                playerTeam = DEFAULT;
                break;
        }

        final String name = Core.getCore().getNickConfig().noNickname(player) ? player.getName() : StringUtil.color(Core.getCore().getNickConfig().getNickname(player));
        final Tag tag = Core.getCore().getTagPlayerManager().getActiveTag(player);
        final String playerDisplay = (tag != null ? tag.getDisplay() + " " : "") + board.getTeam(playerTeam).getPrefix() + name;

        board.getTeam(playerTeam).addPlayer(player);

        player.setPlayerListName(playerDisplay);
        player.setScoreboard(board);
    }

    private void setAllFakePlayerTeams(Player player) {
        for (FakePlayerList fake : FakePlayerList.values()) {
            if (Core.getCore().getNpcManager().isSaved(fake.getName())) {
                setFakePlayerTeam(player, fake);
            }
        }
    }

    private void setFakePlayerTeam(Player player, FakePlayerList fake) {
        String fakeTeam = null;
        String group = fake.getRank();

        switch (group.toLowerCase()) {
            case "immortal":
                fakeTeam = IMMORTAL;
                break;
            case "elite":
                fakeTeam = ELITE;
                break;
            case "mvp":
                fakeTeam = MVP;
                break;
            case "vip":
                fakeTeam = VIP;
                break;
            case "booster":
                fakeTeam = BOOSTER;
                break;
            case "default":
            case "member":
                fakeTeam = DEFAULT;
                break;
        }

        String display = board.getTeam(fakeTeam).getPrefix() + fake.getName();
        board.getTeam(fakeTeam).addEntry(fake.getName());

        player.setScoreboard(board);
    }

    private String title() {
        switch (counter) {
            case 0:
            case 1: return "&f&lW&c&lELCOME TO FABBY SMP!";
            case 2: return "&c&lW&f&lE&c&lLCOME TO FABBY SMP!";
            case 3: return "&c&lWE&f&lL&c&lCOME TO FABBY SMP!";
            case 4: return "&c&lWEL&f&lC&c&lOME TO FABBY SMP!";
            case 5: return "&c&lWELC&f&lO&c&lME TO FABBY SMP!";
            case 6: return "&c&lWELCO&f&lM&c&lE TO FABBY SMP!";
            case 7: return "&c&lWELCOM&f&lE &c&lTO FABBY SMP!";
            case 8: return "&c&lWELCOME &f&lT&c&lO FABBY SMP!";
            case 9: return "&c&lWELCOME T&f&lO &c&lFABBY SMP!";
            case 10: return "&c&lWELCOME TO &f&lF&c&lABBY SMP!";
            case 11: return "&c&lWELCOME TO F&f&lA&c&lBBY SMP!";
            case 12: return "&c&lWELCOME TO FA&f&lB&c&lBY SMP!";
            case 13: return "&c&lWELCOME TO FAB&f&lB&c&lY SMP!";
            case 14: return "&c&lWELCOME TO FABB&f&lY &c&lSMP!";
            case 16: return "&c&lWELCOME TO FABBY &f&lS&c&lMP!";
            case 17: return "&c&lWELCOME TO FABBY S&f&lM&c&lP!";
            case 18: return "&c&lWELCOME TO FABBY SM&f&lP&c&l!";
            case 19: return "&c&lWELCOME TO FABBY SMP&f&l!";
        }
        return "&c&lWELCOME TO FABBY SMP!";
    }

    private String header(Player player) {
        final StringBuilder headerBuilder = new StringBuilder();
        headerBuilder.append(title()).append("\n");
        headerBuilder.append("\n");
        headerBuilder.append("&e&lServer: &aSurvival").append("\n");
        headerBuilder.append("&e&lPing: &6").append(player.getPing()).append(" &ems\n");

        return ChatColor.translateAlternateColorCodes('&', headerBuilder.toString());
    }

    private String footer(Player player) {
        int x = (int) Math.round(player.getLocation().getX());
        int y = (int) Math.round(player.getLocation().getY());
        int z = (int) Math.round(player.getLocation().getZ());


        final StringBuilder footerBuilder = new StringBuilder();
        footerBuilder.append("\n");
        footerBuilder.append("  &cWebsite: &7").append("store.fabbysmp.com  ").append("\n");
        footerBuilder.append("    &cDiscord: &7").append("discord.gg/fabbysmp   ").append("\n");
        footerBuilder.append("\n");
        footerBuilder.append("&dConsider donating to support the server!\n");
        footerBuilder.append("\n");
        footerBuilder.append("&7X: &a").append(x).append(" &7Y: &a").append(y).append(" &7Z: &a").append(z).append("\n");

        return ChatColor.translateAlternateColorCodes('&', footerBuilder.toString());
    }

    private void sendTabTitle(Player player) {
        setTeams(player);
        setAllFakePlayerTeams(player);
        player.setPlayerListHeaderFooter(header(player), footer(player));
    }

    @Override
    public void run() {
        counter++;
        Bukkit.getOnlinePlayers().forEach(this::sendTabTitle);

        if (counter > 19)
            counter = 0;
    }
}
