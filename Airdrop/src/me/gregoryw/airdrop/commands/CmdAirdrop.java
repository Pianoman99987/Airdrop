package me.gregoryw.airdrop.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.gregoryw.airdrop.Airdrop;
import me.gregoryw.airdrop.ChatHandler;
import me.gregoryw.airdrop.Crate;

public class CmdAirdrop implements CommandExecutor {

	/** Private Instance Variables */
	private static final Airdrop instance = new Airdrop();
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
			if (args.length == 2) {

				Location loc = player.getLocation();

				noBlocksAbovePlayer = true;

				if (noBlocksAbovePlayer(loc)) {

					// TODO Find package and package contents
					ArrayList<ItemStack> packageContents = new ArrayList<ItemStack>();

					Crate crate = new Crate(loc, loc.getWorld(), packageContents);

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

	/**
	 * Checks if there are blocks within 20 blocks above player
	 * 
	 * @param loc
	 * @return
	 */
	private boolean noBlocksAbovePlayer(Location loc) {

		Bukkit.getScheduler().runTask(instance, new Runnable() {

			@Override
			public void run() {

				for (int x = 0; x < 20; x++) {

					if (loc.getBlock().getType() != Material.AIR) {
						noBlocksAbovePlayer = false;
					}

					loc.add(0, 1, 0);
				}
			}

		});

		return noBlocksAbovePlayer;
	}

}
