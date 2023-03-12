package com.pdc.spigotmc.houserules;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.pdc.spigotmc.houserules.commands.houseRulesCommand;
import com.pdc.spigotmc.houserules.configs.houseRulesConfig;
import com.pdc.spigotmc.houserules.events.houseRulesEvents;
import com.pdc.spigotmc.houserules.utils.houseRulesUtil;

public class houseRulesPlugin extends JavaPlugin{ 

    private final Logger logger = Logger.getLogger("HouseRules");

    //Our internal config object for storing the configuration options that server owners can set
	private static houseRulesConfig config;
	
	//An instance of this plugin for easy access
	private static houseRulesPlugin plugin;

    /**
	 * Ran when plugin is enabled
	 * Set static instance of this class
	 * Register event listeners
	 * Create configuration object
	 * Set command executor
	 * Register recipe (if needed)
	 * Register permissions
	 */
	@Override
	public void onEnable(){
        getLogger().info("Attempting to enable House Rules.");
		plugin = this;
		Bukkit.getPluginManager().registerEvents(new houseRulesEvents(), this);
		config = new houseRulesConfig();
		this.getCommand("houserules").setExecutor(new houseRulesCommand());
		//houseRulesUtil.registerRecipe();
		houseRulesUtil.registerPermissions();
        getLogger().info("House Rules have been enabled.");
	}

    /**
	 * Ran when plugin is disabled
	 * Remove crafting recipes if the plugin is disabled for any reason
	 * Remove permissions to clean up in case plugin is added again before server restart
	 */
	@Override
	public void onDisable(){
        //houseRulesUtil.unregisterRecipe();
        houseRulesUtil.unregisterPermissions();
        getLogger().info("House Rules have been disabled.");
    }

    /**
	 * Gets the logger for this plugin
	 */
	public Logger getLogger(){
		return logger;
	}
	
	/**
	 * Gets an instance of this plugin
	 * @return The static instance of this plugin
	 */
	public static houseRulesPlugin getInstance(){
		return plugin;
	}
	
	/**
	 * Gets the internal config
	 * @return The internal config
	 */
	public static houseRulesConfig getInternalConfig(){
		return config;
	}


}
