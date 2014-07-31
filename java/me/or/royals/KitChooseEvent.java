package me.or.royals;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KitChooseEvent extends Event {
    private static final HandlerList handlers = new HandlerList();


    KitType kt;
    Player p;
	    public KitChooseEvent(Player p, KitType kt) {
	        this.kt = kt;
	        this.p = p;
	    }
	    
	    public KitType getKit(){
	    	
	    	return kt;
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

