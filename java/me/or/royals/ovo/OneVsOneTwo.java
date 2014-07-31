package me.or.royals.ovo;
import java.util.ArrayList;

import me.or.royals.WarpsManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class OneVsOneTwo {

	public OneStatus status = OneStatus.WAITING;

	OvOManager ovo = OvOManager.getInstance();
	public ArrayList<String> red = new ArrayList<String>();
	public ArrayList<String> blue = new ArrayList<String>();
	public Location bluel = new Location(Bukkit.getWorld(ovo.getData().getString("ovotwo.blue.world")), ovo.getData().getInt("ovotwo.blue.x"),  ovo.getData().getInt("ovotwo.blue.y"),  ovo.getData().getInt ("ovotwo.blue.z"));
	public Location redl = new Location(Bukkit.getWorld(ovo.getData().getString("ovotwo.red.world")), ovo.getData().getInt("ovotwo.red.x"),  ovo.getData().getInt("ovotwo.red.y"),  ovo.getData().getInt ("ovotwo.red.z"));
	public Location bluelg = new Location(Bukkit.getWorld(ovo.getData().getString("ovotwo.blue.lounge.world")), ovo.getData().getInt("ovotwo.blue.lounge.x"),  ovo.getData().getInt("ovotwo.blue.lounge.y"),  ovo.getData().getInt ("ovotwo.blue.lounge.z"));
	public Location redlg = new Location(Bukkit.getWorld(ovo.getData().getString("ovotwo.red.lounge.world")), ovo.getData().getInt("ovotwo.red.lounge.x"),  ovo.getData().getInt("ovotwo.red.lounge.y"),  ovo.getData().getInt ("ovotwo.red.lounge.z"));
	public Location lounge = new Location(Bukkit.getWorld(ovo.getData().getString("ovotwo.lounge.world")), ovo.getData().getInt("ovotwo.lounge.x"),  ovo.getData().getInt("ovotwo.lounge.y"),  ovo.getData().getInt ("ovotwo.lounge.z"));

	
	
	public OneStatus getStatus(){
		return status;
		
	}
	
	

	@SuppressWarnings("deprecation")
	public Player getBluePlayer(){
		return Bukkit.getPlayer(blue.get(0));
	}
	
	@SuppressWarnings("deprecation")
	public Player getRedPlayer(){
		return Bukkit.getPlayer(red.get(0));
	}
	
	public Location getSpawn(){
		WarpsManager settings = WarpsManager.getInstance();
		return new Location(Bukkit.getWorld(settings.getData().getString("spawn.world")),settings.getData().getInt("spawn.x"),settings.getData().getInt("spawn.y"),settings.getData().getInt("spawn.z"));
	}
	public Location getBlue(){
		return bluel;
	}
	public Location getRed(){
		return redl;
	}
	public Location getBlueLounge(){
		return bluelg;
	}
	public Location getRedLounge(){
		return redlg;
	}
	public Location getLounge(){
		return lounge;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void setUpPlayer(Player p){

		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
		for(int x = 0; x < 34 ; x++){
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.setHealth(20);
		p.setFoodLevel(20);
	}
}
