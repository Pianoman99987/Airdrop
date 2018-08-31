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
import me.gregoryw.airdrop.Crate;
import me.gregoryw.airdrop.helpers.ChatHandler;

public class CmdAirdrop implements CommandExecutor {

	/** Private Instance Variables */
	private boolean noBlocksAbovePlayer;
	
	private Airdrop plugin;
	
	public CmdAirdrop(Airdrop plugin) {
		this.plugin = plugin;
	}

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
				
				String contents = args[0];
				
				if(contents.equals("testPackage")) {
					ChatHandler.sendMessage(player, "You summoned the test package");
				}

				Location loc = player.getLocation();
				
				System.out.println(loc.getY());

				noBlocksAbovePlayer = true;
				
				checkBlocksAbovePlayer(loc);

				if (noBlocksAbovePlayer) {

					// TODO Find package and package contents
					ArrayList<ItemStack> packageContents = new ArrayList<ItemStack>();

					Crate crate = new Crate(loc.add(0, 20, 0), loc.getWorld(), packageContents);
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

	/**
	 * Checks if there are blocks within 20 blocks above player
	 * 
	 * @param loc
	 * @return
	 */
	private void checkBlocksAbovePlayer(Location loc) {

		Bukkit.getScheduler().runTask(plugin, new Runnable() {

			@Override
			public void run() {

				System.out.println("THe scheduler starts");
				
				for (int x = 0; x < 20; x++) {
					
					System.out.println("Checking");
					
					System.out.println(loc.getY());

					if (!loc.getBlock().getType().equals(Material.AIR)) {
						System.out.println("Found block");
						noBlocksAbovePlayer = false;
					}

					loc.add(0, 1.0, 0);
				}
			}

		});
	}

}
