package dev.fabby.com.commands.staff.moderation

import dev.fabby.com.commands.BaseCommand
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class InvSeeCommand : BaseCommand() {
    override fun name(): String {
        return "invsee"
    }

    override fun description(): String {
        return "Opens a player's inventory."
    }

    override fun aliases(): Array<String> {
        return arrayOf()
    }

    override fun neededPermission(): String {
        return "fabby.moderation.invsee"
    }

    override fun minArgs(): Int {
        return 1
    }

    override fun usage(): String {
        return "/invsee <player>"
    }

    override fun consoleAllowed(): Boolean {
        return false
    }

    override fun execute(p: Player, args: Array<String>) {
        val target = p.server.getPlayer(args[0])
        if (target == null) {
            p.sendMessage("§cThat player is not online.")
            return
        }



    }

    override fun executeAsConsole(sender: CommandSender, args: Array<String>) {
        TODO("Not yet implemented")
    }

    override fun customTabCompleter(): Boolean {
        TODO("Not yet implemented")
    }

    override fun tabCompleter(): TabCompleter? {
        TODO("Not yet implemented")
    }
}