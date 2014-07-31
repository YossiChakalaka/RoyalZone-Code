package me.or.royals.event;

import java.util.ArrayList;
import java.util.Random;

import me.or.royals.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class API {
public Main plugin;
	ArrayList<String> oitc = new ArrayList<String>();
	ArrayList<String> players = new ArrayList<String>();
	ArrayList<String> tf = new ArrayList<String>();
	ArrayList<String> tfp = new ArrayList<String>();
	String prefix = (ChatColor.GRAY+"[" + ChatColor.GREEN+"Royal" +ChatColor.RED+"Event" + ChatColor.GRAY+"]");

	static API instance = new API();
	public static API getInstance(){
	 
		return instance;
		
	}
	Gui g = Gui.getInstance();
	

	
	
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
	public void clearArmor(Player p){
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);	
	}
	@SuppressWarnings("deprecation")
	public void startOITC(){
		
		tpAllPlayers(new Location(Bukkit.getWorld("world"), 478, 18, -354));
		for(Player p : Bukkit.getOnlinePlayers()){
	
			clearArmor(p);
			oitc.add(p.getName());
			p.setHealth(20);
			p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
		ItemStack s = specialItemStack(Material.BOW, "§lAmighty Bow", ChatColor.GREEN, "Oitc Bow", ChatColor.AQUA);
		p.getInventory().addItem(s);
		p.getInventory().addItem(new ItemStack(Material.ARROW));
		}
		Bukkit.broadcastMessage(prefix + "a One in the Chamber Event has Started !");
		
	}
	@SuppressWarnings("deprecation")
	public void startTF(){

		tpAllPlayers(new Location(Bukkit.getWorld("world"), 199, 72, -381));
		for(Player p : Bukkit.getOnlinePlayers()){
			if(!p.isDead()){
				p.setHealth(20);
			clearArmor(p);
			setArmor(p, 1);

			p.getInventory().clear();
			tf.add(p.getName());
			

			}	
		}
		Bukkit.broadcastMessage(prefix + "a The Fighter Event has Started !");

		Player t = Bukkit.getOnlinePlayers()[new Random().nextInt(Bukkit.getOnlinePlayers().length)];

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

		
		t.updateInventory();
		tf.remove(t.getName());
		tfp.add(t.getName());
		
		for(String sa : tf){
			Player ta = Bukkit.getPlayer(sa);
		ta.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));

		}
		
clearArmor(Bukkit.getPlayer(tfp.get(0)));
setArmor(Bukkit.getPlayer(tfp.get(0)), 2);

		Bukkit.broadcastMessage(prefix + "The Fighter is " + t.getName());
		

	}
	public void startLMS(){
		tpAllPlayers(new Location(Bukkit.getWorld("world"), 0, 53, 1031));
		for(Player p : Bukkit.getOnlinePlayers()){
			p.getInventory().clear();
			clearArmor(p);

			ItemStack s = specialItemStack(Material.STICK, "SUMO", ChatColor.GREEN, "TAKE HIM OUT OF THE ARENA", ChatColor.DARK_PURPLE);
			s.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 100));
			
			p.getInventory().addItem(s);
			players.add(p.getName());
			
		}

	}
	public void tpAllPlayers(Location l){
		for (Player p : Bukkit.getOnlinePlayers()){
			p.teleport(l);
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
	public void setUpInventory(){
	
		g.i.addItem(specialItemStack(Material.IRON_BLOCK, "Parkour", ChatColor.RED, "Click to Start !", ChatColor.DARK_PURPLE));

		g.i.addItem(specialItemStack(Material.WOOL, "Last Man Standing", ChatColor.GREEN, "Click to Start !", ChatColor.DARK_PURPLE));
		g.i.addItem(specialItemStack(Material.BOW, "One in the Chamber", ChatColor.RED, "Click to Start !", ChatColor.GREEN));
		g.i.addItem(specialItemStack(Material.IRON_AXE, "The Fighter", ChatColor.DARK_PURPLE, "Click to start", ChatColor.GREEN));
	}
}
