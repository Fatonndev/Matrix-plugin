package matrix.discordBot.communication;

import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import matrix.discordBot.Bot;
import matrix.utils.Config;
import matrix.utils.ConfigTranslate;
import matrix.utils.RemoveColors;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.MessageChannel;


public class SendToDiscord {
    public static JDA jda = Bot.jda;
    public static void send(String nick, String msg) {
        msg = msg.replace("@here", ConfigTranslate.get("pingDeleted")).replace("@everyone", ConfigTranslate.get("pingDeleted"));
        TemmieWebhook temmie = new TemmieWebhook(Config.get("webHookMsg"));
        DiscordMessage dm = new DiscordMessage(RemoveColors.main(nick), msg, Config.get("messagerAvatarURL"));
        temmie.sendMessage(dm);
    }
    public static void log(String nick, String msg) {
        TemmieWebhook temmie = new TemmieWebhook(Config.get("webHookLog"));
        DiscordMessage dm = new DiscordMessage(ConfigTranslate.get("loggerName")
                ,ConfigTranslate.get("usageCmd").replace("{0}", RemoveColors.main(nick))
                +msg, Config.get("loggerAvatarURL"));
        temmie.sendMessage(dm);
    }
    public static void sendBotMessage(String message) {
        MessageChannel channel = jda.getTextChannelById(Config.get("discordChannelId"));
        channel.sendMessage(message).queue();
    }
}
