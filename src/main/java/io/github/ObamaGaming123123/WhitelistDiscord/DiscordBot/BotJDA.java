package io.github.ObamaGaming123123.WhitelistDiscord.DiscordBot;

import io.github.ObamaGaming123123.WhitelistDiscord.DiscordBot.commands.commandManager;
import io.github.ObamaGaming123123.WhitelistDiscord.WhitelistDiscord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/**
 * Author: ObamaGaming123123, Qz2
 * Description: This is the server bot code, sets the moods and puts the bot requirements to light as not to destroy memory
 * **/

public class BotJDA{
    private final JDA JDAManager;

    //This is where the bot builder starts and sets up listeners
    public BotJDA(String token, WhitelistDiscord plugin) throws LoginException {
        //if the token is incorrect it will spit out an error message
        try{
            JDABuilder builder = JDABuilder.createLight(token);
            builder.setStatus(OnlineStatus.ONLINE);
            builder.setActivity(Activity.watching("Minecraft"));
            this.JDAManager = builder.build();
        }catch(Exception e){
            throw new LoginException("Bot Token Does not exist please check again!");
        }

        //Registered Listeners
        JDAManager.addEventListener(new commandManager(plugin));
    }
}
