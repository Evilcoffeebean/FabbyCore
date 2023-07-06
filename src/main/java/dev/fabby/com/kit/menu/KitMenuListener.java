package dev.fabby.com.kit.menu;

import dev.fabby.com.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.HashMap;


public class KitMenuListener implements Listener {


    private final Material[] playerKits = {
            // Player, Player+, Player++
            Material.CHARCOAL,
            Material.COAL,
            Material.COAL_BLOCK,
            // Food, Food+, Food++
            Material.APPLE,
            Material.GOLDEN_APPLE,
            Material.ENCHANTED_GOLDEN_APPLE,
            // Miner, Miner+, Miner++
            Material.IRON_PICKAXE,
            Material.DIAMOND_PICKAXE,
            Material.NETHERITE_PICKAXE,
            // Special
            Material.BREWING_STAND,
            Material.ENCHANTING_TABLE,
            Material.PHANTOM_MEMBRANE,
            // Ranks
            Material.GREEN_DYE,
            Material.LIGHT_BLUE_DYE,
            Material.BLUE_DYE,
            Material.RED_DYE,
            // Booster
            Material.RED_TULIP
    };

    public static final HashMap<Material, String> mappings = new HashMap<>();


    @EventHandler (priority = EventPriority.LOW)
    public void onKitSelect(final InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getCurrentItem() == null)
            return;
        if (!(e.getWhoClicked() instanceof Player))
            return;

        final Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Kit Menu")) {
            e.setCancelled(true);


            var isPlayerKit = Arrays.stream(playerKits).anyMatch(material -> material == e.getCurrentItem().getType());

            if (isPlayerKit) {
                var clickedKit = mappings.get(e.getCurrentItem().getType());

                if(!player.hasPermission("fabby.kit." + mappings.get(e.getCurrentItem().getType()))) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                    player.sendMessage(ChatColor.AQUA + "You don't have permission to purchase that kit.");
                    return;
                }

                if(Core.getCore().getKitManager().isCooldown(player, Core.getCore().getKitManager().getKit(mappings.get(e.getCurrentItem().getType()))) && Core.getCore().getKitManager().getRemainingCooldown(player, Core.getCore().getKitManager().getKit(mappings.get(e.getCurrentItem().getType()))).get() > 0) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                    player.sendMessage(ChatColor.AQUA + "You can't currently purchase this kit.");
                    return;
                }

                Core.getCore().getKitManager().getKit(mappings.get(e.getCurrentItem().getType())).execute(player);
                Core.getCore().getKitManager().addCooldown(player, Core.getCore().getKitManager().getKit(mappings.get(e.getCurrentItem().getType())));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                player.sendMessage(ChatColor.AQUA + "You have purchased the " + Core.getCore().getKitManager().getKit(mappings.get(e.getCurrentItem().getType())).getDisplayName() + ".");
                player.closeInventory();
            }


            /*switch (e.getCurrentItem().getType()) {
                //player kits



                case CHARCOAL:
                    final IKit playerKit = Core.getCore().getKitManager().getKit("Player");

                    if (Core.getCore().getKitManager().isCooldown(player, playerKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    playerKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, playerKit);

                    new BukkitRunnable() {
                        double timer = playerKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, playerKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Player kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case COAL:
                    final IKit playerPlusKit = Core.getCore().getKitManager().getKit("PlayerPlus");

                    if (Core.getCore().getKitManager().isCooldown(player, playerPlusKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    playerPlusKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, playerPlusKit);

                    new BukkitRunnable() {
                        double timer = playerPlusKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, playerPlusKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Player+ kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case COAL_BLOCK:
                    final IKit playerMaxKit = Core.getCore().getKitManager().getKit("PlayerMax");

                    if (Core.getCore().getKitManager().isCooldown(player, playerMaxKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    playerMaxKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, playerMaxKit);

                    new BukkitRunnable() {
                        double timer = playerMaxKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, playerMaxKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Player++ kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                //food kits
                case APPLE:
                    final IKit foodKit = Core.getCore().getKitManager().getKit("Food");

                    if (Core.getCore().getKitManager().isCooldown(player, foodKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    foodKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, foodKit);

                    new BukkitRunnable() {
                        double timer = foodKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, foodKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Food kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case GOLDEN_APPLE:
                    final IKit foodPlusKit = Core.getCore().getKitManager().getKit("FoodPlus");

                    if (Core.getCore().getKitManager().isCooldown(player, foodPlusKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    foodPlusKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, foodPlusKit);

                    new BukkitRunnable() {
                        double timer = foodPlusKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, foodPlusKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Food+ kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case ENCHANTED_GOLDEN_APPLE:
                    final IKit foodMaxKit = Core.getCore().getKitManager().getKit("FoodMax");

                    if (Core.getCore().getKitManager().isCooldown(player, foodMaxKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    foodMaxKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, foodMaxKit);

                    new BukkitRunnable() {
                        double timer = foodMaxKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, foodMaxKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Food++ kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                //miner kits
                case IRON_PICKAXE:
                    final IKit minerKit = Core.getCore().getKitManager().getKit("Miner");

                    if (Core.getCore().getKitManager().isCooldown(player, minerKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    minerKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, minerKit);

                    new BukkitRunnable() {
                        double timer = minerKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, minerKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Miner kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case DIAMOND_PICKAXE:
                    final IKit minerPlusKit = Core.getCore().getKitManager().getKit("MinerPlus");

                    if (Core.getCore().getKitManager().isCooldown(player, minerPlusKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    minerPlusKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, minerPlusKit);

                    new BukkitRunnable() {
                        double timer = minerPlusKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, minerPlusKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Miner+ kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case NETHERITE_PICKAXE:
                    final IKit minerMaxKit = Core.getCore().getKitManager().getKit("MinerMax");

                    if (Core.getCore().getKitManager().isCooldown(player, minerMaxKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    minerMaxKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, minerMaxKit);

                    new BukkitRunnable() {
                        double timer = minerMaxKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, minerMaxKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Miner++ kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                //other kits
                case BREWING_STAND:
                    final IKit biohazardKit = Core.getCore().getKitManager().getKit("Biohazard");

                    if (Core.getCore().getKitManager().isCooldown(player, biohazardKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    biohazardKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, biohazardKit);

                    new BukkitRunnable() {
                        double timer = biohazardKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, biohazardKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Biohazard kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case ENCHANTING_TABLE:
                    final IKit enchantmentKit = Core.getCore().getKitManager().getKit("Enchantment");

                    if (Core.getCore().getKitManager().isCooldown(player, enchantmentKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    enchantmentKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, enchantmentKit);

                    new BukkitRunnable() {
                        double timer = enchantmentKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, enchantmentKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Enchantment kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case PHANTOM_MEMBRANE:
                    final IKit clandestineKit = Core.getCore().getKitManager().getKit("Clandestine");

                    if (Core.getCore().getKitManager().isCooldown(player, clandestineKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    clandestineKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, clandestineKit);

                    new BukkitRunnable() {
                        double timer = clandestineKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, clandestineKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Clandestine kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                //rank kits
                case GREEN_DYE:
                    final IKit vipKit = Core.getCore().getKitManager().getKit("Vip");

                    if (Core.getCore().getKitManager().isCooldown(player, vipKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    vipKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, vipKit);

                    new BukkitRunnable() {
                        double timer = vipKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, vipKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the VIP kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case LIGHT_BLUE_DYE:
                    final IKit mvpKit = Core.getCore().getKitManager().getKit("Mvp");

                    if (Core.getCore().getKitManager().isCooldown(player, mvpKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    mvpKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, mvpKit);

                    new BukkitRunnable() {
                        double timer = mvpKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, mvpKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the MVP kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case BLUE_DYE:
                    final IKit eliteKit = Core.getCore().getKitManager().getKit("Elite");

                    if (Core.getCore().getKitManager().isCooldown(player, eliteKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    eliteKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, eliteKit);

                    new BukkitRunnable() {
                        double timer = eliteKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, eliteKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Elite kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                case RED_DYE:
                    final IKit immortalKit = Core.getCore().getKitManager().getKit("Immortal");

                    if (Core.getCore().getKitManager().isCooldown(player, immortalKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    immortalKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, immortalKit);

                    new BukkitRunnable() {
                        double timer = immortalKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, immortalKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Immortal kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
                    //special
                case RED_TULIP:
                    final IKit thanksKit = Core.getCore().getKitManager().getKit("Thanks");

                    if (Core.getCore().getKitManager().isCooldown(player, thanksKit)) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(ChatColor.AQUA + "You can't purchase that kit yet.");
                        return;
                    }

                    thanksKit.execute(player);
                    Core.getCore().getKitManager().addCooldown(player, thanksKit);

                    new BukkitRunnable() {
                        double timer = thanksKit.getCooldodwn();
                        @Override
                        public void run() {
                            timer--;
                            if (timer <= 0) {
                                this.cancel();
                                Core.getCore().getKitManager().removeCooldown(player, thanksKit);
                                player.sendMessage(ChatColor.AQUA + "You can purchase the Gratitude kit again.");
                            }
                        }
                    }.runTaskTimerAsynchronously(Core.getCore(), 0L, 20L);
                    return;
            }*/
        }
    }
}
