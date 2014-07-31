package me.or.royals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.confuser.barapi.BarAPI;
import me.or.royals.Grappler.Grappler;
import me.or.royals.event.API;
import me.or.royals.event.GameStatus;
import me.or.royals.event.Gui;
import me.or.royals.event.LMS;
import me.or.royals.event.Listeners;
import me.or.royals.event.OITC;
import me.or.royals.event.Parkour;
import me.or.royals.event.TF;
import me.or.royals.ovo.OneStatus;
import me.or.royals.ovo.OneVsOneTwo;
import me.or.royals.ovo.OvOManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
// lol lo meshane, toda bhol mikre :)
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {
	Inventory Main = Bukkit.createInventory(null, 9, ChatColor.GREEN
			+ "Main GUI");
	// eim ata roshem "//" az lo,vfuck ani tzarih lotzi et a kalba sheli lol brb
	// Eclipse, and one sec i think my mom arrived nvm its just my dog
	Inventory VIP = Bukkit.createInventory(null, 9, ChatColor.AQUA + "VIPKits");
	Inventory Shop = Bukkit.createInventory(null, 9, ChatColor.RED
			+ "Shop Kits");
	public LMS l;

	public OITC oa1;
	
	public Parkour parkour;
	ArrayList<String> vanish  = new ArrayList<String>();
	public static int currentLine = 0;
	SettingsManager instance = SettingsManager.getInstance();
	WarpsManager settings = WarpsManager.getInstance();
	BanManager bm = BanManager.getInstance();
	OnevOneManager o = OnevOneManager.getInstance();

	ArrayList<String> spy = new ArrayList<String>();
	AchManager am = AchManager.getInstance();

	OnevOne oa = OnevOne.getInstance();
	ArrayList<String> Blue = new ArrayList<String>();
	ArrayList<String> Red = new ArrayList<String>();
	Inventory TeamShop = Bukkit.createInventory(null, 9, ChatColor.GREEN
			+ "TeamShop");

	public TF tf;
	TeamManager tm = TeamManager.getInstance();
	Teams ta = new Teams();
	TeamList tl = TeamList.getInstance();
	// slots, Colored tag, Change tag
	Inventory colors = Bukkit.createInventory(null, 9, "Colors");

	Inventory slots = Bukkit.createInventory(null, 9, "Team SLots");
	AntiCurse ac = AntiCurse.getInstance();

	Gui g = Gui.getInstance();

	API api = API.getInstance();
	OneVsOneTwo ovo;

	
	ArrayList<String> fly = new ArrayList<String>();
	ReportManager rm = ReportManager.getInstance();
	ArrayList<String> teleport = new ArrayList<String>();
	ArrayList<String> muted = new ArrayList<String>();
	Map<Player, String> invited = new HashMap<Player, String>();
	Inventory Youtuber = Bukkit.createInventory(null, 9, ChatColor.BLACK
			+ "You" + ChatColor.RED + "Tuber");
	public static String prefix = ChatColor.GRAY + "[" + ChatColor.RED
			+ "Royal" + ChatColor.GREEN + "Zone" + ChatColor.GRAY + "] ";
	public String prefixa = ChatColor.GRAY + "[" + ChatColor.RED + "Royal"
			+ ChatColor.GREEN + "Zone" + ChatColor.GRAY + "] ";
	public String teamprefix = ChatColor.GRAY + "[" + ChatColor.RED + "Royal"
			+ ChatColor.GREEN + "Teams" + ChatColor.GRAY + "] ";
	ArrayList<String> Count = new ArrayList<String>();
	Inventory sShop = Bukkit.createInventory(null, 27, ChatColor.RED + "Shop");
	ArrayList<String> Charge = new ArrayList<String>();
	ArrayList<String> Freezer = new ArrayList<String>();
	OvOManager ovom = OvOManager.getInstance();
	
	ArrayList<String> Jump = new ArrayList<String>();
	Inventory kcg = Bukkit.createInventory(null, 36);
	public Map<String, ItemStack> troller = new HashMap<>();
	ArrayList<String> NoFall = new ArrayList<String>();
	Inventory Regular = Bukkit.createInventory(null, 9, ChatColor.RED
			+ "RegularKits");
	ArrayList<String> logged = new ArrayList<String>();
	BYKManager byk = BYKManager.getInstance();
	ArrayList<String> kangaroo = new ArrayList<String>();

	public void startTF(){
		final String prefixaa = tf.prefix;
		tf.status = GameStatus.COUNTDOWN;
		Bukkit.broadcastMessage(prefixaa + "An TF event is starting more 30 Seconds, /join to join the Event");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int count = 30;
			@Override
			public void run() {
				
				
				

				count--;
				if(count == 15){
					Bukkit.broadcastMessage(prefixaa + " TF Event Starting more 15 Seconds, /join the join the Event");
					
				}else if (count <= 5){
					Bukkit.broadcastMessage(prefixaa + " TF Event Starting more " + count + " , /join the join the Event");
				}
				if(count == 0){
					if(tf.players.size() <= 1){
						Bukkit.broadcastMessage(prefixaa + " TF Event Has Canclled Becouse the amount of players for the LMS Event is too low");
					Bukkit.getScheduler().cancelAllTasks();
					tf.status = GameStatus.WAITING;
						return;
					}
					Bukkit.getScheduler().cancelAllTasks();
					tf.status = GameStatus.INGAME;

					tf.start();

				}
				
			}
		}, 0, 20);

	}
	public void startParkour(){
		final String prefixaa = parkour.prefix;
		parkour.status = GameStatus.COUNTDOWN;
		Bukkit.broadcastMessage(prefixaa + "An parkour event is starting more 30 Seconds, /join to join the Event");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int count = 30;
			@Override
			public void run() {
				
				
				

				count--;
				if(count == 15){
					Bukkit.broadcastMessage(prefixaa + " parkour Event Starting more 15 Seconds, /join the join the Event");
					
				}else if (count <= 5){
					Bukkit.broadcastMessage(prefixaa + " parkour Event Starting more " + count + " , /join the join the Event");
				}
				if(count == 0){
					if(parkour.getPlayers().size() <= 1){
						Bukkit.broadcastMessage(prefixaa + " parkour Event Has Canclled Becouse the amount of players for the LMS Event is too low");
					Bukkit.getScheduler().cancelAllTasks();
					parkour.status = GameStatus.WAITING;
						return;
					}
					Bukkit.getScheduler().cancelAllTasks();
					parkour.status = GameStatus.INGAME;

					parkour.start();

				}
				
			}
		}, 0, 20);

	}
	
	public void startOOITC(){
		final String prefixaa = oa1.prefix;
		oa1.status = GameStatus.COUNTDOWN;
		Bukkit.broadcastMessage(prefixaa + "An OITC event is starting more 30 Seconds, /join to join the Event");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int count = 30;
			@Override
			public void run() {

				count--;
				if(count == 15){
					Bukkit.broadcastMessage(prefixaa + " OITC Event Starting more 15 Seconds, /join the join the Event");
					
				}else if (count <= 5){
					Bukkit.broadcastMessage(prefixaa + " OITC Event Starting more " + count + " , /join the join the Event");
				}
				if(count == 0){
					if(oa1.players.size() <= 1){
						Bukkit.broadcastMessage(prefixaa + " OITC Event Has Canclled Becouse the amount of players for the LMS Event is too low");
					Bukkit.getScheduler().cancelAllTasks();
					oa1.status = GameStatus.WAITING;
						return;
					}
					Bukkit.getScheduler().cancelAllTasks();
					oa1.status = GameStatus.INGAME;

					oa1.startOITC();

				}
				
			}
		}, 0, 20);

	}
	public void start(){
		final String prefixaa = ChatColor.BLUE+"§lLMS>";

		l.status = GameStatus.COUNTDOWN;
		l.started = true;
		Bukkit.broadcastMessage(prefixaa + "An LMS event is starting more 30 Seconds, /join to join the Event");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int count = 30;
			@Override
			public void run() {

				count--;
				if(count == 15){
					Bukkit.broadcastMessage(prefixaa + " LMS Event Starting more 15 Seconds, /join the join the Event");
					
				}else if (count <= 5){
					Bukkit.broadcastMessage(prefixaa + " LMS Event Starting more " + count + " , /join the join the Event");
				}
				if(count == 0){

					if(tf.players.size() == 1){
						Bukkit.broadcastMessage(prefixaa + " LMS Event Has Canclled Becouse the amount of players for the LMS Event is too low");
						Bukkit.getScheduler().cancelAllTasks();

						l.status = GameStatus.WAITING;
						return;
					}
					l.StartLMS();

					l.status = GameStatus.INGAME;
					Bukkit.getScheduler().cancelAllTasks();
				}
				
				
			}
		}, 0, 20);

	}

	@Override
	public void onEnable() {
		tf = new TF(this);
ovom.setup(this);
ovom.saveData();
		new Cooldown();

	
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				try {
					broadcastMessage("plugins/RoyalZone/messages.txt");
				} catch (IOException e) {
					//
					e.printStackTrace();
				}
			}
		}, 0, 7500L);

		parkour = new Parkour(this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		getLogger().info(
				getName() + " version " + getDescription().getVersion()

				+ " has been enabled!");
		am.setup(this);
		am.saveData();
		byk.setup(this);
		byk.saveData();
		instance.setup(this);
		o.setup(this);
		o.saveData();
		settings.setup(this);
		ac.setup(this);
		bm.setup(this);
		bm.saveData();
		instance.saveData();
		settings.saveData();
		tl.setup(this);
		tm.setup(this);
		List<String> l = new ArrayList<String>();
		tl.getData().set("Teams", l);
		tm.saveData();
		tl.saveData();
		rm.setup(this);
		rm.saveData();
		ac.setup(this);
		ac.saveData();
		api.setUpInventory();

		if (ac.getData().getStringList("badwords").contains("Bitch")) {

		} else {
			List<String> perms = ac.getData().getStringList("badwords");
			perms.add("Bitch");
			ac.getData().set("badwords", perms);
			ac.saveData();
		}
this.l = new LMS(this);
this.oa1 = new OITC(this);
		setUpTeamInventory();
		SetupInventories();
		setUpShop();
		ovo = new OneVsOneTwo();

		getServer().getPluginManager().registerEvents(this.oa1, this);
		getServer().getPluginManager().registerEvents(this.l, this);

		getServer().getPluginManager().registerEvents(new Listeners(this), this);

		getServer().getPluginManager().registerEvents(new Grappler(), this);
		getServer().getPluginManager().registerEvents(new BYK(this), this);

		getServer().getPluginManager().registerEvents(new OnevOne(this), this);
		getServer().getPluginManager().registerEvents(new TeamShop(this), this);
		getServer().getPluginManager().registerEvents(new CoinsSystem(this),
				this);
		getServer().getPluginManager().registerEvents(new KillStreaks(this),
				this);
		getServer().getPluginManager().registerEvents(new KitsGUIClick(this),
				this);
		getServer().getPluginManager().registerEvents(new KitsListener(this),
				this);
		getServer().getPluginManager().registerEvents(this.tf, this);
		getServer().getPluginManager().registerEvents(this.parkour, this);

		getServer().getPluginManager().registerEvents(this, this);
		

	}

	@Override
	public void onDisable() {
	
		getLogger().info(
				getName() + " version " + getDescription().getVersion()

				+ " has been disabled!");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd,
			final String[] args) {


	

			if(cmd.equalsIgnoreCase("setvip")){
				if (sender instanceof Player){
					sender.sendMessage(ChatColor.RED+"You are'nt the console !");
					return true;
				}

				if(args.length < 1){
				sender.sendMessage("usage: /setvip name");
				return true;
				}
				Player p = Bukkit.getPlayer(args[0]);
					addPermissions(p, "royalzone.vip.all");
					addPermissions(p, "RoyalZone.kits.kangaroo");
					addPermissions(p, "RoyalZone.kits.grappler");
					addPermissions(p, "RoyalZone.kits.warth");
					addPermissions(p, "RoyalZone.kits.ghast");
					addPermissions(p, "RoyalZone.kits.vampire");
					addPermissions(p, "RoyalZone.kits.gangsta");
					addPermissions(p, "RoyalZone.kits.groopy");
					addPermissions(p, "RoyalZone.kits.troller");
					addPermissions(p, "RoyalZone.kits.rabbit");
				
				

				
				}
			if (cmd.equalsIgnoreCase("royalgive")) {
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.RED+"You are'nt the console !");
					return true;
				}

				Player CheckT = getServer().getPlayer(args[0]);
				if(CheckT == null){
					sender.sendMessage("couldnot find player");
					return true;
				}
				int CreditsGive = Integer.valueOf(args[1]);
				if (CheckT != null) {
					int CheckTC = getConfig().getInt(
							CheckT.getUniqueId().toString() + ".coins");
					sender.sendMessage("§eYou gave §6" + CreditsGive
							+ " §ecoins to §6" + CheckT.getName());
					if (CheckT.isOnline()) {
						CheckT.sendMessage("§6§lAdmin gave you some.coins. enjoy!");
					}
					getConfig().set(CheckT.getUniqueId().toString() + ".coins",
							CheckTC + CreditsGive);
					saveConfig();
				}

			}

			if (cmd.equalsIgnoreCase("addpermissionsa")) {
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.RED+"You are'nt the console !");
					return true;
				}

				if (args.length < 1) {
					sender.sendMessage(ChatColor.RED
							+ "Please Specify a Player");
					return true;
				}

				OfflinePlayer paa = getServer().getOfflinePlayer(args[0]);
				Bukkit.broadcastMessage(instance
						.getData()
						.getStringList(paa.getPlayer().getUniqueId().toString())
						.toString());
				List<String> perms = instance.getData().getStringList(
						paa.getPlayer().getUniqueId().toString());
				perms.add(args[1]);
				instance.getData().set(
						paa.getPlayer().getUniqueId().toString(), perms);
			}

		
		

		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (cmd.equalsIgnoreCase("spy") && hasPermission(p, "royalzone.admin.spy")){
			
				if (spy.contains(p.getName())){
					spy.remove(p.getName());
					p.sendMessage(prefix + "Spy Mod has been Disabled");
					return true;
				}
				spy.add(p.getName());
				p.sendMessage(prefix + " Spy Mod has been Enabled");
			}
			if(cmd.equalsIgnoreCase("me")){
				return true;
			}
			if (cmd.equalsIgnoreCase("fly") && hasPermission(p, "royalzone.admin.fly")){
				if (fly.contains(p.getName())){
					p.setAllowFlight(false);
					p.sendMessage(prefix + ChatColor.GREEN+"You are no longer flying");
					fly.remove(p.getName());
					return true;
				}
				fly.add(p.getName());
				p.setAllowFlight(true);
				p.sendMessage(prefix + ChatColor.GREEN+"Your are now flying");
			}
			if (cmd.equalsIgnoreCase("inv") && hasPermission(p, "royalzone.admin.inv")){
				if (args.length < 1){
					p.sendMessage(prefix + ChatColor.RED+"usage: inv <nick> ");
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				if ( t== null){
					p.sendMessage(prefix + ChatColor.RED+"Couldnot find player");
					return true;
				}
				p.openInventory(t.getInventory());
			}
			if (cmd.equalsIgnoreCase("tphere") && hasPermission(p, "royalzone.admin.tphere")){
				if (args.length < 1){
					p.sendMessage(prefix + ChatColor.RED+"usage: tphere <Nick>");
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				if ( t== null){
					p.sendMessage(prefix + "Couldnot find player");
				}
				t.teleport(p.getLocation());
				p.sendMessage(ChatColor.GREEN+"Succesufly tpped " + t.getName() + " to you");
				
			}
			if(cmd.equalsIgnoreCase("tp") && hasPermission(p, "royalzone.admin.tp")){
				if (args.length < 1){
					p.sendMessage(prefix + ChatColor.RED+"usage: Tp <Nick>");
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				if (t == null){
					p.sendMessage(prefix + ChatColor.RED+"Couldnot find player");
					return true;
				}
				p.sendMessage(prefix + ChatColor.GREEN+"Succsefuly tpped to " + t.getName());
				p.teleport(t.getLocation());
				
			}
			if(cmd.equalsIgnoreCase("stats")){
				if(args.length < 1){
				p.sendMessage(ChatColor.GREEN + "Kills: " + getConfig().getInt(p.getUniqueId().toString() + ".kills"));

				p.sendMessage(ChatColor.GREEN + "Deaths: " + getConfig().getInt(p.getUniqueId().toString() + ".deaths"));
			int kills = getConfig().getInt(p.getUniqueId().toString()+".kills");
			int death = getConfig().getInt(p.getUniqueId().toString() + ".deaths");
			p.sendMessage(ChatColor.GREEN + "Coins: " + getCoins(p));

			if(kills == 0 || death == 0){
				p.sendMessage("could not convert KD, your KD is 0");
				return true;
			}
				int i = getConfig().getInt(p.getUniqueId().toString() +".kills") / getConfig().getInt(p.getUniqueId().toString() + ".deaths");

				p.sendMessage(ChatColor.GREEN + "K/D Ratio: " + i);

			
				
				}
				
				
					
					
				}
					
				
				
			if(cmd.equalsIgnoreCase("join")){
			
			
				
				if(Blue.contains(p.getName()) || Red.contains(p.getName())){
					p.sendMessage(prefix + ChatColor.BLUE+"§lyou cant join while in 1v1, /1v1 leave to leave the 1v1");
				}
				if (args.length < 1){
				
					p.sendMessage(ChatColor.GREEN+"usage: /join LMS, OITC, TF, Parkour");
				
				}else if (args[0].equalsIgnoreCase("LMS")){
					if(l.players.contains(p.getName())){
						p.sendMessage(l.prefix + " You are already in the LMS Event !");
						return true;
					}
					if (l.getStatus() == GameStatus.INGAME){
						p.sendMessage(prefix + ChatColor.RED+"There's already event right now");
						return true;
						
					}
					if(l.getStatus() == GameStatus.WAITING){
						p.sendMessage(prefix + ChatColor.RED+"There isn't a Event right now");
						return true;
					}
					if(l.getStatus() == GameStatus.COUNTDOWN){
						
						
						l.players.add(p.getName());
						Bukkit.broadcastMessage(l.prefix + p.getName() + " Has joined the LMS Event");
						p.sendMessage(l.prefix + " Succesfuly joined the LMS Event");
						
					}
				}else if (args[0].equalsIgnoreCase("TF")){
					if(tf.players.contains(p.getName())){
						p.sendMessage(tf.prefix + " You are already in the TF Event !");
						return true;
					}
					if (tf.getStatus() == GameStatus.INGAME){
						p.sendMessage(prefix + ChatColor.RED+"There's already event right now");
						return true;
						
					}

					if(tf.getStatus() == GameStatus.WAITING){
						p.sendMessage(prefix + ChatColor.RED+"There isn't a Event right now");
						return true;
					}
					if(tf.getStatus() == GameStatus.COUNTDOWN){

						tf.players.add(p.getName());

						Bukkit.broadcastMessage(tf.prefix + p.getName() + " Has joined the TF Event");

						p.sendMessage(tf.prefix + " Succesfuly joined the TF Event");
						

					}
				

				}else if (args[0].equalsIgnoreCase("OITC")){
					if(oa1.players.contains(p.getName())){
						p.sendMessage(oa1.prefix + " You are already in the OITC Event !");
						return true;
					}
					if (oa1.getStatus() == GameStatus.INGAME){
						p.sendMessage(prefix + ChatColor.RED+"There's already event right now");
						return true;
						
					}

					if(oa1.getStatus() == GameStatus.WAITING){
						p.sendMessage(prefix + ChatColor.RED+"There isn't a Event right now");
						return true;
					}
					if(oa1.getStatus() == GameStatus.COUNTDOWN){

						oa1.players.add(p.getName());

						Bukkit.broadcastMessage(oa1.prefix + p.getName() + " Has joined the OITC Event");

						p.sendMessage(oa1.prefix + " Succesfuly joined the OITC Event");
						

					}
			
			
			}else if (args[0].equalsIgnoreCase("Parkour")){
				if(parkour.getPlayers().contains(p.getName())){
					p.sendMessage(parkour.prefix + " You are already in the Parkour Event !");
					return true;
				}
				if (parkour.getStatus() == GameStatus.INGAME){
					p.sendMessage(prefix + ChatColor.RED+"There's already event right now");
					return true;
					
				}
				if(parkour.getStatus() == GameStatus.WAITING){
					p.sendMessage(prefix + ChatColor.RED+"There isn't a Event right now");
					return true;
				}
				if(parkour.getStatus() == GameStatus.COUNTDOWN){
					
					
					parkour.getPlayers().add(p.getName());
					Bukkit.broadcastMessage(parkour.prefix + p.getName() + " Has joined the Parkour Event");
					p.sendMessage(parkour.prefix + " Succesfuly joined the Parkour Event");
					
				}
			}
			}
			if (cmd.equalsIgnoreCase("vanish") && hasPermission(p, "royalzone.admin.vanish")){
				if (vanish.contains(p.getName())){
					vanish.remove(p.getName());
					p.sendMessage(ChatColor.GREEN+"You are no longer vanished");
					for(Player pa: Bukkit.getOnlinePlayers()){
						pa.showPlayer(p);

					}
					Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Join> " + p.getName());

					
					
					return true;
				}
				vanish.add(p.getName());
				for(Player pa : Bukkit.getOnlinePlayers()){
					pa.hidePlayer(p);
				}
				p.sendMessage(prefix + ChatColor.GREEN+"You are now Vanished");

				Bukkit.broadcastMessage(ChatColor.RED + "Quit> " + p.getName());

			}

			
			if (cmd.equalsIgnoreCase("report")) {
				if (args.length < 1) {

					p.sendMessage(prefix
							+ "usage: /report <Report>/ /report Player <Player> <Report>");
					return true;
				}
				if (!(args[0].equalsIgnoreCase("Player"))) {
					if (args.length < 1) {
						p.sendMessage(prefix + "usage: /report <Report>");

					}
					rm.getData().set(p.getName() + ".uuid",
							p.getUniqueId().toString());
					List<String> reports = rm.getData().getStringList(
							p.getName() + ".reports");
					reports.add(args[0]);
					rm.getData().set(p.getName() + ".reports", reports);
					p.sendMessage(prefix
							+ ChatColor.GREEN
							+ "Thanks for the Report, the Report has been Sent.");
					rm.saveData();
					return true;

				} else if (args[0].equalsIgnoreCase("Player")) {
					rm.getData().set(p.getName() + ".uuid",
							p.getUniqueId().toString());
					List<String> reports = rm.getData().getStringList(
							p.getName() + ".playerreports");

					reports.add(args[2]);
					rm.getData().set(
							p.getName() + ".playerreports"
									+ Bukkit.getPlayer(args[1]).getName(),
							reports);

					p.sendMessage(prefix
							+ ChatColor.GREEN
							+ "Thanks for the Report, the Report has been Sent.");
					rm.saveData();
				}
			}
			if(cmd.equalsIgnoreCase("1v12")){
				final String ovoprefix = (ChatColor.DARK_PURPLE+"§l1v1> " + ChatColor.RESET + ChatColor.BOLD);
				if(args.length < 1){
					p.sendMessage(ovoprefix + ChatColor.BLUE+"§lWellcome to our 1v1 Second arena System :), click the sign to join !");
				}else if (args[0].equalsIgnoreCase("yossijoinaba")){
				
					if(ovo.red.contains(p.getName()) || ovo.blue.contains(p.getName())){
						p.sendMessage(ovoprefix + "You are already in the arena");
						return true;
					}

					if (ovo.getStatus() == OneStatus.COUNTDOWN || ovo.getStatus() == OneStatus.STARTED){
						p.sendMessage(ovoprefix + " There's already a match on");
						return true;
					}
					if(ovo.getStatus() == OneStatus.WAITING){
						ovo.red.add(p.getName());
					p.sendMessage(ovoprefix + "You are Red !");
				
					p.teleport(ovo.getRedLounge());
					ovo.status = OneStatus.ONEPLAYER;
					}else if (ovo.getStatus() == OneStatus.ONEPLAYER){
						ovo.blue.add(p.getName());
						p.sendMessage(ovoprefix + " You are Blue !");
				
						p.teleport(ovo.getBlueLounge());
						if(ovo.blue.size() == 1 && ovo.red.size() == 1){
							final Player b = ovo.getBluePlayer();
							final Player r = ovo.getRedPlayer();
							b.sendMessage(ovoprefix + "You are Blue, you will fight 1v1 vs " + r.getName());
							r.sendMessage(ovoprefix + "You are Red, you will fight 1v1 vs " + b.getName());
							b.sendMessage(ovoprefix + "The 1v1 will start more 5 seconds");
							r.sendMessage(ovoprefix + "The 1v1 will start more 5 seconds");
							ovo.status = OneStatus.COUNTDOWN;

							Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
								
								int count = 5;
								@Override
								public void run() {
									count--;
									if(b != null && r != null){
										if(count == 0){
											ovo.setUpPlayer(r);
											ovo.setUpPlayer(b);
											r.teleport(ovo.getRed());
											b.teleport(ovo.getBlue());
											r.sendMessage(ovoprefix + "The Fight has Started !");
											b.sendMessage(ovoprefix + "The Fight has Started !");
											ovo.status = OneStatus.STARTED;

										}
									}
								}
							}, 0L, 20L);

						}
					}
					
				}else if (args[0].equalsIgnoreCase("leave")){
					if(ovo.blue.contains(p.getName()) || ovo.red.contains(p.getName())){
					ovo.status = OneStatus.WAITING;
					if(ovo.blue.size() > 0 && ovo.red.size() > 0){
					ovo.getBluePlayer().sendMessage(ovoprefix + "One of the players quit, the game has ended");
					ovo.getRedPlayer().sendMessage(ovoprefix + "One of the players quit, the game has ended");
					ovo.getRedPlayer().teleport(ovo.getLounge());
					ovo.getBluePlayer().teleport(ovo.getLounge());
					ovo.getBluePlayer().getInventory().clear();
					ovo.getBluePlayer().getInventory().setArmorContents(null);
					ovo.getRedPlayer().getInventory().clear();
					ovo.getRedPlayer().getInventory().setArmorContents(null);
					ovo.red.clear();
					ovo.blue.clear();
					}else if (ovo.blue.size() > 0 ){
						ovo.status = OneStatus.WAITING;
						ovo.getBluePlayer().teleport(ovo.getLounge());
						ovo.getBluePlayer().getInventory().clear();
						ovo.getBluePlayer().getInventory().setArmorContents(null);
						ovo.blue.clear();
					}else if (ovo.red.size() > 0){
						ovo.status = OneStatus.WAITING;

						ovo.getRedPlayer().getInventory().clear();
						ovo.getRedPlayer().getInventory().setArmorContents(null);
						ovo.getRedPlayer().teleport(ovo.getLounge());

						ovo.red.clear();
					}
					
					}else{
						p.sendMessage(ovoprefix + "You are'nt in the arena");
					}
				}else if (args[0].equalsIgnoreCase("set") && hasPermission(p, "royalzone.admin.ovotwo.set")){
					if (args.length < 2){
						p.sendMessage(prefix + "usage: 1v1 set red, redlounge, bluelounge, lounge, blue");
					}else if (args[1].equalsIgnoreCase("red")){
						ovom.getData().set("ovotwo.red.world", p.getWorld().getName());
						ovom.getData().set("ovotwo.red.x", p.getLocation().getBlockX());
						ovom.getData().set("ovotwo.red.y", p.getLocation().getBlockY());
						ovom.getData().set("ovotwo.red.z", p.getLocation().getBlockZ());


						ovom.saveData();

					}else if (args[1].equalsIgnoreCase("blue")){
						ovom.getData().set("ovotwo.blue.world", p.getWorld().getName());
						ovom.getData().set("ovotwo.blue.x", p.getLocation().getBlockX());
						ovom.getData().set("ovotwo.blue.y", p.getLocation().getBlockY());

						ovom.getData().set("ovotwo.blue.z", p.getLocation().getBlockZ());
						ovom.saveData();

					}else if (args[1].equalsIgnoreCase("redlounge")){
						ovom.getData().set("ovotwo.red.lounge.world", p.getWorld().getName());
						ovom.getData().set("ovotwo.red.lounge.x", p.getLocation().getBlockX());
						ovom.getData().set("ovotwo.red.lounge.y", p.getLocation().getBlockY());
						ovom.getData().set("ovotwo.red.lounge.z", p.getLocation().getBlockZ());
						ovom.saveData();

					}else if (args[1].equalsIgnoreCase("bluelounge")){
						ovom.getData().set("ovotwo.blue.lounge.world", p.getWorld().getName());
						ovom.getData().set("ovotwo.blue.lounge.x", p.getLocation().getBlockX());
						ovom.getData().set("ovotwo.blue.lounge.y", p.getLocation().getBlockY());
						ovom.getData().set("ovotwo.blue.lounge.z", p.getLocation().getBlockZ());
						ovom.saveData();

					}else if (args[1].equalsIgnoreCase("lounge")){
						ovom.getData().set("ovotwo.lounge.world", p.getWorld().getName());
						ovom.getData().set("ovotwo.lounge.x", p.getLocation().getBlockX());
						ovom.getData().set("ovotwo.lounge.y", p.getLocation().getBlockY());
						ovom.getData().set("ovotwo.lounge.z", p.getLocation().getBlockZ());
						ovom.saveData();
					}
				}
			}
			if(cmd.equalsIgnoreCase("help")){
				if (args.length < 1){
					p.sendMessage(prefix + ChatColor.GREEN+"Wellcome the the User Guide !");
					p.sendMessage(prefix + ChatColor.GREEN+"Lets start with the commands should we ?");
					p.sendMessage(ChatColor.GREEN+"/coins - show's you how much coins you have");
					p.sendMessage(ChatColor.GREEN+"/team - all information about the teams System");
					p.sendMessage(ChatColor.GREEN+"/report - Report a bug");
					p.sendMessage(ChatColor.GREEN+"/report <player> - Report a player");
					p.sendMessage(ChatColor.GREEN+"/helpme <Message> - Send's the message you wrote to all the admins are online.");

				
				} else if (args[0].equalsIgnoreCase("mod") && hasPermission(p, "royalzone.admin.help")){
					p.sendMessage(prefix + ChatColor.GREEN + "Wellcome to The Admin Guide");
					p.sendMessage(prefix + ChatColor.GREEN+"Lets start with the commands, should we ?");
					p.sendMessage(ChatColor.GREEN+"/kick - Kick a player");
					p.sendMessage(ChatColor.GREEN+"/ban - Ban a player");
					p.sendMessage(ChatColor.GREEN+"/fly - Give's you fly");
					p.sendMessage(ChatColor.GREEN+"/inv - Show's you an inventory of a player");
					p.sendMessage(ChatColor.GREEN+"/vanish - making you invisible");
					p.sendMessage(ChatColor.GREEN+"/mute - Mute a player");
					p.sendMessage(ChatColor.GREEN+"/tp - tp to a player");
					p.sendMessage(ChatColor.GREEN+"/tphere - tp a player to you");
					p.sendMessage(ChatColor.GREEN+"/unban -  unban a player");
					p.sendMessage(ChatColor.GREEN+"/broadcast -  broadcast a message");
					p.sendMessage(ChatColor.GREEN+"/cc -  to clear the chat");
					p.sendMessage(ChatColor.GREEN+"/event -  to open the EVents GUI");
					p.sendMessage(ChatColor.GREEN+"/stopevents -  to stop all the events");

				
				}
			}
		
			if(cmd.equalsIgnoreCase("stopEvents") && hasPermission(p, "royalzone.admin.stopevents")){
				Bukkit.getScheduler().cancelAllTasks();
				if(tf.players.size() >= 1){
					for(String s : tf.players){
						Bukkit.getPlayer(s).teleport(tf.getSpawn());
						
					}
					tf.players.clear();
					
					if(l.players.size() >= 1){
						for(String s : l.players)
					Bukkit.getPlayer(s).teleport(l.getSpawn());
					}
					l.players.clear();
					if(oa1.players.size() >= 1){
						for(String s : oa1.players){
							Bukkit.getPlayer(s).teleport(oa1.getSpawn());
						}
						oa1.players.clear();
					}
				}
				tf.status = GameStatus.WAITING;
				l.status = GameStatus.WAITING;
				oa1.status = GameStatus.WAITING;
				Bukkit.broadcastMessage(prefix + " All Events has Stopped !");
				p.sendMessage(prefix + "Succesfuly stopped all the events.");
				
			}
			if (cmd.equalsIgnoreCase("unban")
					&& hasPermission(p, "royalzone.admin.unban")) {
				if (args.length < 1) {
					p.sendMessage(ChatColor.DARK_RED
							+ "Usage: /unban <PlayerNick>");
					return true;
				}
				UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(args[0]));
				UUID of = null;
				try {
					of = fetcher.getUUIDOf(args[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String s = of.toString();
				bm.getData().set(s + ".banned", Boolean.valueOf(false));
				bm.getData().set(s + ".reason", " ");
				bm.saveData();
				Bukkit.getOfflinePlayer(args[0]).setBanned(false);

				bm.getData().set(Bukkit.getOfflinePlayer(args[0]) + ".banned",
						Boolean.valueOf(false));
				bm.saveData();
				bm.reloadData();
				p.sendMessage(prefix + ChatColor.GREEN + "unbanned");

			}
			if (cmd.equalsIgnoreCase("mute")
					&& hasPermission(p, "royalzone.admin.mute")) {
				if (args.length < 2) {
					p.sendMessage(prefix + ChatColor.DARK_RED
							+ "Usage:/mute <nickname> <time>");
					return true;
				}
				final Player t = Bukkit.getPlayer(args[0]);

				if(Integer.parseInt(args[1]) == 0){
					Bukkit.getPlayer(args[0]).sendMessage(
							prefix + ChatColor.RED
									+ "You Cant Talk now, Your'e muted");

					muted.add(t.getName());
					return true;
				}
				bm.getData().set(t.getUniqueId().toString()+".muted", args[1]);
				bm.saveData();
					muted.add(Bukkit.getPlayer(args[0]).getName());
					Bukkit.getPlayer(args[0]).sendMessage(
							prefix + ChatColor.RED
									+ "You Cant Talk now, Your'e muted");

					Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
						int i = Integer.parseInt(args[1]);

						@Override
						public void run() {
							// TODO Auto-generated method stub
							i--;
							bm.getData().set(t.getUniqueId().toString() + ".muted", i);
							if (i == 0){
								muted.remove(t.getName());
							
								t.sendMessage(prefix + "You are free to talk.");
							}
						}
					}, 0, 20);
			}
			if (cmd.equalsIgnoreCase("broadcast")
					&& hasPermission(p, "royalzone.admin.broadcast")) {
				if (args.length < 1) {
					p.sendMessage(prefixa + ChatColor.RED
							+ "Usage: /broadcast <Message>");
					return true;
				}
				String msg = "";
				for (int i = 1; i < args.length; i++) {
					msg += args[i] + " ";
				}
				msg = msg.replace("&", "§");
				Bukkit.broadcastMessage(prefix + " " + msg);
				p.sendMessage(prefix + "Succesfuly broadcasted.");
			}
			if (cmd.equalsIgnoreCase("kick")
					&& hasPermission(p, "royalzone.admin.kick")) {
				if (args.length < 1) {
					p.sendMessage(prefix + ChatColor.DARK_RED
							+ "Usage:/kick <Player> <Reason>");
					return true;
				}
				if(Bukkit.getPlayer(args[0]) == null){
		
					p.sendMessage(prefix + "Couldnot find player !");
					return true;
				}

				String msg = "";
				for (int i = 1; i < args.length; i++) {
					msg += args[i] + " ";
				}
                 				Bukkit.getPlayer(args[0]).kickPlayer(msg);
			}
			if (cmd.equalsIgnoreCase("shop")) {
				p.openInventory(sShop);
				p.sendMessage(prefix + ChatColor.GREEN
						+ "Succesfuly opened The Shop");
			}
			if(cmd.equalsIgnoreCase("cc") && hasPermission(p, "royalzone.admin.cc")){
				for(int i = 0 ; i < 151 ; i++ ){
					Bukkit.broadcastMessage(" ");
				}
				p.sendMessage(prefix + ChatColor.GREEN+"Succesfuly cleared the chat");
				Bukkit.broadcastMessage(prefix + ChatColor.DARK_PURPLE+"The chat has been cleared");
				

			}
			if (cmd.equalsIgnoreCase("1v1")) {
				if (args.length < 1) {

					p.sendMessage(prefix
							+ ChatColor.GREEN
							+ "Hey, Wellcome to RoyalZone 1v1 System, use the Signs to join the arenas :)");
				} else if (args[0].equalsIgnoreCase("set")
						&& hasPermission(p, "royalzone.ovo.set")) {
					if (args.length < 2) {
						p.sendMessage(prefix
								+ ChatColor.GREEN
								+ "Usage:/set oa.blue/oa.red/oa.bluelounge/oa.redlounge/lounge");
					} else if (args[1].equalsIgnoreCase("blue")) {
						o.getData().set("blue.world", p.getWorld().getName());
						o.getData().set("blue.x", p.getLocation().getBlockX());
						o.getData().set("blue.y", p.getLocation().getBlockY());
						o.getData().set("blue.z", p.getLocation().getBlockZ());
						o.saveData();

					} else if (args[1].equalsIgnoreCase("red")) {
						o.getData().set("red.world", p.getWorld().getName());
						o.getData().set("red.x", p.getLocation().getBlockX());
						o.getData().set("red.y", p.getLocation().getBlockY());
						o.getData().set("red.z", p.getLocation().getBlockZ());
						o.saveData();

					} else if (args[1].equalsIgnoreCase("bluelounge")) {
						o.getData().set("bluelounge.world",
								p.getWorld().getName());
						o.getData().set("bluelounge.x",
								p.getLocation().getBlockX());
						o.getData().set("bluelounge.y",
								p.getLocation().getBlockY());
						o.getData().set("bluelounge.z",
								p.getLocation().getBlockZ());
						o.saveData();

					} else if (args[1].equalsIgnoreCase("redlounge")) {
						o.getData().set("redlounge.world",
								p.getWorld().getName());
						o.getData().set("redlounge.x",
								p.getLocation().getBlockX());
						o.getData().set("redlounge.y",
								p.getLocation().getBlockY());
						o.getData().set("redlounge.z",
								p.getLocation().getBlockZ());
						o.saveData();

					} else if (args[1].equalsIgnoreCase("lounge")) {
						o.getData().set("lounge.world", p.getWorld().getName());
						o.getData()
								.set("lounge.x", p.getLocation().getBlockX());
						o.getData()
								.set("lounge.y", p.getLocation().getBlockY());
						o.getData()
								.set("lounge.z", p.getLocation().getBlockZ());
						o.saveData();

					}
				} else if (args[0].equalsIgnoreCase("yossijoinaba")) {

					if (args.length < 2) {
						World baw = Bukkit.getWorld(o.getData().getString(
								"blue.world"));
						double bax = o.getData().getInt("blue.x");
						double bay = o.getData().getInt("blue.y");
						double baz = o.getData().getInt("blue.z");
						final Location LocationBlue = new Location(baw, bax,
								bay, baz);

						World raw = Bukkit.getWorld(o.getData().getString(
								"red.world"));
						double rax = o.getData().getInt("red.x");
						double ray = o.getData().getInt("red.y");
						double raz = o.getData().getInt("red.z");
						final Location LocationRed = new Location(raw, rax,
								ray, raz);

						World rw = Bukkit.getWorld(o.getData().getString(
								"redlounge.world"));
						double rx = o.getData().getInt("redlounge.x");
						double ry = o.getData().getInt("redlounge.y");
						double rz = o.getData().getInt("redlounge.z");
						final Location RedLounge = new Location(rw, rx, ry, rz);

						World w = Bukkit.getWorld(o.getData().getString(
								"bluelounge.world"));
						double x = o.getData().getInt("bluelounge.x");
						double y = o.getData().getInt("bluelounge.y");
						double z = o.getData().getInt("bluelounge.z");
						final Location BlueLounge = new Location(w, x, y, z);

						if (Red.contains(p.getName())
								|| Blue.contains(p.getName())) {
							p.sendMessage(prefix + ChatColor.RED
									+ " You can't join while in the arena");
						} else if (Red.size() == 1 && Blue.size() == 1) {
							p.sendMessage(prefix + ChatColor.RED
									+ " A match is now on, Please wait..");
						} else if (Red.size() == 0 && Blue.size() == 0) {
							p.sendMessage(prefix + ChatColor.RED
									+ " You are §cRed§f!");
							p.sendMessage(prefix + ChatColor.RED
									+ " Please wait for another player to join");
							p.getInventory().clear();
							Red.add(p.getName());
							p.teleport(RedLounge);
							p.getInventory().setHelmet(null);
							p.getInventory().setChestplate(null);
							p.getInventory().setLeggings(null);
							p.getInventory().setBoots(null);
						} else if (Red.size() == 1) {
							p.sendMessage(prefix + ChatColor.RED
									+ " You are §1Blue");
							Blue.add(p.getName());
							p.getInventory().clear();
							p.teleport(BlueLounge);
							p.getInventory().setHelmet(null);
							p.getInventory().setChestplate(null);
							p.getInventory().setLeggings(null);
							p.getInventory().setBoots(null);
						}
						if (Red.size() == 1 && Blue.size() == 1) {
							final Player bp = p.getServer().getPlayer(
									Blue.get(0));
							final Player rp = p.getServer().getPlayer(
									Red.get(0));
							if (Red.contains(p.getName())
									|| Blue.contains(p.getName())) {
								rp.sendMessage(prefix + ChatColor.RED
										+ bp.getName() + " is §1Blue");
								bp.sendMessage(prefix + ChatColor.RED
										+ " You fight versus " + rp.getName());
								rp.sendMessage(prefix + ChatColor.RED
										+ " Starting in §e5 §fseconds..");
								bp.sendMessage(prefix + ChatColor.RED
										+ " Starting in §e5 §fseconds..");
								this.getServer()
										.getScheduler()
										.scheduleAsyncRepeatingTask(this,
												new Runnable() {
													int Count = 5;

													public void run() {

														if (Count != -1) {
															if (Count != 0) {
																Count--;
																if (Count == 0) {
																	if (Red.size() == 1
																			&& Blue.size() == 1) {
																		rp.sendMessage(prefix
																				+ ChatColor.RED
																				+ " Battle Began!");
																		bp.sendMessage(prefix
																				+ ChatColor.RED
																				+ " Battle Began!");
																		rp.setHealth(20);
																		bp.setHealth(20);

																		rp.getInventory()
																				.clear();
																		rp.setHealth(20);
																		rp.setFoodLevel(20);
																		for (PotionEffect effect : rp
																				.getActivePotionEffects())
																			rp.removePotionEffect(effect
																					.getType());
																		ItemStack Ds = new ItemStack(
																				Material.DIAMOND_SWORD);
																		ItemStack Soup = new ItemStack(
																				Material.MUSHROOM_SOUP);
																		ItemStack Bowl = new ItemStack(
																				Material.BOWL);
																		Enchantment Sharpness = new EnchantmentWrapper(
																				16);
																		Ds.addEnchantment(
																				Sharpness,
																				1);
																		rp.getInventory()
																				.setHelmet(
																						new ItemStack(
																								Material.DIAMOND_HELMET));
																		rp.getInventory()
																				.setChestplate(
																						new ItemStack(
																								Material.IRON_CHESTPLATE));
																		rp.getInventory()
																				.setLeggings(
																						new ItemStack(
																								Material.IRON_LEGGINGS));
																		rp.getInventory()
																				.setBoots(
																						new ItemStack(
																								Material.IRON_BOOTS));
																		rp.getInventory()
																				.addItem(
																						Ds);
																		rp.getInventory()
																				.setItem(
																						35,
																						Bowl);
																		for (int Sn = 0; Sn < 33; Sn++) {
																			rp.getInventory()
																					.addItem(
																							Soup);
																		}
																		rp.updateInventory();
																		bp.getInventory()
																				.clear();
																		bp.setHealth(20);
																		bp.setFoodLevel(20);
																		for (PotionEffect effect : bp
																				.getActivePotionEffects())
																			bp.removePotionEffect(effect
																					.getType());
																		bp.getInventory()
																				.setHelmet(
																						new ItemStack(
																								Material.DIAMOND_HELMET));
																		bp.getInventory()
																				.setChestplate(
																						new ItemStack(
																								Material.IRON_CHESTPLATE));
																		bp.getInventory()
																				.setLeggings(
																						new ItemStack(
																								Material.IRON_LEGGINGS));
																		bp.getInventory()
																				.setBoots(
																						new ItemStack(
																								Material.IRON_BOOTS));
																		bp.getInventory()
																				.addItem(
																						Ds);
																		bp.getInventory()
																				.setItem(
																						35,
																						Bowl);
																		for (int Sn = 0; Sn < 33; Sn++) {
																			bp.getInventory()
																					.addItem(
																							Soup);
																		}
																		bp.updateInventory();
																		bp.teleport(LocationBlue);
																		rp.teleport(LocationRed);
																	} else {
																		if (Red.size() == 1) {
																			rp.sendMessage(prefix
																					+ ChatColor.RED
																					+ " The §1Blue §fplayer left the game");
																			Red.clear();
																			Blue.clear();
																			World lw = Bukkit
																					.getWorld(o
																							.getData()
																							.getString(
																									"lounge.world"));
																			double lx = o
																					.getData()
																					.getInt("lounge.x");
																			double ly = o
																					.getData()
																					.getInt("lounge.y");
																			double lz = o
																					.getData()
																					.getInt("lounge.z");
																			final Location LocationEnd = new Location(
																					lw,
																					lx,
																					ly,
																					lz);
																			rp.teleport(LocationEnd);
																		}
																		if (Blue.size() == 1) {
																			bp.sendMessage(prefix
																					+ " The §4Red §fplayer left the game");
																			Red.clear();
																			Blue.clear();
																			World lw = Bukkit
																					.getWorld(o
																							.getData()
																							.getString(
																									"lounge.world"));
																			double lx = o
																					.getData()
																					.getInt("lounge.x");
																			double ly = o
																					.getData()
																					.getInt("lounge.y");
																			double lz = o
																					.getData()
																					.getInt("lounge.z");
																			final Location LocationEnd = new Location(
																					lw,
																					lx,
																					ly,
																					lz);
																			bp.teleport(LocationEnd);
																		}
																	}
																}
															}
														}
													}
												}, 0L, 20L);
							}
						}
					}

				} else if (args[0].equalsIgnoreCase("leave")) {
					if (args.length < 2) {
						if (!(Blue.contains(p.getName()) || Red.contains(p
								.getName()))) {
							p.sendMessage(prefix + ChatColor.RED
									+ " You aren't in the arena!");
						}
						if (Red.contains(p.getName()) && Blue.size() == 1) {
							Player bp = p.getServer().getPlayer(Blue.get(0));
							Player rp = p.getServer().getPlayer(Red.get(0));
							Inventory bi = bp.getInventory();
							Inventory ri = rp.getInventory();
							Red.clear();
							Blue.clear();
							bp.setHealth(20);
							rp.setHealth(20);
							bp.sendMessage(prefix + ChatColor.RED
									+ " The other side left the battle!");
							rp.sendMessage(prefix + ChatColor.RED
									+ " You left the battle!");
							World lw = Bukkit.getWorld(o.getData().getString(
									"lounge.world"));
							double lx = o.getData().getInt("lounge.x");
							double ly = o.getData().getInt("lounge.y");
							double lz = o.getData().getInt("lounge.z");
							final Location LocationEnd = new Location(lw, lx,
									ly, lz);
							bp.teleport(LocationEnd);
							rp.teleport(LocationEnd);
							bi.clear();
							ri.clear();
							rp.setHealth(20);
							bp.setHealth(20);
							rp.getInventory().setHelmet(null);
							rp.getInventory().setChestplate(null);
							rp.getInventory().setLeggings(null);
							rp.getInventory().setBoots(null);
							bp.getInventory().setHelmet(null);
							bp.getInventory().setChestplate(null);
							bp.getInventory().setLeggings(null);
							bp.getInventory().setBoots(null);
						}
						if (Blue.contains(p.getName()) && Red.size() == 1) {
							Player bp = p.getServer().getPlayer(Blue.get(0));
							Player rp = p.getServer().getPlayer(Red.get(0));
							Inventory bi = bp.getInventory();
							Inventory ri = rp.getInventory();
							Red.clear();
							Blue.clear();
							rp.sendMessage(prefix + ChatColor.RED
									+ " The other side left the battle!");
							bp.sendMessage(prefix + ChatColor.RED
									+ " You left the battle!");
							World lw = Bukkit.getWorld(o.getData().getString(
									"lounge.world"));
							double lx = o.getData().getInt("lounge.x");
							double ly = o.getData().getInt("lounge.y");
							double lz = o.getData().getInt("lounge.z");
							final Location LocationEnd = new Location(lw, lx,
									ly, lz);
							bp.teleport(LocationEnd);
							rp.teleport(LocationEnd);
							bi.clear();
							rp.setHealth(20);
							bp.setHealth(20);
							ri.clear();
							rp.getInventory().setHelmet(null);
							rp.getInventory().setChestplate(null);
							rp.getInventory().setLeggings(null);
							rp.getInventory().setBoots(null);
							bp.getInventory().setHelmet(null);
							bp.getInventory().setChestplate(null);
							bp.getInventory().setLeggings(null);
							bp.getInventory().setBoots(null);
						}
						if (Red.contains(p.getName())) {
							Player rp = p.getServer().getPlayer(Red.get(0));
							Inventory ri = rp.getInventory();
							Red.clear();
							rp.sendMessage(prefix + ChatColor.RED
									+ " You left the battle!");
							World lw = Bukkit.getWorld(o.getData().getString(
									"lounge.world"));
							double lx = o.getData().getInt("lounge.x");
							double ly = o.getData().getInt("lounge.y");
							double lz = o.getData().getInt("lounge.z");
							final Location LocationEnd = new Location(lw, lx,
									ly, lz);
							rp.teleport(LocationEnd);
							ri.clear();
							rp.setHealth(20);
							p.getInventory().setHelmet(null);
							p.getInventory().setChestplate(null);
							p.getInventory().setLeggings(null);
							p.getInventory().setBoots(null);
						}
						if (Blue.contains(p.getName())) {
							Player bp = p.getServer().getPlayer(Blue.get(0));
							Inventory bi = bp.getInventory();
							Blue.clear();
							bp.sendMessage(prefix + ChatColor.RED
									+ " You left the battle!");
							World lw = Bukkit.getWorld(o.getData().getString(
									"lounge.world"));
							double lx = o.getData().getInt("lounge.x");
							double ly = o.getData().getInt("lounge.y");
							double lz = o.getData().getInt("lounge.z");
							final Location LocationEnd = new Location(lw, lx,
									ly, lz);
							bp.teleport(LocationEnd);
							bi.clear();
							bp.setHealth(20);
							p.getInventory().setHelmet(null);
							p.getInventory().setChestplate(null);
							p.getInventory().setLeggings(null);
							p.getInventory().setBoots(null);
						}
					}
				}
			}

		
			if (cmd.equalsIgnoreCase("Npc")
					&& hasPermission(p, "royalzone.admin.npc")) {
				if (args.length < 1) {
					p.sendMessage(ChatColor.GREEN + "Usage:Npc create");

				} else if (args[0].equalsIgnoreCase("create")) {
				
					String name = args[1];
					ItemStack paper = new ItemStack(Material.MAP);
					ItemMeta meta = paper.getItemMeta();
					meta.setDisplayName(name);
					paper.setItemMeta(meta);
					p.getInventory().addItem(paper);
				}
			}
			
			if (cmd.equalsIgnoreCase("ban")
					&& hasPermission(p, "royalzone.admin.ban")) {
				if (args.length != 2) {
					p.sendMessage(ChatColor.DARK_RED
							+ "Usage: /ban Nickname <Reason>");
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				if (t == null) {
					p.sendMessage(ChatColor.DARK_RED + "Invaild Nickname");
					return true;
				}
				t.setBanned(true);
				String msg = "";
				for (int i = 1; i < args.length; i++) {
					msg += args[i] + " ";
				}
                 
				bm.getData().set(t.getUniqueId().toString() + ".banned",
						Boolean.valueOf(true));
				bm.getData().set(t.getUniqueId().toString() + ".reason",
						msg);
				t.kickPlayer(msg);
				bm.saveData();
			}
			if (cmd.equalsIgnoreCase("removepermissions") && p.isOp()) {
				if (args.length < 1) {
					sender.sendMessage(ChatColor.RED
							+ "Please Specify a Player");
					return true;
				}

				removePermissions(Bukkit.getPlayer(args[0]), args[1]);
			}

			if (cmd.equalsIgnoreCase("addpermissions") && p.isOp()) {
				if (args.length < 1) {
					sender.sendMessage(ChatColor.RED
							+ "Please Specify a Player");
					return true;
				}
				UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(args[0]));
				UUID of = null;
				try {
					of = fetcher.getUUIDOf(args[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String s = of.toString();
				addtoOfflinePermissions(s, args[1]);

				addPermissions(Bukkit.getPlayer(args[0]), args[1]);
			}

			if(cmd.equalsIgnoreCase("event") && hasPermission(p, "royalzone.admin.event")){
				p.openInventory(g.i);

				p.sendMessage(prefixa + "Succesfuly opened the Event Inventory");
			}
			
			if (cmd.equalsIgnoreCase("team")) {
				if (args.length < 1) {
					ta.printhelpMessage(p);

				} else if (args[0].equalsIgnoreCase("create")) {
					if (args.length != 3) {
						p.sendMessage(teamprefix
								+ "usage: team create <TeamName> <TeamTag>");
						return true;
					}
					if (isVip(p)) {
						if (tl.getData().contains("Teams" + args[1])) {
							p.sendMessage(ChatColor.DARK_RED
									+ "There's Already Team named " + args[1]);
							return true;
						}
						if (ta.isinTeam(p)) {
							p.sendMessage(ChatColor.DARK_RED
									+ "Your'e Already in a Team !");
							return true;
						}

						if (args[2].toCharArray().length > 3
								|| args[2].toCharArray().length < 3) {
							p.sendMessage(teamprefix + ChatColor.DARK_RED
									+ "Tag can be only 3 Chars !");
							return true;
						}
						ta.createTeam(p, args[1], args[2]);
						p.sendMessage(teamprefix + ChatColor.GREEN
								+ "Succesfuly Created " + args[1]);
					} else {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "You ar'ent V.I.P, You cant create a team.");
					}
				} else if (args[0].equalsIgnoreCase("invite")) {

					if (ta.getSlots(ta.getTeam(p)) == ta.getMembers(ta
							.getTeam(p))) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Your'e Team is Full");
						return true;
					}
					if (args.length != 2) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Usage: /team invite <PlayerName>");
						return true;
					}

					if (!(ta.getLeader(ta.getTeam(p)).equals(p.getUniqueId()
							.toString()))) {

						p.sendMessage(prefix + ChatColor.DARK_RED
								+ " You are'nt the Team Leader");
						return true;
					}

					final Player i = Bukkit.getPlayer(args[1]);
					if (i == null) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Invaild Nickname");
						return true;
					}
					if ((ta.isinTeam(i))) {
						p.sendMessage(teamprefix + ChatColor.RED + "Player "
								+ i.getName() + " is Already in a team !");
						return true;
					}

					invited.put(i, ta.getTeam(p));
					i.sendMessage(teamprefix + ChatColor.GREEN
							+ "You have invited to join to the "
							+ invited.get(i) + " Team");
					i.sendMessage(teamprefix + ChatColor.GOLD
							+ "to accept the invitation Write /team accept");
					i.sendMessage(teamprefix + ChatColor.RED
							+ "To deny the invitation use /team deny");
					p.sendMessage(teamprefix + ChatColor.GREEN
							+ "Succesfuly invited Player !");

				} else if (args[0].equalsIgnoreCase("accept")) {

					if (invited.containsKey(p)) {
						if (invited.get(p) == null) {

							p.sendMessage(teamprefix + ChatColor.DARK_RED
									+ "Team has closed.");
							return true;
						}

						ta.joinTeam(p, invited.get(p));

						p.sendMessage(teamprefix + ChatColor.GREEN
								+ "Succesfuly Joined " + invited.get(p));

						invited.remove(p);

					}
				} else if (args[0].equalsIgnoreCase("deny")) {
					if (!(invited.containsKey(p))) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Your'e not invited !");
						return true;
					}
					p.sendMessage(teamprefix + ChatColor.GREEN
							+ "Succesfuly Denied Team Invite");
					invited.remove(p);

				} else if (args[0].equalsIgnoreCase("disband")) {

					if (ta.getTeam(p) == null) {
						p.sendMessage(teamprefix + ChatColor.RED
								+ "You are'nt in a Team !");
						return true;
					}
					if (!(ta.getLeader(ta.getTeam(p)).equals(p.getUniqueId()
							.toString()))) {
						p.sendMessage(teamprefix + ChatColor.RED
								+ "You are'nt the Team Leader !");
						return true;
					} else {
						ta.disbandTeam(p, ta.getTeam(p));
						p.sendMessage(teamprefix + ChatColor.GREEN
								+ "Succesfuly Disbanded your team !");
					}
				} else if (args[0].equalsIgnoreCase("kick")) {
					if (args.length != 2) {
						p.sendMessage(teamprefix + ChatColor.RED
								+ "Usage: Team kick <name>");

						return true;
					}

					if (ta.getTeam(p) == null) {

						p.sendMessage(teamprefix + ChatColor.RED
								+ "You are'nt in a Team !");
						return true;
					}

					if (!(ta.getLeader(ta.getTeam(p)).equals(p.getUniqueId()
							.toString()))) {

						p.sendMessage(teamprefix + ChatColor.RED
								+ "You are'nt the Team Leader !");
						return true;
					}

					Player t = Bukkit.getPlayer(args[1]);

					if (t == null) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Inviald nickname");
						return true;
					}
					if (t == p) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "You Cant Kick yourself !");
						return true;
					}

					ta.kickPlayer(t, ta.getTeam(t));
					t.sendMessage(teamprefix + ChatColor.RED
							+ "You've Been kicked from your team !");
					p.sendMessage(teamprefix + ChatColor.GREEN
							+ "Succesfuly Kicked " + t.getName());
				} else if (args[0].equalsIgnoreCase("tag")) {
					ItemStack Tag = new ItemStack(Material.PAPER);
					ItemMeta meta = Tag.getItemMeta();
					meta.setDisplayName(ChatColor.RED + "ChangeTag Paper");
					Tag.setItemMeta(meta);
					if (!(p.getItemInHand().equals(Tag))) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "You dont have The ChangeTag Paper.");
						return true;
					}

					if (args.length != 2) {
						p.sendMessage(teamprefix + ChatColor.RED
								+ "Usage: Team tag <name>");
						return true;

					}
					if (args[1].toCharArray().length < 3) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Team Tag must have 3 Chars !");
						return true;
					}
					if (ta.getTeam(p) == null) {
						p.sendMessage(teamprefix + ChatColor.RED
								+ "You are'nt in a Team !");
						return true;
					}
					if (!(ta.getLeader(ta.getTeam(p)).equals(p.getUniqueId()
							.toString()))) {
						p.sendMessage(teamprefix + ChatColor.RED
								+ "You are'nt the Team Leader !");
						return true;
					}

					ta.setteamTag(ta.getTeam(p), p, args[1]);
					p.sendMessage(ChatColor.GREEN
							+ "Succesfuly Changed Team Tag");
					p.getInventory().removeItem(Tag);

				} else if (args[0].equalsIgnoreCase("leave")) {
					if (ta.getTeam(p) == null) {

						p.sendMessage(teamprefix + ChatColor.RED
								+ "You are'nt in a Team !");
						return true;
					}

					if (ta.getLeader(ta.getTeam(p)).equals(
							p.getUniqueId().toString())) {
						p.sendMessage(teamprefix
								+ ChatColor.DARK_RED
								+ "You Cant leave your team , but you can Disband it.");
					}

					ta.leaveTeam(p);

				} else if (args[0].equalsIgnoreCase("info")) {
					if (!(ta.isinTeam(p))) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Your'e not in a team !");
					}

					if (ta.isinTeam(p)) {
						p.sendMessage(ChatColor.GRAY + "-----" + ta.getTeam(p)
								+ ChatColor.GRAY + "-----");
						p.sendMessage(ChatColor.GREEN + "Kills: "
								+ ta.getTeamKills(p));
						p.sendMessage(ChatColor.GREEN + "Deaths: "
								+ ta.getTeamDeaths(p));
						p.sendMessage(ChatColor.GREEN + "K/D Ratio: "
								+ ta.getTeamKD(p));
						p.sendMessage(ChatColor.GREEN + "TeamCoins: "
								+ ta.getTeamCoins(ta.getTeam(p)));

					}
				} else if (args[0].equalsIgnoreCase("shop")) {
					p.openInventory(TeamShop);
					p.sendMessage(teamprefix + "Succesfuly opened Team Shop");
				} else if (args[0].equalsIgnoreCase("give")
						&& hasPermission(p, "royalzone.team.give")) {

					if (args.length != 3) {
						p.sendMessage(teamprefix + ChatColor.DARK_RED
								+ "Usage: Team give TeamName <amount>");
						return true;
					}
					int CoinsGive = Integer.parseInt(args[2]);
					int Coins = tl.getData().getInt(args[1] + ".coins");
					tl.getData().set(args[1] + ".coins", Coins + CoinsGive);
					p.sendMessage(teamprefix + ChatColor.GREEN
							+ "Succesfuly Added team Coins");
					tl.saveData();
				}
			}

			if (cmd.equalsIgnoreCase("setspawn") && hasPermission(p, "royalzone.admin.warp")) {
				settings.getData().set("spawn.world",
						p.getLocation().getWorld().getName());
				settings.getData().set("spawn.x", p.getLocation().getX());
				settings.getData().set("spawn.y", p.getLocation().getY());
				settings.getData().set("spawn.z", p.getLocation().getZ());
				settings.saveData();
				p.sendMessage(ChatColor.GREEN + "Spawn set!");
				return true;
			}

			if (cmd.equalsIgnoreCase("spawn")) {
				if (settings.getData().getConfigurationSection("spawn") == null) {
					p.sendMessage(ChatColor.RED
							+ "The spawn has not yet been set!");
					return true;
				}
				World w = Bukkit.getServer().getWorld(
						settings.getData().getString("spawn.world"));
				double x = settings.getData().getDouble("spawn.x");
				double y = settings.getData().getDouble("spawn.y");
				double z = settings.getData().getDouble("spawn.z");
				Location l = new Location(w, x, y, z);
				SpawnCommand(p, l);
				p.sendMessage(prefix + ChatColor.GREEN
						+ "Welcome to the spawn!");
			}

			if (cmd.equalsIgnoreCase("setwarp") && hasPermission(p, "royalzone.admin.warp")) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "Please specify a name!");
					return true;
				}
				settings.getData().set("warps." + args[0] + ".world",
						p.getLocation().getWorld().getName());
				settings.getData().set("warps." + args[0] + ".x",
						p.getLocation().getX());
				settings.getData().set("warps." + args[0] + ".y",
						p.getLocation().getY());
				settings.getData().set("warps." + args[0] + ".z",
						p.getLocation().getZ());
				settings.saveData();
				p.sendMessage(ChatColor.GREEN + "Set warp " + args[0] + "!");
			}

			if (cmd.equalsIgnoreCase("warp")) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "Please specify a name!");
					return true;
				}
				if (settings.getData().getConfigurationSection(
						"warps." + args[0]) == null) {
					p.sendMessage(ChatColor.RED + "Warp " + args[0]
							+ " does not exist!");
					return true;
				}
				World w = Bukkit.getServer().getWorld(
						settings.getData().getString(
								"warps." + args[0] + ".world"));
				double x = settings.getData().getDouble(
						"warps." + args[0] + ".x");
				double y = settings.getData().getDouble(
						"warps." + args[0] + ".y");
				double z = settings.getData().getDouble(
						"warps." + args[0] + ".z");
				Location l = new Location(w, x, y, z);
				SpawnCommand(p, l);
				p.sendMessage(ChatColor.GREEN + "Teleported to " + args[0]
						+ "!");
			}

			if (cmd.equalsIgnoreCase("delwarp") && hasPermission(p, "royalzone.admin.warp")) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "Please specify a name!");
					return true;
				}
				if (settings.getData().getConfigurationSection(
						"warps." + args[0]) == null) {
					p.sendMessage(ChatColor.RED + "Warp " + args[0]
							+ " does not exist!");
					return true;
				}
				settings.getData().set("warps." + args[0], null);
				settings.saveData();
				p.sendMessage(ChatColor.GREEN + "Removed warp " + args[0] + "!");
			}

			if (cmd.equalsIgnoreCase("addpermission")) {
				List<String> perms = instance.getData().getStringList(
						p.getUniqueId().toString());
				perms.add("royalzone.send");
				instance.getData().set(p.getUniqueId().toString(), perms);
				instance.saveData();
			}
			if ((cmd.equalsIgnoreCase("helpme"))) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "Right way /helpme <Message>");
				} else {
					StringBuilder str = new StringBuilder();
					for (int x = 0; x < args.length; x++) {
						str.append(args[x] + " ");
					}
					String fmessage = str.toString();
					boolean admin = false;
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						if (hasPermission(p2, "royalzone.admin.kick")) {
							admin = true;
							p2.sendMessage(ChatColor.RED + "The Player - "
									+ p.getName() + " Need Help !");
							p2.sendMessage(ChatColor.RED + "His Message - "
									+ fmessage);
						}
					}
					if (admin) {
						p.sendMessage(ChatColor.GREEN
								+ "Your Message has been Sent");
					} else if (!admin) {
						p.sendMessage(ChatColor.RED + "No admin online");
					}
				}
			}

			if (cmd.equalsIgnoreCase("coins")) {
				if (args.length < 1) {
					int Coins = getConfig().getInt(
							p.getUniqueId().toString() + ".coins");
					p.sendMessage(ChatColor.GOLD + "Coins : " + Coins);
				} else if (args[0].equalsIgnoreCase("give")
						&& p.hasPermission("royalzone.coins.give")) {
					Player CheckT = p.getServer().getPlayer(args[1]);
					int CreditsGive = Integer.valueOf(args[2]);
					if (CheckT != null) {
						int CheckTC = getConfig().getInt(
								CheckT.getUniqueId().toString() + ".coins");
						p.sendMessage("§eYou gave §6" + CreditsGive
								+ " §ecoins to §6" + CheckT.getName());
						if (CheckT.isOnline()) {
							CheckT.sendMessage("§6§lAdmin gave you some.coins. enjoy!");
						}
						getConfig().set(
								CheckT.getUniqueId().toString() + ".coins",
								CheckTC + CreditsGive);
						saveConfig();
					}

				} else if (args[0].equalsIgnoreCase("pay")) {
					if (hasPermission(p, "vip.coins.pay")){
						
					
					if (args.length > 2) {
						
						Player PayPlayer = p.getServer().getPlayer(args[1]);
					 if(PayPlayer == p){ p.sendMessage(prefix + "You cant pay to yourself !"); return true;};
						int SenderCoins = getConfig().getInt(
								p.getUniqueId().toString() + ".coins");
						if (PayPlayer != null && PayPlayer.isOnline()) {
							if (isInt(args[2])) {
								int CreditsGive = Integer.valueOf(args[2]);
								if (SenderCoins >= CreditsGive
										&& CreditsGive > 0) {
									int PayPlayerCoins = getConfig().getInt(
											PayPlayer.getUniqueId().toString()
													+ ".coins");
									int Misim = (int) ((CreditsGive) * 0.09);
									getConfig()
											.set(PayPlayer.getUniqueId()
													.toString() + ".coins",
													(PayPlayerCoins + (CreditsGive * 0.91)));
									getConfig().set(p.getUniqueId().toString() + ".coins",
											SenderCoins - CreditsGive);
									saveConfig();
									int PpCoins = getConfig().getInt(
											p.getUniqueId().toString()
													+ ".coins");
									p.sendMessage("§eYou gave §6" + CreditsGive
											+ " §ecoins to §6"
											+ PayPlayer.getName());
									p.sendMessage("§6" + Misim
											+ "§e.coins went to tax");
									p.sendMessage("§eYou now have §6" + PpCoins
											+ " §ecoins");
									PayPlayer.sendMessage("§6" + p.getName()
											+ "§e gave you §6" + CreditsGive
											+ "§e.coins");
									PayPlayer.sendMessage("§e0.09% - §6( §6"
											+ Misim + ".coins ) §ewent to tax");

								}else{
									p.sendMessage(prefix + ChatColor.GREEN+"You dont have enough coins");
								}
							}
						}
					}else{
						p.sendMessage(prefix + ChatColor.GREEN+"/coins pay <Player> <Amount>");
					}
				
						}else{
							p.sendMessage(prefix + ChatColor.RED+"You cant pay, you dont have the perimssions for that.");
						}
				}
			}
		}
		
		return true;
	}

	public void removePermissions(Player p, String permission) {

		List<String> perms = instance.getData().getStringList(
				p.getUniqueId().toString());
		perms.remove(permission);
		instance.getData().set(p.getUniqueId().toString(), perms);
		instance.saveData();

	}

	public void addPermissions(Player p, String permission) {

		List<String> perms = instance.getData().getStringList(
				p.getUniqueId().toString());
		perms.add(permission);
		instance.getData().set(p.getUniqueId().toString(), perms);
		instance.saveData();

	}

	@EventHandler
	public void damage(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			int x = p.getLocation().getBlockX();
			int y = p.getLocation().getBlockY();
			int z = p.getLocation().getBlockZ();

			if (x<= 21 && x>= -11 && y <= 23 && y >= 10 && z <= 979 && z >= 967){
				e.setCancelled(true);
			}
			if( x<=  21 && x >= -6 && y <= 17 && x>= 5 && z <= 975 && z >= 966){
				e.setCancelled(true);
			}
			if (x <= 94 && x >= -47 && y <= 58 && y >= 1 && z <= 948
					&& z >= 811) {

				e.setCancelled(true);
			} else if (x <= -27 && x >= -33 && y <= 97 && y >= 94 && z <= 1
					&& z >= -3) {

				e.setCancelled(true);
			} else if (x < 182 && x >= 160 && y <= 22 && y >= 2 && z <= 993
					&& z >= 987) {

				e.setCancelled(true);
			} else if (x <= 179 && x >= 163 && y <= 7 && y >= 4 && z == 993) {

				e.setCancelled(true);
			} else if (x <= -28 && x >= -32 && y <= 96 && y >= 94 && z == 2) {

				e.setCancelled(true);
			} else if (x <= -12 && x >= -42 && y <= 109 && y >= 86 && z < 16
					&& z > -14) {

				e.setCancelled(true);
			}
		}
	}


	public boolean isinRegion(Player p){
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();

		if( x<=  26 && x >= -5 && y <= 19 && y>= 7 && z <= 977 && z >= 964){
return true;
		}
		if (x <= 94 && x >= -47 && y <= 58 && y >= 1 && z <= 948
				&& z >= 811) {

return true;
			
		} else if (x <= -27 && x >= -33 && y <= 97 && y >= 94 && z <= 1
				&& z >= -3) {

return true;
		} else if (x < 182 && x >= 161 && y <= 22 && y >= 2 && z <= 993
				&& z >= 988) {

return true;
		} else if (x <= 179 && x >= 163 && y <= 7 && y >= 4 && z == 993) {

return true;
		} else if (x <= -28 && x >= -32 && y <= 96 && y >= 94 && z == 2) {

return true;
		} else if (x <= -12 && x >= -42 && y <= 109 && y >= 86 && z < 16
				&& z > -14) {

return true;
		}
		return false;
	}
	
	@EventHandler
	public void OneVOneDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player
				&& e.getEntity().getKiller() instanceof Player) {
			Player k = (Player) e.getEntity().getKiller();
			Player p = (Player) e.getEntity();
			if (p.getName().equalsIgnoreCase("RecentWolf")) {
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.RecentWolfKiller));
			} else if (p.getName().equalsIgnoreCase("OdinPixel")) {
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.ODINPIXELKILLER));

			}

		}

		World lw = Bukkit.getWorld(o.getData().getString("lounge.world"));
		double lx = o.getData().getInt("lounge.x");
		double ly = o.getData().getInt("lounge.y");
		double lz = o.getData().getInt("lounge.z");
		final Location LocationEnd = new Location(lw, lx, ly, lz);
		Player Dead = e.getEntity().getPlayer();
		if (e.getEntity().getKiller() instanceof Player) {
			Player Kill = e.getEntity().getKiller();
			if (Red.contains(Dead.getName()) || Blue.contains(Dead.getName())) {
				Dead.teleport(LocationEnd);
				Kill.getInventory().clear();
				Kill.getInventory().clear();
				Kill.getInventory().setHelmet(null);
				Kill.getInventory().setChestplate(null);
				Kill.getInventory().setLeggings(null);
				Kill.getInventory().setBoots(null);
				Blue.clear();
				Red.clear();
				Kill.teleport(LocationEnd);
				Kill.sendMessage(prefix + " You won (:");
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(Kill, Achivments.OVOWINNER));

				AddCoin(Kill, 10);
				saveConfig();

				Dead.sendMessage(prefix + " You lost ):");
				Dead.teleport(LocationEnd);

			}
		}
	}

	@EventHandler
	public void joina(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.DARK_AQUA + "Join> "
				+ e.getPlayer().getName());

		BarAPI.setMessage(e.getPlayer(), ChatColor.AQUA+"§lRoyalZone, /buy to get to the shop");
	
		if(vanish.size() > 0){

			for(String s : vanish){
				Player t= Bukkit.getPlayer(s);
				for(Player kaw : getServer().getOnlinePlayers()){
					kaw.hidePlayer(t);

				}
			}
		}
		if (vanish.contains(e.getPlayer().getName())){

			for(Player pa : Bukkit.getOnlinePlayers()){
				pa.hidePlayer(e.getPlayer());
			}
		}
	
		Player p = e.getPlayer();
		if (isVip(p)) {
			if (Bukkit.getOnlinePlayers().length >= 20) {
				Random r = new Random();
				int num = r.nextInt(Bukkit.getOnlinePlayers().length);
				Player[] paa = Bukkit.getOnlinePlayers();
				Player pa = Bukkit.getPlayer(paa[num].getName());
				if (!isVip(pa)) {
					pa.kickPlayer("V.I.P Just Entered the Server, We're Sorry.");
				}
			}
		}

	
		if (!(e.getPlayer().hasPlayedBefore())) {
			Bukkit.getPluginManager()
					.callEvent(
							new AchivementGetEvent(e.getPlayer(),
									Achivments.FIRSTJOIN));
			World w = Bukkit.getServer().getWorld(
					settings.getData().getString("spawn.world"));
			double x = settings.getData().getDouble("spawn.x");
			double y = settings.getData().getDouble("spawn.y");
			double z = settings.getData().getDouble("spawn.z");
			e.getPlayer().teleport(new Location(w, x, y, z));
		}
	}

	@EventHandler
	public void join (PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(hasPermission(p, "royalzone.hax")){
			e.getPlayer().setPlayerListName(ChatColor.LIGHT_PURPLE+"§o§l[NOTMOD]Bear");
	return;
		}
		if(hasPermission(p, "royalzone.youtuber")){
			e.getPlayer().setPlayerListName("§9§l" + p.getName());
		}
		if(hasPermission(p, "royalzone.vip.all")){
			if(!p.getName().equalsIgnoreCase("YossiChakalaka")){
			e.getPlayer().setPlayerListName("§b§l"+p.getName());
			return;

			}
		}
		if (hasPermission(p, "royalzone.name.koren")) {
			p.setPlayerListName("§a§l" + p.getName());
			return;

		}
		if (hasPermission(p, "royalzone.name.kulikoko")) {
			p.setPlayerListName("§5§l" + p.getName());
			return;

		}
		if (hasPermission(p, "royalzone.name.spring")) {
			p.setPlayerListName("§6§l" + p.getName());
			return;

		}

		if (hasPermission(p, "royalzone.colors.blue")) {
			p.setPlayerListName(ChatColor.BOLD + p.getName());

			return;

		}
	}
	@EventHandler
	public void Achivmenet(AchivementGetEvent e) {
		Player p = e.getPlayer();
		Achivments c = e.getAchivement();

		List<String> ach = am.getData().getStringList(
				p.getUniqueId().toString() + ".Achivments");

		
		
			// so i loogged the achivemtn i get, and i logged the string from the array list, so now look 
			if(ach.contains(c.toString().toLowerCase())){

				return;

			}

			ach.add(c.toString().toLowerCase());

			am.getData().set(p.getUniqueId().toString() + ".Achivments", ach);
		
			am.saveData();

			p.sendMessage("§2§ka" + ChatColor.GOLD + "Achievement Get !" + "§2§ka");
			p.sendMessage("§2§ka"
					+ ChatColor.GOLD
					+ e.getAchivement().toString().toLowerCase()
							.replace("_", " ") + "§2§ka");
			p.sendMessage("§2§ka" + ChatColor.GOLD + "Achievement Get !" + "§2§ka");
			AddCoin(p, 15);

		
			if (c.equals(Achivments.OVOWINNER)) {
				if (!(ach.contains("ovowinner"))) {

					p.sendMessage("§2§ka" + ChatColor.GOLD + "Achivment Get"
							+ "§2§ka");
					p.sendMessage("§2§ka" + ChatColor.GOLD + "1v1 Winner"
							+ "§2§ka");
					p.sendMessage("§2§ka" + ChatColor.GOLD + "Achivment Get"
							+ "§2§ka");
					AddCoin(p, 15);
					am.saveData();
				}

			}

	}
		
	

	@EventHandler
	public void OneVsOneLeave(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.RED + "Quit> " + e.getPlayer().getName());

		Player p = e.getPlayer();
		World lw = Bukkit.getWorld(o.getData().getString("lounge.world"));
		double lx = o.getData().getInt("lounge.x");
		double ly = o.getData().getInt("lounge.y");
		double lz = o.getData().getInt("lounge.z");
		final Location LocationEnd = new Location(lw, lx, ly, lz);
		if (Blue.size() == 1 && Red.size() == 1 && Red.contains(p.getName())) {
			Player rp = p.getServer().getPlayer(Red.get(0));
			Player bp = p.getServer().getPlayer(Blue.get(0));
			rp.getInventory().clear();
			rp.getInventory().setHelmet(null);
			rp.getInventory().setChestplate(null);
			rp.getInventory().setLeggings(null);
			rp.getInventory().setBoots(null);
			bp.getInventory().clear();
			bp.getInventory().setHelmet(null);
			bp.getInventory().setChestplate(null);
			bp.getInventory().setLeggings(null);
			bp.getInventory().setBoots(null);
			rp.teleport(LocationEnd);
			bp.teleport(LocationEnd);
			Red.clear();
			Blue.clear();
			rp.setHealth(20.0);
			bp.setHealth(20.0);
			bp.sendMessage(prefix + " The §4Red §fplayer left the game");
		} else if (Blue.size() == 1 && Red.size() == 1
				&& Blue.contains(p.getName())) {
			Player rp = p.getServer().getPlayer(Red.get(0));
			Player bp = p.getServer().getPlayer(Blue.get(0));
			rp.getInventory().clear();
			rp.getInventory().setHelmet(null);
			rp.getInventory().setChestplate(null);
			rp.getInventory().setLeggings(null);
			rp.getInventory().setBoots(null);
			bp.getInventory().clear();
			bp.getInventory().setHelmet(null);
			bp.getInventory().setChestplate(null);
			bp.getInventory().setLeggings(null);
			bp.getInventory().setBoots(null);
			rp.teleport(LocationEnd);
			bp.teleport(LocationEnd);
			Red.clear();
			Blue.clear();
			rp.setHealth(20.0);
			bp.setHealth(20.0);
			rp.sendMessage(prefix + " The §1Blue §fplayer left the game");
		} else if (Blue.contains(p.getName())) {
			Player bp = p.getServer().getPlayer(Blue.get(0));
			bp.getInventory().clear();
			bp.getInventory().setHelmet(null);
			bp.getInventory().setChestplate(null);
			bp.getInventory().setLeggings(null);
			bp.getInventory().setBoots(null);
			bp.teleport(LocationEnd);
			Red.clear();
			Blue.clear();
			bp.setHealth(20.0);
		} else if (Red.contains(p.getName())) {
			Player rp = p.getServer().getPlayer(Red.get(0));
			rp.getInventory().clear();
			rp.getInventory().setHelmet(null);
			rp.getInventory().setChestplate(null);
			rp.getInventory().setLeggings(null);
			rp.getInventory().setBoots(null);
			rp.teleport(LocationEnd);
			Red.clear();
			Blue.clear();
			rp.setHealth(20.0);
		}
	}

	@EventHandler
	public void Portal(PlayerPortalEvent e) {
		Player p = e.getPlayer();
		Location l = p.getLocation();
		e.setCancelled(true);
		int xa = l.getBlockX();
		int ya = l.getBlockY();

		int za = l.getBlockZ();
		if (xa <= 11 && xa >= 4 && ya <= 13 && ya >= 5 && za == 922) {
			e.setCancelled(true);
			double x = settings.getData().getDouble("warps." + "ach" + ".x");
			double y = settings.getData().getDouble("warps." + "ach" + ".y");
			double z = settings.getData().getDouble("warps." + "ach" + ".z");
			Location la = new Location(p.getWorld(), x, y, z);
			p.teleport(la);
		}

		if (xa <= 18 && xa >= 10 && ya <= 18 && ya >= 10 && za == 840) {
			e.setCancelled(true);
			double x = settings.getData().getDouble("warps." + "1v1" + ".x");
			double y = settings.getData().getDouble("warps." + "1v1" + ".y");
			double z = settings.getData().getDouble("warps." + "1v1" + ".z");
			Location la = new Location(p.getWorld(), x, y, z);
			p.teleport(la);
		}

		if (l.getBlockX() == 58 && l.getBlockZ() >= 877 && l.getBlockZ() <= 883
				&& l.getBlockY() >= 9 && l.getBlockY() <= 19) {
			e.setCancelled(true);
			double x = settings.getData().getDouble("warps." + "PvP" + ".x");
			double y = settings.getData().getDouble("warps." + "PvP" + ".y");
			double z = settings.getData().getDouble("warps." + "PvP" + ".z");
			Location la = new Location(p.getWorld(), x, y, z);
			p.teleport(la);
		}
	}

	@EventHandler
	public void respawn(PlayerRespawnEvent e) {

		World w = Bukkit.getServer().getWorld(
				settings.getData().getString("spawn.world"));
		double x = settings.getData().getDouble("spawn.x");
		double y = settings.getData().getDouble("spawn.y");
		double z = settings.getData().getDouble("spawn.z");
		e.setRespawnLocation(new Location(w, x, y, z));
	}

	@SuppressWarnings("resource")
	public static void broadcastMessage(String fileName) throws IOException {
		FileInputStream fs = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));

		for (int i = 0; i < currentLine; i++) {
			br.readLine();
		}

		String line = br.readLine();
		line = line.replaceAll("&1", ChatColor.DARK_BLUE + "");
		line = line.replaceAll("&2", ChatColor.DARK_GREEN + "");
		line = line.replaceAll("&3", ChatColor.DARK_AQUA + "");
		line = line.replaceAll("&4", ChatColor.DARK_RED + "");
		line = line.replaceAll("&5", ChatColor.DARK_PURPLE + "");
		line = line.replaceAll("&6", ChatColor.GOLD + "");
		line = line.replaceAll("&7", ChatColor.GRAY + "");
		line = line.replaceAll("&8", ChatColor.DARK_GRAY + "");
		line = line.replaceAll("&9", ChatColor.BLUE + "");
		line = line.replaceAll("&a", ChatColor.GREEN + "");
		line = line.replaceAll("&b", ChatColor.AQUA + "");
		line = line.replaceAll("&c", ChatColor.RED + "");
		line = line.replaceAll("&d", ChatColor.LIGHT_PURPLE + "");
		line = line.replaceAll("&e", ChatColor.YELLOW + "");
		line = line.replaceAll("&f", ChatColor.WHITE + "");
		line = line.replaceAll("&k", ChatColor.MAGIC + "");
		line = line.replaceAll("&l", ChatColor.BOLD + "");
		line = line.replaceAll("&m", ChatColor.STRIKETHROUGH + "");
		line = line.replaceAll("&n", ChatColor.UNDERLINE + "");
		line = line.replaceAll("&o", ChatColor.ITALIC + "");
		line = line.replaceAll("&r", ChatColor.RESET + "");

		Bukkit.getServer().broadcastMessage(prefix + ChatColor.WHITE + line);

		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(
				fileName)));
		lnr.skip(9223372036854775807L);

		int lastLine = lnr.getLineNumber();

		if (currentLine + 1 == lastLine + 1) {
			currentLine = 0;
		} else {
			currentLine += 1;
		}
	}

	public void Hulk(Player p) {
		p.getInventory().clear();
		for (PotionEffect e : p.getActivePotionEffects()) {
			p.removePotionEffect(e.getType());

		}
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

		ItemStack helmet = createItemStack(Material.LEATHER_HELMET, (byte) 1,
				"Hulk Helmet", "EasterEgg", ChatColor.BLACK, ChatColor.GREEN);

		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.getInventory().addItem(new ItemStack(Material.ARROW));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public void Fisherman(Player p) {
		p.getInventory().clear();
		for (PotionEffect e : p.getActivePotionEffects()) {
			p.removePotionEffect(e.getType());
		}
		ItemStack fisher = specialItemSTack(Material.FISHING_ROD,
				"Fisherman Fishing rod");
		fisher.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		p.getInventory().addItem(fisher);
		p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.LEATHER_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 33; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	@EventHandler
	public void chata(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(hasPermission(p, "royalzone.helper.all")){
			e.setFormat(ChatColor.BLUE+"H> " + ChatColor.RESET+p.getName() +ChatColor.BLUE+"> " +e.getMessage() );
		}
		if (hasPermission(p, "royalzone.vip.all")) {

			p.setDisplayName("§b§l"+p.getName() + ChatColor.RESET + ChatColor.BOLD);
			return;
		}
		if (ta.isinTeam(p)) {

			String tag = ta.getTag(p, ta.getTeam(p)) + ChatColor.RESET;
			p.setDisplayName("[" + tag + "]" + p.getName());
		}
		if (!(ta.isinTeam(p))) {
			p.setDisplayName(p.getName());
		}
		if(hasPermission(p, "royalzone.mrsheler")){
			e.setFormat("§f§l<§r§9§lMrSheler5§r§f§l>§r§6 " + e.getMessage());
		}
		if(hasPermission(p, "royalzone.youtuber")){
p.setDisplayName("§9§l" + p.getName() + ChatColor.RESET+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.koren")) {
			p.setDisplayName("§a§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.kulikoko")) {
			p.setDisplayName("§5§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.spring")) {
			p.setDisplayName("§6§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.owner")) {
			p.setDisplayName("§3§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
			p.setCustomNameVisible(true);
		}
		if(hasPermission(p, "royalzone.hax")){
			p.setDisplayName(ChatColor.LIGHT_PURPLE+"§o§l[NOTMOD]Bear" + ChatColor.RESET);

		}

	}
	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {

		Player p = e.getPlayer();

		

		if (hasPermission(p, "royalzone.vip.all")) {

			p.setDisplayName("§b§l"+p.getName() + ChatColor.RESET + ChatColor.BOLD);
			return;
		}
		if (ta.isinTeam(p)) {

			String tag = ta.getTag(p, ta.getTeam(p)) + ChatColor.RESET;
			p.setDisplayName("[" + tag + "]" + p.getName());
		}
		if (!(ta.isinTeam(p))) {
			p.setDisplayName(p.getName());
		}
		if(hasPermission(p, "royalzone.mrsheler")){
			e.setFormat("§f§l<§r§9§lMrSheler5§r§f§l>§r§6 " + e.getMessage());
		}
		if(hasPermission(p, "royalzone.youtuber")){
p.setDisplayName("§9§l" + p.getName() + ChatColor.RESET+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.koren")) {
			p.setDisplayName("§a§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.kulikoko")) {
			p.setDisplayName("§5§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.spring")) {
			p.setDisplayName("§6§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
		}
		if (hasPermission(p, "royalzone.name.owner")) {
			p.setDisplayName("§3§l" + p.getName() + ChatColor.RESET
					+ ChatColor.BOLD);
			p.setCustomNameVisible(true);
		}
		if(hasPermission(p, "royalzone.hax")){
			p.setDisplayName(ChatColor.LIGHT_PURPLE+"§o§l[NOTMOD]Bear" + ChatColor.RESET);

		}

		if (e.getMessage().equalsIgnoreCase("swag")) {
			if(!hasAch(p, Achivments.SWAG)){
			Bukkit.getPluginManager().callEvent(
					new AchivementGetEvent(p, Achivments.SWAG));
			}
		}
		if (muted.contains(p.getName())) {
			e.setCancelled(true);
		}
		
		for (String word : e.getMessage().toLowerCase().split(" ")) {

			if (ac.getData().getStringList("badwords").contains(word)) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Dont Curse !");
				p.sendMessage(ChatColor.RED
						+ "10 Coins Removed From your Account");
				if (getCoins(p) == 0) {

				} else {
					RemoveCoin(p, 10);
				}
			}
		}
		for (String word : e.getMessage().split(" ")) {

			if (ac.getData().getStringList("badwords").contains(word)) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Dont Curse !");
				p.sendMessage(ChatColor.RED
						+ "10 Coins Removed From your Account");
				if (getCoins(p) == 0) {

				} else {
					RemoveCoin(p, 10);
				}
			}
		}

	}

	@EventHandler
	public void spawn(final ItemSpawnEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {

				e.getEntity().remove();
				e.getEntity()
						.getWorld()
						.playEffect(e.getEntity().getLocation(), Effect.SMOKE,
								100);
			}
		}, 60);
	}

	@EventHandler
	public void mainEvent(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		int i = getConfig().getInt(p.getUniqueId().toString() + ".coins");

		if (e.getInventory().getTitle()
				.equalsIgnoreCase(ChatColor.RED + "Shop")) {

			e.setCancelled(true);

			if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.DARK_GREEN + "Minion")) {

				if (i >= 225) {

					if (!(hasPermission(p, "RoyalZone.kits.minion"))) {

						Bukkit.getPluginManager().callEvent(
								new AchivementGetEvent(p, Achivments.FIRSTBUY));
						addPermissions(p, "RoyalZone.kits.minion");
						PrivateMessage(
								"Congratz :) You Bought the Minion kit !", p);
						RemoveCoin(p, 225);
						if (hasPermission(p, "RoyalZone.kits.minion")
								&& hasPermission(p, "RoyalZone.kits.spider")
								&& hasPermission(p, "RoyalZone.kits.smasher")
								&& hasPermission(p, "RoyalZone.kits.batman")
								&& hasPermission(p, "RoyalZone.kits.fisher")) {
							Bukkit.getPluginManager().callEvent(
									new AchivementGetEvent(p,
											Achivments.UNLOCK_EM_ALL));
						}
					} else {
						p.sendMessage(prefixa + ChatColor.DARK_RED
								+ "You already have this kit");
					}
				} else {
					p.sendMessage(prefixa + ChatColor.DARK_RED
							+ "You dont have enough money for that kit");

				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.WHITE + "Spider")) {
				if (i >= 150) {
					if (!(hasPermission(p, "RoyalZone.kits.spider"))) {

						Bukkit.getPluginManager().callEvent(
								new AchivementGetEvent(p, Achivments.FIRSTBUY));

						addPermissions(p, "RoyalZone.kits.spider");
						PrivateMessage(
								"Congratz :) You Bought the Spider kit !", p);
						RemoveCoin(p, 150);
						if (hasPermission(p, "RoyalZone.kits.minion")
								&& hasPermission(p, "RoyalZone.kits.spider")
								&& hasPermission(p, "RoyalZone.kits.smasher")
								&& hasPermission(p, "RoyalZone.kits.batman")
								&& hasPermission(p, "RoyalZone.kits.fisher")) {
							Bukkit.getPluginManager().callEvent(
									new AchivementGetEvent(p,
											Achivments.UNLOCK_EM_ALL));
						}
					} else {
						p.sendMessage(prefixa + ChatColor.DARK_RED
								+ "You already have this kit");
					}
				} else {
					p.sendMessage(prefixa + ChatColor.DARK_RED
							+ "You dont have enough money for that kit");

				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.BLACK + "Smasher")) {
				if (i >= 300) {
					if (!(hasPermission(p, "RoyalZone.kits.smasher"))) {

						Bukkit.getPluginManager().callEvent(
								new AchivementGetEvent(p, Achivments.FIRSTBUY));

						addPermissions(p, "RoyalZone.kits.smasher");
						PrivateMessage(
								"Congratz :) You Bought the Smasher kit !", p);
						RemoveCoin(p, 300);
						if (hasPermission(p, "RoyalZone.kits.minion")
								&& hasPermission(p, "RoyalZone.kits.spider")
								&& hasPermission(p, "RoyalZone.kits.smasher")
								&& hasPermission(p, "RoyalZone.kits.batman")
								&& hasPermission(p, "RoyalZone.kits.fisher")) {
							Bukkit.getPluginManager().callEvent(
									new AchivementGetEvent(p,
											Achivments.UNLOCK_EM_ALL));
						}
					} else {
						p.sendMessage(prefixa + ChatColor.DARK_RED
								+ "You already have this kit");
					}
				} else {
					p.sendMessage(prefixa + ChatColor.DARK_RED
							+ "You dont have enough money for that kit");

				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase("Batman")) {

				if (i >= 300) {
					if (!(hasPermission(p, "RoyalZone.kits.batman"))) {

						Bukkit.getPluginManager().callEvent(
								new AchivementGetEvent(p, Achivments.FIRSTBUY));

						addPermissions(p, "RoyalZone.kits.batman");
						PrivateMessage(
								"Congratz :) You Bought the Batman kit !", p);
						RemoveCoin(p, 300);
						if (hasPermission(p, "RoyalZone.kits.minion")
								&& hasPermission(p, "RoyalZone.kits.spider")
								&& hasPermission(p, "RoyalZone.kits.smasher")
								&& hasPermission(p, "RoyalZone.kits.batman")
								&& hasPermission(p, "RoyalZone.kits.fisher")) {

							Bukkit.getPluginManager().callEvent(
									new AchivementGetEvent(p,
											Achivments.UNLOCK_EM_ALL));
						}
					} else {
						p.sendMessage(prefixa + ChatColor.DARK_RED
								+ "You already have this kit");
					}
				} else {
					p.sendMessage(prefixa + ChatColor.DARK_RED
							+ "You dont have enough money for that kit");

				}

			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase("Fisherman")) {

				if (i >= 200) {
					if (!(hasPermission(p, "RoyalZone.kits.fisher"))) {

						Bukkit.getPluginManager().callEvent(
								new AchivementGetEvent(p, Achivments.FIRSTBUY));

						addPermissions(p, "RoyalZone.kits.fisher");
						PrivateMessage(
								"Congratz :) You Bought the Fisherman kit !", p);
						RemoveCoin(p, 200);
						if (hasPermission(p, "RoyalZone.kits.minion")
								&& hasPermission(p, "RoyalZone.kits.spider")
								&& hasPermission(p, "RoyalZone.kits.smasher")
								&& hasPermission(p, "RoyalZone.kits.batman")
								&& hasPermission(p, "RoyalZone.kits.fisher")) {

							Bukkit.getPluginManager().callEvent(
									new AchivementGetEvent(p,
											Achivments.UNLOCK_EM_ALL));
						}
					} else {
						p.sendMessage(prefixa + ChatColor.DARK_RED
								+ "You already have this kit");
					}
				} else {
					p.sendMessage(prefixa + ChatColor.DARK_RED
							+ "You dont have enough money for that kit");

				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase("Hulk")) {

				if (i >= 250) {
					if (!(hasPermission(p, "RoyalZone.kits.hulk"))) {

						Bukkit.getPluginManager().callEvent(
								new AchivementGetEvent(p, Achivments.FIRSTBUY));

						addPermissions(p, "RoyalZone.kits.hulk");
						PrivateMessage(
								"Congratz :), You Bought the Hulk kit !", p);
						RemoveCoin(p, 250);
						if (hasPermission(p, "RoyalZone.kits.minion")
								&& hasPermission(p, "RoyalZone.kits.spider")
								&& hasPermission(p, "RoyalZone.kits.smasher")
								&& hasPermission(p, "RoyalZone.kits.batman")
								&& hasPermission(p, "RoyalZone.kits.fisher")) {

							Bukkit.getPluginManager().callEvent(
									new AchivementGetEvent(p,
											Achivments.UNLOCK_EM_ALL));
						}
					} else {
						p.sendMessage(prefixa + ChatColor.DARK_RED
								+ "You already have this kit");
					}
				} else {
					p.sendMessage(prefixa + ChatColor.DARK_RED
							+ "You dont have enough money for that kit");

				}
			}

		}
	}

	public void setUpShop() {
		ItemStack Hulk = createItemStack(Material.FEATHER, (byte) 1, "Hulk",
				"250", ChatColor.BLACK, ChatColor.GOLD);

		ItemStack Batman = createItemStack(Material.EGG, (byte) 1, "Batman",
				"300", ChatColor.WHITE, ChatColor.GOLD);

		ItemStack Fisher = createItemStack(Material.FISHING_ROD, (byte) 1,
				"Fisherman", "200 Coins", ChatColor.RED, ChatColor.GOLD);

		ArrayList<String> smashlore = new ArrayList<String>();
		ArrayList<String> slore = new ArrayList<String>();
		ArrayList<String> weblore = new ArrayList<String>();
		smashlore.add(ChatColor.GOLD + "300 coins");
		slore.add(ChatColor.GOLD + "225 coins");
		weblore.add(ChatColor.GOLD + "150 coins");

		ItemStack glass = new ItemStack(Material.getMaterial(160), 1, (short) 9);
		ItemStack smash = new ItemStack(Material.GRASS);
		ItemMeta metaa = smash.getItemMeta();

		metaa.setLore(smashlore);
		metaa.setDisplayName(ChatColor.BLACK + "Smasher");
		smash.setItemMeta(metaa);
		ItemStack web = new ItemStack(Material.GOLD_SWORD);
		ItemMeta meta6 = web.getItemMeta();
		meta6.setDisplayName(ChatColor.WHITE + "Spider");
		meta6.setLore(weblore);
		web.setItemMeta(meta6);

		ItemStack s = new ItemStack(Material.SKULL_ITEM, (short) 1);
		ItemMeta ma = s.getItemMeta();
		ma.setDisplayName(ChatColor.DARK_GREEN + "Minion");
		ma.setLore(slore);

		s.setItemMeta(ma);
		ItemStack Hay = new ItemStack(Material.HAY_BLOCK);

		sShop.setItem(0, web);

		sShop.setItem(1, glass);
		sShop.setItem(2, Batman);
		sShop.setItem(3, glass);
		sShop.setItem(4, glass);
		sShop.setItem(5, glass);
		sShop.setItem(6, s);
		sShop.setItem(7, glass);
		sShop.setItem(8, Fisher);
		sShop.setItem(9, glass);
		sShop.setItem(10, glass);
		sShop.setItem(11, glass);
		sShop.setItem(12, glass);
		sShop.setItem(13, smash);
		sShop.setItem(14, glass);
		sShop.setItem(15, glass);
		sShop.setItem(16, glass);
		sShop.setItem(17, glass);
		sShop.setItem(18, Hulk);
		sShop.setItem(19, glass);
		sShop.setItem(20, Hay);
		sShop.setItem(21, glass);
		sShop.setItem(22, glass);
		sShop.setItem(23, glass);
		sShop.setItem(24, Hay);
		sShop.setItem(25, glass);
		sShop.setItem(26, Hay);

	}


	public void setUpTeamInventory() {
		ItemStack slots = new ItemStack(Material.COAL_ORE);
		ItemMeta smeta = slots.getItemMeta();
		smeta.setDisplayName(ChatColor.GREEN + "Team Slots");
		slots.setItemMeta(smeta);
		TeamShop.addItem(slots);
		ItemStack Tag = new ItemStack(Material.PAPER);
		ItemMeta meta = Tag.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "ChangeTag Paper");
		ArrayList<String> tagmeta = new ArrayList<String>();
		tagmeta.add(ChatColor.GOLD + "100 Coins");
		meta.setLore(tagmeta);
		Tag.setItemMeta(meta);
		TeamShop.addItem(Tag);
		ItemStack Dye = new ItemStack(Material.INK_SACK);
		ItemMeta dmeta = Dye.getItemMeta();
		dmeta.setDisplayName("Tag Colors");
		Dye.setItemMeta(dmeta);
		TeamShop.addItem(Dye);
		ItemStack Red = new ItemStack(Material.INK_SACK, 1, (short) 1);
		ItemMeta rmeta = Red.getItemMeta();
		rmeta.setDisplayName(ChatColor.RED + "Red");
		Red.setItemMeta(rmeta);
		colors.addItem(Red);
		ItemStack Green = new ItemStack(Material.INK_SACK, 1, (short) 2);
		ItemMeta gmeta = Green.getItemMeta();
		gmeta.setDisplayName(ChatColor.DARK_GREEN + "Dark Green");
		Green.setItemMeta(gmeta);
		colors.addItem(Green);
		ItemStack Blue = new ItemStack(Material.INK_SACK, 1, (short) 4);
		ItemMeta bmeta = Blue.getItemMeta();
		bmeta.setDisplayName(ChatColor.BLUE + "Blue");
		Blue.setItemMeta(bmeta);
		colors.addItem(Blue);
		ItemStack PURPLE = new ItemStack(Material.INK_SACK, 1, (short) 5);
		ItemMeta pmeta = PURPLE.getItemMeta();
		pmeta.setDisplayName(ChatColor.DARK_PURPLE + "Purple");
		PURPLE.setItemMeta(pmeta);
		colors.addItem(PURPLE);
		ItemStack GRAY = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta grmeta = GRAY.getItemMeta();
		grmeta.setDisplayName(ChatColor.GRAY + "Gray");
		GRAY.setItemMeta(grmeta);
		colors.addItem(GRAY);
		ItemStack LIGHT_PURPLE = new ItemStack(Material.INK_SACK, 1, (short) 9);
		ItemMeta lpmeta = LIGHT_PURPLE.getItemMeta();
		lpmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Pink");
		LIGHT_PURPLE.setItemMeta(lpmeta);
		colors.addItem(LIGHT_PURPLE);
		ItemStack YELLOW = new ItemStack(Material.INK_SACK, 1, (short) 11);
		ItemMeta ymeta = YELLOW.getItemMeta();
		ymeta.setDisplayName(ChatColor.YELLOW + "Yellow");
		YELLOW.setItemMeta(ymeta);
		colors.addItem(YELLOW);
		ItemStack Gold = new ItemStack(Material.INK_SACK, 1, (short) 11);
		ItemMeta gameta = Gold.getItemMeta();
		gameta.setDisplayName(ChatColor.GOLD + "Gold");

		Gold.setItemMeta(gameta);
		colors.addItem(Gold);

	}
	@EventHandler
	public void commandPreprocess(PlayerCommandPreprocessEvent e) {
		if (spy.size() > 0){
			for(String s : spy){
				Bukkit.getPlayer(s).sendMessage(prefix + "The Player " + e.getPlayer().getName() + " Has Exectue command !" );
				Bukkit.getPlayer(s).sendMessage(e.getMessage());
			}
		}
        Pattern p = Pattern.compile("^/([a-zA-Z0-9_]+):");
        Matcher m = p.matcher(e.getMessage());
        String pluginRef;
      
        if (m.find()) pluginRef = m.group(1);
        else return;
      
        for (Plugin plugin : getServer().getPluginManager().getPlugins()) {
            if (plugin.getName().toLowerCase().equals(pluginRef.toLowerCase())
                    || pluginRef.toLowerCase().equals("bukkit")
                    || pluginRef.toLowerCase().equals("minecraft")) {
                e.getPlayer().sendMessage("INVALID_CMD");
                e.setCancelled(true);
                break;
            }
        }
	}
	public boolean hasPermission(Player p, String permission) {
		return instance.getData().getStringList(p.getUniqueId().toString())
				.contains(permission);
	}

	public boolean isVip(Player p) {

		return hasPermission(p, "royalzone.vip.all");
	}
@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	  public void onLeavesDecay(LeavesDecayEvent event)
	  {
	    event.setCancelled(true);
	  }
	@EventHandler
	public void Soups(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.MAP) {

			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

				Villager v = (Villager) p.getWorld().spawn(p.getLocation(),Villager.class);

				v.setCustomName(ChatColor.GREEN
						+ p.getItemInHand().getItemMeta().getDisplayName());

			}
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.ENDER_CHEST || e.getClickedBlock().getType() == Material.TRAPPED_CHEST){
				e.setCancelled(true);

				Inventory inv = Bukkit.createInventory(null, 54, "Free Soups");
				for (int x = 0; x < 54; x++) {
					inv.addItem(new ItemStack(Material.MUSHROOM_SOUP));
				}
				p.openInventory(inv);
			}			
		}
		

		int SoupRegen = 7;
		Damageable PlayerHealth = p;
		if ((PlayerHealth.getHealth() != 20)
				&& ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
				&& (p.getItemInHand().getType() == Material.MUSHROOM_SOUP)) {
			e.setCancelled(true);
			p.setHealth(PlayerHealth.getHealth() + SoupRegen > PlayerHealth
					.getMaxHealth() ? PlayerHealth.getMaxHealth()
					: PlayerHealth.getHealth() + SoupRegen);
			p.setItemInHand(new ItemStack(Material.BOWL));
			p.updateInventory();
		}
	}

	@EventHandler
	public void CommandsSend(PlayerCommandPreprocessEvent e) {
	Player p = e.getPlayer();
	if(ovo.red.contains(p.getName()) || ovo.blue.contains(p.getName()) && ovo.getStatus()  == OneStatus.STARTED || ovo.getStatus() == OneStatus.COUNTDOWN || Blue.contains(p.getName()) || Red.contains(p.getName())){
	if(!(e.getMessage().equalsIgnoreCase("/1v1 leave") || e.getMessage().equalsIgnoreCase("/1v12 leave") || e.getMessage().equalsIgnoreCase("/kit hero") || e.getMessage().equalsIgnoreCase("/god") || e.getMessage().equalsIgnoreCase("/fly"))) {
	if(!p.isOp()){
	p.sendMessage("§lYou can't run commands in the 1v1 arena!");
	e.setCancelled(true);
	}
	}
	}
	}
	@EventHandler
	public void sign(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("soup")) {
			e.setLine(0, "==============");
			e.setLine(1, ChatColor.GREEN + "Free Soups");
			e.setLine(2, ChatColor.RED + "Right Click");
			e.setLine(3, "==============");

		}
		if (e.getLine(0).equalsIgnoreCase("regular")) {
			e.setLine(1, ChatColor.YELLOW + "Regular Kits");
			e.setLine(2, ChatColor.BLACK + "Right Click");
		}
		if (e.getLine(0).equalsIgnoreCase("1v12")) {
			e.setLine(0, null);
			e.setLine(1, ChatColor.GREEN + "Join 1v1 2");
		}
		if (e.getLine(0).equalsIgnoreCase("1v1")) {
			e.setLine(0, null);
			e.setLine(1, ChatColor.GREEN + "Join 1v1");

		}
		if (e.getLine(0).equalsIgnoreCase("main")) {
			e.setLine(1, ChatColor.GREEN + "Kits");
			e.setLine(2, "Right Click");
			e.setLine(0, null);
		}
		if (e.getLine(0).equalsIgnoreCase("return")) {
			e.setLine(0, null);
			e.setLine(1, ChatColor.DARK_PURPLE + "Return to Hub");
		}
		if (e.getLine(0).equalsIgnoreCase("shop")) {
			e.setLine(1, ChatColor.RED + "Shop Kits");
			e.setLine(2, ChatColor.BLACK + "Right Click");
		}
		if (e.getLine(0).equalsIgnoreCase("Youtuber")) {
			e.setLine(0, ChatColor.BLACK + "You" + ChatColor.RED + "Tuber");

		}
	}

	@EventHandler
	public void quit(PlayerQuitEvent e) {
		if (invited.containsKey(e.getPlayer())) {
			invited.remove(e.getPlayer());
		}
	}

	
	@EventHandler
	public void Kitchoose(KitChooseEvent e) {
		e.getPlayer().setHealth(20);
	}

	@EventHandler
	public void WitherArcherArrow(ProjectileLaunchEvent e) {
		new ItemStack(Material.FIREBALL, 1, (byte) 1);
		if (e.getEntity() instanceof Arrow) {
			if (e.getEntity().getShooter() instanceof Player) {
				Player p = (Player) e.getEntity().getShooter();

				if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){
					if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.WHITE + "GhastBow")) {
						e.getEntity().remove();
						PlayerCooldown pc = Cooldown.getCooldown("DWAa",
								p.getName());
						if (pc == null) {
							Fireball WitherSkull = p
									.launchProjectile(Fireball.class);
							WitherSkull.setShooter(p);
							WitherSkull.setFallDistance(100);

							WitherSkull.setVelocity(p.getLocation()
									.getDirection().multiply(1));
							p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 5,
									1);
							e.getEntity().remove();

							Cooldown.addCooldown("DWAa", p.getName(), 2000);
						} else {
							final int Time = pc.getTimeLeft() / 2000;
							if (pc.isOver()) {
								Fireball WitherSkull = p
										.launchProjectile(Fireball.class);
								WitherSkull.setShooter(p);
								WitherSkull.setVelocity(p.getLocation()
										.getDirection().multiply(1));
								p.playSound(p.getLocation(),
										Sound.GHAST_SCREAM, 5, 1);
								e.getEntity().remove();
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
		}
	

	@EventHandler
	public void WitherExplosion(EntityExplodeEvent e) {
		if (e.getEntity() instanceof Fireball) {
			e.getEntity();
			e.setCancelled(true);
		}
	}

	public static ItemStack createItemStack(Material itemMaterial, byte iByte,
			String iName, String iLore, ChatColor iDisplayClr,
			ChatColor iLoreClr) {
		ItemStack iStack = new ItemStack(itemMaterial, 1, iByte);
		ItemMeta iData = iStack.getItemMeta();

		iData.setLore(null);
		// iData.setDisplayName(iDisplayClr + iName);
		iData.setDisplayName(iName);
		ArrayList<String> bLore = new ArrayList<String>();
		bLore.add(iLoreClr + iLore);
		iData.setLore(bLore);
		iStack.setItemMeta(iData);

		return iStack;
	}

	@EventHandler
	public void ChickenEgg(PlayerEggThrowEvent e) {
		Player p = e.getPlayer();
		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().getType() == Material.CHAINMAIL_HELMET) {
				e.setHatching(false);
				Location loc = new Location(Bukkit.getWorld("world"), e
						.getEgg().getLocation().getX(), e.getEgg()
						.getLocation().getY() + 0.5, e.getEgg().getLocation()
						.getZ());
				final Bat C1 = (Bat) p.getWorld().spawnEntity(loc,
						EntityType.BAT);
				final Bat C2 = (Bat) p.getWorld().spawnEntity(loc,
						EntityType.BAT);
				final Bat C3 = (Bat) p.getWorld().spawnEntity(loc,
						EntityType.BAT);
				C1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						1000000, 3));
				C2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						1000000, 3));
				C3.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						1000000, 3));
				C1.setCustomName("§4Evil Bat [ §c" + p.getName() + " §4]");
				C2.setCustomName("§4Evil Bat [ §c" + p.getName() + " §4]");
				C3.setCustomName("§4Evil Bat [ §c" + p.getName() + " §4]");
				C1.damage(0.01, p);
				C2.damage(0.01, p);
				C3.damage(0.01, p);
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								C1.getWorld().createExplosion(
										C1.getLocation().getX(),
										C1.getLocation().getY(),
										C1.getLocation().getZ(), 3F, false,
										false);
								C1.setHealth(0.00);
							}
						}, 60L);
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								C2.getWorld().createExplosion(
										C2.getLocation().getX(),
										C2.getLocation().getY(),
										C2.getLocation().getZ(), 3F, false,
										false);
								C2.setHealth(0.00);
							}
						}, 60L);
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								C3.getWorld().createExplosion(
										C3.getLocation().getX(),
										C3.getLocation().getY(),
										C3.getLocation().getZ(), 3F, false,
										false);
								C3.setHealth(0.00);
							}
						}, 60L);
			}
		}
	}

	public void Batman(Player p) {
		p.getInventory().clear();
		for (PotionEffect f : p.getActivePotionEffects()) {
			p.removePotionEffect(f.getType());

		}
		ItemStack bat = createItemStack(Material.EGG, (byte) 0, "Super Egg",
				"Right Click it", ChatColor.GOLD, ChatColor.YELLOW);
		ItemStack helmet = createItemStack(Material.CHAINMAIL_HELMET, (byte) 0,
				"Super Helmet", "Special Helmet", ChatColor.AQUA,
				ChatColor.GREEN);

		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		for (int x = 0; x < 10; x++)
			p.getInventory().addItem(bat);
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));

		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public void Rabbit(Player p) {
		p.getInventory().clear();
		for (PotionEffect f : p.getActivePotionEffects()) {
			p.removePotionEffect(f.getType());

		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 3));
		ItemStack carrot = createItemStack(Material.GOLDEN_CARROT, (byte) 0,
				"Super Carrot", "Right Click it", ChatColor.AQUA,
				ChatColor.GREEN);
		ItemStack helmet = createItemStack(Material.DIAMOND_HELMET, (byte) 0,
				"Super Helmet", "Special Helmet", ChatColor.AQUA,
				ChatColor.GREEN);

		p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
		for (int x = 0; x < 10; x++)
			p.getInventory().addItem(carrot);
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));

		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	@EventHandler
	public void WitherSkullPotion(ProjectileHitEvent e) {
		if (e.getEntity() instanceof WitherSkull) {
			if (e.getEntity().getShooter() instanceof Player) {
				for (Player Ap : Bukkit.getOnlinePlayers()) {
					if (e.getEntity().getLocation().distance(Ap.getLocation()) <= 3) {
						if (Ap != e.getEntity().getShooter()) {
							Ap.playSound(Ap.getLocation(), Sound.GHAST_DEATH,
									5, 1);
							Ap.addPotionEffect(new PotionEffect(
									PotionEffectType.WITHER, 120, 0));
						}
					}
				}
			}
		}
	}

	public boolean hasAch(Player p, Achivments c) {
		List<String> ach = am.getData().getStringList(
				p.getUniqueId().toString() + ".Achivments");
		
	if(ach.contains(c.toString().toLowerCase())){
		return true;
		}
		return false;
	}
	public ArrayList<String> newLore(String name){
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(name);
		return lore;
		}
	@EventHandler
	public void Click(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Villager) {
			Villager v = (Villager) e.getRightClicked();
			if (v.getCustomName().equalsIgnoreCase(ChatColor.GREEN + "Bob")) {
				e.setCancelled(true);
				Inventory ach = Bukkit.createInventory(null, 27, ChatColor.AQUA
						+ "Achievments");

				Player p = e.getPlayer();
				ItemStack SoupMaster = new ItemStack(Material.REDSTONE_LAMP_OFF);
				ItemMeta SoupMasterMeta = SoupMaster.getItemMeta();
				if (!(hasAch(p, Achivments.SOUP_MASTER))) {

				} else {
					SoupMaster.setType(Material.GLOWSTONE);
				 SoupMasterMeta.setDisplayName(
							ChatColor.GREEN + "Soup Master");
					p.updateInventory();

				}
					SoupMasterMeta.setLore(newLore(ChatColor.GREEN+"Kill a guy with a Mushroom Soup"));

				SoupMasterMeta.setDisplayName(ChatColor.RED + "Soup Master");
				SoupMaster.setItemMeta(SoupMasterMeta);

				ItemStack Marksman = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.MARKSMAN)) {
					Marksman.setType(Material.GLOWSTONE);
				}
				ItemMeta MarksmanMeta = Marksman.getItemMeta();
				MarksmanMeta.setDisplayName(ChatColor.RED + "Marksman");
				MarksmanMeta.setLore(newLore(ChatColor.GREEN+"Kill a guy with a bow"));

				Marksman.setItemMeta(MarksmanMeta);

				ItemStack AirBone = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.AIRBONE)) {

					AirBone.setType(Material.GLOWSTONE);
					p.updateInventory();

				}


				ItemMeta AirBoneMeta = AirBone.getItemMeta();
				AirBoneMeta.setDisplayName(ChatColor.RED + "AirBone");
				AirBoneMeta.setLore(newLore(ChatColor.GREEN+"Kill a guy while in air"));

				AirBone.setItemMeta(AirBoneMeta);

				ItemStack GodOfWar = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.GOD_OF_WAR)) {
					GodOfWar.setType(Material.GLOWSTONE);
				}
				ItemMeta GodOfWarMeta = GodOfWar.getItemMeta();
				GodOfWarMeta.setLore(newLore(ChatColor.GREEN+"get 1000 Kills"));

				GodOfWarMeta.setDisplayName(ChatColor.RED + "God Of War");
				GodOfWar.setItemMeta(GodOfWarMeta);

				ItemStack AmaTurtle = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.AMA_TUTRLE)) {
					AmaTurtle.setType(Material.GLOWSTONE);
				}
				ItemMeta AmaTurtleMeta = AmaTurtle.getItemMeta();

				AmaTurtleMeta.setLore(newLore(ChatColor.GREEN+"Kill a guy while Sneaking"));

				AmaTurtleMeta.setDisplayName(ChatColor.RED + "Ama Turtle");
				AmaTurtle.setItemMeta(AmaTurtleMeta);

				ItemStack Swag = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.SWAG)) {
					Swag.setType(Material.GLOWSTONE);
				}
				ItemMeta SwagMeta = Swag.getItemMeta();
				SwagMeta.setLore(newLore(ChatColor.GREEN+"write swag in chat"));

				SwagMeta.setDisplayName(ChatColor.RED + "Swag");
				Swag.setItemMeta(SwagMeta);

				ItemStack Zeus = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.ZEUS)) {
					Zeus.setType(Material.GLOWSTONE);
				}
				ItemMeta ZeusMeta = Zeus.getItemMeta();
				ZeusMeta.setLore(newLore(ChatColor.GREEN+"Kill a guy while in a lava"));

				ZeusMeta.setDisplayName(ChatColor.RED + "Zeus");
				Zeus.setItemMeta(ZeusMeta);

				ItemStack UnlockEmAll = new ItemStack(
						Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.UNLOCK_EM_ALL)) {
					UnlockEmAll.setType(Material.GLOWSTONE);
				}
				ItemMeta UnlockEmAllMeta = UnlockEmAll.getItemMeta();
				UnlockEmAllMeta.setLore(newLore(ChatColor.GREEN+"Buy All the Shop Kits."));

				UnlockEmAllMeta.setDisplayName(ChatColor.RED + "Unlock Em All");
				UnlockEmAll.setItemMeta(UnlockEmAllMeta);

				ItemStack Immortal = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.IMMORTAL)) {
					Immortal.setType(Material.GLOWSTONE);
				}
				ItemMeta ImmortalMeta = Immortal.getItemMeta();
				ImmortalMeta.setDisplayName(ChatColor.RED + "Immortal");
				ImmortalMeta.setLore(newLore(ChatColor.GREEN+"Get a KillStreak of 50"));

				Immortal.setItemMeta(ImmortalMeta);

				ItemStack UnBelieveAble = new ItemStack(
						Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.UNBELIEVEABLE)) {
					UnBelieveAble.setType(Material.GLOWSTONE);
				}
				ItemMeta UnBelieveAbleMeta = UnBelieveAble.getItemMeta();
				UnBelieveAbleMeta.setLore(newLore(ChatColor.GREEN+"Get a KillStreak of 20"));

				UnBelieveAbleMeta.setDisplayName(ChatColor.RED
						+ "UnBelieveAble");
				UnBelieveAble.setItemMeta(UnBelieveAbleMeta);

				ItemStack UnStopAble = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.UNSTOPABLE)) {
					UnStopAble.setType(Material.GLOWSTONE);
				}
				ItemMeta UnStopAbleMeta = UnStopAble.getItemMeta();
				UnStopAbleMeta.setLore(newLore(ChatColor.GREEN+"Get a KillStreak of 10"));

				UnStopAbleMeta.setDisplayName(ChatColor.RED + "UnStopAble");
				UnStopAble.setItemMeta(UnStopAbleMeta);

				ItemStack Rusher = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.RUSHER)) {
					Rusher.setType(Material.GLOWSTONE);
				}
				ItemMeta RusherMeta = Rusher.getItemMeta();
				RusherMeta.setLore(newLore(ChatColor.GREEN+"Get a KillStreak of 5"));

				RusherMeta.setDisplayName(ChatColor.RED + "Rusher");
				Rusher.setItemMeta(RusherMeta);

				ItemStack Poseidon = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.POSEIDON)) {
					Poseidon.setType(Material.GLOWSTONE);
				}
				ItemMeta PoseidonMeta = Poseidon.getItemMeta();
				PoseidonMeta.setLore(newLore(ChatColor.GREEN+"Kill a guy while in water"));

				PoseidonMeta.setDisplayName(ChatColor.RED + "Poseidon");
				Poseidon.setItemMeta(PoseidonMeta);

				ItemStack Donator = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.DONATOR)) {
					Donator.setType(Material.GLOWSTONE);
				}
				ItemMeta DonatorMeta = Donator.getItemMeta();
				DonatorMeta.setDisplayName(ChatColor.RED + "Donator");
				DonatorMeta.setLore(newLore(ChatColor.GREEN+"Donate to the Server."));

				Donator.setItemMeta(DonatorMeta);

				ItemStack FirstBuy = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.FIRSTBUY)) {
					FirstBuy.setType(Material.GLOWSTONE);
				}
				ItemMeta FirstBuyMeta = FirstBuy.getItemMeta();
				FirstBuyMeta.setDisplayName(ChatColor.RED + "FirstBuy");
				FirstBuyMeta.setLore(newLore(ChatColor.GREEN+"Buy Your first kit !"));

				FirstBuy.setItemMeta(FirstBuyMeta);

				ItemStack Odin = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.ODINPIXELKILLER)) {
					Odin.setType(Material.GLOWSTONE);
				}
				ItemMeta OdinMeta = Odin.getItemMeta();
				OdinMeta.setDisplayName(ChatColor.RED + "OdinPixel Killer");
				OdinMeta.setLore(newLore(ChatColor.GREEN+"Kill OdinPixel"));

				Odin.setItemMeta(OdinMeta);

				ItemStack FirstJoin = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.FIRSTJOIN)) {
					FirstJoin.setType(Material.GLOWSTONE);
				}
				ItemMeta firstmeta = FirstJoin.getItemMeta();
				firstmeta.setDisplayName(ChatColor.RED + "FirstJoin");
				firstmeta.setLore(newLore(ChatColor.GREEN+"Your first Join !"));

				FirstJoin.setItemMeta(firstmeta);

				ItemStack ovo = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.OVOWINNER)) {
					ovo.setType(Material.GLOWSTONE);
				}
				ItemMeta ovoMeta = ovo.getItemMeta();
				ovoMeta.setDisplayName(ChatColor.RED + "1v1Winner");
				ovoMeta.setLore(newLore(ChatColor.GREEN+"win at the 1v1"));

				ovo.setItemMeta(ovoMeta);
				

				ItemStack Recent = new ItemStack(Material.REDSTONE_LAMP_OFF);
				if (hasAch(p, Achivments.RecentWolfKiller)) {
					Recent.setType(Material.GLOWSTONE);
				}
				ItemMeta rMeta = Recent.getItemMeta();
				rMeta.setDisplayName(ChatColor.RED + "RecentWolfKiller");
				rMeta.setLore(newLore(ChatColor.GREEN+"Kill RecentWolf"));

				Recent.setItemMeta(rMeta);
				ach.addItem(Recent);
				ach.addItem(ovo);
				ach.addItem(FirstJoin);
				ach.addItem(Odin);
				ach.addItem(FirstBuy);
				ach.addItem(Donator);
				ach.addItem(Rusher);
				ach.addItem(UnStopAble);
				ach.addItem(UnBelieveAble);
				ach.addItem(Poseidon);
				ach.addItem(Zeus);
				ach.addItem(Immortal);
				ach.addItem(UnlockEmAll);
				ach.addItem(AmaTurtle);
				ach.addItem(Swag);
				ach.addItem(GodOfWar);
				ach.addItem(Marksman);
				ach.addItem(SoupMaster);
				ach.addItem(AirBone);
				p.openInventory(ach);
			}

		}
	}

	public boolean isinWater(Player k){
		if(k.getLocation().getBlock().getType() == Material.STATIONARY_WATER || k.getLocation().getBlock().getType() == Material.WATER){
			return true;
		}
		return false;
	}
	@EventHandler
	public void Kill(EntityDeathEvent e) {
		if(e.getEntity().getLastDamageCause().equals(DamageCause.VOID)){
			
		}
		if (e.getEntity() instanceof Player
				&& e.getEntity().getKiller() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player k = e.getEntity().getKiller();

			if (!(k.isOnGround())) {
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.AIRBONE));

			}
			if (k.getItemInHand().equals(new ItemStack(Material.MUSHROOM_SOUP))) {
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.SOUP_MASTER));

			}
			if (k.getItemInHand().getType() == Material.BOW) {
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.MARKSMAN));

			}
			if (getConfig().getInt(k.getUniqueId().toString() + ".kills") == 1000) {
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.GOD_OF_WAR));

			}
			if (k.isSneaking()) {
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.AMA_TUTRLE));

			}
			if(k.getLocation().getBlock().getType() == Material.STATIONARY_LAVA || k.getLocation().getBlock().getType() == Material.LAVA){
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.ZEUS));

			}
			if(k.getLocation().getBlock().getType() == Material.STATIONARY_WATER || k.getLocation().getBlock().getType() == Material.WATER){
				Bukkit.getPluginManager().callEvent(
						new AchivementGetEvent(k, Achivments.POSEIDON));
			}
			if (!ta.isinTeam(p)) {
				return;

			}
			tm.getData().set(ta.getTeam(p) + " Deaths",
					tm.getData().getInt(ta.getTeam(p) + " Deaths" + 1));

			if (!ta.isinTeam(k)) {
				return;
			}
			tm.getData().set(ta.getTeam(k) + " Kills",
					tm.getData().getInt(ta.getTeam(k) + " Kills" + 1));

		}
	}

	@EventHandler
	public void WitherArcherHit(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof Villager
				&& e.getDamager() instanceof Player) {
			Player k = (Player) e.getDamager();
			if (k.isOp()) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
			}
		}

		if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
			Player p = (Player) e.getEntity();

			Arrow a = (Arrow) e.getDamager();
			Player k = (Player) a.getShooter();
			if (ta.getTeam(p) != null && ta.getTeam(k) != null){
			if (ta.getTeam(p).equals(ta.getTeam(k))) {
				e.setCancelled(true);

			}
			}
		}

		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player k = (Player) e.getDamager();
			if(ta.getTeam(k) != null && ta.getTeam(p) != null){
			if (ta.getTeam(k).equals(ta.getTeam(p))) {
				if(ovo.red.contains(p.getName()) && ovo.blue.contains(k.getName())){
					e.setCancelled(true);
				}else{
					e.setCancelled(false);
				}
				if (!(Blue.contains(p.getName()) && Red.contains(k.getName()))) {

					if (!(Blue.contains(k.getName()) && Red.contains(p
							.getName()))) {

						e.setCancelled(true);
					} else {

						e.setCancelled(false);
					}
				} else {

					e.setCancelled(false);
				}
			}
			}
		} else if (e.getDamager() instanceof Fireball) {

			
			
		
			
			e.getEntity();

			double Da = e.getDamage();
			
		((Player) e.getEntity()).damage(Da * 3, ((Player) e.getDamager()));

		}

	}

	@EventHandler
	public void Click(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {

			return;
		}

		try {
			Sign s = (Sign) e.getClickedBlock().getState();

			if (s.getLine(1).equalsIgnoreCase(ChatColor.GREEN + "Free Soups")) {
				Inventory inv = Bukkit.createInventory(null, 54, "Free Soups");
				for (int x = 0; x < 54; x++) {
					inv.addItem(new ItemStack(Material.MUSHROOM_SOUP));
				}
				p.openInventory(inv);
			}
			if (s.getLine(0).equalsIgnoreCase("V.I.P Kits")) {
				p.openInventory(VIP);
			}

			if (s.getLine(1)
					.equalsIgnoreCase(ChatColor.YELLOW + "Regular Kits")) {
				p.openInventory(Regular);
			}
			if(s.getLine(1).equalsIgnoreCase(ChatColor.GREEN+"Join 1v1 2")){
				p.chat("/1v12 yossijoinaba");
			}
			if (s.getLine(1).equalsIgnoreCase(ChatColor.GREEN + "Join 1v1")) {
			
							
				p.chat("/1v1 yossijoinaba");
			}
			if (s.getLine(1).equalsIgnoreCase(ChatColor.GREEN + "Kits")) {
				p.openInventory(Main);
			}
			if (s.getLine(1).equalsIgnoreCase(
					ChatColor.DARK_PURPLE + "Return to hub")) {
				World w = Bukkit.getServer().getWorld(
						settings.getData().getString("spawn.world"));
				double x = settings.getData().getDouble("spawn.x");
				double y = settings.getData().getDouble("spawn.y");
				double z = settings.getData().getDouble("spawn.z");
				Location l = new Location(w, x, y, z);
				p.teleport(l);
			}
			if (s.getLine(1).equalsIgnoreCase(ChatColor.RED + "Shop Kits")) {
				p.openInventory(Shop);
			}
			if (s.getLine(0).equalsIgnoreCase(
					ChatColor.BLACK + "You" + ChatColor.RED + "Tuber")) {
				p.openInventory(Youtuber);

			}
		} catch (ClassCastException e2) {

		}
	}

	public void SpawnCommand(final Player p, final Location l) {
		p.sendMessage(prefix + ChatColor.GREEN + "Dont move, 3 seconds.");
		teleport.add(p.getName());
		if (!(p.isOp())) {

			Bukkit.getScheduler().scheduleAsyncDelayedTask(this,
					new Runnable() {

						@Override
						public void run() {
							if (teleport.contains(p.getName())) {
								p.teleport(l);
								teleport.remove(p.getName());
								p.sendMessage(prefix
										+ "Teleportation successed!");
								p.getInventory().clear();
								p.getPlayer().getInventory()
										.setArmorContents(null);

							}
						}
					}, 70);
		} else {
			p.teleport(l);
		}
	}

	@EventHandler
	public void move(PlayerMoveEvent e) {
		final Player p = e.getPlayer();

		if (e.getPlayer() instanceof Villager) {
			e.setCancelled(true);
		}
		if (teleport.contains(p.getName())) {
			double xfrom = e.getFrom().getX();
			double zfrom = e.getFrom().getZ();
			double xto = e.getTo().getX();
			double zto = e.getTo().getZ();
			if (!(xfrom == xto && zfrom == zto)) {
				teleport.remove(p.getName());
				p.sendMessage(prefix + "You Moved !");
			}
		}
		ItemStack Bow = new ItemStack(Material.DIAMOND_SWORD);
		Bow.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Jumper Sword");
		Bow.setItemMeta(meta);

		if (p.getInventory().contains(Bow)) {
			if (p.isSneaking()) {
				Bukkit.getScheduler().scheduleAsyncRepeatingTask(this,
						new Runnable() {
							int Count = 0;

							@Override
							public void run() {
								if (!p.isSneaking()) {
									Count = 0;
								} else {
									if (Main.this.Count.contains(p.getName())) {
										Count++;
									} else {
										p.sendMessage(ChatColor.GREEN
												+ "Charging the Skill");
										Main.this.Count.add(p.getName());

										Count++;
									}

									if (Count == 10) {
										if (Jump.contains(p.getName())) {
											Count = 0;
										}
										if (Charge.contains(p.getName())) {
											p.setVelocity(new Vector(p
													.getVelocity().getBlockX(),
													2, p.getVelocity()
															.getBlockZ()));

											Jump.add(p.getName());
											Count = 0;
										} else {
											p.sendMessage(ChatColor.GREEN
													+ "Skill is Charged !");
											Charge.add(p.getName());

											p.setVelocity(new Vector(p
													.getVelocity().getBlockX(),
													2, p.getVelocity()
															.getBlockZ()));

											Jump.add(p.getName());

											Count = 0;

										}

									}
								}
							}
						}, 0, 20);
			}
		}
	}

	public ItemStack addspecialItem(Material m, String name, int amount,
			ChatColor color) {
		ItemStack s = new ItemStack(m);
		ItemMeta meta = s.getItemMeta();
		meta.setDisplayName(color + name);
		s.setItemMeta(meta);

		return s;
	}

	public void Troller(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack s = addspecialItem(Material.JACK_O_LANTERN,
				"Troller Pumpkim", 1, ChatColor.GOLD);
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

		p.getInventory().addItem(s);
		for (int x = 0; x < 33; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));

		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}


	public void Gangsta(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack PistolAmmo = new ItemStack(Material.SEEDS);

		ItemStack Hoe = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta HMeta = Hoe.getItemMeta();
		HMeta.setDisplayName(ChatColor.GREEN + "Gangsta Pistol");
		HMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		Hoe.setItemMeta(HMeta);

		p.getInventory().addItem(Hoe);

		p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 32; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		for (int x = 0; x < 64; x++) {
			p.getInventory().addItem(PistolAmmo);
		}
		p.getInventory().addItem(PistolAmmo);
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));
	}

	public void alchemist(Player p) {

	}

	@EventHandler
	public void join(PlayerQuitEvent e) {
		if (logged.contains(e.getPlayer().getName())) {
			e.getPlayer().setHealth(0);
			logged.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	public void entity(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if(ta.getTeam(p) != null && ta.getTeam(((Player) e.getDamager())) != null){
			if (!isinRegion(p) || ta.getTeam(p).equals(ta.getTeam(((Player) e.getDamager())))){
			if (logged.contains(p.getName())) {

			} else {
				logged.add(p.getName());
				p.sendMessage(prefixa + ChatColor.RED
						+ "You are in a Combat ! Wait 3 Seconds.");
				Bukkit.getScheduler().scheduleAsyncDelayedTask(this,
						new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								logged.remove(p.getName());
								p.sendMessage(prefixa + ChatColor.GREEN
										+ "You are'nt in a combat");
							}
						}, 70L);
			}
			}
		}
		}

	}

	public void Groopy(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack pink = new ItemStack(Material.getMaterial(351), 1, (short) 9);
		ItemMeta pmeta = pink.getItemMeta();
		pmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Groopy Wand");
		pink.setItemMeta(pmeta);
		ItemStack Bow = new ItemStack(Material.IRON_SWORD);
		Bow.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Groopy Sword");
		Bow.setItemMeta(meta);

		p.getInventory().addItem(Bow);

		p.getInventory().addItem(pink);
		p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 33; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));
	}

	public void Minion(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack Bow = new ItemStack(Material.STONE_SWORD);
		Bow.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN + "Minion Sword");
		Bow.setItemMeta(meta);

		p.getInventory().addItem(Bow);

		p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));
	}

	public void Jumper(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack Bow = new ItemStack(Material.DIAMOND_SWORD);
		Bow.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Jumper Sword");
		Bow.setItemMeta(meta);

		p.getInventory().addItem(Bow);

		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public void Broadcast(String s) {
		Bukkit.broadcastMessage(prefix + s);
	}

	public void PrivateMessage(String s, Player p) {
		p.sendMessage(prefix + s);
	}

	public void Spider(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack web = new ItemStack(Material.GOLD_SWORD);
		web.addUnsafeEnchantment(Enchantment.DURABILITY, 50);

		ItemMeta meta = web.getItemMeta();

		meta.setDisplayName(ChatColor.WHITE + "Spider Sword");
		web.setItemMeta(meta);

		p.getInventory().addItem(web);

		p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	@EventHandler
	public void quita(PlayerQuitEvent e){
		if(ovo.red.contains(e.getPlayer().getName())){
			ovo.red.clear();
			ovo.getBluePlayer().sendMessage(prefix + "The red player quit.");
			ovo.getBluePlayer().teleport(ovo.getLounge());

			ovo.blue.clear();
			e.getPlayer().teleport(ovo.getLounge());
		}
		if(ovo.blue.contains(e.getPlayer().getName())){
			ovo.red.clear();
			ovo.blue.clear();
			ovo.getRedPlayer().sendMessage(prefix + "The blue player quit.");
			ovo.getRedPlayer().teleport(ovo.getLounge());

			e.getPlayer().teleport(ovo.getLounge());
			ovo.status = OneStatus.WAITING;
		}
	}
	@EventHandler
	public void ondeath(PlayerDeathEvent e){
		if (e.getEntity().equals(ovo.getBluePlayer())){
			ovo.status = OneStatus.WAITING; 

			e.getEntity().sendMessage(prefix + "You lost");
			ovo.getRedPlayer().sendMessage(prefix + "You won :)");
			e.getEntity().setHealth(20);

			ovo.getRedPlayer().teleport(ovo.getLounge());
			ovo.getRedPlayer().getInventory().clear();
			e.getEntity().teleport(ovo.getLounge());

			AddCoin(ovo.getRedPlayer(), 7);

			ovo.blue.clear();
			ovo.red.clear();


			Bukkit.getPluginManager().callEvent(new AchivementGetEvent(ovo.getRedPlayer(), Achivments.OVOWINNER));

		}

		if(e.getEntity().equals(ovo.getRedPlayer())){
			ovo.status = OneStatus.WAITING;

			e.getEntity().setHealth(20);
			e.getEntity().teleport(ovo.getLounge());
			ovo.getBluePlayer().teleport(ovo.getLounge());
			ovo.getBluePlayer().getInventory().clear();

			ovo.getBluePlayer().sendMessage(prefix + "You won :)");

			AddCoin(ovo.getBluePlayer(), 7);
			ovo.blue.clear();
			ovo.red.clear();
			Bukkit.getPluginManager().callEvent(new AchivementGetEvent(ovo.getBluePlayer(), Achivments.OVOWINNER));
	}
		}
	
	public void Ghast(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack Bow = new ItemStack(Material.BOW);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + "GhastBow");
		Bow.setItemMeta(meta);
		Bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

		p.getInventory().addItem(Bow);
		p.getInventory().addItem(new ItemStack(Material.ARROW));

		p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.DIAMOND_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 32; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public void Hero(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		// ata yodea anglit ?
		// Speak English ?

		p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		// lol, btw how old are you ?, ben kama ata ? IM 14 AND IM COOL !
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			// Internet i learned by myself
			// Websites, videos, and brb 1 sec
			// im back
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

		// Java, ze kmo anglit, ata lomed et a safa, vata yotzer latzmeha
		// mishpatim.
	}

	public void Grappler(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}

		PlayerInventory inv = p.getInventory();
		inv.addItem(new ItemStack(Material.IRON_SWORD));
		ItemStack grappler = new ItemStack(Material.LEASH);
		ItemMeta im = grappler.getItemMeta();
		im.setDisplayName("Grappling Hook");
		grappler.setItemMeta(im);
		inv.addItem(new ItemStack[] { grappler });
		inv.setHelmet(new ItemStack(Material.LEATHER_HELMET));
		inv.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		inv.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		inv.setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 33; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.getInventory().addItem(new ItemStack(Material.ARROW));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));
	}

	public void Archer(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		p.getInventory().addItem(sword);
		p.getInventory().addItem(bow);
		p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 32; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.getInventory().addItem(new ItemStack(Material.ARROW));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));
	}

	@EventHandler
	public void spread(BlockSpreadEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void drop(PlayerDropItemEvent e) {
		int I = e.getItemDrop().getItemStack().getTypeId();
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			if (I == 288 || I == 420 || I == 373 || I == 276 || I == 267
					|| I == 261 || I == 262 || I == 388 || I == 283 || I == 89
					|| I == 346 || I == 258 || I == 46 || I == 137 || I == 347
					|| I == 279 || I == 275 || I == 383 || I == 282 || I == 401
					|| I == 267 || I == 282 || I == 378) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void ArmorBreak(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().getTypeId() != 397) {
				p.getInventory().getHelmet().setDurability((short) 0);
			}
		}
		if (p.getInventory().getChestplate() != null) {
			if (p.getInventory().getChestplate().getTypeId() != 397) {
				p.getInventory().getChestplate().setDurability((short) 0);
			}
		}
		if (p.getInventory().getLeggings() != null) {
			if (p.getInventory().getLeggings().getTypeId() != 397) {
				p.getInventory().getLeggings().setDurability((short) 0);
			}
		}
		if (p.getInventory().getBoots() != null) {
			if (p.getInventory().getBoots().getTypeId() != 397) {
				p.getInventory().getBoots().setDurability((short) 0);
			}
		}
	}

	public void Vampire(Player p) {
		p.getInventory().clear();

		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta meta2 = helmet.getItemMeta();
		meta2.setDisplayName(ChatColor.DARK_PURPLE + "Vampire Helmet");
		helmet.setItemMeta(meta2);
		ItemStack Bow = new ItemStack(Material.IRON_SWORD);
		Bow.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Vampire Sword");
		Bow.setItemMeta(meta);

		p.getInventory().addItem(Bow);

		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public void SetupInventories() {
		ItemStack helmet = createItemStack(Material.FEATHER, (byte) 1, "Hulk",
				"Catch your Target, and then Eject him", ChatColor.BLACK,
				ChatColor.GREEN);

		Shop.addItem(helmet);
		ItemStack fisher = specialItemSTack(Material.FISHING_ROD, "Fisher");
		Shop.addItem(fisher);
		ItemStack sa = addspecialItem(Material.JACK_O_LANTERN, "Troller", 1,
				ChatColor.GOLD);
		// ?
		ItemStack saa = specialItemSTack(Material.FIREWORK, "§l§5Kangaroo");
		VIP.addItem(saa);

		ItemStack bat = createItemStack(Material.EGG, (byte) 0, "Batman",
				"Right Click it", ChatColor.GOLD, ChatColor.YELLOW);

		ItemStack carrot = createItemStack(Material.GOLDEN_CARROT, (byte) 0,
				"Rabbit", "Right Click it", ChatColor.AQUA, ChatColor.GREEN);

		ItemStack ten = new ItemStack(Material.IRON_INGOT);
		ItemStack tenfive = new ItemStack(Material.GOLD_INGOT);
		ItemStack twenti = new ItemStack(Material.DIAMOND);

		ItemMeta tenmeta = ten.getItemMeta();
		ItemMeta tenfivemeta = tenfive.getItemMeta();
		ItemMeta twentimeta = twenti.getItemMeta();
		tenmeta.setDisplayName(ChatColor.WHITE + "10");
		tenfivemeta.setDisplayName(ChatColor.GOLD + "15");
		twentimeta.setDisplayName(ChatColor.AQUA + "20");
		ArrayList<String> tenlore = new ArrayList<String>();
		ArrayList<String> tenfivelore = new ArrayList<String>();
		ArrayList<String> twentilore = new ArrayList<String>();

		tenlore.add(ChatColor.GOLD + "100 TeamCoins");
		tenfivelore.add(ChatColor.GOLD + "300 TeamCoins");
		twentilore.add(ChatColor.GOLD + "475 TeamCoins");
		tenmeta.setLore(tenlore);
		tenfivemeta.setLore(tenfivelore);
		twentimeta.setLore(twentilore);
		ten.setItemMeta(tenmeta);
		tenfive.setItemMeta(tenfivemeta);
		twenti.setItemMeta(twentimeta);

		ItemStack grappler = new ItemStack(Material.LEASH);
		ItemMeta im = grappler.getItemMeta();
		im.setDisplayName("Grappler");
		grappler.setItemMeta(im);
		VIP.addItem(new ItemStack[] { grappler });

		ItemStack shop = new ItemStack(Material.STRING);
		ItemStack regular = new ItemStack(Material.ARROW);
		ItemStack Youtubera = new ItemStack(Material.ANVIL);
		ItemStack Vip = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta shmeta = shop.getItemMeta();
		ItemMeta rmeta = regular.getItemMeta();
		ItemMeta ymeta = Youtubera.getItemMeta();
		ItemMeta vmeta = Vip.getItemMeta();

		shmeta.setDisplayName(ChatColor.RED + "Shop Kits");
		rmeta.setDisplayName(ChatColor.GOLD + "Regular Kits");
		ymeta.setDisplayName(ChatColor.RED + "You" + ChatColor.BLACK + "Tuber"
				+ ChatColor.GOLD + " Kits");
		vmeta.setDisplayName(ChatColor.GREEN + "V.I.P Kits");
		shop.setItemMeta(shmeta);
		regular.setItemMeta(rmeta);
		Youtubera.setItemMeta(ymeta);
		Vip.setItemMeta(vmeta);
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta smeta = sword.getItemMeta();
		smeta.setDisplayName(ChatColor.GREEN + "Knight");
		sword.setItemMeta(smeta);
		ItemStack bow = new ItemStack(Material.BOW);

		ItemMeta bameta = bow.getItemMeta();
		bameta.setDisplayName(ChatColor.RED + "Archer");
		bow.setItemMeta(bameta);
		ItemStack pink = new ItemStack(Material.getMaterial(351), 1, (short) 9);
		ItemMeta pmeta = pink.getItemMeta();
		pmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Groopy");
		pink.setItemMeta(pmeta);
		ItemStack Hoe = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta HMeta = Hoe.getItemMeta();
		HMeta.setDisplayName(ChatColor.GREEN + "Gangsta");
		Hoe.setItemMeta(HMeta);
		ItemStack smash = new ItemStack(Material.GRASS);
		ItemMeta metaa = smash.getItemMeta();
		metaa.setDisplayName(ChatColor.BLACK + "Smasher");
		smash.setItemMeta(metaa);
		ItemStack web = new ItemStack(Material.GOLD_SWORD);
		ItemMeta meta6 = web.getItemMeta();
		meta6.setDisplayName(ChatColor.WHITE + "Spider");
		web.setItemMeta(meta6);

		ItemStack s = new ItemStack(Material.SKULL_ITEM, (short) 1);
		ItemMeta ma = s.getItemMeta();
		ma.setDisplayName(ChatColor.DARK_GREEN + "Minion");
		s.setItemMeta(ma);

		ItemStack Bow4 = new ItemStack(Material.DIAMOND_SWORD);
		Bow4.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta5 = Bow4.getItemMeta();
		meta5.setDisplayName(ChatColor.GREEN + "Jumper");
		Bow4.setItemMeta(meta5);
		ItemStack Bow3 = new ItemStack(Material.IRON_SWORD);
		Bow3.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta3 = Bow3.getItemMeta();
		meta3.setDisplayName(ChatColor.DARK_PURPLE + "Vampire");
		Bow3.setItemMeta(meta3);

		ItemStack Bow2 = new ItemStack(Material.FIREBALL);
		ItemMeta meta2 = Bow2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE + "Ghast");
		Bow2.setItemMeta(meta2);
		ItemStack Bow = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta4 = Bow.getItemMeta();
		meta4.setDisplayName(ChatColor.BLUE + "Warth");
		Bow.setItemMeta(meta4);

		ItemStack Button = new ItemStack(Material.WOOD_BUTTON);
		ItemMeta meta = Button.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Creeper");
		Button.setItemMeta(meta);
		ItemStack EnderArcher = new ItemStack(Material.ENDER_PEARL);
		ItemMeta EMeta = EnderArcher.getItemMeta();
		EMeta.setDisplayName(ChatColor.DARK_PURPLE + "EnderArcher");
		EnderArcher.setItemMeta(EMeta);
		Youtuber.addItem(EnderArcher);
		Youtuber.addItem(Button);
		VIP.addItem(Bow);
		VIP.removeItem(EnderArcher);
		VIP.removeItem(Button);
		VIP.addItem(Bow2);
		VIP.addItem(Bow3);
		Shop.addItem(s);
		Shop.addItem(web);
		Shop.addItem(smash);
		VIP.addItem(Hoe);
		VIP.addItem(pink);
		VIP.addItem(sa);
		Regular.addItem(bow);
		Regular.addItem(sword);
		Main.addItem(Youtubera);

		Main.addItem(regular);
		Main.addItem(Vip);
		Main.addItem(shop);
		slots.addItem(ten);
		slots.addItem(tenfive);
		slots.addItem(twenti);
		VIP.addItem(carrot);
		Shop.addItem(bat);

	}

	@EventHandler
	public void command(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();

		if (e.getMessage().equalsIgnoreCase("/about")) {
			e.setCancelled(true);
		}
		if (e.getMessage().equalsIgnoreCase("?")
				|| e.getMessage().equalsIgnoreCase("Help")) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.GRAY + "------" + ChatColor.RED + "Royal"
					+ ChatColor.GREEN + "Zone" + ChatColor.GRAY + "------");
			p.sendMessage(ChatColor.GRAY + "/team");
			p.sendMessage(ChatColor.GRAY + "/vip");
			p.sendMessage(ChatColor.GRAY + "/spawn");

		}
	}

	public void Kangaroo(Player p) {

		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}

		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		ItemStack s = specialItemSTack(Material.FIREWORK, "§l§5Double Jump !");
		p.getInventory().addItem(s);
		p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 33; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public void Smasher(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack Helmet = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta meta2 = Helmet.getItemMeta();
		meta2.setDisplayName("Smasher Helmet");
		Helmet.setItemMeta(meta2);
		ItemStack Bow = new ItemStack(Material.IRON_SWORD);
		Bow.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.BLACK + "Smashers Sword");
		Bow.setItemMeta(meta);

		p.getInventory().addItem(Bow);

		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	@EventHandler
	public void damagea(EntityDamageEvent e) {
		
		if (e.getEntity() instanceof Player) {
		
			Player p = (Player) e.getEntity();
	if(teleport.contains(p.getName())){
				teleport.remove(p.getName());
				if(!isinRegion(p)){
				p.sendMessage(prefix +"You Damaged, Teleport canclled !");
				}
				}
			ItemStack s = specialItemSTack(Material.FIREWORK,
					"§l§5Double Jump !");

			if (e.getCause().equals(DamageCause.FALL)) {

				if (p.getInventory().contains(s)) {
					kangaroo.remove(p.getName());

					e.setDamage(e.getDamage() / 3);
					if (e.getDamage() > 6.5){
						e.setDamage(6.5);
					}
				}
			}
		}
	}

	public ItemStack specialItemSTack(Material m, String name) {
		ItemStack s = new ItemStack(m);
		ItemMeta meta = s.getItemMeta();
		meta.setDisplayName(name);
		s.setItemMeta(meta);

		return s;
	}

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		// wtf ? lawl
		if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null){

		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§l§5Double Jump !")) {

			if (e.getAction() == Action.RIGHT_CLICK_BLOCK
					|| e.getAction() == Action.RIGHT_CLICK_AIR) {

				e.setCancelled(true);
				if (p.isOnGround()) {

					Vector v = p.getLocation().getDirection().multiply(2)
							.setY(1);
					p.setVelocity(v);

					if (p.isSneaking()) {
						Vector va = p.getLocation().getDirection().multiply(4)
								.setY(0.7);
						p.setVelocity(va);
					}
				} else {
					if (kangaroo.contains(p.getName())) {

					} else {
						Vector v = p.getLocation().getDirection().multiply(2)
								.setY(1);
						p.setVelocity(v);
						kangaroo.add(p.getName());

					}
				}
			}
		}
		}

	}

	@EventHandler
	public void inventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		ItemStack shop = new ItemStack(Material.STRING);
		ItemStack regular = new ItemStack(Material.ARROW);
		ItemStack Youtubera = new ItemStack(Material.ANVIL);
		ItemStack Vip = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta shmeta = shop.getItemMeta();
		ItemMeta rmeta = regular.getItemMeta();
		ItemMeta ymeta = Youtubera.getItemMeta();
		ItemMeta vmeta = Vip.getItemMeta();
		shmeta.setDisplayName(ChatColor.RED + "Shop Kits");
		rmeta.setDisplayName(ChatColor.GOLD + "Regular Kits");
		ymeta.setDisplayName(ChatColor.RED + "You" + ChatColor.BLACK + "Tuber"
				+ ChatColor.GOLD + " Kits");
		vmeta.setDisplayName(ChatColor.GREEN + "V.I.P Kits");
		shop.setItemMeta(shmeta);
		regular.setItemMeta(rmeta);
		Youtubera.setItemMeta(ymeta);
		Vip.setItemMeta(vmeta);
		if (e.getInventory().getTitle()
				.equalsIgnoreCase(ChatColor.GREEN + "Main GUI")) {
			e.setCancelled(true);
			if (e.getCurrentItem().equals(shop)) {
				p.closeInventory();
				p.openInventory(Shop);
			} else if (e.getCurrentItem().equals(regular)) {
				p.closeInventory();

				p.openInventory(Regular);
			} else if (e.getCurrentItem().equals(Youtubera)) {
				p.closeInventory();

				p.openInventory(Youtuber);
			} else if (e.getCurrentItem().equals(Vip)) {
				p.closeInventory();

				p.openInventory(VIP);
			}
		}
	}

	@EventHandler
	public void login(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if (isVip(p)) {
			if (Bukkit.getOnlinePlayers().length <= 50
					&& Bukkit.getOnlinePlayers().length >= 40) {
				if (!(p.isBanned())) {
					e.allow();
				}
			}
		}
		if (p.getName().equals("naorpeled")) {
			p.setBanned(true);
			e.disallow(Result.KICK_BANNED, "Beled, ata aravi ?");
		}
		if (bm.getData().getBoolean(p.getUniqueId().toString() + ".banned")) {
			e.disallow(
					Result.KICK_BANNED,
					bm.getData().getString(
							p.getUniqueId().toString() + ".reason"));
		}
	}

	public void Warth(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack Bow = new ItemStack(Material.IRON_SWORD);
		Bow.addEnchantment(Enchantment.DAMAGE_ALL, 1);

		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Warth Sword");
		Bow.setItemMeta(meta);

		p.getInventory().addItem(Bow);

		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		p.getInventory().setChestplate(
				new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 34; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public void Creeper(Player p) {

		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack Bow = new ItemStack(Material.WOOD_BUTTON);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Creeper's Button");
		Bow.setItemMeta(meta);
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

		p.getInventory().addItem(Bow);

		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta meta1 = (LeatherArmorMeta) helmet.getItemMeta();
		meta1.setColor(Color.GREEN);
		helmet.setItemMeta(meta1);
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 33; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	public int getCoins(Player p) {

		int i = getConfig().getInt(p.getUniqueId().toString() + ".coins");
		return i;

	}

	public void AddCoin(Player p, int amount) {
		int CurrentCoins = getConfig().getInt(
				p.getUniqueId().toString() + ".coins");
		getConfig().set(p.getUniqueId().toString() + ".coins",
				CurrentCoins + amount);

		saveConfig();
	}

	public void addtoOfflinePermissions(String UUID, String permission) {
		List<String> perms = instance.getData().getStringList(UUID);
		perms.add(permission);
		instance.getData().set(UUID, perms);
		instance.saveData();

	}

	public void RemoveCoin(Player p, int amount) {
		int CurrentCoins = getConfig().getInt(
				p.getUniqueId().toString() + ".coins");
		getConfig().set(p.getUniqueId().toString() + ".coins",
				CurrentCoins - amount);
		saveConfig();
	}

	public void EnderArcher(Player p) {
		p.getInventory().clear();
		for (PotionEffect a : p.getActivePotionEffects()) {
			p.removePotionEffect(a.getType());
		}
		ItemStack Bow = new ItemStack(Material.BOW);
		ItemMeta meta = Bow.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "EnderBow");
		Bow.setItemMeta(meta);
		Bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

		p.getInventory().addItem(Bow);
		p.getInventory().addItem(new ItemStack(Material.ARROW));

		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		for (int x = 0; x < 32; x++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().addItem(new ItemStack(Material.BOWL));
		p.updateInventory();
		p.teleport(new Location(Bukkit.getWorld("world"), -29, 89, 0));

	}

	@SuppressWarnings("unused")
	private void region(Location loc, Location loc1, World w, Player p) {
		int minX = Math.min(loc.getBlockX(), loc1.getBlockX()), minY = Math
				.min(loc.getBlockY(), loc1.getBlockY()), minZ = Math.min(
				loc.getBlockZ(), loc1.getBlockZ()), maxX = Math.max(
				loc.getBlockX(), loc1.getBlockX()), maxY = Math.max(
				loc.getBlockY(), loc1.getBlockY()), maxZ = Math.max(
				loc.getBlockZ(), loc1.getBlockZ());

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {

					p.getWorld().getBlockAt(x, y, z).setType(Material.PORTAL);

				}
			}
		}
	}

	@EventHandler
	public void inv(EntityDamageEvent e){
		if (e.getEntity() instanceof Player){

	
			if(oa1.players.contains(((Player) e.getEntity()).getName()) || tf.players.contains(((Player) e.getEntity()).getName()) || tf.fighter.contains(((Player) e.getEntity()).getName())){
				if(oa1.getStatus() == GameStatus.INV || tf.getStatus() == GameStatus.INV) {
				e.setCancelled(true);
			}
			}
		}
	}
	@EventHandler
	public void antiblockbreak(BlockPhysicsEvent event) {

		if (event.getBlock().getType().equals(Material.PORTAL)) {
			event.setCancelled(true);

		}
	}
	@EventHandler
	public void friendly (EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player k = (Player) e.getDamager();
			Player p = (Player) e.getEntity();
			Teams Team = Teams.getInstance();
			
			if (tf.players.contains(p.getName()) && tf.fighter.contains(k.getName()) || l.players.contains(p.getName()) && l.players.contains(k.getName()) || oa1.players.contains(p.getName()) || oa1.players.contains(k.getName())){

			if(Team.getTeam(p).equals(Team.getTeam(k))){

				e.setCancelled(false);
				return;
			}
			}
		}
	}
	public boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
// 3193