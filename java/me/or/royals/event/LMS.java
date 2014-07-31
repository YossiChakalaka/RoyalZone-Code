package me.or.royals.event;

import java.util.ArrayList;

import me.or.royals.Main;
import me.or.royals.Teams;
import me.or.royals.WarpsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class LMS implements Listener {
	public Main plugin;
	public ArrayList<String> players = new ArrayList<String>();

	public ArrayList<String> inv = new ArrayList<String>();
	public GameStatus status = GameStatus.WAITING;
	public LMS(Main pluginl){
		this.plugin = pluginl;
	}
	public String prefix = ChatColor.BLUE+"§lLMS>";
public boolean started = false;
	
	
	public boolean hasStarted(){
		return started;
	}
	@EventHandler
	public void friendly (EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player k = (Player) e.getDamager();
			Player p = (Player) e.getEntity();
			Teams Team = Teams.getInstance();
			
			if (players.contains(p.getName()) && players.contains(k.getName())){

			if(Team.getTeam(p).equals(Team.getTeam(k))){

				e.setCancelled(false);
			}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void quit (PlayerQuitEvent e){
		if (players.contains(e.getPlayer().getName())){
			e.setQuitMessage(prefix + " The Player " + e.getPlayer().getName() + " has Left the Game");
			players.remove(e.getPlayer().getName());
			if (players.size() == 1){
				status = GameStatus.WAITING;

				Player k = Bukkit.getPlayer(players.get(0));
				Bukkit.broadcastMessage(prefix + " the Player " + k.getName() + " has won the LMS, and earned 20 coins");
				started = false;
				status = GameStatus.WAITING;
				plugin.AddCoin(k, 20);
				k.teleport(getSpawn());
				k.getInventory().clear();
				k.getInventory().setArmorContents(null);
			}
			
		}
	}
	@EventHandler
	public void damage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
		Player p = (Player) e.getEntity();
			if (inv.contains(p.getName())){
			e.setCancelled(true);
		}else{

			e.setCancelled(false);
		}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void death(PlayerDeathEvent e){
		if (e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();


				if(players.contains(p.getName())){
					if(getStatus() == GameStatus.INGAME){

				players.remove(p.getName());
				e.setDeathMessage(prefix + " The Player " + p.getName() + " Has Died " + players.size() + " Players Left.");
				p.teleport(getSpawn());
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				if (players.size() == 1){
					status = GameStatus.WAITING;

					Player k = Bukkit.getPlayer(players.get(0));
					Bukkit.broadcastMessage(prefix + " the Player " + k.getName() + " has won the LMS, and earned 20 coins");
					started = false;
					plugin.AddCoin(k, 20);
					k.teleport(getSpawn());
					k.getInventory().clear();
					k.getInventory().setArmorContents(null);
				}
					}
				}
			
		}
	}
	
	public GameStatus getStatus(){
		return status;
	}
	public Location getSpawn(){
		WarpsManager settings = WarpsManager.getInstance();
		return new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z"));
	}
	@SuppressWarnings("deprecation")

	public void StartLMS(){
		ItemStack stick= new ItemStack(Material.STICK);
		stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);

		for(final String s : players){
		
			Player t = Bukkit.getPlayer(s);
		t.getInventory().clear();
		t.getInventory().setArmorContents(null);
			t.teleport(new Location(Bukkit.getWorld("world"), -281, 200, 1268));

			t.getInventory().addItem(stick);
			started = true;
		}
	}
}
