package me.or.royals.Grappler;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Grappler
  implements Listener
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
Map<Player, CopyOfFishingHook> hooks = new HashMap();
  
  @EventHandler
  public void onSlot(PlayerItemHeldEvent e)
  {
    if (this.hooks.containsKey(e.getPlayer()))
    {
      ((CopyOfFishingHook)this.hooks.get(e.getPlayer())).remove();
      this.hooks.remove(e.getPlayer());
    }
  }
  
  @EventHandler
  public void onMove(PlayerMoveEvent e)
  {
    if (this.hooks.containsKey(e.getPlayer())) {
      if ((!e.getPlayer().getItemInHand().getType().equals(Material.LEASH)) || 
        (!e.getPlayer().getItemInHand().hasItemMeta()) || 
        
        (!e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) || 
        
        (!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("Grappling Hook")))
      {
        ((CopyOfFishingHook)this.hooks.get(e.getPlayer())).remove();
        this.hooks.remove(e.getPlayer());
      }
    }
  }
  
  @SuppressWarnings("deprecation")
@EventHandler
  public void onLeash(PlayerLeashEntityEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getPlayer().getItemInHand().getType().equals(Material.LEASH)) && 
      (p.getItemInHand().hasItemMeta()) && 
      (p.getItemInHand().getItemMeta().hasDisplayName())) {
      if (p.getItemInHand().getItemMeta().getDisplayName().equals("Grappling Hook"))
      {
        e.setCancelled(true);
        e.getPlayer().updateInventory();
        e.setCancelled(true);
        if (!this.hooks.containsKey(p)) {
          return;
        }
        if (!((CopyOfFishingHook)this.hooks.get(p)).isHooked()) {
          return;
        }
        double d = ((CopyOfFishingHook)this.hooks.get(p))
          .getBukkitEntity().getLocation().distance(p.getLocation());
        double t = d;
        double v_x = (1.0D + 0.07000000000000001D * t) * (
          ((CopyOfFishingHook)this.hooks.get(p))
          .getBukkitEntity().getLocation().getX() - p
          .getLocation().getX()) / t;
        double v_y = (1.0D + 0.03D * t) * (
          ((CopyOfFishingHook)this.hooks.get(p))
          .getBukkitEntity().getLocation().getY() - p
          .getLocation().getY()) / t;
        double v_z = (1.0D + 0.07000000000000001D * t) * (
          ((CopyOfFishingHook)this.hooks.get(p))
          .getBukkitEntity().getLocation().getZ() - p
          .getLocation().getZ()) / t;
        
        Vector v = p.getVelocity();
        v.setX(v_x);
        v.setY(v_y);
        v.setZ(v_z);
        p.setVelocity(v);
        
        p.getWorld().playSound(p.getLocation(), Sound.STEP_GRAVEL, 0.0F, 
          0.0F);
      }
    }
  }
  
  @EventHandler
  public void onClick(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getPlayer().getItemInHand().getType().equals(Material.LEASH)) && 
      (p.getItemInHand().hasItemMeta()) && 
      (p.getItemInHand().getItemMeta().hasDisplayName())) {
      if (p.getItemInHand().getItemMeta().getDisplayName().equals("Grappling Hook"))
      {
        e.setCancelled(true);
        if ((e.getAction() == Action.LEFT_CLICK_AIR) || 
          (e.getAction() == Action.LEFT_CLICK_BLOCK))
        {
          if (this.hooks.containsKey(p)) {
            ((CopyOfFishingHook)this.hooks.get(p)).remove();
          }
          CopyOfFishingHook nmsHook = new CopyOfFishingHook(p.getWorld(), 
            ((CraftPlayer)p).getHandle());
          nmsHook.spawn(p.getEyeLocation().add(
            p.getLocation().getDirection().getX(), 
            p.getLocation().getDirection().getY(), 
            p.getLocation().getDirection().getZ()));
          nmsHook.move(p.getLocation().getDirection().getX() * 5.0D, p
            .getLocation().getDirection().getY() * 5.0D, p
            .getLocation().getDirection().getZ() * 5.0D);
          this.hooks.put(p, nmsHook);
        }
        else
        {
          if (!this.hooks.containsKey(p)) {
            return;
          }
          if (!((CopyOfFishingHook)this.hooks.get(p)).isHooked())
          {
            p.sendMessage(ChatColor.GRAY + "You're now hooked !");
            return;
          }
          double d = ((CopyOfFishingHook)this.hooks.get(p))
            .getBukkitEntity().getLocation()
            .distance(p.getLocation());
          double t = d;
          double v_x = (1.0D + 0.07000000000000001D * t) * (
            ((CopyOfFishingHook)this.hooks.get(p))
            .getBukkitEntity().getLocation().getX() - p
            .getLocation().getX()) / t;
          double v_y = (1.0D + 0.03D * t) * (
            ((CopyOfFishingHook)this.hooks.get(p))
            .getBukkitEntity().getLocation().getY() - p
            .getLocation().getY()) / t;
          double v_z = (1.0D + 0.07000000000000001D * t) * (
            ((CopyOfFishingHook)this.hooks.get(p))
            .getBukkitEntity().getLocation().getZ() - p
            .getLocation().getZ()) / t;
          
          Vector v = p.getVelocity();
          v.setX(v_x);
          v.setY(v_y);
          v.setZ(v_z);
          p.setVelocity(v);
          
          p.getWorld().playSound(p.getLocation(), Sound.STEP_GRAVEL, 
            10.0F, 10.0F);
        }
      }
    }
  }
}
