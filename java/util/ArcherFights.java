package util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ArcherFights {

ArrayList<String> Blue = new ArrayList<String>();
ArrayList<String> Red = new ArrayList<String>();
static ArcherFights instance = new ArcherFights();	
	public ArcherFights getInstance(){
		return instance;
	}


	
@SuppressWarnings("deprecation")
public Player getRedPlayer(){
	return Bukkit.getPlayer(Red.get(0));
}
@SuppressWarnings("deprecation")
public Player getBluePlayer(){
	return Bukkit.getPlayer(Blue.get(0));
}
public boolean hasStarted(){
	if (Blue.size() == 1 && Red.size() == 2){
		return true;
	
	}
	return false;
}
	public void setupPlayer(Player p){
		
	}
}
