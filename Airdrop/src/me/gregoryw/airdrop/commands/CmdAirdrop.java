package me.gregoryw.airdrop.commands;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.gregoryw.airdrop.Crate;
import me.gregoryw.airdrop.PackagesConfig;
import me.gregoryw.airdrop.helpers.ChatHandler;

public class CmdAirdrop implements CommandExecutor {

	/** Private Instance Variables */
	private boolean noBlocksAbovePlayer;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// Check if sender is a Player
		if (sender instanceof Player) {

			// Cast sender to Player
			Player player = (Player) sender;

			// If no arguments, return false
			if (args.length == 0) {
				return false;
			}

			// Send package to sender where only argument is the package
			if (args.length == 1) {
				
				String packageName = args[0];
				
				ArrayList<ItemStack> items = new ArrayList<ItemStack>();
				
				if(PackagesConfig.getConfig().contains("packages." + packageName)) {
					int itemNumber = 1;
					while(PackagesConfig.getConfig().contains("packages." + packageName + ".items." + itemNumber)) {
						items.add(PackagesConfig.getConfig().getItemStack("packages." + packageName + ".items." + itemNumber));
						itemNumber++;
					}
				} else {
					ChatHandler.sendMessage(player, "This is an invalid package!");
					return false;
				}
 
				Location loc = player.getLocation();
				
				noBlocksAbovePlayer = true;
				
				for (int x = 0; x < 20; x++) {

					if (!loc.getBlock().getType().equals(Material.AIR)) {
						noBlocksAbovePlayer = false;
					}

					loc.add(0, 1.0, 0);
				}

				if (noBlocksAbovePlayer) {

					Crate crate = new Crate(loc, loc.getWorld(), items);
					crate.dropCrate();

					return true;

				} else {
					// Send some error
					ChatHandler.sendErrorMessage(player);
					System.out.println("No space above player");
				}

			}

			// Case where crate is dropped on another player
			if (args.length == 3) {

			}

			// Case where crate is dropped at a certain location
			if (args.length == 4) {

			}

		}

		// If sender isn't player, return false
		return false;
	}

}
