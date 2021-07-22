import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Home extends JavaPlugin implements Listener {
	
	public void onEnable() {
		File config = new File(getDataFolder()+File.separator+"config.yml");
		if (!config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
		
		getCommand("sethome").setExecutor(new CommandSethome(this));
		getCommand("delhome").setExecutor(new CommandDelhome(this));
		getCommand("home").setExecutor(new CommandHome(this));

		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onDeath(PlayerRespawnEvent e) {

		Player p = (Player) e.getPlayer();
		if (!getConfig().getKeys(false).contains(p.getName())) return;
		Location loc = p.getLocation();
		
		loc.setWorld(Bukkit.getWorld(getConfig().getString(p.getName()+".home.world")));
		loc.setX(getConfig().getInt(p.getName()+".home.x"));
		loc.setY(getConfig().getInt(p.getName()+".home.y"));
		loc.setZ(getConfig().getInt(p.getName()+".home.z"));
		p.sendMessage("§cТелепортация на дом!");
		e.setRespawnLocation(loc);
	}
}
