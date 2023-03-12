package com.pdc.spigotmc.houserules.configs;

import org.bukkit.configuration.file.FileConfiguration;

import com.pdc.spigotmc.houserules.houseRulesPlugin;

import net.md_5.bungee.api.ChatColor;

/**
 * An internal config class used to store all variables from the plugin's FileConfiguration
 * Storing the variables in an object like this is a much more efficient method than constantly accessing the config.yml
 * @author Brian Whited
 */
public final class houseRulesConfig {
    
	//Keys for accessing information in this plugin's FileConfiguration
	//We are saving these to avoid "Magic Values"
	//What that means is that instead of directly inputting the keys every time we want to access the FileConfiguration, 
	//we use these constant values to ensure no typos as well as clarity. If a key needs to be changed, it only needs to be updated in one place
	//In the FileConfiguration, a period '.' represents a break,
	//therefore the string after the period is a ConfigurationSection "underneath" ConfigurationSection represented by the string before the period

	public static final String NO_PERM_KEY = "locale.noPermissions";
	public static final String CONFIG_RELOAD_KEY = "locale.reload";
	public static final String HOUSERULES_MESSAGE = "houserules.message";
	public static final String HOUSERULES_MESSAGE_COLOR = "houserules.color";
	//public static final String 
	
	//Message strings
	//We are going to use these to store the messages from the FileConfiguration
	private String noPermMessage;
	private String houseRulesMessage;
	private String houseRulesMessageColor;
    private String configReloadedMessage;
	
	/**
	 * Default config constructor
	 * Once we create the Config object we want to ensure that the defaults are set and that we load whatever data is in the file
	 */
	public houseRulesConfig(){
		setDefaults();
		loadConfig();
	}
	
	/**
	 * Load data from plugin FileConfiguration
	 * Here we load all information from the FileConfiguration to our internal variables
	 */
	public void loadConfig(){
		//Get this plugin's FileConfiguration object
		FileConfiguration config = houseRulesPlugin.getInstance().getConfig();
		
		//Load all the values from the FileConfiguration into our internal config
		//The string key is required to get the value, however the second provides a default in case it was unable to get a value
		noPermMessage = config.getString(NO_PERM_KEY, "&4You do not have permission for that!");
		configReloadedMessage = config.getString(CONFIG_RELOAD_KEY, "&2[House Rules Config Reloaded]");
		
		//houseRulesMessageColor = config.getString(HOUSERULES_MESSAGE_COLOR, ChatColor.WHITE.toString());
		houseRulesMessage = config.getString(HOUSERULES_MESSAGE, "&4No House Rules have been setup.");

		this.setHouseRulesMessageColor(config.getString(HOUSERULES_MESSAGE_COLOR, ChatColor.WHITE.toString()));
	}
	
	/**
	 * Set default FileConfiguration
	 * This will create the file if it doesn't exist
	 * This will also setup any default values and write them to the config.yml file if they are missing
	 * You may have seen people using saveDefaultConfig() to copy a config.yml file from the plugin jar,
	 * however I personally don't like this method because if we add values to the config then they won't be copied over if the config.yml file already exists
	 */
	public void setDefaults(){
		//Get this plugin's FileConfiguration object
		FileConfiguration config = houseRulesPlugin.getInstance().getConfig();
		
		//Set the default values using a string key plus the value we want to set

		config.addDefault(NO_PERM_KEY, "&4You do not have permission for that!");
		config.addDefault(CONFIG_RELOAD_KEY, "&2[House Rules Config Reloaded]");
		config.addDefault(HOUSERULES_MESSAGE, "House Rules");
		config.addDefault(HOUSERULES_MESSAGE_COLOR, "WHITE");
		
		//Copy the defaults that we set back to the FileConfiguration object
		config.options().copyDefaults(true);
		
		//Last step is to actually write the defaults to the config.yml file
		houseRulesPlugin.getInstance().saveConfig();
	}
	
	/**
	 * Save the FileConfiguration
	 * Currently unused in our plugin but can be useful if values can be updated from in game or if another plugin updates the values
	 * It's often easier to create these methods in the beginning so they already exist if we need them in the future
	 * For example if we add a way to change the config in game we already have an easy way to save their changes
	 */
	public void saveConfig(){
		//Get this plugin's FileConfiguration object
		FileConfiguration config = houseRulesPlugin.getInstance().getConfig();
		
		//Save any updated values from our internal config to the FileConfiguration object

		config.set(NO_PERM_KEY, noPermMessage);
		config.set(CONFIG_RELOAD_KEY, configReloadedMessage);
		config.set(HOUSERULES_MESSAGE, houseRulesMessage);
		config.set(HOUSERULES_MESSAGE_COLOR, houseRulesMessageColor);
		
		//We always need to remember to save the plugin's updated FileConfiguration object to write to file
		houseRulesPlugin.getInstance().saveConfig();
	}
	
	/**
	 * Reloads the config
	 * Currently does nothing more than loadConfig()
	 * Can be used in some situations if any specific actions are required to unload config before loading values again
	 */
	public void reloadConfig(){
		loadConfig();
	}
	
	/**
	 * Gets the No Permission message
	 * @return The No Permission message
	 */
	public String getNoPermMessage() {
		return noPermMessage;
	}

	/**
	 * Sets the No Permission message
	 * @param The No Permission message
	 */
	public void setNoPermMessage(String message) {
		this.noPermMessage = message;
	}

	/**
	 * Gets the House Rules message
	 * @return The House Rules message
	 */
	public String getHouseRulesMessage() {
		String returnValue = '\u00A7' + houseRulesMessageColor + houseRulesMessage;

		return returnValue;
	}

	/**
	 * Sets the House Rules message
	 * @param The House Rules message
	 */
	public void setHouseRulesMessage(String message) {
		this.houseRulesMessage = message;
	}

	/**
	 * Gets the House Rules message color
	 * @return The House Rules message color
	 */
	public String getHouseRulesMessageColor() {
		return houseRulesMessageColor;
	}

	/**
	 * Sets the House Rules message color
	 * @param The House Rules message color
	 */
	public void setHouseRulesMessageColor(String colorName) {
		String colorValue;

		switch (colorName)
		{
			case "BLACK":
				colorValue = "0";
				break;

			case "DARK_BLUE":
				colorValue = "1";
				break;

			case "DARK_GREEN":
				colorValue = "2";
				break;

			case "DARK_AQUA":
				colorValue = "3";
				break;

			case "DARK_RED":
				colorValue = "4";
				break;

			case "DARK_PURPLE":
				colorValue = "5";
				break;

			case "GOLD":
				colorValue = "6";
				break;

			case "GRAY":
				colorValue = "7";
				break;

			case "DARK_GRAY":
				colorValue = "8";
				break;

			case "BLUE":
				colorValue = "9";
				break;

			case "GREEN":
				colorValue = "a";
				break;

			case "AQUA":
				colorValue = "b";
				break;

			case "RED":
				colorValue = "c";
				break;

			case "LIGHT_PURPLE":
				colorValue = "d";
				break;

			case "YELLOW":
				colorValue = "e";
				break;

			case "WHITE":
				colorValue = "f";
				break;
			
			default:
				colorValue = "f";
		}
		this.houseRulesMessageColor = colorValue;
	}

	/**
	 * Gets the Config Reloaded message
	 * @return The Config Reloaded message
	 */
	public String getConfigReloadedMessage() {
		return configReloadedMessage;
	}
	
	/**
	 * Sets the Config Reloaded message
	 * @param The Config Reloaded message
	 */
	public void setConfigReloadedMessage(String message) {
		this.configReloadedMessage = message;
	}
}
