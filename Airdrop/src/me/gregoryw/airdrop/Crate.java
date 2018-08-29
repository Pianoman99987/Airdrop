package me.gregoryw.airdrop;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;

public class Crate {

	private Location location;
	private World world;

	private ArrayList<ItemStack> contents;

	private FallingBlock fallingCrate;
	private Block blockChest;

	/**
	 * Construct a new Crate object with a location, world, and ArrayList of
	 * contents
	 * 
	 * @param location
	 * @param world
	 * @param contents
	 */
	public Crate(Location location, World world, ArrayList<ItemStack> contents) {

		this.location = location;
		this.world = world;
		this.contents = contents;

		dropCrate();

	}

	/**
	 * Drop the crate
	 */
	private void dropCrate() {

		fallingCrate = world.spawnFallingBlock(location, Material.OAK_PLANKS, (byte) 0);

	}

	/**
	 * Spawn chest where the falling block was
	 */
	private void spawnChest() {

		blockChest.setType(Material.CHEST);

		Chest chest = (Chest) blockChest;
		chest.getBlockInventory().addItem(new ItemStack(Material.STICK, 3));

		for (ItemStack is : contents) {
			chest.getBlockInventory().addItem(is);
		}

	}

	/**
	 * Handle EntityChangeBlockEvent and test for when the falling block changes
	 * 
	 * @param e
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	private void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {

		// If crateFalling isn't a null object and the entity is the fallingBlock
		if (fallingCrate != null && e.getEntity().equals(fallingCrate)) {
			fallingCrate.getLocation().getBlock().setType(Material.AIR);
			blockChest = fallingCrate.getLocation().getBlock();
			spawnChest();
		}

	}

}
