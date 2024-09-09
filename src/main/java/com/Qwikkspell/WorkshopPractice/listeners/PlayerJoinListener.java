package com.Qwikkspell.WorkshopPractice.listeners;

import com.Qwikkspell.WorkshopPractice.WorkshopPractice;
import com.Qwikkspell.WorkshopPractice.game.GameManager;
import com.Qwikkspell.WorkshopPractice.game.SidebarManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {
    private WorkshopPractice plugin;
    private final GameManager gameManager;
    private final SidebarManager sidebarManager;

    public PlayerJoinListener(Plugin plugin, GameManager gameManager, SidebarManager sidebarManager) {
        this.plugin = (WorkshopPractice) plugin;
        this.gameManager = gameManager;
        this.sidebarManager = sidebarManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        sidebarManager.updateSidebar(player);
        player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        player.sendMessage(ChatColor.GOLD + "Welcome to Workshop Practice " + ChatColor.AQUA + player.getName() +
                ChatColor.GOLD + "! Use " + ChatColor.AQUA + "/play <left/right> <easy/normal> <pregame seconds>" + ChatColor.GOLD + " to play, " +
                "and use " + ChatColor.AQUA + "/leaderboard" + ChatColor.GOLD + " to see the top players!");
        player.sendMessage(ChatColor.AQUA + "Plugin Author: Qwikkspell");

    }
}
