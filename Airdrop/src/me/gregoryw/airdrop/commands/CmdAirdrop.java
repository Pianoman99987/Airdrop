package me.gregoryw.airdrop.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.gregoryw.airdrop.Airdrop;

public class CmdAirdrop implements CommandExecutor {

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

			if (args.length == 3) {

				Location loc = player.getLocation();

				noBlocksAbovePlayer = true;

				if (noBlocksAbovePlayer(loc)) {
					// Do stuff
					return true;
				} else {
					// Send some error
					System.out.println("No space above player");
				}

			}

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
