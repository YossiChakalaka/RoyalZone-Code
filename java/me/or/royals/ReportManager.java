package me.or.royals;
 
import java.io.File;
import java.io.IOException;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
 
public class ReportManager {
 
        private ReportManager() { }
       
        static ReportManager instance = new ReportManager();
       
        public static ReportManager getInstance() {
                return instance;
        }
       
        Plugin p;
       
     
       
        FileConfiguration data;
        File dfile;
       
        public void setup(Plugin p) {
                //config.options().copyDefaults(true);
                //saveConfig();
               
                if (!p.getDataFolder().exists()) {
                        p.getDataFolder().mkdir();
                }
               
                dfile = new File(p.getDataFolder(), "datareport.yml");
               
                if (!dfile.exists()) {
                        try {
                                dfile.createNewFile();
                        }
                        catch (IOException e) {
                                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
                        }
                }
               
                data = YamlConfiguration.loadConfiguration(dfile);
        }
       
        public FileConfiguration getData() {
                return data;
        }
       
        public void saveData() {
                try {
                        data.save(dfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
                }
        }
       
        public void reloadData() {
                data = YamlConfiguration.loadConfiguration(dfile);
        }
       
       
  
                
       
      
       
        public PluginDescriptionFile getDesc() {
                return p.getDescription();
        }
}