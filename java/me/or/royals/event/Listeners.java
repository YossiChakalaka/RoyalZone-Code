package me.or.royals.event;

import me.or.royals.Main;
import me.or.royals.Teams;
import me.or.royals.WarpsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Listeners implements Listener {

	String prefix = (ChatColor.GRAY+"[" + ChatColor.GREEN+"Royal" +ChatColor.RED+"Event" + ChatColor.GRAY+"] ");
 API a = API.instance;
	public Main plugin;
	LMS l = new LMS(plugin);

	public Listeners(Main plugin){
		this.plugin =plugin;

	}
	@EventHandler
	public void inventory(InventoryClickEvent e){
		if (e.getWhoClicked() instanceof Player){
			Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("Events")){
			e.setCancelled(true);
			
		if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED+"Parkour")){
				
				
				if(plugin.tf.status == GameStatus.INGAME || plugin.oa1.status == GameStatus.INGAME || plugin.l.status == GameStatus.INGAME || plugin.parkour.getStatus() == GameStatus.INGAME){
					p.sendMessage(prefix + "There's already an event now.");
					Bukkit.broadcastMessage(plugin.tf.status.toString());
					Bukkit.broadcastMessage(plugin.l.status.toString());
					Bukkit.broadcastMessage(plugin.oa1.status.toString());

					return;
				}
				plugin.startParkour();
		p.sendMessage(prefix + ChatColor.GREEN+"Succesfuly created an a Parkour Event");
		
			}
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE+"The Fighter")){
				
				if(Bukkit.getOnlinePlayers().length < 1){
					p.sendMessage(prefix + ChatColor.GREEN+"There's only one player !");
				return;
				}
				if(plugin.tf.status == GameStatus.INGAME || plugin.oa1.status == GameStatus.INGAME || plugin.l.status == GameStatus.INGAME){
					p.sendMessage(prefix + "There's already an event now.");
					Bukkit.broadcastMessage(plugin.tf.status.toString());
					Bukkit.broadcastMessage(plugin.l.status.toString());
					Bukkit.broadcastMessage(plugin.oa1.status.toString());

					return;
				}
				plugin.startTF();
		p.sendMessage(prefix + ChatColor.GREEN+"Succesfuly created an a The Fighter Event");
		
			}
			}
		if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null){
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED+"One in the Chamber")){
				if(Bukkit.getOnlinePlayers().length < 1){
					p.sendMessage(prefix + ChatColor.GREEN+"There's only one player !");
				return;
				}
				if(plugin.tf.status == GameStatus.INGAME || plugin.oa1.status == GameStatus.INGAME || plugin.l.status == GameStatus.INGAME){
					p.sendMessage(prefix + "There's already an event now.");
					Bukkit.broadcastMessage(plugin.tf.status.toString());
					Bukkit.broadcastMessage(plugin.l.status.toString());
					Bukkit.broadcastMessage(plugin.oa1.status.toString());
					return;
				}
				plugin.startOOITC();
		p.sendMessage(prefix + ChatColor.GREEN+"Succesfuly created an a OITC Event");
			}
			}
if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null){
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Last Man Standing")){
				if(Bukkit.getOnlinePlayers().length < 1){
					p.sendMessage(prefix + ChatColor.GREEN+"There's only one player !");
				return;
				}
				if(plugin.tf.status == GameStatus.INGAME || plugin.oa1.status == GameStatus.INGAME || plugin.l.status == GameStatus.INGAME){
					p.sendMessage(prefix + "There's already an event now.");
					Bukkit.broadcastMessage(plugin.tf.status.toString());
					Bukkit.broadcastMessage(plugin.l.status.toString());
					Bukkit.broadcastMessage(plugin.oa1.status.toString());
					return;
					
				}
				plugin.start();
		p.sendMessage(prefix + ChatColor.GREEN+"Succesfuly created an a LMS Event");
			}
			}
		}
		}
	
	public boolean bugs(Player p){
		if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){
			return true;
		}
		return false;
		
	}
	@EventHandler
	public void quit(PlayerQuitEvent e){
		if(a.oitc.contains(e.getPlayer().getName()) || a.tf.contains(e.getPlayer().getName()) || a.players.contains(e.getPlayer().getName())){
			a.oitc.remove(e.getPlayer().getName());
			a.tf.remove(e.getPlayer().getName());
			a.players.remove(e.getPlayer().getName());

			Bukkit.broadcastMessage("The player " + e.getPlayer().getName() + " Has quit !");
			if(a.tfp.contains(e.getPlayer().getName())){
				a.tfp.remove(e.getPlayer().getName());
				Bukkit.broadcastMessage(prefix +"The Fighter has Quit !");
			}
		}
	}
	@EventHandler
	public void damage(EntityDamageByEntityEvent e){
		Teams t = Teams.getInstance();
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player p = (Player) e.getEntity();
			Player k = (Player) e.getDamager();
			if(t.isinTeam(p) && t.isinTeam(k)){
				e.setCancelled(false);
			}
			if(a.tf.contains(p.getName()) && a.tf.contains(k.getName())){
				e.setCancelled(true);
			}
		}
	
	
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void oitc (OitcDeathEvent e){
		WarpsManager settings = WarpsManager.getInstance();

		a.oitc.remove(e.getPlayer().getName());

		
		Bukkit.broadcastMessage(prefix + e.getPlayer().getName() +" Has died " + a.oitc.size() + " Player Left");
		e.getPlayer().teleport(new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z")));
	e.getPlayer().getInventory().clear();
		if (a.oitc.size() == 1){
			e.getPlayer().setHealth(20);
			e.getPlayer().getInventory().clear();
			a.oitc.remove(e.getPlayer().getName());
			Player p = Bukkit.getPlayer(a.oitc.get(0));
			Bukkit.broadcastMessage(prefix+" The Player " + p.getName() + " Has Won The OITC And Earned 50 Coins !" );
				a.oitc.clear();
			p.teleport(new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z")));


			p.setHealth(20);
			p.getInventory().clear();
			a.oitc.remove(p);
			plugin.AddCoin(p, 50);
			
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void IceMelt(BlockFromToEvent e){
	Block To = e.getToBlock();
	if(To.getTypeId() == 8 || To.getTypeId() == 79){e.setCancelled(true);}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void death(PlayerDeathEvent e){
		WarpsManager settings = WarpsManager.getInstance();

		if(a.players.contains(e.getEntity().getName())){
			e.getEntity().setHealth(20);
		
			
			e.getEntity().teleport(new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z")));
			a.players.remove(e.getEntity().getName());
	
			Bukkit.broadcastMessage(prefix + "The Player " + e.getEntity().getName() + " Has Died " + a.players.size() + " Left" );
			if(a.players.size() == 1){
				
				Player t = Bukkit.getPlayer(a.players.get(0));
				t.getInventory().clear();
				Bukkit.broadcastMessage(prefix + "The player " + t.getName() + " Has won the game, and earned 30 Coins !");
				plugin.AddCoin(t, 30);
				a.players.remove(e.getEntity().getName()); 
			a.players.clear();
			t.removePotionEffect(PotionEffectType.REGENERATION);
			}
		}
		if(a.tfp.contains(e.getEntity().getName())){
		
			e.getEntity().getInventory().clear();
			Bukkit.broadcastMessage(prefix + "The Fighter event has ended ! the Fighter has died !");
			Bukkit.broadcastMessage(prefix + "all the players who stay alive earned 20 Coins !");
			e.getEntity().getInventory().clear();
			e.getEntity().getInventory().setHelmet(null);
			e.getEntity().getInventory().setChestplate(null);
			e.getEntity().getInventory().setLeggings(null);
			e.getEntity().getInventory().setBoots(null);


			e.getEntity().teleport(new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z")));
			
			for(String s : a.tf){
				plugin.AddCoin(Bukkit.getPlayer(s), 20);
				Bukkit.getPlayer(s).teleport(new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z")));
				Player taa = Bukkit.getPlayer(s);
				taa.getInventory().clear();
				taa.getInventory().setHelmet(null);
				taa.getInventory().setChestplate(null);
				taa.getInventory().setLeggings(null);
				taa.getInventory().setBoots(null);
			}
			a.tf.clear();
			a.tfp.clear();
			e.getEntity().getInventory().clear();

		}
		if (a.tf.contains(e.getEntity().getName())){
			a.tf.remove(e.getEntity().getName());
			Bukkit.broadcastMessage(prefix + " The Player " + e.getEntity().getName() + " has Died !");
			e.getEntity().teleport(new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z")));

			e.getEntity().getInventory().clear();
		}
		if (a.tf.size() < 1 && a.tfp.size() == 1){
			
			Bukkit.broadcastMessage(prefix + "The Fighter has Won the Game, and earned 50 Coins !");
			Bukkit.getPlayer(a.tfp.get(0)).teleport(new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z")));
			Bukkit.getPlayer(a.tfp.get(0)).getInventory().clear();
			a.tf.clear();
			a.tfp.clear();
			
		}
			
		
		
		if(a.oitc.contains(e.getEntity().getName())){
	
			e.setDeathMessage(null);
			e.getEntity().setHealth(20);
			a.oitc.remove(e.getEntity().getName());
			e.getEntity().getKiller().getInventory().addItem(new ItemStack(Material.ARROW));
		
		Bukkit.getPluginManager().callEvent(new OitcDeathEvent(e.getEntity()));
		}
	
		
	}
	
	
}
