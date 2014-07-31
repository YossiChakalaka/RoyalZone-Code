package me.or.royals.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OitcDeathEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
  
    Player p;
    public OitcDeathEvent(Player p){
    	this.p =p;
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
