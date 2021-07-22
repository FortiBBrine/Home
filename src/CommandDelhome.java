
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandDelhome implements CommandExecutor {
	
	private Home plugin;
	public CommandDelhome(Home plugin) {
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
		
		plugin.getConfig().set(p.getName(), null);
		plugin.saveConfig();
		plugin.reloadConfig();
		
		p.sendMessage("§fТочка дома удалена!");
		return true;
	}
}
