package util;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.or.royals.Main;

public class MainAPI {

	


public Main plugin;
public MainAPI(Main plugin){
	this.plugin = plugin;
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
public int getcoins(Player p){
	return plugin.getConfig().getInt(p.getUniqueId().toString()  +".coins");
}
public void addCoins(Player p, int amount){
	plugin.getConfig().set(p.getUniqueId().toString() + ".coins", plugin.getConfig().getInt(p.getUniqueId().toString() + ".coins") + amount);
}
}
