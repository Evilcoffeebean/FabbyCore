package dev.fabby.com.utils;

import dev.fabby.com.Core;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class Task {

    public BukkitTask runAsync(Runnable runnable) {
        return Bukkit.getServer().getScheduler().runTaskAsynchronously(Core.getCore(), runnable);
    }

    public BukkitTask runSync(Runnable runnable) {
        return Bukkit.getServer().getScheduler().runTask(Core.getCore(), runnable);
    }

    public BukkitTask runSyncTimer(Runnable runnable, long delay, long timer) {
        return Bukkit.getServer().getScheduler().runTaskTimer(Core.getCore(), runnable, delay, timer);
    }

    public BukkitTask runAsyncTimer(Runnable runnable, long delay, long timer) {
        return Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Core.getCore(), runnable, delay, timer);
    }

    public BukkitTask runSyncLater(Runnable runnable, long delay) {
        return Bukkit.getServer().getScheduler().runTaskLater(Core.getCore(), runnable, delay);
    }

    public BukkitTask runAsyncLater(Runnable runnable, long delay) {
        return Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Core.getCore(), runnable, delay);
    }
}
