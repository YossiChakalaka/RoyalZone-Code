package me.or.royals.event;

import java.util.ArrayList;

import me.or.royals.Main;
import me.or.royals.Teams;
import me.or.royals.WarpsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OITC implements Listener {

	public Main plugin;
	public ArrayList<String> players = new ArrayList<String>();
	boolean started;
	ArrayList<String> inv = new ArrayList<String>();
	public GameStatus status = GameStatus.WAITING;
	public String prefix = ChatColor.GOLD+"§lOITC>";
	public OITC(Main plugin){
		this.plugin = plugin;
	}
	public void clearArmor(Player p){
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);	
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
	@EventHandler
	public void join(PlayerQuitEvent e){
		if (players.contains(e.getPlayer().getName())){
			players.remove(e.getPlayer().getName());
			if(getStatus() == GameStatus.INGAME){
			e.setQuitMessage(prefix +"The Player " + e.getPlayer().getName() + " has Left the Game, " + players.size() + " Players Left");
			}
			}
	}
	public ItemStack specialItemStack(Material m, String name, ChatColor c, String lore, ChatColor lorec){
		
		ItemStack s = new ItemStack(m);
		ItemMeta ma = s.getItemMeta();
		ma.setDisplayName(c + name);
		ArrayList<String> lorea = new ArrayList<String>();
		lorea.add(lorec + lore);
		ma.setLore(lorea);
		s.setItemMeta(ma);
		return s;
	}
	
	@SuppressWarnings("deprecation")
	public void startOITC(){

	
		inv();
		for( String s : players){
		 Player t = Bukkit.getPlayer(s);
		 t.getInventory().clear();
			t.teleport((new Location(Bukkit.getWorld("world"), 478, 18, -354)));

			clearArmor(t);
			t.setHealth(20);
			t.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
		ItemStack sa = specialItemStack(Material.BOW, "§lAmighty Bow", ChatColor.GREEN, "Oitc Bow", ChatColor.AQUA);
		t.getInventory().addItem(sa);
		
		t.getInventory().addItem(new ItemStack(Material.ARROW));
		}
		
	}


	public GameStatus getStatus(){
		return status;
	}
	@EventHandler
	public void creature(CreatureSpawnEvent e){
	if(e.getEntity().getType() == EntityType.ZOMBIE || e.getEntity().getType() == EntityType.VILLAGER){
		e.setCancelled(false);
	}else{
		e.setCancelled(true);
	}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void arrow(EntityDamageByEntityEvent e){

		if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow){

			
			Arrow v = (Arrow) e.getDamager();
			Player k = (Player) v.getShooter();
			Player a = (Player) e.getEntity();
			
		
			if(players.contains(k.getName()) && players.contains(a.getName()) && status == GameStatus.INGAME ){

				a.setHealth(20);
				k.getWorld().playSound(a.getLocation(), Sound.NOTE_PLING, 20, 20);
				players.remove(a.getName());
				Bukkit.broadcastMessage(prefix + " The Player " + a.getName() + " has Died, " + players.size() + " Players Left" );
				a.teleport(getSpawn());
				a.getInventory().clear();
				a.getInventory().setArmorContents(null);
				k.getInventory().addItem(new ItemStack(Material.ARROW));
				if(players.size() == 1){

					a.setHealth(20);
					k.setHealth(20);
					k.teleport(getSpawn());
					a.teleport(getSpawn());
					k.getWorld().playSound(a.getLocation(), Sound.NOTE_PLING, 20, 20);
					players.remove(a.getName());
					players.clear();
					a.teleport(getSpawn());
					Bukkit.broadcastMessage(prefix + " The Player " + k.getName() + "  has Won the game, and earned 50 Coins");
					status = GameStatus.WAITING;
					plugin.AddCoin(k, 50);
					k.teleport(getSpawn());
					a.teleport(getSpawn());
					k.getInventory().clear();
					k.getInventory().setArmorContents(null);
					a.getInventory().clear();
					a.getInventory().setArmorContents(null);

				}
			
				
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		
		if (e.getEntity() instanceof Player){
			Player p = e.getEntity();
			if(getStatus() == GameStatus.INGAME){
			if (players.contains(p.getName())){
				p.setHealth(20);
				p.getInventory().clear();
				clearArmor(p);
				p.teleport(getSpawn());
				players.remove(p.getName());
				
				Bukkit.broadcastMessage(prefix +"The Player " + p.getName() + " Has Died " + players.size() + " Players Left");
				e.getEntity().getKiller().getInventory().addItem(new ItemStack(Material.ARROW));

				if(players.size() == 1){
					Player t = Bukkit.getPlayer(players.get(0));
					p.setHealth(20);
					p.getInventory().clear();
					clearArmor(p);
					p.teleport(getSpawn());
					players.remove(p.getName());
					p.teleport(getSpawn());
					t.setHealth(20);
					t.getInventory().clear();
					clearArmor(t);
					t.teleport(getSpawn());
					Bukkit.broadcastMessage(prefix + " The Player " + t.getName()  + " Has Won the OITC Event, and earned 50 Coins" );
					status = GameStatus.WAITING;

					plugin.AddCoin(t, 50);
					status = GameStatus.WAITING;
				}
			}
				
			}
		}
	}
	public Location getSpawn(){
		WarpsManager settings = WarpsManager.getInstance();
		return new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z"));
	}

	@SuppressWarnings("deprecation")
	public void inv(){
		status = GameStatus.INV;

		Bukkit.broadcastMessage(prefix +" 5 Seconds of Invicibility");

		Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {

				status = GameStatus.INGAME;
			Bukkit.broadcastMessage(prefix + "5 Seconds of Invicibility has Gone, FIGHT !");

			
			}
		}, 100L);
	}
}