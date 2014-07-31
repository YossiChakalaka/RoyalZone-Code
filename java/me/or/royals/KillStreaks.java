package me.or.royals;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class KillStreaks implements Listener{
	public Main plugin;
	public KillStreaks(Main plugin){
		this.plugin = plugin;
	}

	Map<String, Integer> killstreak = new HashMap<String, Integer>();

	@EventHandler
	public void playerdeath (PlayerDeathEvent ev){
		Player p = ev.getEntity();
		if(p.getKiller() instanceof Player){
			Player killer = p.getKiller();
			p.sendMessage("Your killstreak has been destroyed by "+ChatColor.RED+killer.getName());
			addtokillstreak(killer);
			killstreak.put(p.getName(), 0);
		}
		return;
		}

	public void addtokillstreak(Player killer) {
		String name = killer.getName();
		if(killstreak.containsKey(name)){
			//already has a killstreak then add one to it
			int kills = killstreak.get(name);
			kills++;
			killstreak.put(name, kills);
			
			killer.sendMessage("Your on a " + ChatColor.RED+ Integer.toString(kills)+ ChatColor.WHITE + " Killstreak.");

			killer.sendMessage(killer.getName() +  " is on a  " + ChatColor.RED+ Integer.toString(kills)+ ChatColor.WHITE + " Killstreak.");

			
			// run another method to run console commands
			
		
			runcommands(name,killstreak.get(name));
		if (kills == 50){
			Bukkit.getPluginManager().callEvent(new AchivementGetEvent(killer, Achivments.IMMORTAL));

		}
			if (kills == 20){
			Bukkit.getPluginManager().callEvent(new AchivementGetEvent(killer, Achivments.UNBELIEVEABLE));

		}
			if (kills == 10){
			Bukkit.getPluginManager().callEvent(new AchivementGetEvent(killer, Achivments.UNSTOPABLE));

		}
			if (kills == 5){
			Bukkit.getPluginManager().callEvent(new AchivementGetEvent(killer, Achivments.RUSHER));
		}
		}else{
			//first kill of a new streak
			killstreak.put(name, 1);
			killer.sendMessage("Your on a " + ChatColor.RED+ "1" + ChatColor.WHITE + " Killstreak.");
			// run another method to run console commands
			runcommands(name,1);
		}
	}

	public void runcommands(String name, int kills){
		String numofkills = Integer.toString(kills);
		boolean rancommands = false;
		int commandnumber = 0;
		while (rancommands == false){
			commandnumber++;
			if(plugin.getConfig().getString(numofkills+"."+commandnumber) != null){
				//there is a valid command to be run
				String command = plugin.getConfig().getString(numofkills+"."+commandnumber).replaceAll("%name%", name);
				String command1 = command.replaceAll("%killstreak%", numofkills);
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command1);
			}
			if(plugin.getConfig().getString(numofkills+"."+commandnumber) == null){
				return;
			}
		}
	}
}