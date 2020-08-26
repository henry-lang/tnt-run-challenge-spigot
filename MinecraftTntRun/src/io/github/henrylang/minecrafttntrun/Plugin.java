package io.github.henrylang.minecrafttntrun;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	public static boolean enabled = false;
	public static ArrayList<Location> cols = new ArrayList<Location>();
	
	@Override
	public void onEnable() {
		PluginCommands cmds = new PluginCommands();
		getCommand("tntrunstart").setExecutor(cmds);
		getCommand("tntrunstop").setExecutor(cmds);
		
		getServer().getPluginManager().registerEvents(new PluginEvents(this), this);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "TNT Run Plugin Loaded!");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "TNT Run Plugin Disabled!");
	}
	
	public void runTaskLater(long ticks, Runnable runnable) {
		getServer().getScheduler().runTaskLater(this, runnable, ticks);
	}
}
