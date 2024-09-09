package com.Qwikkspell.WorkshopPractice;

import com.Qwikkspell.WorkshopPractice.commands.LeaderboardCommand;
import com.Qwikkspell.WorkshopPractice.commands.PlayCommand;
import com.Qwikkspell.WorkshopPractice.game.GameManager;
import com.Qwikkspell.WorkshopPractice.game.ScoreManager;
import com.Qwikkspell.WorkshopPractice.game.SidebarManager;
import com.Qwikkspell.WorkshopPractice.listeners.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorkshopPractice extends JavaPlugin {
    private SidebarManager sidebarManager;
    private ScoreManager scoreManager;
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        GameManager gameManager = new GameManager(this);
       // scoreManager = new ScoreManager(this);
        sidebarManager = new SidebarManager(gameManager);


        gameManager.setSidebarManager(sidebarManager);

        for (Player player : getServer().getOnlinePlayers()) {
            sidebarManager.showGeneralInfo(player);
        }

        getCommand("play").setExecutor(new PlayCommand(gameManager));
        this.getCommand("leaderboard").setExecutor(new LeaderboardCommand(gameManager.getScoreManager()));
        getServer().getPluginManager().registerEvents(new PlayerRestrictionListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, gameManager, sidebarManager), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new BlockClickListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new FurnaceListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new VillagerListener(gameManager), this);
//        getServer().getPluginManager().registerEvents(new InventoryCraftingListener(gameManager), this);
    }

    @Override
    public void onDisable() {

    }

    public SidebarManager getSidebarManager() {
        return sidebarManager;
    }
}
