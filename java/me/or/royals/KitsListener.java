package me.or.royals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

@SuppressWarnings("deprecation")
public class KitsListener implements Listener {

	public Main plugin;

	HashMap<Player, Player> fisherman = new HashMap<Player, Player>();
	ArrayList<String> fisher = new ArrayList<String>();
	SettingsManager instance = SettingsManager.getInstance();
	public KitsListener(Main plugin) {
		this.plugin = plugin;
	}

	

	@EventHandler
	public void KillStreak(PlayerDeathEvent e) {
		if (fisherman.containsKey(e.getEntity())){

		}
		if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player){
			
			ItemStack Bow2 = new ItemStack(Material.STONE_SWORD);


			Bow2.addEnchantment(Enchantment.DAMAGE_ALL, 2);

			ItemMeta meta2 = Bow2.getItemMeta();

			meta2.setDisplayName(ChatColor.DARK_GREEN + "Minion Sword");

			Bow2.setItemMeta(meta2);

			Player p = (Player) e.getEntity();

			if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){

			if (p.getItemInHand().getItemMeta().equals(meta2)) {

				Location l = e.getEntity().getLocation();


				Zombie z = (Zombie) p.getWorld().spawn(l, Zombie.class);

				z.setTarget(e.getEntity().getKiller());

				z.setCustomName(p.getName());

				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 1));

				z.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999, 3));
				z.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 9999, 100));
				z.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			}
			}
		}
			if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Zombie){
			Zombie z = (Zombie) e.getEntity().getKiller();
			if (!(z.getCustomName().equalsIgnoreCase("Zombie"))){
				Player killer = Bukkit.getPlayer(z.getCustomName());
				if (killer != null){
			if (plugin.isVip(killer)){
				plugin.AddCoin(killer, 2);
			}else{
				plugin.AddCoin(killer, 1);
			}
				}
				}
			}
		
		if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player){
			
		Player k = (Player) e.getEntity().getKiller();
		
		if (k.getInventory().getHelmet() != null && k.getInventory().getHelmet().getItemMeta() != null && k.getInventory().getHelmet().getItemMeta().getDisplayName() != null){
		if (k.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("Special Helmet")){
			k.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
		}
		}
		
		ItemStack helmet= new ItemStack(Material.IRON_HELMET);
		ItemMeta meta = helmet.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Vampire Helmet");
		helmet.setItemMeta(meta);
		
		if(k.getInventory().getHelmet() != null && k.getInventory().getHelmet().getItemMeta() != null){
		if (k.getInventory().getHelmet().getItemMeta().equals(meta)){
			k.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 5));

			Damageable d = (Damageable) k;


			d.setHealth(d.getHealth() + 3);
		}
		}
 
		
		}
	
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() == null) {
				return;
			}
					}
	}
			
		
	

	@EventHandler
	public void move(PlayerMoveEvent e) {
		
		final Player p = e.getPlayer();
		if (p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SPONGE
				&& p.getLocation().subtract(0, 2, 0).getBlock().getType() != Material.GLOWSTONE
				&& p.getLocation().subtract(0, 2, 0).getBlock().getType() != Material.STONE) {
			Vector v = p.getLocation().getDirection().multiply(3).setY(3.3);
			p.setVelocity(v);
			
			if (!plugin.NoFall.contains(p.getName())){
					if (!plugin.parkour.getPlayers().contains(p.getName())) {

				plugin.NoFall.add(p.getName());
					}
			}

		}	
		
		if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getItemMeta() != null && p.getInventory().getHelmet().getItemMeta().getDisplayName() != null){
		if (p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("Super Helmet")){
			if (!(p.isOnGround())){
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0));

			}
		}
		}
			
		

		
		
		
		if (plugin.Freezer.contains(p.getName())) {

		
			p.teleport(p.getLocation());

			p.getLocation().getBlock().setType(Material.WEB);

			Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin,

					new Runnable() {

						@Override
						public void run() {

							p.getLocation().getBlock().setType(Material.AIR);
							plugin.Freezer.remove(p.getName());

							
						}
					}, 60L);
		}
	}

	
	@EventHandler
	public void Explosion(PlayerDeathEvent e) {
	
		
		
		if (e.getEntity() instanceof Player) {

			e.setDroppedExp(0);
			e.getDrops().clear();
			Player p = e.getEntity();
			if (p.getKiller() != null){
			e.setDeathMessage(ChatColor.GREEN + p.getKiller().getName()
					+ ChatColor.WHITE + " Killed " + ChatColor.RED
					+ p.getName());
			ItemStack Button = new ItemStack(Material.WOOD_BUTTON);
			ItemMeta meta = Button.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Creeper's Button");
			Button.setItemMeta(meta);
			if (p.getInventory().contains(Button)) {
				p.getWorld().createExplosion(p.getLocation().getX(),
						p.getLocation().getY(), p.getLocation().getZ(), 3F,
						false, false);

			}
			}
		}
	}

	
	public void fishPlayer(final Player p, Player k){
		
		p.teleport(k.getLocation());
		fisher.add(p.getName());
		p.sendMessage(plugin.prefixa + ChatColor.RED+"Youv'e been fished by a Fisherman, you can hit him for 3 seconds.");
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				fisher.remove(p.getName());
				p.sendMessage(ChatColor.GREEN+"You can hit the Fisherman who Fished you now");
			}
		}, 60L);
	}
	@EventHandler
	public void fisher(PlayerFishEvent e){
		if (e.getCaught() instanceof Player){
		Player p = e.getPlayer();
		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Fisherman Fishing rod"))


			fishPlayer(((Player)e.getCaught()), p);
		
		if (e.getCaught().isDead()){

			fisherman.put((Player) e.getCaught(), p);
		}
		}
		 }
	
	
	
	@EventHandler
	public void entityInteract(PlayerInteractEntityEvent e){
		if (e.getRightClicked() instanceof Player && e.getRightClicked() instanceof Player){
			Player p = e.getPlayer();
			Player k = (Player) e.getRightClicked();
			Teams t = Teams.getInstance();
			if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getItemMeta() != null && p.getInventory().getHelmet().getItemMeta().getDisplayName() != null){
			if (p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("Hulk Helmet")){
			if (t.isinTeam(p) && t.isinTeam(k)){
				if(t.getTeam(p).equals(t.getTeam(k))){
					e.setCancelled(true);
				}
			}
				PlayerCooldown pc = Cooldown.getCooldown("DWAawaa",
						p.getName());
				if (pc == null) {
				p.setPassenger(k);

					Cooldown.addCooldown("DWAawaa", p.getName(), 5000);
				} else {
					final int Time = pc.getTimeLeft() / 50000;
					if (pc.isOver()) {
				p.setPassenger(k);
						pc.reset();
					} else if (Time > 0) {
						p.sendMessage("§cYour cooldown will over in "
								+ Time + " seconds!");
					}
				}
			
			}
			}
		}
	}
	@EventHandler(ignoreCancelled = false)
	public void HeadShot(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball ){

			Snowball s = (Snowball) e.getDamager();
			Player p = (Player) e.getEntity();
			Player sa = (Player) s.getShooter();

			Teams t = Teams.getInstance();
			if (sa.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Gangsta Pistol")){
				if (t.isinTeam(p) && t.isinTeam(sa)){

				if (t.getTeam(p).equals(t.getTeam(sa))){

					e.setCancelled(true);

					return;
				}
				}else{

			

				if (p.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE){

	    			e.setDamage(7);	

				}else if (p.getInventory().getChestplate() == null){
					e.setDamage(15);
				}else if (p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE){
				e.setDamage(8);	
				}else if (p.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE){
	    			e.setDamage(6);	

				}else if (p.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE){
	    			e.setDamage(4);	
	    			}
				if (p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE){
	    			e.setDamage(3);	
	    			}
				}
			}
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player k = (Player) e.getDamager();
	
		if (fisher.contains(k.getName())){
			e.setCancelled(true);
			
		}
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Fish){
			Player p = (Player) e.getEntity();
			Fish f = (Fish) e.getDamager();
			Player k = (Player) f.getShooter();
			if (k.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Fisher Fishing Rod")){
				fishPlayer(p, k);
			
			}
				
			}
	
	
		
	}

	@EventHandler
	public void entity(EntityDamageByEntityEvent e){
		if (e.getCause() != DamageCause.PROJECTILE)
			return;
		Projectile proj = (Projectile) e.getDamager();
		if (!(proj.getShooter() instanceof Player))
			return;
		org.bukkit.entity.Entity shot = e.getEntity();
		double y = proj.getLocation().getY();
		double shoty = shot.getLocation().getY();
		boolean headshot = y - shoty > 1.35d;
		if (headshot) {
			if (proj.getShooter() instanceof Player) {
				if (shot instanceof Player) {
					Player D = (Player) proj.getShooter();
					if (D.getInventory().getHelmet() != null) {
							e.setDamage((e.getDamage() * 1.4));
							D.playSound(D.getLocation(), Sound.NOTE_PLING, 5, 1);
						
					}
				}
			}
		}
	}
	@EventHandler
	public void rabbit (PlayerInteractEvent e){
		Player p = e.getPlayer();

		if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getItemMeta() != null && p.getInventory().getHelmet().getItemMeta().getDisplayName() != null){

	if (p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("Hulk Helmet")){
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){

			
				Vector va = p.getLocation().getDirection().multiply(0.5);
				if(p.getPassenger() != null){
				p.getPassenger().setVelocity(va);
				p.getPassenger().leaveVehicle();
				}

		}
		}
	}
		if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){

		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Super Carrot")){

		
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){

				Damageable pa = (Damageable) p;

				if (pa.getHealth() == 20){
					
				}else{
		pa.setHealth(pa.getHealth() + 4);
				}
	
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));


		if (p.getItemInHand().getAmount() < 0){
			p.getItemInHand().setType(null);

		}else{
		p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
		}
			}
		}
		}
	}
		
	@EventHandler
	public void ArmorChange(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE) {
				if (e.getCurrentItem() != null) {
					if (e.getSlotType() == SlotType.ARMOR
							&& !e.getWhoClicked().isOp()) {
						e.setCancelled(true);
						Player p = (Player) e.getWhoClicked();
						e.setCancelled(true);
						p.playSound(p.getLocation(), Sound.BURP, 5, 1);
					}
				}
			}
		}
	}

	


	@EventHandler
	public void damage(EntityDamageEvent e){
		if (e.getEntity() instanceof Player){
		Player p = (Player) e.getEntity();
		if (e.getCause().equals(DamageCause.FALL)){
		if (plugin.NoFall.contains(p.getName())) {

			e.setCancelled(true);
			plugin.NoFall.remove(p.getName());
		}
		}
		}
	}
	@EventHandler
	public void Entity(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player){
		
			Player p = (Player) e.getEntity();
			if (e.getCause() == DamageCause.FALL) {
			
			}
		if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getCause() == DamageCause.FALL)) return;
        Player Sumo = (Player) e.getEntity();
        double dmg = e.getDamage();
        if(Sumo.getInventory() != null && Sumo.getInventory().getHelmet() != null && Sumo.getInventory().getHelmet().getItemMeta() != null && Sumo.getInventory().getHelmet().getItemMeta().getDisplayName() != null){
        if(!Sumo.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("Smasher Helmet")) return;
        if(e.getDamage() >= 8){
        e.setDamage(Sumo.getFallDistance() / 8);
        for(org.bukkit.entity.Entity en : Sumo.getNearbyEntities(6, 5, 6)){
        if(!(en instanceof Player)) continue;
        Player a = (Player) en;
        if(a == Sumo) continue;
        if(a.isSneaking()){a.damage(4, Sumo);return;}
        a.damage(dmg / 2, Sumo);}
        }
        }
        
	


			ItemStack Bow = new ItemStack(Material.IRON_SWORD);
			ItemMeta meta3 = Bow.getItemMeta();
			meta3.setDisplayName(ChatColor.BLUE + "Warth Sword");
			Bow.setItemMeta(meta3);
			ItemStack Button = new ItemStack(Material.WOOD_BUTTON);
			ItemMeta meta = Button.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Creeper's Button");
			Button.setItemMeta(meta);
		

			if (plugin.Jump.contains(p.getName())) {
				if (e.getCause().equals(DamageCause.FALL)) {
					e.setCancelled(true);

					plugin.Jump.remove(p.getName());
					plugin.Charge.remove(p.getName());
					plugin.Count.remove(p.getName());
				}
			}
			if (p.getInventory().contains(Bow)) {

				if (e.getCause().equals(DamageCause.LIGHTNING)) {
					e.setCancelled(true);
				}
			}
			if (p.getInventory().contains(Button)) {
				if (e.getCause().equals(DamageCause.BLOCK_EXPLOSION)) {
					e.setCancelled(true);
				}
			}
		}
		}
	

	@EventHandler
	public void food(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void pick(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			
			if(d.getItemInHand() != null && d.getItemInHand().getItemMeta() != null && d.getItemInHand().getItemMeta().getDisplayName() != null){

				if (d.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.WHITE + "Spider Sword")) {

					

				Random r = new Random();

				int msg;

				for (int counter = 1; counter <= 1; counter++) {

					
					msg = 1 + r.nextInt(10); // 10 == the max int of the msg
												// if(msg == 5){ r.nextInt(5);

					if (msg == 1) {

						plugin.Freezer.add(p.getName());

					}
				}
			}
			}
		
	
			if(d.getItemInHand() != null && d.getItemInHand().getItemMeta() != null && d.getItemInHand().getItemMeta().getDisplayName() != null){
			if (d.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Warth Sword")) {

				d.setLevel(d.getLevel() + 10);

			}
			}
		}
	
					
					

		
	}

	
	
	@EventHandler
	public void interact(PlayerInteractEvent e) {

		Player p = e.getPlayer();
    	ItemStack PistolAmmo = new ItemStack(Material.SEEDS, 1);
		PlayerCooldown pca= Cooldown.getCooldown("Bananaa", p.getName());


    	
    	if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){

    		
			if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){

    			if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Groopy Wand")){

    				for (org.bukkit.entity.Entity en : p.getNearbyEntities(10, 10, 10)){

    					if (en instanceof Player){
    						if(pca == null){
    						Player pa = (Player) en;
    						Vector v = pa.getLocation().getDirection().multiply(3).setY(3.3);
    						pa.setVelocity(v);
    						if (!(plugin.NoFall.contains(pa.getName()))){

    							plugin.NoFall.add(pa.getName());
    						}
    						Cooldown.addCooldown("Bananaa", p.getName(), 10000);

    						}else{
    							final int Time = pca.getTimeLeft() / 1000;

    							if(pca.isOver()){

    								Player pa = (Player) en;
    	    						Vector v = pa.getLocation().getDirection().multiply(3).setY(3.3);
    	    						pa.setVelocity(v);
    	    						if (!(plugin.NoFall.contains(pa.getName()))){

    	    							plugin.NoFall.add(pa.getName());
    	    						}
    	    						pca.reset();
    							}else if (Time > 0) {
    								p.sendMessage("§cYour cooldown will over in "
    										+ Time + " seconds!");
    							}
    						
    						}
    				}
    			}
    			}
    	
			}
			if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){

    	 if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Gangsta Pistol")){
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
			
		
			
		e.setCancelled(true);
			PlayerCooldown pc = Cooldown.getCooldown("Ninja", p.getName());
			if(pc == null){
				if (!(p.getInventory().contains(PistolAmmo))){
					return;
				}else{
			
		Snowball s = p.throwSnowball();
		s.setFallDistance(200);
		s.setVelocity(s.getVelocity().multiply(2));
		p.getInventory().removeItem(PistolAmmo);
		p.updateInventory();
		Cooldown.addCooldown("Ninja", p.getName(), 1000);
		p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 10, 10);
		
				}
			}else{
				if(pc.isOver()){
					if (p.getInventory().contains(PistolAmmo)){
					Snowball s = p.throwSnowball();
	    			s.setFallDistance(200);
	    			s.setVelocity(s.getVelocity().multiply(2));
	    			p.getInventory().removeItem(PistolAmmo);
	    			p.updateInventory();
	    			p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 10, 10);

	    			pc.reset();
					}
				
			
				}
	
			}
	
	
    	 }
		}
    	}
	
	}
	@EventHandler
	public void strike(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){

		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Warth Sword")) {
			
		

			
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)

			if (p.getLevel() == 100 || p.getLevel() > 100) {

				p.setLevel(p.getLevel() - 100);

				p.getWorld().strikeLightning(p.getTargetBlock(null, 10).getLocation());
			}
		
		}
		}
	}
	@EventHandler
	public void einteract(PlayerInteractEntityEvent e){
		if (e.getRightClicked() instanceof Player){
			final Player p = e.getPlayer();
			final Player k = (Player) e.getRightClicked();
			final ItemStack jack = new ItemStack(Material.JACK_O_LANTERN, 1);
			if(p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){
			if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Troller Pumpkim")){
				PlayerCooldown pc = Cooldown.getCooldown("troller", p.getName());
				if (pc == null){
				plugin.troller.put(k.getName(), k.getInventory().getHelmet());
				k.getInventory().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
				k.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
				Cooldown.addCooldown("troller", p.getName(), 15000);
				}else{
					final int Time = pc.getTimeLeft();
					if (pc.isOver()){
						plugin.troller.put(k.getName(), k.getInventory().getHelmet());
						k.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
					
						k.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
						pc.reset();

					}else if (Time > 0 ){
						p.sendMessage("§cYour cooldown will over in " + Time
								+ " seconds!");
					}
				}
				
				Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						ItemStack Helmet = plugin.troller.get(k.getName());
						k.getInventory().setHelmet(Helmet);
						p.getInventory().removeItem(jack);
						
					}
				}, 100);
			}
			}
		}
	}
	@EventHandler
	public void regen(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			ItemStack Bow = new ItemStack(Material.IRON_HELMET);
			ItemMeta meta3 = Bow.getItemMeta();
			meta3.setDisplayName(ChatColor.DARK_PURPLE + "Vampire Helmet");
			Bow.setItemMeta(meta3);
			Player p = (Player) e.getEntity();
			if (p.getInventory().getHelmet() == Bow) {
				e.setCancelled(false);
			}else{
			e.setCancelled(true);
			}
			}
	}

	@EventHandler
	public void Interact(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){

		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Creeper's Button")) {
		


	if (e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			PlayerCooldown pc = Cooldown.getCooldown("M4", p.getName());
			try{
			if (pc == null) {

				p.getWorld().createExplosion(p.getLocation().getX(),
						p.getLocation().getY(), p.getLocation().getZ(), 3F,
						false, false);
				Cooldown.addCooldown("M4", p.getName(), 15000);
				

			} else {
				final int Time = pc.getTimeLeft() / 1000;

				if (pc.isOver()) {
					p.getWorld().createExplosion(p.getLocation().getX(),
							p.getLocation().getY(), p.getLocation().getZ(), 3F,
							false, false);

					pc.reset();
				
				} else if (Time > 0) {
					p.sendMessage("§cYour cooldown will over in " + Time
							+ " seconds!");
				}
			}
			}catch (NullPointerException e2) {
			}
	}
		}
			}

		}
	

	@EventHandler
	public void BlockPlace1(BlockBreakEvent e) {
		if (plugin.hasPermission(e.getPlayer(), "royalzone.block.break")) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void ping(ServerListPingEvent e) {
		if (Bukkit.hasWhitelist()) {
			e.setMotd(ChatColor.RED + "Royal" + ChatColor.GREEN + "Zone - "
					+ ChatColor.RED + "Maintance !");
		} else {
			e.setMotd("§c§lRoyal§a§lZone - " + ChatColor.LIGHT_PURPLE + "§ka"
					+ "§d§lOpen !" + "§ka");
		}
	}

	@EventHandler
	public void WheaterChange(WeatherChangeEvent e) {
		
		e.setCancelled(true);
	}

	@EventHandler
	public void BlockPlace(BlockPlaceEvent e) {
		if (e.getPlayer().isOp()) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	
	@EventHandler
	public void OnProjectile(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getEntity();

			Player Shooter = (Player) arrow.getShooter();
			if (Shooter.getItemInHand() != null
					&& Shooter.getItemInHand().getItemMeta() != null
					&& Shooter.getItemInHand().getItemMeta().getDisplayName() != null){
			if (Shooter.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "EnderBow")) {
				PlayerCooldown pc = Cooldown.getCooldown("Arrow",
						Shooter.getName());

				if (pc == null) {
					Shooter.teleport(arrow.getLocation());
					Cooldown.addCooldown("Arrow", Shooter.getName(), 10000);
				} else {
					final int Time = pc.getTimeLeft() / 1000;

					if (pc.isOver()) {
						Shooter.teleport(arrow.getLocation());
						pc.reset();
					} else if (Time > 0) {
						Shooter.sendMessage("§cYour cooldown will over in "
								+ Time + " seconds!");
					}
				}
			}
			}
			

		}
	}

}
