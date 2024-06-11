package io.github.ObamaGaming123123.WhitelistDiscord.DiscordBot.commands;

import io.github.ObamaGaming123123.WhitelistDiscord.WhitelistDiscord;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class getWhiteListNames implements CommandExecutor {

    private final WhitelistDiscord plugin;

    public getWhiteListNames(WhitelistDiscord plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getwhitelisted")) {
            List<String> discordWhitelistedPlayers = plugin.getDiscordWhitelistedPlayers();
            sender.sendMessage("Players whitelisted via Discord:");
            for (String player : discordWhitelistedPlayers) {
                sender.sendMessage(player);
            }

            // Log to console as well
            plugin.getLogger().info("Players whitelisted via Discord:");
            for (String player : discordWhitelistedPlayers) {
                plugin.getLogger().info(player);
            }

            return true;
        }
        return false;
    }
}
