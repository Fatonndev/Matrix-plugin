package matrix.discordBot.commands;

import matrix.utils.Config;
import matrix.utils.ConfigTranslate;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MainCmd extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;
        Message msg = event.getMessage();
        if (msg.getContentRaw().startsWith(Config.get("prefix")+ ConfigTranslate.get("cmd.setMsgChannel.name"))) {
            SetMsgChannel.main(event);
        }
        if (msg.getContentRaw().startsWith(Config.get("prefix")+ConfigTranslate.get("cmd.items.name"))) {
            ItemsCmd.main(event);
        }
        if (msg.getContentRaw().startsWith(Config.get("prefix")+ConfigTranslate.get("cmd.memory.name"))) {
            Memory.main(event);
        }
        if (msg.getContentRaw().startsWith(Config.get("prefix")+ConfigTranslate.get("cmd.map.name"))) {
            MapCmd.main(event);
        }
    }
}
