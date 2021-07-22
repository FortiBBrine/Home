import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandHome implements CommandExecutor {
	
	private Home plugin;
	public CommandHome(Home plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("&1Это команду могут использовать только игроки!".replace("&", "\u00a7"));
			return true;
		}
		Player p = (Player) sender;
		if (!plugin.getConfig().getKeys(false).contains(p.getName())) {
			sender.sendMessage("§cУ вас нету дома!");
			return true;
		}
		
		String world = plugin.getConfig().getString(p.getName()+".home.world");
		int x = (int) plugin.getConfig().getInt(p.getName()+".home.x");
		int y = (int) plugin.getConfig().getInt(p.getName()+".home.y");
		int z = (int) plugin.getConfig().getInt(p.getName()+".home.z");
		
		Location loc = p.getLocation();
		loc.setWorld(Bukkit.getWorld(world));
		loc.setX(x);
		loc.setY(y);
		loc.setZ(z);
		p.teleport(loc);
		
		p.sendMessage("§fТелепортация!");
		return true;
	}
}
