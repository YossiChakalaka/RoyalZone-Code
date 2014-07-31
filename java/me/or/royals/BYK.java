package me.or.royals;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BYK implements Listener {
public Main plugin;
public BYK(Main plugin){
	this.plugin = plugin;

}
Inventory helmet = Bukkit.createInventory(null, 9, ChatColor.GREEN+"Helmet Inventory");
Inventory leggings = Bukkit.createInventory(null, 9, ChatColor.GREEN+"Leggings Inventory");
Inventory boots = Bukkit.createInventory(null, 9, ChatColor.GREEN+"Leggings Inventory");


Inventory chestpalte = Bukkit.createInventory(null, 9, ChatColor.GREEN+"Chestplate Inventory");


BYKManager byk = BYKManager.getInstance();
FileConfiguration f = byk.getData();

@EventHandler
public void Join(PlayerJoinEvent e){
	Player p = e.getPlayer();
	String s = p.getUniqueId().toString();
	f.set(s+".byk", f.getInt(s+".byk"));
	
	
}
public Inventory getHelmetInventory(){
	return helmet;
	
}

public static ItemStack createItemStack(Material itemMaterial, byte iByte, String iName, String iLore, ChatColor iDisplayClr, ChatColor iLoreClr)
{
 ItemStack iStack = new ItemStack(itemMaterial, 1, iByte);
 ItemMeta iData = iStack.getItemMeta();
 
 iData.setLore(null);
 //iData.setDisplayName(iDisplayClr + iName);
 iData.setDisplayName(iName);
 ArrayList<String> bLore = new ArrayList<String>();
 bLore.add(iLoreClr + iLore);
 iData.setLore(bLore);
 iStack.setItemMeta(iData);
 
 return iStack;
}
@SuppressWarnings("null")
public void setup(){
	ItemStack diamondh = createItemStack(Material.DIAMOND_HELMET, (Byte) null, "Diamond Helmet", "30 BYK", ChatColor.AQUA, ChatColor.GOLD);
	ItemStack ironh = createItemStack(Material.IRON_HELMET, (Byte) null, "Iron Helmet", "20 BYK", ChatColor.GRAY, ChatColor.GOLD);
	ItemStack goldh = createItemStack(Material.GOLD_HELMET, (Byte) null, "Gold Helmet", "15 BYK", ChatColor.GOLD, ChatColor.GOLD);
	ItemStack leatherh= createItemStack(Material.LEATHER_HELMET, (Byte) null, "Leather Helmet", "Free !", ChatColor.DARK_GRAY, ChatColor.GOLD);
	helmet.addItem(diamondh);
	helmet.addItem(ironh);
	helmet.addItem(goldh);
	helmet.addItem(leatherh);

}
public void addByk(Player p, int amount){
	f.set(p.getUniqueId().toString()+".byk", f.getInt(p.getUniqueId().toString() + ".byk") + amount);
}
public int getPlayerbyk(Player p){
return f.getInt(p.getUniqueId().toString()+".byk");
}

@EventHandler
public void inventoryclick(InventoryClickEvent e){
	Player p = (Player) e.getWhoClicked();
	if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.GREEN+"Helmet Inventory")){
		p.openInventory(chestpalte);
	}else if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.GREEN+"Chestplate Inventory")){
		p.openInventory(leggings);
	}else if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.GREEN+"Chestplate Inventory")){
		p.openInventory(boots);
	}
}
@EventHandler
public void onclick(PlayerInteractEntityEvent e){
	Player p = e.getPlayer();

	if (e.getRightClicked() instanceof Villager){
		Villager v = (Villager) e.getRightClicked();
		if (v.getCustomName().equalsIgnoreCase(ChatColor.BLUE+"Jacob")){
			p.openInventory(getHelmetInventory());
		}
	}

	
}
	
	
}
