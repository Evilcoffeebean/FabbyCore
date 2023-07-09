package dev.fabby.com;

import com.comphenix.protocol.ProtocolLibrary;
import dev.fabby.com.bossbar.DefaultServerBar;
import dev.fabby.com.commands.*;
import dev.fabby.com.commands.donor.NickCommand;
import dev.fabby.com.commands.staff.*;
import dev.fabby.com.commands.staff.fakeplayers.FakePlayerCommand;
import dev.fabby.com.commands.tag.TagCommandExecutor;
import dev.fabby.com.config.NickConfig;
import dev.fabby.com.cosmetics.CosmeticsListener;
import dev.fabby.com.cosmetics.ParticleManager;
import dev.fabby.com.crates.CrateListener;
import dev.fabby.com.crates.CrateManager;
import dev.fabby.com.crates.CratesConfig;
import dev.fabby.com.crates.menu.MainCratesMenuListener;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.fakeplayers.events.FakeEventHandlers;
import dev.fabby.com.fakeplayers.menu.FakePlayerMenuListener;
import dev.fabby.com.fakeplayers.npc.NPCManager;
import dev.fabby.com.fakeplayers.tasks.DeathMessageTask;
import dev.fabby.com.kit.KitManager;
import dev.fabby.com.kit.menu.KitMenuListener;
import dev.fabby.com.libraries.api.API;
import dev.fabby.com.libraries.player.FabbyPlayer;
import dev.fabby.com.listeners.PlayerJoin;
import dev.fabby.com.misc.Scrambler;
import dev.fabby.com.misc.ScramblerListener;
import dev.fabby.com.staff.LocatorListener;
import dev.fabby.com.staff.PersonalProtection;
import dev.fabby.com.staff.StaffPotionListener;
import dev.fabby.com.tab.TabUpdater;
import dev.fabby.com.tags.Tag;
import dev.fabby.com.tags.TagPlayerManager;
import dev.fabby.com.tags.menu.TagMenuListener;
import dev.fabby.com.utils.*;
import dev.fabby.com.vouchers.VoucherListener;
import dev.sergiferry.playernpc.api.NPCLib;
import fr.mrmicky.fastboard.FastBoard;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

@Getter
public class Core extends JavaPlugin implements Listener {

    private static Core core;
    private API api;
    private CommandHandler commandHandler;
    private Task taskManager;
    private Economy economy;
    private LuckPerms luckPermsApi;
    private TabUpdater tabUpdater;
    private NickConfig nickConfig;
    private CratesConfig cratesConfig;
    private IdFetcher idFetcher;
    private final ParticleManager particleManager = new ParticleManager();
    private final CrateManager crateManager = new CrateManager();
    private final KitManager kitManager = new KitManager();
    private final Scrambler scrambler = new Scrambler();
    private final TagPlayerManager tagPlayerManager = new TagPlayerManager();
    private final NPCManager npcManager = new NPCManager();
    private final Map<UUID, FastBoard> boards = new ConcurrentHashMap<>();
    private final Map<UUID, FabbyPlayer> fabbyPlayers = new ConcurrentHashMap<>();
    private final String CHAT_FORMAT = "%s %s%s&7: "; //tag, rank, name
    private final String JOIN_FORMAT = "&7[&a+&7] %s%s", QUIT_FORMAT = "&7[&c-&7] %s%s"; //rank, name
    private final Sound JOIN_SOUND = Sound.BLOCK_NOTE_BLOCK_PLING;

    private final Map<UUID, String> ipMap = new HashMap<>();

    public static Core getCore() {
        return core;
    }

    @Override
    public void onEnable() {
        core = this;
        api = new API();
        nickConfig = new NickConfig(this);
        cratesConfig = new CratesConfig();
        idFetcher = new IdFetcher(1);
        taskManager = new Task();
        commandHandler = new CommandHandler();

        setupPermsApi();
        if (!setupEconomy()) {
            getServer().getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("nick").setExecutor(new NickCommand());
        getCommand("announce").setExecutor(new AnnounceCommand());
        getCommand("rankvoucher").setExecutor(new RankVoucherCommand());
        getCommand("effectvoucher").setExecutor(new EffectVoucherCommand());
        getCommand("cosmetics").setExecutor(new CosmeticsCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand());
        getCommand("key").setExecutor(new KeyCommand());
        getCommand("moneyvoucher").setExecutor(new MoneyVoucherCommand());
        getCommand("crates").setExecutor(new CratesCommand());
        getCommand("setcrate").setExecutor(new SetCrateCommand());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("kitvoucher").setExecutor(new KitVoucherCommand());
        getCommand("tag").setExecutor(new TagCommandExecutor());
        getCommand("tagvoucher").setExecutor(new TagVoucherCommand());
        //getCommand("autograph").setExecutor(new AutographCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("fakeplayer").setExecutor(new FakePlayerCommand());
        getCommand("rtp").setExecutor(new RtpCommand());

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new VoucherListener(), this);
        getServer().getPluginManager().registerEvents(new CosmeticsListener(), this);
        getServer().getPluginManager().registerEvents(new StaffPotionListener(), this);
        getServer().getPluginManager().registerEvents(new DefaultServerBar(), this);
        getServer().getPluginManager().registerEvents(new MainCratesMenuListener(), this);
        getServer().getPluginManager().registerEvents(new CrateListener(), this);
        getServer().getPluginManager().registerEvents(new ScramblerListener(), this);
        getServer().getPluginManager().registerEvents(new KitMenuListener(), this);
        getServer().getPluginManager().registerEvents(new LocatorListener(), this);
        getServer().getPluginManager().registerEvents(new TagMenuListener(), this);
        getServer().getPluginManager().registerEvents(new MultiverseWorkaround(), this);
        getServer().getPluginManager().registerEvents(new FakePlayerMenuListener(), this);
        getServer().getPluginManager().registerEvents(new FakeEventHandlers(), this);
        getServer().getPluginManager().registerEvents(new PersonalProtection(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        NPCLib.getInstance().registerPlugin(this);

        tabUpdater = new TabUpdater();
        tabUpdater.runTaskTimerAsynchronously(this, 1L, 3L);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (UUID id : boards.keySet()) {
                    updateBoard(boards.get(id), Bukkit.getPlayer(id));
                }
            }
        }.runTaskTimerAsynchronously(this, 1L, 3L);

        //scrambler.startScrambler();
        new AnnouncerUtil().runTaskTimerAsynchronously(this, 20*60*5, 20*60*10); //broadcast every 10 mins, with a 5 min delay
        //new DialogueTask().runTaskTimerAsynchronously(this, 20*30, 20*30);
        new DeathMessageTask().runTaskTimerAsynchronously(this, 20*60*10, 20*60*15);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PlayerCountSpooferListener());
    }

    @Override
    public void onDisable() {
        boards.clear();
        crateManager.clearAllCrates();
        crateManager.clearCrateEditor();
        particleManager.clearActiveEffects();
        kitManager.clearKits();
        kitManager.clearCooldowns();
        tagPlayerManager.clearActiveTags();
        npcManager.clearNpc();
        npcManager.getPacketMap().clear();
        ipMap.clear();
        getServer().getScheduler().cancelTasks(this);
        ProtocolLibrary.getProtocolManager().removePacketListener(new PlayerCountSpooferListener());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;
        economy = rsp.getProvider();
        return economy != null;
    }

    public void setupPermsApi() {
        RegisteredServiceProvider<LuckPerms> provider = getServer().getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPermsApi = provider.getProvider();
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onJoin(final PlayerJoinEvent e) {
        e.setJoinMessage(null);

        final User user = luckPermsApi.getPlayerAdapter(Player.class).getUser(e.getPlayer());
        final String prefix = user.getCachedData().getMetaData().getPrefix();
        final String name = nickConfig.noNickname(e.getPlayer()) ? e.getPlayer().getName() : nickConfig.getNickname(e.getPlayer());
        final String joinMsg = String.format(JOIN_FORMAT, prefix, name);
        final List<String> completions = new ArrayList<>();

        Bukkit.getOnlinePlayers().forEach(pl -> pl.sendMessage(StringUtil.color(joinMsg)));
        getServer().getOnlinePlayers().forEach(pl -> pl.playSound(pl.getLocation(), JOIN_SOUND, 1f, 1f));

        FastBoard fastBoard = new FastBoard(e.getPlayer());
        fastBoard.updateTitle(StringUtil.color("&c&lFABBY SMP"));
        boards.put(e.getPlayer().getUniqueId(), fastBoard);
        //npcManager.getNpcMap().values().forEach(npc -> npc.addPlayer(e.getPlayer()));

        npcManager.getPacketMap().values().forEach(packet -> packet.getTablistAddPacket().sendPacketOnce(e.getPlayer()));

        for (FakePlayerList fake : FakePlayerList.values()) {
            if (npcManager.isSaved(fake.getName()))
                completions.add(fake.getName());
        }
        e.getPlayer().addCustomChatCompletions(completions);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onQuit(final PlayerQuitEvent e) {
        e.setQuitMessage(null);

        final User user = luckPermsApi.getPlayerAdapter(Player.class).getUser(e.getPlayer());
        final String prefix = user.getCachedData().getMetaData().getPrefix();
        final String name = nickConfig.noNickname(e.getPlayer()) ? e.getPlayer().getName() : nickConfig.getNickname(e.getPlayer());
        final String quitMsg = String.format(QUIT_FORMAT, prefix, name);

        Bukkit.getOnlinePlayers().forEach(pl -> pl.sendMessage(StringUtil.color(quitMsg)));
        boards.remove(e.getPlayer().getUniqueId());
        particleManager.removeActiveEffect(e.getPlayer());
        crateManager.removeEditor(e.getPlayer());
        npcManager.getNpcMap().values().forEach(npc -> npc.removePlayer(e.getPlayer()));
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onChat(final AsyncPlayerChatEvent e) {
        final User user = luckPermsApi.getPlayerAdapter(Player.class).getUser(e.getPlayer());
        final String prefix = user.getCachedData().getMetaData().getPrefix();
        final Tag tag = tagPlayerManager.getActiveTag(e.getPlayer());
        final String name = nickConfig.noNickname(e.getPlayer()) ? "%s" : nickConfig.getNickname(e.getPlayer());

        boolean chatColorPerm = e.getPlayer().hasPermission("fabby.colorchat") || e.getPlayer().isOp();
        e.setMessage(chatColorPerm ? StringUtil.color(e.getMessage()) : ChatColor.WHITE + e.getMessage());
        e.setFormat((tag != null ? tag.getDisplay() + " " : "") + StringUtil.color(prefix +  name) + ChatColor.GRAY + " » " + ChatColor.WHITE + "%2$s");
    }

    public boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

    private void updateBoard(FastBoard board, Player player) {
        String group = luckPermsApi.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup();
        String rank = !group.equalsIgnoreCase("default")
                ? luckPermsApi.getPlayerAdapter(Player.class).getUser(player).getCachedData().getMetaData().getPrefix()
                : "&7None";

        board.updateLines(
                ChatColor.translateAlternateColorCodes('&', "&6&l&m⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜"),
                ChatColor.translateAlternateColorCodes('&', "&6&nPersonal Info:"),
                "",
                ChatColor.translateAlternateColorCodes('&', "   &6● &eRank: " + rank),
                ChatColor.translateAlternateColorCodes('&', "   &6● &eBalance: &a" + StringUtil.formatBalance(economy.getBalance(player))),
                ChatColor.translateAlternateColorCodes('&', "   &6● &eServer: &aSurvival"),
                ChatColor.translateAlternateColorCodes('&', "   &6● &ePlaytime: &a" + TimeUtil.formatDateDiff(System.currentTimeMillis() - (50L*player.getStatistic(Statistic.PLAY_ONE_MINUTE)))),
                "",
                ChatColor.translateAlternateColorCodes('&', "   &astore.fabbysmp.com"),
                ChatColor.translateAlternateColorCodes('&', "   &adiscord.gg/fabbysmp"),
                ChatColor.translateAlternateColorCodes('&', "&6&l&m⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜⤜")
        );
    }

    public void addFabbyPlayer(FabbyPlayer fabbyPlayer) {
        fabbyPlayers.put(fabbyPlayer.getUuid(), fabbyPlayer);
    }

    public void discordHook(String link, String rank, String name, String message) {
        final String unicode = "\\u00BB"; //for '»'
        final String payload = String.format("{\"content\": \"**%s** %s " + unicode + " %s\"}", rank, name, message);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(payload);
            writer.flush();
            writer.close();

            int result = connection.getResponseCode();
            Bukkit.getLogger().log(Level.INFO, "Response code: " + result);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
