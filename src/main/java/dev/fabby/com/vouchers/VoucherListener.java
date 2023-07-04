package dev.fabby.com.vouchers;

import dev.fabby.com.commands.*;
import dev.fabby.com.commands.staff.*;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class VoucherListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onRankVoucherClaim(final PlayerInteractEvent e) {
        RankVoucher rankVoucher = RankVoucherCommand.rankVoucher;
        if (rankVoucher == null) return;

        if (rankVoucher.hasVoucher(e.getPlayer())) {
            if (e.getHand() == EquipmentSlot.HAND) {
                rankVoucher.execute(e.getPlayer());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Rank claimed. Updating your rank now.");
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onEffectVoucherClaim(final PlayerInteractEvent e) {
        EffectVoucher effectVoucher = EffectVoucherCommand.effectVoucher;
        if (effectVoucher == null) return;

        if (effectVoucher.hasVoucher(e.getPlayer())) {
            if (e.getHand() == EquipmentSlot.HAND) {
                effectVoucher.execute(e.getPlayer());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Effect Voucher claimed. Use /effects to see all available effects.");
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onWithdrawVoucherClaim(final PlayerInteractEvent e) {
        WithdrawVoucher voucher = WithdrawCommand.voucher;
        if (voucher == null) return;

        if (voucher.hasVoucher(e.getPlayer())) {
            if (e.getHand() == EquipmentSlot.HAND) {
                voucher.execute(e.getPlayer());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Withdrawal Voucher claimed. Depositing money into your account.");
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onMoneyVoucherClaim(final PlayerInteractEvent e) {
        MoneyVoucher voucher = MoneyVoucherCommand.voucher;
        if (voucher == null) return;

        if (voucher.hasVoucher(e.getPlayer())) {
            if (e.getHand() == EquipmentSlot.HAND) {
                voucher.execute(e.getPlayer());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Money Voucher claimed. Depositing money into your account.");
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onKitVoucherClaim(final PlayerInteractEvent e) {
        KitVoucher voucher = KitVoucherCommand.kitVoucher;
        if (voucher == null) return;

        if (voucher.hasVoucher(e.getPlayer())) {
            if (e.getHand() == EquipmentSlot.HAND) {
                voucher.execute(e.getPlayer());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Kit Voucher claimed. Use /kit to equip.");
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onTagVoucherClaim(final PlayerInteractEvent e) {
        TagVoucher voucher = TagVoucherCommand.voucher;
        if (voucher == null) return;

        if (voucher.hasVoucher(e.getPlayer())) {
            if (e.getHand() == EquipmentSlot.HAND) {
                voucher.execute(e.getPlayer());
                return;
            }
        }
    }
}
