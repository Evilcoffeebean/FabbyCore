package dev.fabby.com.commands.commandargs

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

import java.util.ArrayList

class NoArgTabCompleter : TabCompleter {

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        s: String,
        args: Array<String>
    ): List<String> {

        val list = ArrayList<String>()
        list.add("")
        return list
    }
}