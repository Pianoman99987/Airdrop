package me.gregoryw.airdrop.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.gregoryw.airdrop.Crate;
import me.gregoryw.airdrop.PackagesConfig;
import me.gregoryw.airdrop.helpers.ChatHandler;

public class CmdAirdrop implements CommandExecutor {

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

				ArrayList<ItemStack> items = getItemsInPackage(packageName, player);

				if (items.isEmpty()) {
					ChatHandler.sendErrorMessage(player, "These is an error with this package!");
					return true;
				}

				Location loc = player.getLocation();

				boolean noBlocksAbovePlayer = checkBlocksAbovePlayer(loc);

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
			// Args: playername packagename
			
			if (args.length == 2) {
				
				String p = args[0];
			
				Player target = Bukkit.getServer().getPlayer(p);
				
				if(target == null) {
					ChatHandler.sendMessage(player, "Player not found!");
					return true;
				}
				
				String packageName = args[1];

				ArrayList<ItemStack> items = getItemsInPackage(packageName, player);

				if (items.isEmpty()) {
					ChatHandler.sendErrorMessage(player, "These is an error with this package!");
					return true;
				}

				Location loc = target.getLocation();

				boolean noBlocksAbovePlayer = checkBlocksAbovePlayer(loc);

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

			// Case where crate is dropped at a certain location
			
			//args x z packagename
			if (args.length == 3) {
				World w = player.getWorld();
				Double xloc = Double.parseDouble(args[0]);
				Double zloc = Double.parseDouble(args[1]);

				Location l = new Location(w,xloc,zloc,(Double) null);
				//Find out how to determine highest y at point
			}

		}

		// If sender isn't player, return false
		return false;
	}

	/**
	 * Returns an ArrayList of ItemStack of items in the specified package. If the
	 * package is invalid, returns an empty ArrayList
	 * 
	 * @param packageName
	 * @param player
	 * @return
	 */
	private ArrayList<ItemStack> getItemsInPackage(String packageName, Player player) {

		ArrayList<ItemStack> itemsToReturn = new ArrayList<ItemStack>();

		if (PackagesConfig.getConfig().contains("packages." + packageName)) {
			int itemNumber = 1;
			while (PackagesConfig.getConfig().contains("packages." + packageName + ".items." + itemNumber)) {
				itemsToReturn.add(
						PackagesConfig.getConfig().getItemStack("packages." + packageName + ".items." + itemNumber));
				itemNumber++;
			}
		} else {
			ChatHandler.sendMessage(player, "This is an invalid package!");
		}

		return itemsToReturn;

	}

	/**
	 * Checks if there are any blocks within 20 blocks above the player
	 * 
	 * @param loc
	 * @return
	 */
	private boolean checkBlocksAbovePlayer(Location loc) {
		boolean isAbove = true;

		for (int x = 0; x < 20; x++) {

			if (!loc.getBlock().getType().equals(Material.AIR)) {
				isAbove = false;
			}

			loc.add(0, 1.0, 0);
		}

		return isAbove;
	}

}
