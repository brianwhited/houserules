package com.pdc.spigotmc.houserules.commands;

//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;

import com.pdc.spigotmc.houserules.houseRulesPlugin;
import com.pdc.spigotmc.houserules.utils.houseRulesUtil;
import com.pdc.spigotmc.houserules.configs.houseRulesConfig;

public class houseRulesCommand implements CommandExecutor {

    

    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0){ 
            if(sender.hasPermission(houseRulesUtil.HOUSERULES_USE_PERMISSION)){
                if(sender instanceof Player){

                    //Player player = (Player) sender;
                    //Send the command sender a message confirming that the item was given
					sender.sendMessage(houseRulesPlugin.getInternalConfig().getHouseRulesMessage());
					
                }
            }
           
        } else if (args[0] == "reload") {

        } else  {
            sender.sendMessage("&bHouse Rules Plugin");
        }
        return false;
    }
    
}
