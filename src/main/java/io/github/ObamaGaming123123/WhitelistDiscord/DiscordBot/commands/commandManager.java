package io.github.ObamaGaming123123.WhitelistDiscord.DiscordBot.commands;

import io.github.ObamaGaming123123.WhitelistDiscord.WhitelistDiscord;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ObamaGaming123123, Qz2
 * Description: This is the class that adds listeners to the bot and act's on any commands given
 * **/

public class commandManager extends ListenerAdapter {
    private WhitelistDiscord plugin;
    //Initializing commandManager class
    public commandManager(WhitelistDiscord plugin){
        this.plugin = plugin;
    }
    //Gets the command and sends the username from discord to plugin listener
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName(); //Gets command name
        //Checks to see if the command is whitelist as not to spam
        if (command.equalsIgnoreCase("whitelist")){
            //Runs the command that will whitelist you on the server
            String username = event.getOption("username").getAsString();
            event.reply(String.format("Adding %s to server's whitelist", username)).queue();
            plugin.addWhitelist(username);
        }
    }

    //Guild command, only has 100 uses, but there is only one command
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        //Sets the message info and username description
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("whitelist", "Whitelisting you username to the server").addOption(OptionType.STRING, "username", "Your Minecraft username"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
