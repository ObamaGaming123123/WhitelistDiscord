package io.github.ObamaGaming123123.WhitelistDiscord;


import io.github.ObamaGaming123123.WhitelistDiscord.DiscordBot.BotJDA;
import io.github.ObamaGaming123123.WhitelistDiscord.DiscordBot.commands.getWhiteListNames;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.List;


/**
 * Author: ObamaGaming123123, Qz2
 * Description: This file is the basis of the plugin, will funnel all commands and classes here
 * **/

public class WhitelistDiscord extends JavaPlugin {
    private static WhitelistDiscord plugin;
    private static boolean floodGate;
    /**
     * This function will be called during when the plugin is set to active/enable
     * **/
    @Override
    public void onEnable(){
        getLogger().info("Whitelist Server Bot is being enabled, please make sure to set the token in config.yml in the plugin folder when launched for the first time!");
        getLogger().info("Version: 1.20.6");

        //Configuration file to set the discord token
        FileConfiguration config = this.getConfig();
        config.addDefault("DiscordBotToken", "");
        config.addDefault("floodgateInstalled", false);
        config.options().copyDefaults(true);
        saveConfig();

        //Starts the discord bot properties and starts all listeners
        try{
            BotJDA bot = new BotJDA(config.getString("DiscordBotToken"), config.getBoolean("floodgateInstalled"));
        }catch(LoginException e){
            getLogger().info("Discord Bot Token is incorrect!");
        }

        this.getCommand("getwhitelisted").setExecutor(new getWhiteListNames(this));

        this.plugin = this;
        this.floodGate = config.getBoolean("floodgateInstalled");

    }
    public void addWhitelist(String username) {
        getLogger().info(String.format("Added %s with uuid:%s to server", username, Bukkit.getOfflinePlayer(username)));
        Bukkit.getScheduler().runTask(this, () -> {
            Bukkit.getOfflinePlayer(username).setWhitelisted(true);
            FileConfiguration config = this.getConfig();
            List<String> discordWhitelistedPlayers = config.getStringList("DiscordWhitelistedPlayers");
            if (!discordWhitelistedPlayers.contains(username)) {
                discordWhitelistedPlayers.add(username);
                config.set("DiscordWhitelistedPlayers", discordWhitelistedPlayers);
                saveConfig();
            }
        });
    }

    public List<String> getDiscordWhitelistedPlayers() {
        return this.getConfig().getStringList("DiscordWhitelistedPlayers");
    }

    public void addfWhitelist(String username){
        if(floodGate) {
            //Getting uuid's and setting that whitelist to true
            getLogger().info(String.format("Adding %s", username));
            Bukkit.getScheduler().runTask(this, () -> getServer().dispatchCommand(getServer().getConsoleSender(), String.format("fwhitelist add %s", username)));
        }else{
            getLogger().info("Cannot invoke fWhitelist whilst config is set to false!");
        }
    }

    public static WhitelistDiscord getPlugin(){
        return plugin;
    }
    @Override
    public void onDisable(){
        getLogger().info("Discord Whitelist is set to false!");
    }
}