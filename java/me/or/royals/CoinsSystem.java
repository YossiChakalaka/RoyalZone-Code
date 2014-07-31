package me.or.royals;



import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class CoinsSystem implements Listener {
	public Main plugin;

	
	AchManager am = AchManager.getInstance();
	TeamList tl = TeamList.getInstance();
	Teams t = Teams.getInstance();
	TeamManager tm = TeamManager.getInstance();
	public CoinsSystem(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void deathevent(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();

		if (killer == null)
			return;

		String pk = killer.getUniqueId().toString();

		int currentCoins = plugin.getConfig().getInt(pk + ".coins");
		plugin.getConfig().set(pk + ".coins", currentCoins + 1);
		plugin.getConfig().set(pk + ".kills", plugin.getConfig().getInt(pk + ".kills") + 1);
		plugin.getConfig().set(e.getEntity().getUniqueId().toString() + ".deaths", plugin.getConfig().getInt(e.getEntity().getUniqueId().toString() + ".deaths") + 1);


		plugin.saveConfig();
		if (t.isinTeam(killer)){
			t.AddTeamKills(t.getTeam(killer), 1);
			t.AddTeamCoin(t.getTeam(killer), 1);
			
		}
		if (t.isinTeam(e.getEntity())){
			t.AddTeamDeath(t.getTeam(e.getEntity()), 1);
		}
	}

	@EventHandler
	public void Join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String s = p.getUniqueId().toString();
		
		List<String> l = am.getData().getStringList(p.getUniqueId().toString() + ".Achivments");
		l.add("example");
		am.getData().set(p.getUniqueId().toString()+".Achivments", l);
		am.saveData();
		am.getData().set(p.getUniqueId().toString() + ".Achivments", l);
		am.saveData();
		tl.getData().set(s + ".Team", tl.getData().getString(s + ".Team"));
		tl.saveData();
	plugin.getConfig().set(p.getUniqueId().toString() + ".coins", plugin.getConfig().getInt(p.getUniqueId().toString() + ".coins"));
		plugin.getConfig().set(p.getUniqueId().toString()+".deaths", plugin.getConfig().getInt(p.getUniqueId().toString() + ".deaths"));
		plugin.getConfig().set(p.getUniqueId().toString()+".kills", plugin.getConfig().getInt(p.getUniqueId().toString()+".kills"));
		plugin.saveConfig();
	}
}
