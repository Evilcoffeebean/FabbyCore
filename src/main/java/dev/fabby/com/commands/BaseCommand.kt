package dev.fabby.com.commands

import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

abstract class BaseCommand {
    abstract fun name(): String
    abstract fun description(): String
    abstract fun aliases(): Array<String>
    abstract fun neededPermission(): String
    abstract fun minArgs(): Int
    abstract fun usage(): String
    abstract fun consoleAllowed(): Boolean
    abstract fun execute(p: Player, args: Array<String>)
    abstract fun executeAsConsole(sender: CommandSender, args: Array<String>)
    abstract fun customTabCompleter(): Boolean
    abstract fun tabCompleter(): TabCompleter?
}
