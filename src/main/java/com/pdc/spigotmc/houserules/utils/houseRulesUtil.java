package com.pdc.spigotmc.houserules.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import com.pdc.spigotmc.houserules.houseRulesPlugin;

public final class houseRulesUtil {

    //NamespacedKey used for the house rules 
	public static final NamespacedKey HOUSERULES_KEY = new NamespacedKey(houseRulesPlugin.getInstance(), "houserules");
	
	//Permission string required for using the house rules
	public static final String HOUSERULES_USE_PERMISSION = "houserules.use";
	
	//Permission string required to reload the config
	public static final String HOUSERULES_RELOAD_PERMISSION = "houserules.reload";
	
	//List of all registered permissions
	private static final ArrayList<Permission> permissions = new ArrayList<>();

	/**
     * Prevent anyone from initializing this class as it is solely to be used for static utility
     */
    private houseRulesUtil() {}
	
	
	
	/**
	 * Register all House Rules permissions
	 */
	public static void registerPermissions(){
		//Create the permissions and store them in a list
		//The list is mainly used internally but a getter could be used to grant other developers access to the list
		//For a permission we only need to have a string representing the key, however it's best to include the description and who has the permission by default
		permissions.add(new Permission(HOUSERULES_USE_PERMISSION, "Allows player to ask for the House Rules", PermissionDefault.TRUE));
		//permissions.add(new Permission(HOUSERULES_GIVE_SELF_PERM, "Allows player give themselves the House Rules", PermissionDefault.OP));
		//permissions.add(new Permission(HOUSERULES_GIVE_OTHERS_PERM, "Allows player give others the House Rules", PermissionDefault.OP));
		permissions.add(new Permission(HOUSERULES_RELOAD_PERMISSION, "Allows players to reload the config", PermissionDefault.OP));
		
		//Loop through the list and add all the permissions we created
		for(Permission perm : permissions){
			Bukkit.getPluginManager().addPermission(perm);
			
			//Log a message that we added the permission
			houseRulesPlugin.getInstance().getLogger().fine("Registered Permission: " + perm.getName());
		}
	}

	/**
	 * Unregister all Enderbow permissions
	 */
	public static void unregisterPermissions(){
		//Remove all permissions that we created
		//Mainly used when disabling the plugin to prevent issues if the permissions are changed and the plugin is enabled again (possibly an update?)
		//While using the /reload command is bad practice, many server owners will do so anyway and that can cause issues if we don't clean up properly
		for(Permission perm : permissions){
			Bukkit.getPluginManager().removePermission(perm);
			
			//Log a message that we removed the permission
			houseRulesPlugin.getInstance().getLogger().fine("Unregistered Permission: " + perm.getName());
		}
		
		//Clear the list of permissions incase this method was called but the plugin wasn't disabled
		//If we don't do this then calling registerPermissions() would result in trying to register each permission twice
		permissions.clear();
	}
}
