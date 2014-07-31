package me.or.royals.event;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Gui {

	static Gui instance = new Gui();
	
	public Inventory i = Bukkit.createInventory(null, 9, "Events");
	
	
	
	public static Gui getInstance(){
		return instance;
	}
	
	
}
