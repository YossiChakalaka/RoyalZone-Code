package me.or.royals;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class OnevOne implements Listener, CommandExecutor {
	public String prefix = (ChatColor.GRAY+"[" + ChatColor.GREEN+"1v1"+ ChatColor.GRAY+"]");
	public static OnevOne instance;
	OnevOneManager o = OnevOneManager.getInstance();
	ArrayList<String> blue = new ArrayList<String>();
	ArrayList<String> red = new ArrayList<String>();

	public Main plugin;
	public OnevOne(Main plugin){
		this.plugin = plugin;
	}
	   
    public OnevOne()
    {
        instance = this;
    }
 
    public static OnevOne getInstance() {
            return instance;
    }
 
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd,
			String[] args) {		// TODO Auto-generated method stub

		if (sender instanceof Player){
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public Player getBluePlayer(){
		return Bukkit.getPlayer(blue.get(0));
		
		
	}
	public OvoType getOvoType(Player p){
		
		if (getBluePlayer().equals(p)){
			return OvoType.BLUE;
		}
		return OvoType.RED;
	}
	@SuppressWarnings("deprecation")
	public Player getRedPlayer(){
		return Bukkit.getPlayer(red.get(0));
	}
	public void setUp(Player p,  OvoType type){
		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		for(int x = 0; x < 34; x++){
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		
		if (getBluePlayer().equals(p)){
			World w = Bukkit.getWorld(o.getData().getString("bluelounge.world"));
			double x = o.getData().getInt("bluelounge.x");
			double y = o.getData().getInt("bluelounge.y");
			double z = o.getData().getInt("bluelounge.z");

			p.sendMessage(prefix+ChatColor.BLUE+"you joined the blue team !");
		p.teleport(new Location(w, x, y, z));
		return;
		}else if (getRedPlayer().equals(p)){
			World w = Bukkit.getWorld(o.getData().getString("redlounge.world"));
			double x = o.getData().getInt("redlounge.x");
			double y = o.getData().getInt("redlounge.y");
			double z = o.getData().getInt("redlounge.z");
			p.teleport(new Location(w, x, y, z));
			p.sendMessage(prefix + ChatColor.RED+"You joined the Red Team !");
			return;

		}

	}
	
	@EventHandler
	public void Death(EntityDeathEvent e){
	
		if (e.getEntity() instanceof Player){
	
		Player p = (Player) e.getEntity();
		if (blue.size() > 0 && blue.get(0).equals(p)){
			blue.clear();
		}
		if (blue.size() > 0 && red.get(0).equals(p)){
			red.clear();
		}
	}
	}
	@EventHandler
	public void quit (PlayerQuitEvent e){
		Player p = e.getPlayer();
		if (red.size() > 0 && red.get(0).equals(p)){
			red.clear();
		}else if (blue.size() > 0 && blue.get(0).equals(p)){
			blue.clear();
		}
	}
}
