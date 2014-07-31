package me.or.royals;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamShop implements Listener {

	Teams t = Teams.getInstance();
	TeamList tl = TeamList.getInstance();
	public Main plugin;
	public TeamShop(Main plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void interact (PlayerInteractEvent e){
		Player p = e.getPlayer();
		ItemStack Red = new ItemStack(Material.INK_SACK, 1, (short) 1);
		ItemMeta rmeta = Red.getItemMeta();
		rmeta.setDisplayName(ChatColor.RED+"Red");
		Red.setItemMeta(rmeta);
		ItemStack Green = new ItemStack(Material.INK_SACK, 1, (short) 2);
		ItemMeta gmeta = Green.getItemMeta();
		gmeta.setDisplayName(ChatColor.DARK_GREEN+"Dark Green");
		Green.setItemMeta(gmeta);
		ItemStack Blue = new ItemStack(Material.INK_SACK, 1, (short) 4);
		ItemMeta bmeta = Blue.getItemMeta();
		bmeta.setDisplayName(ChatColor.BLUE+"Blue");
		Blue.setItemMeta(bmeta);
		ItemStack PURPLE = new ItemStack(Material.INK_SACK, 1, (short) 5);
		ItemMeta pmeta = PURPLE.getItemMeta();
		pmeta.setDisplayName(ChatColor.DARK_PURPLE+"Purple");
		PURPLE.setItemMeta(pmeta);
		ItemStack GRAY = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta grmeta = GRAY.getItemMeta();
		grmeta.setDisplayName(ChatColor.GRAY+"Gray");
		GRAY.setItemMeta(grmeta);
		ItemStack LIGHT_PURPLE = new ItemStack(Material.INK_SACK, 1, (short) 9);
		ItemMeta lpmeta = LIGHT_PURPLE.getItemMeta();
		lpmeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Pink");
		LIGHT_PURPLE.setItemMeta(lpmeta);
		ItemStack YELLOW = new ItemStack(Material.INK_SACK, 1, (short) 11);
		ItemMeta ymeta = YELLOW.getItemMeta();
		ymeta.setDisplayName(ChatColor.YELLOW+"Yellow");
		YELLOW.setItemMeta(ymeta);
		ItemStack Gold = new ItemStack(Material.INK_SACK, 1, (short) 11);
		ItemMeta gameta = Gold.getItemMeta();
		gameta.setDisplayName(ChatColor.GOLD+"Gold");
		
		Gold.setItemMeta(gameta);
		if (p.getItemInHand().equals(Red)){
			
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§c"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(Red);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}else if (p.getItemInHand().equals(Gold)){
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§6"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(Gold);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}else if (p.getItemInHand().equals(LIGHT_PURPLE)){
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§d"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(LIGHT_PURPLE);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}else if (p.getItemInHand().equals(Green)){
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§a"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(Green);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}else if (p.getItemInHand().equals(YELLOW)){
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§e"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(YELLOW);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}else if (p.getItemInHand().equals(PURPLE)){
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§5"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(PURPLE);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}else if (p.getItemInHand().equals(GRAY)){
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§7"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(GRAY);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}else if (p.getItemInHand().equals(Blue)){
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				tl.getData().set("Teams" + t.getTeam(p) + "Tag", "§1"+t.getTag(p, t.getTeam(p)));
				p.getInventory().removeItem(Blue);
				tl.saveData();
				t.removeTeamCoins(t.getTeam(p), 30);

			}
		}
		
	}
	@EventHandler
	public void inventory(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.GREEN+"TeamShop")){
			e.setCancelled(true);
			if (!(t.getLeader(t.getTeam(p)).equals(p.getUniqueId().toString()))){
				p.sendMessage(plugin.teamprefix+ChatColor.DARK_RED+"Your'e not the Leader !");	
				}
			if (e.getCurrentItem().getType() == Material.PAPER){
			int TeamCoins = tl.getData().getInt(t.getTeam(p) + ".coins");
			if (TeamCoins < 100){
				p.sendMessage(ChatColor.DARK_RED+"You Dont Have enough Coins !");
			}else if (TeamCoins >= 100){
				ItemStack Tag = new ItemStack(Material.PAPER);
				ItemMeta meta = Tag.getItemMeta();
				meta.setDisplayName(ChatColor.RED+"ChangeTag Paper");
				Tag.setItemMeta(meta);
	
				p.getInventory().addItem(Tag);
				t.removeTeamCoins(t.getTeam(p), 100);

				p.closeInventory();
				p.sendMessage(plugin.teamprefix + ChatColor.GREEN+"Succesfuly Bought The ChangeTag Paper");
			}
			
			}else if (e.getCurrentItem().getType() == Material.INK_SACK){
				p.closeInventory();
				p.openInventory(plugin.colors);
			}else if (e.getCurrentItem().getType() == Material.COAL_ORE){
				p.closeInventory();
				p.openInventory(plugin.slots);
			}
			
		}else if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.AQUA+"Achievments")){
			e.setCancelled(true);
			
		}else if (e.getInventory().getTitle().equalsIgnoreCase("Colors")){
		
			if (!(t.getLeader(t.getTeam(p)).equals(p.getUniqueId().toString()))){
				p.sendMessage(plugin.teamprefix+ChatColor.DARK_RED+"Your'e not the Leader !");	
				return;
				}
			e.setCancelled(true);
			ItemStack Red = new ItemStack(Material.INK_SACK, 1, (short) 1);
			ItemMeta rmeta = Red.getItemMeta();
			rmeta.setDisplayName(ChatColor.RED+"Red");
			Red.setItemMeta(rmeta);
			ItemStack Green = new ItemStack(Material.INK_SACK, 1, (short) 2);
			ItemMeta gmeta = Green.getItemMeta();
			gmeta.setDisplayName(ChatColor.DARK_GREEN+"Dark Green");
			Green.setItemMeta(gmeta);
			ItemStack Blue = new ItemStack(Material.INK_SACK, 1, (short) 4);
			ItemMeta bmeta = Blue.getItemMeta();
			bmeta.setDisplayName(ChatColor.BLUE+"Blue");
			Blue.setItemMeta(bmeta);
			ItemStack PURPLE = new ItemStack(Material.INK_SACK, 1, (short) 5);
			ItemMeta pmeta = PURPLE.getItemMeta();
			pmeta.setDisplayName(ChatColor.DARK_PURPLE+"Purple");
			PURPLE.setItemMeta(pmeta);
			ItemStack GRAY = new ItemStack(Material.INK_SACK, 1, (short) 8);
			ItemMeta grmeta = GRAY.getItemMeta();
			grmeta.setDisplayName(ChatColor.GRAY+"Gray");
			GRAY.setItemMeta(grmeta);
			ItemStack LIGHT_PURPLE = new ItemStack(Material.INK_SACK, 1, (short) 9);
			ItemMeta lpmeta = LIGHT_PURPLE.getItemMeta();
			lpmeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Pink");
			LIGHT_PURPLE.setItemMeta(lpmeta);
			ItemStack YELLOW = new ItemStack(Material.INK_SACK, 1, (short) 11);
			ItemMeta ymeta = YELLOW.getItemMeta();
			ymeta.setDisplayName(ChatColor.YELLOW+"Yellow");
			YELLOW.setItemMeta(ymeta);
			ItemStack Gold = new ItemStack(Material.INK_SACK, 1, (short) 11);
			ItemMeta gameta = Gold.getItemMeta();
			gameta.setDisplayName(ChatColor.GOLD+"Gold");
			
			Gold.setItemMeta(gameta);
			if (e.getCurrentItem().equals(Red)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(Red);
				}
			}else if (e.getCurrentItem().equals(Green)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(Green);
				}
			}else if (e.getCurrentItem().equals(YELLOW)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(YELLOW);
				}
			}else if (e.getCurrentItem().equals(Blue)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(Blue);
				}
			}else if (e.getCurrentItem().equals(LIGHT_PURPLE)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(LIGHT_PURPLE);
				}
			}else if (e.getCurrentItem().equals(PURPLE)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(PURPLE);
				}
			}else if (e.getCurrentItem().equals(GRAY)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(GRAY);
				}
			}else if (e.getCurrentItem().equals(Gold)){
				
				int TeamCoins = t.getTeamCoins(t.getTeam(p));
				if (!(TeamCoins >= 30)){
					p.sendMessage(plugin.teamprefix + ChatColor.DARK_RED+"You Dont have enough Team Coins !");
					
				}else{
					p.getInventory().addItem(Gold);
				
			
				}
			}
		}else if (e.getInventory().getTitle().equalsIgnoreCase("Team SLots")){
			e.setCancelled(true);
			ItemStack ten = new ItemStack(Material.IRON_INGOT);
			ItemStack tenfive = new ItemStack(Material.GOLD_INGOT);
			ItemStack twenti = new ItemStack(Material.DIAMOND);

			ItemMeta tenmeta = ten.getItemMeta();
			ItemMeta tenfivemeta = tenfive.getItemMeta();
			ItemMeta twentimeta = twenti.getItemMeta();
			tenmeta.setDisplayName(ChatColor.WHITE+"10");
			tenfivemeta.setDisplayName(ChatColor.GOLD+"15");
			twentimeta.setDisplayName(ChatColor.AQUA+"20");
			ArrayList<String> tenlore = new ArrayList<String>();
			ArrayList<String> tenfivelore = new ArrayList<String>();
			ArrayList<String> twentilore = new ArrayList<String>();

			tenlore.add(ChatColor.GOLD+"100 TeamCoins");
			tenfivelore.add(ChatColor.GOLD+"300 TeamCoins");
			twentilore.add(ChatColor.GOLD+"475 TeamCoins");
			tenmeta.setLore(tenlore);
			tenfivemeta.setLore(tenfivelore);
			twentimeta.setLore(twentilore);
			ten.setItemMeta(tenmeta);
			tenfive.setItemMeta(tenfivemeta);
			twenti.setItemMeta(twentimeta);

			if (e.getCurrentItem().equals(ten)){
				if (t.getTeamCoins(t.getTeam(p)) >= 100){
					t.addTeamSlots(t.getTeam(p), 10);
					p.sendMessage(plugin.teamprefix+ChatColor.GREEN+"Succesfuly Bought 10 Slots for your Team");
					p.closeInventory();
				}else{
					p.sendMessage(plugin.teamprefix+"You dont have enough Team Coins !");
				}
			}else if (e.getCurrentItem().equals(tenfive)){
				if (t.getTeamCoins(t.getTeam(p)) >= 300){
				t.addTeamSlots(t.getTeam(p), 15);
				p.sendMessage(plugin.teamprefix+ChatColor.GREEN+"Succesfuly Bought 15 Slots for your Team");
				p.closeInventory();
				}else{
					p.sendMessage(plugin.teamprefix+"You dont have enough Team Coins !");
				}
			}else if (e.getCurrentItem().equals(twenti)){
				if (t.getTeamCoins(t.getTeam(p)) >= 475){

				p.sendMessage(plugin.teamprefix+ChatColor.GREEN+"Succesfuly Bought 20 Slots for your Team");
				p.closeInventory();
				t.addTeamSlots(t.getTeam(p), 20);
				}else{
					p.sendMessage(plugin.teamprefix+"You dont have enough Team Coins !");
				}
			}
		}
	}
}
