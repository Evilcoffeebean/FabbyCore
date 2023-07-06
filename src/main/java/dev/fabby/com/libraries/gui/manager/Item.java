package dev.fabby.com.libraries.gui.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {


    /*public static ItemStack createItem(Material material, Component name, ArrayList<Component> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.valueOf(name));
        ArrayList<Component> tempLore = new ArrayList<>();
        if (lore != null) {
            for (Component component : lore) {
                if (!component.hasDecoration(TextDecoration.ITALIC)) {
                    component = component.decoration(TextDecoration.ITALIC, false);
                }
                tempLore.add(component);
            }
            meta.setLore(tempLore);
            tempLore.forEach(meta::setLore);
        } else {
            meta.lore(lore);
        }
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }*/

    public static ItemStack createItem(Material material, String name, ArrayList<String> lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(meta);
        return itemStack;

    }

    /*public static ItemStack createCustomItem(ItemStack material, Component name, ArrayList<Component> lore) {
        ItemMeta meta = material.getItemMeta();
        meta.displayName(name.hasDecoration(TextDecoration.ITALIC) ? name : C.Companion.noItalics().append(name));
        ArrayList<Component> tempLore = new ArrayList<>();
        if (lore != null) {
            for (Component component : lore) {
                if (!component.hasDecoration(TextDecoration.ITALIC)) {
                    component = component.decoration(TextDecoration.ITALIC, false);
                }
                tempLore.add(component);
            }
            meta.lore(tempLore);
        } else {
            meta.lore(lore);
        }
        meta.addItemFlags(ItemFlag.values());
        material.setItemMeta(meta);
        return material;
    }*/

    public static ItemStack createCustomItem(ItemStack material, String name, ArrayList<String> lore) {
        ItemMeta meta = material.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        material.setItemMeta(meta);
        return material;
    }

    /*public static ItemStack createEnchantedItem(Material material, Component name, ArrayList<Component> lore, HashMap<Enchantment, Integer> enchantments) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(C.Companion.noItalics().append(name));
        meta.lore(lore);
        meta.addItemFlags(ItemFlag.values());
        for (Enchantment en : enchantments.keySet()) {
            meta.addEnchant(en, enchantments.get(en), true);
        }
        item.setItemMeta(meta);
        return item;
    }*/

    public static ItemStack createEnchantedItem(Material material, String name, ArrayList<String> lore, HashMap<Enchantment, Integer> enchantments) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        for (Enchantment en : enchantments.keySet()) {
            meta.addEnchant(en, enchantments.get(en), true);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack glass(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.valueOf(Component.empty()));
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    /*public static ItemStack createPageArrowPrevious() {
        return createCustomItem(TarvosCore.instance.getResourcePackManager().getItemStack("icon_left_blue"), C.noItalics().append(Component.text("Previous Page", NamedTextColor.WHITE)), null);
    }

    public static ItemStack createPageArrowNext() {
        return createCustomItem(TarvosCore.instance.getResourcePackManager().getItemStack("icon_right_blue"), C.noItalics().append(Component.text("Next Page", NamedTextColor.WHITE)), null);
    }

    public static ItemStack createBackItem() {
        ItemStack item = new ItemStack(TarvosCore.instance.getResourcePackManager().getItemStack("icon_back_orange"));
        ItemMeta meta = item.getItemMeta();
        meta.displayName(C.noItalics().append(Component.text("Return", TextColor.fromHexString(C.BACK_COLOUR))));
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }*/

    /*@Deprecated
    public static ItemStack createRealm(Material material, String name, NamedTextColor colour, String serverName, ArrayList<Component> lore) {
        ServerInfo info = TarvosCore.instance.getServerManager().getServerInfo(serverName);
        ServerStatus status = null;
        if (info == null) {
            status = ServerStatus.OFFLINE;
        } else {
            status = info.getStatus();
        }
        ItemStack item = new ItemStack(material);
        if (lore == null) lore = new ArrayList<>();
        lore.add(Component.empty());
        int players = 0;
        if (status.equals(ServerStatus.ONLINE)) {
            players = info.getOnlinePlayers();
        }
        lore.add(C.noItalics().append(Component.text("Players: ", NamedTextColor.WHITE).append(Component.text(players, NamedTextColor.GREEN))));
        if (serverName.equalsIgnoreCase(TarvosCore.instance.getRealmsManager().getRealmType().getRawRealmName())) {
            lore.add(C.noItalics().append(Component.text(" ", NamedTextColor.WHITE)
                    .append(Emoji.CONFIRM.getFormat())
                    .append(Component.text(" Currently Connected!", NamedTextColor.GREEN))));
        } else {
            lore.add(status.equals(ServerStatus.ONLINE) ? C.noItalics().append(Component.text(C.RIGHT_ARROW + "Click to connect", NamedTextColor.GREEN)) :
                    (status.equals(ServerStatus.OFFLINE) ? C.noItalics().append(C.CANCEL
                            .append(status.getStatusName())) : C.noItalics().append(status.getStatusName())));
        }
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        meta.displayName(C.noItalics().append(Component.text(name, colour)));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createRealm(RealmType realmType, ArrayList<Component> lore) {
        ServerInfo info = TarvosCore.instance.getServerManager().getServerInfo(realmType.getRedisName());
        ServerStatus status = null;
        if (info == null) {
            status = ServerStatus.OFFLINE;
        } else {
            status = info.getStatus();
        }
        ItemStack item = new ItemStack(realmType.getIcon());
        if (lore == null) lore = new ArrayList<>();
        lore.add(Component.empty());
        int players = 0;
        if (status.equals(ServerStatus.ONLINE)) {
            players = info.getOnlinePlayers();
        }
        lore.add(C.noItalics().append(Component.text("Players: ", NamedTextColor.WHITE).append(Component.text(players, NamedTextColor.GREEN))));
        if (realmType.getRedisName().equalsIgnoreCase(TarvosCore.instance.getRealmsManager().getRealmType().getRedisName())) {
            lore.add(C.noItalics().append(Component.text(" ", NamedTextColor.WHITE)
                    .append(Emoji.CONFIRM.getFormat())
                    .append(Component.text(" Currently Connected!", NamedTextColor.GREEN))));
        } else {
            lore.add(status.equals(ServerStatus.ONLINE) ? C.noItalics().append(Component.text(C.RIGHT_ARROW + "Click to connect", NamedTextColor.GREEN)) :
                    (status.equals(ServerStatus.OFFLINE) ? C.noItalics().append(C.CANCEL
                            .append(status.getStatusName())) : C.noItalics().append(status.getStatusName())));
        }
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        meta.displayName(C.noItalics().append(realmType.getRealmName()));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createPrivateArcadeRealm(final String name, final NamedTextColor colour, final String serverName, ArrayList<Component> lore, final UUID ownerID) {
        ServerInfo info = TarvosCore.instance.getServerManager().getServerInfo(serverName);
        ServerStatus status = null;
        if (info == null) {
            status = ServerStatus.OFFLINE;
        } else {
            status = info.getStatus();
        }
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        if (lore == null) lore = new ArrayList<>();
        lore.add(Component.empty());
        int players = 0;
        if (status.equals(ServerStatus.ONLINE)) {
            players = info.getOnlinePlayers();
        }
        lore.add(Component.text("Players: ", NamedTextColor.WHITE).append(Component.text(players, NamedTextColor.GREEN)));
        if (serverName.equalsIgnoreCase(TarvosCore.instance.getRealmsManager().getRealmType().getRawRealmName())) {
            lore.add(C.noItalics().append(Component.text(" ", NamedTextColor.WHITE)
                    .append(Emoji.CONFIRM.getFormat())
                    .append(Component.text(" Currently Connected!", NamedTextColor.GREEN))));
        } else {
            lore.add(status.equals(ServerStatus.ONLINE) ? C.noItalics().append(Component.text(C.RIGHT_ARROW + "Click to connect", NamedTextColor.GREEN)) :
                    (status.equals(ServerStatus.OFFLINE) ? C.noItalics().append(C.CANCEL
                            .append(status.getStatusName())) : C.noItalics().append(status.getStatusName())));
        }
        SkullMeta headMeta = (SkullMeta) item.getItemMeta();
        headMeta.setOwningPlayer(Bukkit.getOfflinePlayer(ownerID));
        headMeta.displayName(C.noItalics().append(Component.text(name, colour)));
        headMeta.lore(lore);
        headMeta.addItemFlags(ItemFlag.values());
        item.setItemMeta(headMeta);
        return item;
    }

    public static ItemStack createPlanetRealm(final String name, final NamedTextColor colour, final String serverName, ArrayList<Component> lore, final String base64) {
        ServerInfo info = TarvosCore.instance.getServerManager().getServerInfo(serverName);
        ServerStatus status = null;
        if (info == null) {
            status = ServerStatus.OFFLINE;
        } else {
            status = info.getStatus();
        }
        ItemStack skull = SkullCreator.itemFromBase64(base64);
        ItemMeta meta = skull.getItemMeta();

        if (lore == null) lore = new ArrayList<>();
        lore.add(Component.empty());
        int players = 0;
        if (status.equals(ServerStatus.ONLINE)) {
            players = info.getOnlinePlayers();
        }
        lore.add(C.noItalics().append(Component.text("Players: ", NamedTextColor.WHITE).append(Component.text(players, NamedTextColor.GREEN))));
        if (serverName.equalsIgnoreCase(TarvosCore.instance.getRealmsManager().getRealmType().getRawRealmName())) {
            lore.add(C.noItalics().append(Component.text(" ", NamedTextColor.WHITE)
                    .append(Emoji.CONFIRM.getFormat())
                    .append(Component.text(" Currently Connected!", NamedTextColor.GREEN))));
        } else {
            lore.add(status.equals(ServerStatus.ONLINE) ? C.noItalics().append(Component.text(C.RIGHT_ARROW + "Click to connect", NamedTextColor.GREEN)) :
                    (status.equals(ServerStatus.OFFLINE) ? C.noItalics().append(C.CANCEL
                            .append(status.getStatusName())) : C.noItalics().append(status.getStatusName())));
        }
        meta.displayName(C.noItalics().append(Component.text(name, colour)));
        meta.addItemFlags(ItemFlag.values());
        meta.lore(lore);
        skull.setItemMeta(meta);
        return skull;
    }

    // Universe

    public static ItemStack createBuyableItem(final Product product, final TarvosPlayer tarvosPlayer) {
        ItemStack item = product.getItemStack();
        ItemMeta meta = item.getItemMeta();
        Component name = product.getName();
        meta.displayName(name.hasDecoration(TextDecoration.ITALIC) ? name : C.noItalics().append(name));
        ArrayList<Component> tempLore = new ArrayList<>();
        if (product.hasDescription()) {
            for (Component component : product.getDescription()) {
                if (!component.hasDecoration(TextDecoration.ITALIC)) {
                    component = component.decoration(TextDecoration.ITALIC, false);
                }
                tempLore.add(component);
            }
        }
        tempLore.add(Component.empty());
        boolean canAfford = tarvosPlayer.hasBalance(product.getPrice());
        tempLore.add(C.noItalics().append(Component.text("Price: ", NamedTextColor.WHITE)
                .append(C.balance(product.getPrice()))));
        if (product.inStock()) {
            if (canAfford) {
                tempLore.add(C.noItalics().append(Component.text(C.RIGHT_ARROW + "Click to purchase", NamedTextColor.GREEN)
                ));
            } else {
                tempLore.add(C.noItalics().append(C.CANCEL
                        .append(Component.text("You cannot afford this!", NamedTextColor.RED))));
            }
        } else {
            tempLore.add(C.noItalics().append(C.CANCEL
                    .append(Component.text("Out of Stock", NamedTextColor.RED))));
        }
        meta.lore(tempLore);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }*/
}