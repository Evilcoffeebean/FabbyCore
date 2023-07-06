package dev.fabby.com.utils

import dev.fabby.com.Core
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

class Task {

    fun runAsync(runnable: Runnable): BukkitTask {
        return Bukkit.getServer().scheduler.runTaskAsynchronously(Core.getCore(), runnable)
    }

    fun runSync(runnable: Runnable): BukkitTask {
        return Bukkit.getServer().scheduler.runTask(Core.getCore(), runnable)
    }

    fun runSyncTimer(runnable: Runnable, delay: Long, timer: Long): BukkitTask {
        return Bukkit.getServer().scheduler.runTaskTimer(Core.getCore(), runnable, delay, timer)
    }

    fun runAsyncTimer(runnable: Runnable, delay: Long, timer: Long): BukkitTask {
        return Bukkit.getServer().scheduler.runTaskTimerAsynchronously(Core.getCore(), runnable, delay, timer)
    }

    fun runSyncLater(runnable: Runnable, delay: Long): BukkitTask {
        return Bukkit.getServer().scheduler.runTaskLater(Core.getCore(), runnable, delay)
    }

    fun runAsyncLater(runnable: Runnable, delay: Long): BukkitTask {
        return Bukkit.getServer().scheduler.runTaskLaterAsynchronously(Core.getCore(), runnable, delay)
    }
}