package me.gregoryw.airdrop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.gregoryw.airdrop.commands.*;
import me.gregoryw.airdrop.listeners.FallingBlockListener;

public class Airdrop extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		// Register Commands
		this.getCommand("airdrop").setExecutor(new CmdAirdrop(this));
		
		// Register Listeners
		Bukkit.getPluginManager().registerEvents(new FallingBlockListener(), this);
		
		
	}
	
	@Override
	public void onDisable() {
		
	}
		
}
