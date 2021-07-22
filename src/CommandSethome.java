import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandSethome implements CommandExecutor {
	
	private Home plugin;
	public CommandSethome(Home plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("&1Это команду могут использовать только игроки!".replace("&", "\u00a7"));
			return true;
		}
		Player p = (Player) sender;
		if (plugin.getConfig().getKeys(false).contains(p.getName())) {
			sender.sendMessage("§cУ вас уже есть дом!");
			return true;
		}
		Location loc = p.getLocation();
		plugin.getConfig().set(p.getName()+".home.world", loc.getWorld().getName());
		plugin.getConfig().set(p.getName()+".home.x", (int) loc.getX());
		plugin.getConfig().set(p.getName()+".home.y", (int) loc.getY());
		plugin.getConfig().set(p.getName()+".home.z", (int) loc.getZ());
		
		plugin.saveConfig();
		plugin.reloadConfig();
		
		p.sendMessage("§fТочка дома установлена!");
		return true;
	}
}
