package me.or.royals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KitsGUIClick implements Listener {

	public Main plugin;

	public KitsGUIClick(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void Click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (e.getInventory()
				.getName()
				.equalsIgnoreCase(
						ChatColor.BLACK + "You" + ChatColor.RED + "Tuber")
				&& e.getCurrentItem()
						.getItemMeta()
						.getDisplayName()
						.equalsIgnoreCase(ChatColor.DARK_PURPLE + "EnderArcher")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p, "Royalzone.youtuber")) {

				plugin.EnderArcher(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.ENDERARCHER));
				p.closeInventory();

			} else {

				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.closeInventory();

			}
		} else if (e
				.getInventory()
				.getName()
				.equalsIgnoreCase(
						ChatColor.BLACK + "You" + ChatColor.RED + "Tuber")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.GREEN + "Creeper")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p,"RoyalZone.youtuber")) {

				plugin.Creeper(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.CREEPER));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.closeInventory();
			}
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.BLUE + "Warth")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p,"RoyalZone.kits.warth")) {

				plugin.Warth(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.WARTH));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.sendMessage(ChatColor.GREEN
						+ "Please Suggest Buying V.I.P Rank it will help Alot !");
				p.closeInventory();
			}
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.WHITE + "Ghast")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p,"RoyalZone.kits.ghast")) {

				plugin.Ghast(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.GHAST));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.sendMessage(ChatColor.GREEN
						+ "Please Suggest Buying V.I.P Rank it will help Alot !");
				p.closeInventory();
			}
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.DARK_PURPLE + "Vampire")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p,"RoyalZone.kits.vampire")) {

				plugin.Vampire(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.VAMPIRE));

				p.closeInventory();
			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.sendMessage(ChatColor.GREEN
						+ "Please Suggest Buying V.I.P Rank it will help Alot !");
				p.closeInventory();
			}
		
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.RED + "Shop Kits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.DARK_GREEN + "Minion")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p, "RoyalZone.kits.minion")) {

				plugin.Minion(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.MINION));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED+ "You Dont Own this Kit");
			
			}
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.RED + "Shop Kits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.WHITE + "Spider")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p, "RoyalZone.kits.spider")) {

				plugin.Spider(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.SPIDER));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				
			}
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.RED + "Shop Kits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.BLACK + "Smasher")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p, "RoyalZone.kits.smasher")) {

				plugin.Smasher(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.SMASHER));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				
			}
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.GREEN + "Gangsta")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p, "RoyalZone.kits.gangsta")) {

				plugin.Gangsta(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.GANGSTER));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.sendMessage(ChatColor.GREEN
						+ "Please Suggest Buying V.I.P Rank it will help Alot !");
				p.closeInventory();
			}
		} else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Groopy")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p, "RoyalZone.kits.groopy")) {

				plugin.Groopy(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.GROOPY));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.sendMessage(ChatColor.GREEN
						+ "Please Suggest Buying V.I.P Rank it will help Alot !");
				p.closeInventory();
			}
		}else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.GOLD + "Troller")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p, "RoyalZone.kits.troller")) {

				plugin.Troller(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.TROLLER));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.sendMessage(ChatColor.GREEN
						+ "Please Suggest Buying V.I.P Rank it will help Alot !");
				p.closeInventory();
			}
		}else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.RED+"RegularKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.GREEN + "Knight")) {
			e.setCancelled(true);


				plugin.Hero(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.KNIGHT));

				p.closeInventory();

			
			
		}else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.RED+"RegularKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "Archer")) {
			e.setCancelled(true);


				plugin.Archer(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.ARCHER));

				p.closeInventory();

			
			
		}else if (e.getInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
				&& e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase("Rabbit")) {
			e.setCancelled(true);

			if (plugin.hasPermission(p,"RoyalZone.kits.rabbit")) {

				plugin.Rabbit(p);
				Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.RABBIT));

				p.closeInventory();

			} else {
				p.sendMessage(ChatColor.DARK_RED
						+ "You Dont Own this Kit");
				p.sendMessage(ChatColor.GREEN
						+ "Please Suggest Buying V.I.P Rank it will help Alot !");
				p.closeInventory();
			}
	} else if (e.getInventory().getName()
			.equalsIgnoreCase(ChatColor.RED + "Shop Kits")
			&& e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase( "Batman")) {
		e.setCancelled(true);

		if (plugin.hasPermission(p, "RoyalZone.kits.batman")) {

			plugin.Batman(p);
			Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.BATMAN));

			p.closeInventory();

		} else {
			p.sendMessage(ChatColor.DARK_RED
					+ "You Dont Own this Kit");
			
		}
	}else if (e.getInventory().getName()
			.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
			&& e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase("Grappler")) {
		e.setCancelled(true);

		if (plugin.hasPermission(p,"RoyalZone.kits.grappler")) {

			plugin.Grappler(p);
			Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.GRAPPLER));

			p.closeInventory();

		} else {
			p.sendMessage(ChatColor.DARK_RED
					+ "You Dont Own this Kit");
			p.sendMessage(ChatColor.GREEN
					+ "Please Suggest Buying V.I.P Rank it will help Alot !");
			p.closeInventory();
		}
} else if (e.getInventory().getName()
		.equalsIgnoreCase(ChatColor.AQUA + "VIPKits")
		&& e.getCurrentItem().getItemMeta().getDisplayName()
				.equalsIgnoreCase("§l§5Kangaroo")) {
	e.setCancelled(true);

	if (plugin.hasPermission(p,"RoyalZone.kits.kangaroo")) {

		plugin.Kangaroo(p);
		Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.KANGAROO));

		p.closeInventory();

	} else {
		p.sendMessage(ChatColor.DARK_RED
				+ "You Dont Own this Kit");
		p.sendMessage(ChatColor.GREEN
				+ "Please Suggest Buying V.I.P Rank it will help Alot !");
		p.closeInventory();
	}
} else if (e.getInventory().getName()
		.equalsIgnoreCase(ChatColor.RED + "Shop Kits")
		&& e.getCurrentItem().getItemMeta().getDisplayName()
				.equalsIgnoreCase( "Fisher")) {
	e.setCancelled(true);

	if (plugin.hasPermission(p, "RoyalZone.kits.fisher")) {

		plugin.Fisherman(p);
		Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.FISHER));

		p.closeInventory();

	} else {
		p.sendMessage(ChatColor.DARK_RED
				+ "You Dont Own this Kit");
		
	}
	}else if (e.getInventory().getName()
			.equalsIgnoreCase(ChatColor.RED + "Shop Kits")
			&& e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase("Hulk")) {
		e.setCancelled(true);

		if (plugin.hasPermission(p, "RoyalZone.kits.hulk")) {

			plugin.Hulk(p);
			Bukkit.getPluginManager().callEvent(new KitChooseEvent(p, KitType.HULK));

			p.closeInventory();

		} else {
			p.sendMessage(ChatColor.DARK_RED
					+ "You Dont Own this Kit");
			
		}
		}
	}
}
