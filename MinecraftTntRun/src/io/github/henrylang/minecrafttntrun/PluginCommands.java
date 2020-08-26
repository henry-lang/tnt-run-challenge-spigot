package io.github.henrylang.minecrafttntrun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class PluginCommands implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tntrunstart")) {
			Plugin.enabled = true;
			sender.sendMessage(ChatColor.GREEN + "TNT Run Started.");
		} else if(cmd.getName().equalsIgnoreCase("tntrunstop")) {
			Plugin.enabled = false;
			sender.sendMessage(ChatColor.RED + "TNT Run Stopped.");
		}
		
		return true;
	}
}
