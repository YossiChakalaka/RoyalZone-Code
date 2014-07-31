package me.or.royals.event;

import java.util.ArrayList;
import java.util.Random;

import me.or.royals.Main;
import me.or.royals.WarpsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Parkour implements Listener {

	public Main plugin;
	public Parkour(Main plugin){
		this.plugin = plugin;
	}
	public String prefix = (ChatColor.RED+"§lParkour> ");
	public GameStatus status = GameStatus.WAITING;
	ArrayList<String> players = new ArrayList<String>();
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void walk(final PlayerMoveEvent e){
		if (plugin.isinWater(e.getPlayer()) && e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.IRON_BLOCK && getStatus() == GameStatus.INGAME){
		
			e.getPlayer().getInventory().clear();
		plugin.AddCoin(e.getPlayer(), 75);
		setStatus(GameStatus.WAITING);
		 
		for(int x = 0 ; x < 10 ; x ++){
			  Firework f = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
	           
	           FireworkMeta fm = f.getFireworkMeta();
	           fm.addEffect(FireworkEffect.builder()
	                           .flicker(false)
	                           .trail(true)
	                           .with(Type.STAR)
	                           .withColor(Color.fromBGR(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)))
	                           .withFade(Color.fromBGR(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)))
	                           .build());
	           fm.setPower(3);
	           f.setFireworkMeta(fm);

		}
		Bukkit.broadcastMessage(prefix + "The player " + e.getPlayer().getName() + " Has won the Event, and earned 75 Coins !");

		Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				e.getPlayer().teleport(getSpawn());
				for(String s : players){
					Player t = Bukkit.getPlayer(s);
					t.getInventory().clear();
					t.teleport(getSpawn());

				
				}
				players.clear();

				Bukkit.getScheduler().cancelAllTasks();

			}
		}, 200);
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void inte(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (p.getItemInHand() != null && p.getItemInHand().hasItemMeta()){
		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE+"§lLeave the Event")){
			players.remove(p.getName());
			p.getInventory().clear();
			p.teleport(getSpawn());
			Bukkit.broadcastMessage(prefix +" The Player " +p.getName() + " has Left the Event !");
		if (players.size() == 1){

			Player t = Bukkit.getPlayer(players.get(0));
			Bukkit.broadcastMessage(prefix + "The player " + t.getName() + " Has won the Event, and earned 75 Coins !");

			t.getInventory().clear();

			t.teleport(getSpawn());
			plugin.AddCoin(t, 75);
		setStatus(GameStatus.WAITING);
		 
		for(int x = 0 ; x < 10 ; x ++){
			  Firework f = (Firework) t.getWorld().spawn(t.getLocation(), Firework.class);
	           
	           FireworkMeta fm = f.getFireworkMeta();
	           fm.addEffect(FireworkEffect.builder()
	                           .flicker(false)
	                           .trail(true)
	                           .with(Type.STAR)
	                           .withColor(Color.fromBGR(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)))
	                           .withFade(Color.fromBGR(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)))
	                           .build());
	           fm.setPower(3);
	           f.setFireworkMeta(fm);
				players.clear();

		}
		}
		}
		}
	}
	@EventHandler
	public void dmg(EntityDamageEvent e){
		if (e.getEntity() instanceof Player){
			if (e.getCause().equals(DamageCause.FALL) && getStatus() == GameStatus.INGAME && players.contains(((Player) e.getEntity()).getName())){
				e.setCancelled(true);
				e.getEntity().teleport(new Location(Bukkit.getWorld("world"), 92, 157, 751));
			}
		}
	}
	
	public ItemStack specialItemStack(String name, String lore, Material m){
		ItemStack s = new ItemStack(m);
		ItemMeta meta = s.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> lorea = new ArrayList<String>();
		lorea.add(lore);
		meta.setLore(lorea);
		s.setItemMeta(meta);
		return s;
	}
	public void setStatus(GameStatus status){
		this.status = status;
	}
	public ArrayList<String> getPlayers(){
		return players;
	}
	public GameStatus getStatus(){
		return status;
	}
	public Location getSpawn(){
		WarpsManager settings = WarpsManager.getInstance();
		return new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y") + 1,settings.getData().getInt("spawn.z"));
	}
	

	@SuppressWarnings("deprecation")
	public void start(){
		Bukkit.broadcastMessage(prefix + "Parkour event has Started, Get to the End And Win !");
		status = GameStatus.INGAME;
		for (String s : players){
			Player p = Bukkit.getPlayer(s);
	
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			ItemStack leave = specialItemStack(ChatColor.BLUE+"§lLeave the Event", ChatColor.GREEN+"§lBack to spawn", Material.MAGMA_CREAM);
		
			p.getInventory().addItem(leave);
			p.teleport(new Location(Bukkit.getWorld("world"), 92, 157, 751));
			
		}
	}
	
	
}
