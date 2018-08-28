package me.gregoryw.airdrop;

import org.bukkit.plugin.java.JavaPlugin;

import me.gregoryw.airdrop.commands.*;

public class Airdrop extends JavaPlugin {

	@Override
	public void onEnable() {
		
		// Register Commands
		this.getCommand("airdrop").setExecutor(new CmdAirdrop());
		
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
