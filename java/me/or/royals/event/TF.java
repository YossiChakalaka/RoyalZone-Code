package me.or.royals.event;

import java.util.ArrayList;
import java.util.Random;

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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TF implements Listener {
	public Main plugin;
	public TF(Main plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void friendly (EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player k = (Player) e.getDamager();
			Player p = (Player) e.getEntity();
			Teams Team = Teams.getInstance();
			
			
			if (players.contains(p.getName()) && fighter.contains(k.getName())){
					if(Team.getTeam(p).equals(Team.getTeam(k))){
				e.setCancelled(false);
					}
					}
		}
	}
	public void setArmor(Player p, int i){
		if (i == 1){
			p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
			p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
			p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
			p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));

		}else if (i == 2){
			p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		}else if (i == 3){
			p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
			p.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
			p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
			p.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
		}else if (i == 4){
			p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
			p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
			p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
			p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		}
	}
	
	public String prefix = (ChatColor.GREEN+"§lTheFighter>");
	// Fighter Event :)
	public  GameStatus status = GameStatus.WAITING;
	public ArrayList<String> players = new ArrayList<String>();
	public ArrayList<String> fighter = new ArrayList<String>();

	
	public GameStatus getStatus(){
		return status;
	}
	public ItemStack specialItemStack(Material m, String name, ChatColor c, String lore, ChatColor lorec){
		ItemStack s = new ItemStack(m);
		ItemMeta meta = s.getItemMeta();
		meta.setDisplayName(c + name);
	
		ArrayList<String> l = new ArrayList<String>();

		l.add(lorec + lore);
		meta.setLore(l);
		s.setItemMeta(meta);
		return s;
	}
	public Location getSpawn(){
		WarpsManager settings = WarpsManager.getInstance();
		return new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y") + 1,settings.getData().getInt("spawn.z"));
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void death(PlayerDeathEvent e){
		if(e.getEntity() instanceof Player){
			Player p = e.getEntity();
			if(fighter.contains(p.getName())){

				fighter.clear();
				p.setHealth(20);
				p.teleport(getSpawn());
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				e.setDeathMessage(prefix + " The Fighter has died, everyone who stay alive earned 20 Coins !");
				status = GameStatus.WAITING;
				
				for(String s : players){
					plugin.AddCoin(Bukkit.getPlayer(s), 20);
				Bukkit.getPlayer(s).teleport(getSpawn());
				Bukkit.getPlayer(s).getInventory().clear();
				Bukkit.getPlayer(s).getInventory().setArmorContents(null);
				}
				players.clear();

			}
			 if (players.contains(p.getName())){
				 if( getStatus() == GameStatus.INGAME){
					
				players.remove(p.getName());
				p.setHealth(20);
				p.teleport(getSpawn());
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				p.updateInventory();
				e.setDeathMessage(prefix + " The Player " + p.getName() + " has Died, " + players.size()  +" Players Left.");
				p.teleport(getSpawn());
				if (players.size() == 0 && fighter.size() == 1){
				for(String s : players){
					Player k = Bukkit.getPlayer(s);
					k.setHealth(20);
					k.teleport(getSpawn());
					k.getInventory().clear();
					k.getInventory().setArmorContents(null);
					k.teleport(getSpawn());
				}
					Player t = Bukkit.getPlayer(fighter.get(0));
					t.setHealth(20);
					t.teleport(getSpawn());
					t.getInventory().clear();
					t.getInventory().setArmorContents(null);
					Bukkit.broadcastMessage(prefix + " The Fighter has won, and earned 50 Coins.");
					fighter.clear();
					status = GameStatus.WAITING;
					plugin.AddCoin(t, 50);
					

				}
			 }
			}
		}
	}
	@SuppressWarnings("deprecation")
	public void inv(){
		status = GameStatus.INV;
		Bukkit.broadcastMessage(prefix + "5 seconds of Invicibile");
		Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				status = GameStatus.INGAME;
				Bukkit.broadcastMessage(prefix + "5 Seconds of Invicibile has done, Fight !");
			}
		}, 100);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void quit(PlayerQuitEvent e){
		Player p = e.getPlayer();
	if(players.contains(p.getName())){
		p.setHealth(20);
		p.teleport(getSpawn());
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		Bukkit.broadcastMessage(prefix + " The Player " + p.getName() + " has quit " + players.size() + " Player Left");
	}
		if(fighter.contains(p.getName())){
			p.setHealth(20);
			p.teleport(getSpawn());
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			Bukkit.broadcastMessage(prefix + " The Fighter has Quit, the event has done");
			fighter.clear();
			for(String s : players){
				
				Player t = Bukkit.getPlayer(s);
				
				t.setHealth(20);
				t.teleport(getSpawn());
				t.getInventory().clear();
				t.getInventory().setArmorContents(null);
			}
			status = GameStatus.WAITING;
			players.clear();
fighter.clear();			
		}
	}
	@EventHandler
	public void damage(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player p = (Player) e.getEntity();
			Player k = (Player) e.getDamager();
			if (players.contains(p.getName()) && players.contains(k.getName())){
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void start(){

		status = GameStatus.INV;
		inv();
		
		Player t = Bukkit.getPlayer(players.get(new Random().nextInt(players.size())));
	t.getInventory().clear();
	t.getInventory().setArmorContents(null);
		players.remove(t.getName());
	fighter.add(t.getName());
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemMeta meta = Bow.getItemMeta();

	meta.setDisplayName(ChatColor.WHITE + "GhastBow");
	Bow.setItemMeta(meta);

	Bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);

	ItemStack axe = new ItemStack(Material.IRON_AXE);

	axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);

	axe.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
	t.getInventory().addItem(axe);

	t.getInventory().addItem(Bow);
	t.getInventory().addItem(new ItemStack(Material.ARROW));
	ItemStack s = specialItemStack(Material.FIREWORK, "§5Double Jump !", ChatColor.BOLD, "Jump !", ChatColor.GREEN);
	t.getInventory().addItem(s);
	ItemStack pink = new ItemStack(Material.getMaterial(351), 1, (short) 9);
	ItemMeta pmeta = pink.getItemMeta();
	pmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Groopy Wand");
	pink.setItemMeta(pmeta);
	t.getInventory().addItem(pink);
	t.setHealth(20);
	Bukkit.broadcastMessage(prefix + "The Game has started, the fighter is " + t.getName());
	
setArmor(t, 2);
	t.teleport(new Location(Bukkit.getWorld("world"), 199, 72, -381));

		for(String sa : players){
			
			Player p = Bukkit.getPlayer(sa);
			setArmor(Bukkit.getPlayer(sa), 1);
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.teleport(new Location(Bukkit.getWorld("world"), 199, 72, -381));
			p.setHealth(20);
// OMG IM COMFUSED !
			
			// sec, there's some music in mah house, ill close the door :)
			// ok :)
			
			
			p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
			
		}
	}
}
