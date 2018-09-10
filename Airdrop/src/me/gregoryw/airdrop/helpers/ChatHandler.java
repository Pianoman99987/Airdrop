package me.gregoryw.airdrop.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatHandler {
	
	public static void sendErrorMessage(Player player, String message) {
		
		player.sendMessage("This is a default error");
		
	}
	
	public static void sendErrorMessage(Player player) {
		
		sendErrorMessage(player, "This is a default error");
		
	}
	
	public static void sendMessage(Player player, String message) {
		
		player.sendMessage(message);
		
	}
	
	public static void sendPFMessage(Player player, String message) {
		
		player.sendMessage(ChatColor.DARK_BLUE + "[Airdrop] " + ChatColor.WHITE +message);
		
	}

}
