package me.gregoryw.airdrop.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdAirdrop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		// Check if sender is a Player
		if(sender instanceof Player) {
			
			// Cast sender to Player
			Player player = (Player) sender;
			
			// If no arguments, return false
			if(args.length == 0) {
				return false;
			}
			
			//TODO
			// Airdrop is a kit
			if(args.length == 1) {
				
				Location loc = player.getLocation();
				double newHeight = loc.getY() + 10;
				
				Location loc2 = new Location(player.getWorld(), loc.getX(), newHeight, loc.getZ());
				
				player.getWorld().spawnFallingBlock(loc2, Material.OAK_PLANKS, (byte) 0);
				
			}
			
			// Airdrop item and amount
			if(args.length == 2) {
				
				//Material mat = Material.getMaterial(args[0].split(":")[1]);		
				
			}
			
			// Airdrop is invalid
			if(args.length > 2) {
				return false;
			}
			
			
		}
		
		// If sender isn't player, return false
		return false;
	}

}
