package dev.fabby.com.fakeplayers.events;

import dev.fabby.com.Core;
import dev.fabby.com.fakeplayers.FakePlayerList;
import dev.fabby.com.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class FakeEventHandlers implements Listener {

    private static final List<String> completions = new ArrayList<>();
    private final Map<UUID, FakePlayerList> replyMap = new HashMap<>();
    private final String[] welcomeMessages = {"hey there", "hello", "hi", "hey", "welcome back", "wb"};
    private final String[] messageChecks = {"w", "m", "t", "pm", "emsg", "epm", "tell", "etell", "whisper", "ewhisper", "msg"};
    private final String[] replyChecks = {"reply", "r", "er", "ereply"};
    private final String[] teleportChecks = {"tpa", "tpahere", "etpahere", "tpask"};
    private final Random random = new Random();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onTabComplete(final TabCompleteEvent event) {
        //.clear();

        for (FakePlayerList fake : FakePlayerList.values()) {
            if (Core.getCore().getNpcManager().isSaved(fake.getName())) {
                //completions.add(fake.getName());
                event.getCompletions().add(fake.getName());
            }
        }
        event.setCompletions(event.getCompletions());
    }

    private void respond(FakePlayerList fakePlayer, Player receiver, String msg) {
        final String[] botChecks = {"bot", "fake", "npc", "real player", "real person"};
        final String[] botResponses = {"no?", " no lol", "no, why?", "what lol", "what are u talking about", "I'm not?", "what's your problem?", "dude just fck off"};
        final String[] responses = {"what lol", "?", "fuck off pls", "not interested", "dont msg me", "what", "what u want", "no thx", "leave me alone", "why u msg me tho"};
        final String outgoing = "&6[&cme &6-> &c{fake}&6] &f{msg}", ingoing = "&6[&c{fake} &6-> &cme&6] &f{msg}";
        final int respondSeconds = 7;
        int result = random.nextInt(100);

        if (!replyMap.containsKey(receiver.getUniqueId())) {
            replyMap.put(receiver.getUniqueId(), fakePlayer);
        } else replyMap.replace(receiver.getUniqueId(), fakePlayer);

        for (String botCheck : botChecks) {
            if (msg.contains(botCheck)) {
                int index = random.nextInt(botResponses.length);
                String response = botResponses[index];
                String formatOut = StringUtil.color(outgoing).replace("{fake}", fakePlayer.getName()).replace("{msg}", msg);
                String formatIn = StringUtil.color(ingoing).replace("{fake}", fakePlayer.getName()).replace("{msg}", response);

                receiver.sendMessage(formatOut);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        receiver.sendMessage(formatIn);
                    }
                }.runTaskLaterAsynchronously(Core.getCore(), 20*respondSeconds);
                return;
            }
        }

        if (result < 75) {
            int index = random.nextInt(responses.length);
            String response = responses[index];
            String formatOut = StringUtil.color(outgoing).replace("{fake}", fakePlayer.getName()).replace("{msg}", msg);
            String formatIn = StringUtil.color(ingoing).replace("{fake}", fakePlayer.getName()).replace("{msg}", response);

            receiver.sendMessage(formatOut);
            new BukkitRunnable() {
                @Override
                public void run() {
                    receiver.sendMessage(formatIn);
                }
            }.runTaskLaterAsynchronously(Core.getCore(), 20*respondSeconds);
        }
    }

    private void cancelTeleportRequest(FakePlayerList fakePlayer, Player receiver) {
        final String outgoing = "&6Teleport request sent to &c{fake}&6. The request will expire in &c120 seconds&6. To cancel the request, type &c/tpacancel", ingoing = "&c{fake} &6denied your teleport request.";
        final int respondSeconds = 5;

        String formatOut = StringUtil.color(outgoing).replace("{fake}", fakePlayer.getName());
        String formatIn = StringUtil.color(ingoing).replace("{fake}", fakePlayer.getName());

        receiver.sendMessage(formatOut);
        new BukkitRunnable() {
            @Override
            public void run() {
                receiver.sendMessage(formatIn);
            }
        }.runTaskLaterAsynchronously(Core.getCore(), 20*respondSeconds);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onChatBotChecks(final AsyncPlayerChatEvent event) {
        final String[] botChecks = {"bot", "fake", "npc", "real player", "real person"};
        final String[] botResponses = {"?", "what", "what?", "wut", "wut lol", "lol", "lmfao",
                                       "no", "no lol", "no? why", "tf why lol", "what why lmfao", "??", "w0t m8",
                                       "lmao", "what lol", "what are u talking about", "what's your deal",
                                       "fuck off", "fvck off", "fck off", "piss of", "go die in a fire"};

        //this isnt quite polished out
        final String[] regularQuestions = {"are you", "are u ", "r u", "do you", "do u", "did you", "did u", "can you", "can u", "how have you", "how have u", "have you", "have u"};
        final String[] positiveResponses = {"yes", "yeah", "yea", "ya", "yup", "yep", "mhm", "yessir", "yeah man", "yeah dude", "beats me", "idk"};
        final String[] negativeResponses = {"no", "nope", "nah", "naw", "na", "not yet lol", "not quite lol"};

        final String[] calloutResponses = {"what", "yeah", "?", "what lol", "lol", "lol what"};


        final String responseMessage = "{rank} {npc} &7»&f {msg}";
        final int responseSeconds = 4;

        for (FakePlayerList fake : FakePlayerList.values()) {
            if (Core.getCore().getNpcManager().isSaved(fake.getName())) {
                if (event.getMessage().contains(fake.getName())) {
                    for (String botCheck : botChecks) {
                        if (event.getMessage().contains(botCheck)) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    int responseIndex = random.nextInt(botResponses.length);
                                    String response = botResponses[responseIndex];

                                    String messageResult = StringUtil.color(responseMessage
                                            .replace("{rank}", Core.getCore().getNpcManager().formatRank(fake.getRank()))
                                            .replace("{npc}", fake.getName())
                                            .replace("{msg}", event.getPlayer().getName().toLowerCase() + " " + response));

                                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(messageResult));
                                }
                            }.runTaskLaterAsynchronously(Core.getCore(), 20*responseSeconds);
                            return;
                        } else {
                            for (String question : regularQuestions) {
                                if (event.getMessage().contains(question)) {
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            int chance = random.nextInt(100);
                                            int responseIndex = -1;
                                            String response = null;

                                            if (chance < 50) {
                                                responseIndex = random.nextInt(positiveResponses.length);
                                                response = positiveResponses[responseIndex];
                                            } else {
                                                responseIndex = random.nextInt(negativeResponses.length);
                                                response = negativeResponses[responseIndex];
                                            }

                                            String messageResult = StringUtil.color(responseMessage
                                                    .replace("{rank}", Core.getCore().getNpcManager().formatRank(fake.getRank()))
                                                    .replace("{npc}", fake.getName())
                                                    .replace("{msg}", event.getPlayer().getName().toLowerCase() + " " + response));

                                            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(messageResult));
                                        }
                                    }.runTaskLaterAsynchronously(Core.getCore(), 20*responseSeconds);
                                    return;
                                }
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        int calloutIndex = random.nextInt(calloutResponses.length);
                                        String response = calloutResponses[calloutIndex];

                                        String messageResult = StringUtil.color(responseMessage
                                                .replace("{rank}", Core.getCore().getNpcManager().formatRank(fake.getRank()))
                                                .replace("{npc}", fake.getName())
                                                .replace("{msg}", event.getPlayer().getName().toLowerCase() + " " + response));

                                        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(messageResult));
                                    }
                                }.runTaskLaterAsynchronously(Core.getCore(), 20*responseSeconds);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onReplyMapReset(final PlayerQuitEvent event) {
        replyMap.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onRegularPlayerJoin(final PlayerJoinEvent event) {
        if (Core.getCore().getNpcManager().getNpcMap().keySet().isEmpty())
            return;

        Set<String> savedPlayers = Core.getCore().getNpcManager().getNpcMap().keySet();
        int playerIndex = random.nextInt(savedPlayers.size());
        FakePlayerList fakePlayer = FakePlayerList.valueOf((String) savedPlayers.toArray()[playerIndex]);

        new BukkitRunnable() {
            @Override
            public void run() {
                String rank = Core.getCore().getNpcManager().formatRank(fakePlayer.getRank());
                String display = fakePlayer.getDisplayName();

                int welcomeIndex = random.nextInt(welcomeMessages.length);
                String result = welcomeMessages[welcomeIndex];

                Bukkit.getOnlinePlayers().forEach(online -> online.sendMessage(StringUtil.color(rank + " " + display + " &7»&f " + result)));
            }
        }.runTaskLaterAsynchronously(Core.getCore(), 20*3);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onCommandIntercept(final PlayerCommandPreprocessEvent event) {
        final String baseCommand = event.getMessage().replace("/", "");
        final String[] args = baseCommand.split(" ");

        for (String messageCheck : messageChecks) {
            if (baseCommand.equalsIgnoreCase(messageCheck))
                return;
        }

        for (String replyCheck : replyChecks) {
            if (baseCommand.equalsIgnoreCase(replyCheck))
                return;
        }

        for (String teleportCheck : teleportChecks) {
            if (baseCommand.equalsIgnoreCase(teleportCheck))
                return;
        }

        final String command = args[0];

        for (String check : replyChecks) {
            if (command.equalsIgnoreCase(check)) {
                if (replyMap.containsKey(event.getPlayer().getUniqueId())) {
                    final FakePlayerList fake = replyMap.get(event.getPlayer().getUniqueId());
                    if (Core.getCore().getNpcManager().isSaved(fake.getName())) {
                        event.setCancelled(true);
                        String msg = StringUtil.join(1, args);
                        respond(fake, event.getPlayer(), msg);
                        return;
                    }
                }
            }
        }

        for (String check : messageChecks) {
            if (command.equalsIgnoreCase(check)) {
                for (FakePlayerList fake : FakePlayerList.values()) {
                    if (args[1].equalsIgnoreCase(fake.getName())) {
                        if (Core.getCore().getNpcManager().isSaved(fake.getName())) {
                            event.setCancelled(true);
                            String msg = StringUtil.join(2, args);
                            respond(fake, event.getPlayer(), msg);
                            return;
                        }
                    }
                }
            }
        }

        for (String check : teleportChecks) {
            if (command.equalsIgnoreCase(check)) {
                for (FakePlayerList fake : FakePlayerList.values()) {
                    if (args[1].equalsIgnoreCase(fake.getName())) {
                        if (Core.getCore().getNpcManager().isSaved(fake.getName())) {
                            event.setCancelled(true);
                            cancelTeleportRequest(fake, event.getPlayer());
                            return;
                        }
                    }
                }
            }
        }
    }
}
