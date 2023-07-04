package dev.fabby.com.utils;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public final class ItemUtil {

    private Material material;
    private String name;
    private List<String> description;

    public ItemUtil(Material material, String name, String... desc) {
        this.material = material;
        this.name = name;

        this.description = Lists.newArrayList();
        description.addAll(Arrays.asList(desc));
    }

    public ItemStack getProduct() {
        return getProduct(1);
    }

    public ItemStack getProductWithGlow() {
        ItemStack result = getProduct(1);
        addGlow(result);
        return result;
    }

    public ItemStack getProduct(int amount) {
        ItemStack item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(StringUtil.color(name));

        if (description != null) {
            List<String> lore = Lists.newArrayList();
            for (String s : description) {
                lore.add(StringUtil.color(s));
            }
            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        return item;
    }

    public void addGlow(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.addEnchant(Enchantment.LURE, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);
    }
}
