package matrix.discordBot.commands;

import matrix.utils.ConfigTranslate;
import matrix.utils.SystemInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.Color;

public class Memory {
    public static void main(MessageReceivedEvent event) {
        TextChannel channel = event.getTextChannel();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(ConfigTranslate.get("cmd.memory.title"), null);

        eb.setColor(
                new Color(
                Integer.parseInt(ConfigTranslate.get("cmd.memory.color.red")),
                Integer.parseInt(ConfigTranslate.get("cmd.memory.color.green")),
                Integer.parseInt(ConfigTranslate.get("cmd.memory.color.blue"))
        ));

        eb.setDescription(ConfigTranslate.get("cmd.memory.description"));

        eb.addField(ConfigTranslate.get("cpu"), SystemInfo.cpu() +"%", true);
        eb.addField(ConfigTranslate.get("cpuServer"), SystemInfo.cpuProcess() +"%", true);
        eb.addField(ConfigTranslate.get("ram"), SystemInfo.ram() +"%", true);

        channel.sendMessage(eb.build()).queue();
    }
}
