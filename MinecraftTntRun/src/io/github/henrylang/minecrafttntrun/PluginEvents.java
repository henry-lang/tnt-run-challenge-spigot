package io.github.henrylang.minecrafttntrun;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PluginEvents implements Listener {
	Plugin plugin;
	
	public PluginEvents(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(Plugin.enabled && e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
			if(e.getFrom().getBlockX() == e.getTo().getBlockX() &&
			   e.getFrom().getBlockZ() == e.getTo().getBlockZ()) return;
			
			Location pos = e.getPlayer().getLocation().clone();
			pos.setY(0);
			
			Location soundPos = e.getPlayer().getLocation().clone();
			
			if(!Plugin.cols.contains(pos)) {
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_TNT_PRIMED, 0.5F, 1.5F);
				plugin.runTaskLater(20, new Runnable() {
					@Override
					public void run() {
						for(int y = 0; y < 255; y++) {
							Location current = pos.clone();
							current.setY(y);
							boolean shouldIgnore = false;
							Material[] ignore = {
								Material.AIR,
								Material.CAVE_AIR,
								Material.WATER,
								Material.LAVA,
								Material.OBSIDIAN,
								Material.NETHER_PORTAL,
								Material.END_PORTAL,
								Material.END_PORTAL_FRAME
							};
							for(Material mat : ignore) {
								if(pos.getWorld().getBlockAt(current).getType() == mat) {
									shouldIgnore = true;
								}
							}
							
							if(!shouldIgnore) {
								pos.getWorld().getBlockAt(current).setType(Material.AIR);
							}
						}
						Plugin.cols.remove(pos);
						e.getPlayer().getWorld().spawnParticle(Particle.LAVA, soundPos, 20);
					}
				});
				
				Plugin.cols.add(pos);
			}
		}
	}
}
