package me.or.royals;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AchivementGetEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

	
	
    Achivments c;
	  Player p;
	    public AchivementGetEvent(Player p, Achivments c) {
	    	this.c = c;
	        this.p = p;
	    }
	    
	  
	    public Achivments getAchivement(){
	    	
	    	
	    	return c;
	    	
	    }
	    public Player getPlayer(){
	    	return p;
	    }
	

  public HandlerList getHandlers() {
      return handlers;
  }

  public static HandlerList getHandlerList() {
      return handlers;
  }
}


