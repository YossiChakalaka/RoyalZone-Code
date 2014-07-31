package me.or.royals;
 
import java.util.List;
 




import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
 
public class Teams {
    public static Teams instance;
   
    public Teams()
    {
        instance = this;
    }
 
    public static Teams getInstance() {
            return instance;
    }
 
    // DOm, dis is for you ;)
        TeamManager tm = TeamManager.getInstance();
        TeamList tl = TeamList.getInstance();
 
        public boolean isinTeam(Player p){

        	if (tm.getData().getString(p.getUniqueId().toString() +".Team") != null){
        		return true;
        	}
        	return false;
// :)
        }
       
        public int getTeamCoins(String name){
        	return tl.getData().getInt(name + ".coins");
        }
        public void AddTeamCoin(String name, int amount){
        	tl.getData().set(name + ".coins", tl.getData().getInt(name + ".coins") + amount);
        }
        public void leaveTeam(Player p){
        	List<String> player = tl.getData().getStringList(getTeam(p));
        	player.remove(p.getUniqueId().toString());
            tl.getData().set(getTeam(p), player);

               tm.getData().set(p.getUniqueId().toString() + ".Team", null);
               tl.saveData();
               tm.saveData();
        }
        public void AddTeamKills(String name, int amount){
        	tl.getData().set(name + ".Kills", tl.getData().getInt(name + ".Kills") + amount);
        	tl.saveData();
// ken
        }
        public void AddTeamDeath(String name, int amount){
        	tl.getData().set(name + ".Deaths", tl.getData().getInt(name + ".Deaths") + amount);
        	tl.saveData();
        }
        public int getTeamDeaths(Player p){
                return tl.getData().getInt(getTeam(p) + ".Deaths");
        }
        public double getTeamKD(Player p){
 
                int i = getTeamDeaths(p);
                int a = getTeamKills(p);
               
                int s = i + a;
                return s / 2;
               // think i copied from PvP Israel ?
                // lol dis code is the opposite of PvP ISrael Teams :P
        }
        public int getTeamKills(Player p){
                return tl.getData().getInt(getTeam(p) + ".Kills");
        }
      
        public void joinTeam(Player p, String name){
        	List<String> player = tl.getData().getStringList("Teams" +name);
player.add(p.getUniqueId().toString());
              tm.getData().set(p.getUniqueId().toString() + ".Team", name);
              tl.getData().set("Teams" +name, player);
              
              tl.saveData();
            tl.reloadData();
              tm.saveData();
              tm.reloadData();
        }
        public void addTeamSlots(String name, int amount){
        	tl.getData().set("Teams" + name + "Slots", amount);
        	tl.saveData();
        } 
        public List<String> getMembersa(String name){
        	return tl.getData().getStringList("Teams" + name);
        		
        }
        public int getMembers(String name){
        	return tl.getData().getStringList("Teams" + name).size();
        }
        public int getSlots(String name){
        	return tl.getData().getInt("Teams" + name + "Slots");
        }
        public void createTeam(Player p, String name, String Tag){
               tm.getData().set(p.getUniqueId().toString() + ".Team", name);

               List<String> player = tl.getData().getStringList("Teams" + name);
               player.add(p.getUniqueId().toString());
               List<String> teams = tl.getData().getStringList("Teams");
               teams.add(name);
               tm.getData().set(p.getUniqueId().toString() +".team", name);
               tl.getData().set("Teams", teams);
               tl.getData().set("Teams" + name, player);
               tl.getData().set("Teams" + name + "Leader", p.getUniqueId().toString());
               tl.getData().set("Teams" + name +"Tag", Tag);
               tl.getData().set("Teams" + name +"Slots", 5);


               tl.saveData();
               tm.saveData();
               
        }
        public String getLeader(String name){

        return tl.getData().getString("Teams" + name + "Leader");
        }
        public void kickPlayer(Player p, String name){
              tm.getData().set(p.getUniqueId().toString() + ".Team", null);
              List<String> players = tl.getData().getStringList("Teams" + name);
              players.remove(p.getUniqueId().toString());
              tl.getData().set("Teams" + name, players);
              tl.saveData();
              tm.saveData();
        }
        public void printhelpMessage(Player p){
                p.sendMessage(ChatColor.GREEN+"===RoyalZoneTeams===");
                p.sendMessage(ChatColor.GOLD+"Plugin created by YossiChakalaka And some help from TheComp5");
                p.sendMessage(ChatColor.GREEN+"/team create <TeamName> <Tag> - Create's the Team");
                p.sendMessage(ChatColor.GREEN+"/team invite <NickName> - Invite a Player to your Team");
                p.sendMessage(ChatColor.GREEN+"/team accept - Accept to an invitation");
                p.sendMessage(ChatColor.GREEN+"/team leave - leave your Team");
                p.sendMessage(ChatColor.GREEN+"/team Kick <NickName> - Kick a Player from your Team");
                p.sendMessage(ChatColor.GREEN+"/team disband - Disbanding your Team.");
                p.sendMessage(ChatColor.GREEN+"/team info - Some info about your team");
                p.sendMessage(ChatColor.GREEN+"/team tag <Tag> - Change your team Tag [Required] ChangeTagPaper");
                p.sendMessage(ChatColor.GREEN+"/team Shop - Open The Team Shop");

 
 
        }
        public void removeTeamCoins(String name, int amount){
        	tl.getData().set(name + ".coins", tl.getData().getInt(name + ".coins") - amount);
        	tl.saveData();
        }
        public void disbandTeam(Player p, String name){

        	List<String> players = tl.getData().getStringList("Teams" +name);
        	for (String playersa : players){
        tm.getData().set(playersa+".team", null);
        leaveTeam(Bukkit.getPlayer(UUID.fromString(playersa)));
        	}
        	tl.getData().set("Teams" + name, null);
        	tl.getData().set("Teams" +name + ".Leader", null);
        	tl.getData().set("Teams" +name + ".Tag", null);
        	tm.getData().set(p.getUniqueId().toString() + ".team", null);
        	tl.saveData();
        	tl.reloadData();
            tm.saveData();
        }
        public String getTag(Player p, String name){

        	return tl.getData().getString("Teams" + name +"Tag");

        
        }
                public void setteamTag(String name, Player p, String tag){
               tl.getData().set("Teams" + name + "Tag", tag);
               tl.saveData();
               tm.saveData();
                	
        }
        public String getTeam(Player p){
               
          return tm.getData().getString(p.getUniqueId().toString() + ".Team");
}
}