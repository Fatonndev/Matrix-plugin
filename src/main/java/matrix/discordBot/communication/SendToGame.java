package matrix.discordBot.communication;

import matrix.utils.Config;
import matrix.utils.ConfigTranslate;
import mindustry.gen.Call;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class SendToGame extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        if(author.isBot()) return;
        Message message = event.getMessage();
        if(!message.getChannel().getId().equals(Config.get("discordChannelId"))) return;
        if(message.getContentDisplay().equals("")) return;
        Guild guild = message.getGuild();

        String content = message.getContentDisplay().replace("\n"," ");

        // Если участник без роли то отправляем сообщение без названия роли.
        if(Objects.requireNonNull(guild.getMember(author)).getRoles().isEmpty()) {

            Call.sendMessage(ConfigTranslate.get("msgToGame.prefix") +"["+author.getName() +"]" +ConfigTranslate.get("msgToGame.suffix") +content);

        } else {
            Role role = Objects.requireNonNull(guild.getMember(author)).getRoles().get(0);
            // Конвертируем RGB в HEX
            String hex = String.format("#%02x%02x%02x", role.getColor().getRed(), role.getColor().getGreen(), role.getColor().getBlue());
            String roleName = "["+hex+"]["+role.getName()+"]";
            Call.sendMessage(ConfigTranslate.get("msgToGame.prefix")+roleName
                    +ConfigTranslate.get("msgToGame.nickColor")+"["+author.getName()+"]"+ConfigTranslate.get("msgToGame.suffix")+content);
        }
    }

}
