package matrix.discordBot;

import static arc.util.Log;

import matrix.discordBot.commands.MainCmd;
import matrix.discordBot.communication.SendToGame;
import matrix.utils.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Bot extends ListenerAdapter {

    public static JDABuilder bot = new JDABuilder(Config.get("token"));
    public static JDA jda;

    public static void main() throws LoginException, InterruptedException {

        if(!Config.get("token").isEmpty()) {
            bot.setDisabledCacheFlags(EnumSet.of(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE));
            bot.setBulkDeleteSplittingEnabled(false);
            bot.setCompression(Compression.NONE);
            bot.setActivity(Activity.watching(Config.get("status")));
            bot.addEventListeners(new MainCmd());
            bot.addEventListeners(new SendToGame());

            jda = bot.build();
            jda.awaitReady();
        } else Log.warn("You didn't enter a token in Config.properties!");
    }
}
