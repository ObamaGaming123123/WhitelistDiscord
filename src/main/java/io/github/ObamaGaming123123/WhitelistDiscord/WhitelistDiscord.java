package io.github.ObamaGaming123123.WhitelistDiscord;

import io.github.ObamaGaming123123.WhitelistDiscord.DiscordBot.BotJDA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

/**
 *
 * Author: ObamaGaming123123, Qz2
 * Description: This file is the basis of the plugin, will funnel all commands and classes here
 *
 *
 * **/

public class WhitelistDiscord extends JavaPlugin {
    /**
     * This function will be called during when the plugin is set to active/enable
     * **/
    @Override
    public void onEnable(){
        getLogger().info("Whitelist Server Bot is being enabled, please make sure to set the token in config.yml in the plugin folder when launched for the first time!");
        getLogger().info("Version: 1.20.1");

        //Configuration file to set the discord token
        FileConfiguration config = this.getConfig();
        config.addDefault("DiscordBotToken", "");
        config.options().copyDefaults(true);
        saveConfig();

        //Starts the discord bot properties and starts all listeners
        try{
            BotJDA bot = new BotJDA(config.getString("DiscordBotToken"), this);
        }catch(LoginException e){
            getLogger().info("Discord Bot Token is incorrect!");
        }

        //TODO: Make this command so it'll show all usernames added with discord bot
        //this.getCommand("GetNames").setExecutor(new CommandGetNames());
    }

    //This is a deprecated action, this may not be used in the future
    public void addWhitelist(String username){
        //Getting uuid's and setting that whitelist to true
        getLogger().info(String.format("Added %s with uuid:%s to server", username, Bukkit.getOfflinePlayer(username)));
        Bukkit.getOfflinePlayer(username).setWhitelisted(true);
    }
    @Override
    public void onDisable(){
        getLogger().info("Discord Whitelist is set to false!");
    }
}
