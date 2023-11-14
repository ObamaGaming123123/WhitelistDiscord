package io.github.ObamaGaming123123.WhitlistDiscord;

import org.bukkit.plugin.java.JavaPlugin;

public class WhitelistDiscord extends JavaPlugin {
    @Override
    public void onEnable(){
        getLogger().info("onEnable is called!");
    }

    @Override
    public void onDisable(){
        getLogger().info("onDisabled is called!");
    }
}
