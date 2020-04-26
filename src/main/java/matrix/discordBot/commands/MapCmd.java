package matrix.discordBot.commands;

import matrix.utils.ConfigTranslate;
import mindustry.Vars;
import mindustry.core.GameState;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.File;

public class MapCmd {
    public static void main(MessageReceivedEvent event) {

        if(!Vars.state.is(GameState.State.playing)) {
            return;
        }

        TextChannel channel = event.getTextChannel();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(ConfigTranslate.get("cmd.map.title"), null);

        eb.setColor(
                new Color(
                        Integer.parseInt(ConfigTranslate.get("cmd.map.color.red")),
                        Integer.parseInt(ConfigTranslate.get("cmd.map.color.green")),
                        Integer.parseInt(ConfigTranslate.get("cmd.map.color.blue"))
                ));

        eb.setDescription(ConfigTranslate.get("cmd.map.description"));

        eb.addField(ConfigTranslate.get("cmd.map"), Vars.world.getMap().name(), true);
        eb.addField(ConfigTranslate.get("cmd.map.author"), Vars.world.getMap().author(), true);

        channel.sendMessage(eb.build()).addFile(new File(Vars.world.getMap().file.absolutePath())).queue();
    }
}
