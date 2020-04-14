package matrix.discordBot.commands;

import matrix.utils.Config;
import matrix.utils.ConfigTranslate;
import matrix.utils.Map;
import mindustry.Vars;
import mindustry.content.Items;
import mindustry.game.Team;
import mindustry.world.modules.ItemModule;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.Color;

public class ItemsCmd {

    public static void main(MessageReceivedEvent event) {
        TextChannel channel = event.getTextChannel();
        String[] args = event.getMessage().getContentDisplay().split(" ");
        Team team = Team.sharded;
        String teamName = ConfigTranslate.get("teams.yellow");

        if(args.length > 1) {
            if (args[1].equals(ConfigTranslate.get("teams.blue"))) {
                team = Team.blue;
                teamName = args[1];
            }
            if (args[1].equals(ConfigTranslate.get("teams.purple"))) {
                team = Team.purple;
                teamName = args[1];
            }
            if (args[1].equals(ConfigTranslate.get("teams.green"))) {
                team = Team.green;
                teamName = args[1];
            }
            if (args[1].equals(ConfigTranslate.get("teams.red"))) {
                team = Team.crux;
                teamName = args[1];
            }
        }

        if (Vars.state.teams.get(team).cores.isEmpty()) {
            channel.sendMessage(ConfigTranslate.get("teams.notFound")).queue();
        }

        ItemModule core = Vars.state.teams.get(team).cores.get(0).items;
        EmbedBuilder eb = new EmbedBuilder();


        int capacity = Map.getCapacity(team);
        if(core != null) {

            eb.setTitle(ConfigTranslate.get("cmd.items.title"), null);
            eb.setThumbnail(Config.get("cmd.items.imageLink"));

            eb.setColor(new Color(
                    Integer.parseInt(ConfigTranslate.get("cmd.items.color.red")),
                    Integer.parseInt(ConfigTranslate.get("cmd.items.color.green")),
                    Integer.parseInt(ConfigTranslate.get("cmd.items.color.blue"))
            ));

            eb.setDescription(ConfigTranslate.get("cmd.items.description")
                    .replace("{0}", String.valueOf(teamName)));

            if(core.get(Items.copper)>0)
            eb.addField(ConfigTranslate.get("items.copper"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.copper)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.lead)>0)
            eb.addField(ConfigTranslate.get("items.lead"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.lead)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.graphite)>0)
            eb.addField(ConfigTranslate.get("items.graphite"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.graphite)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.silicon)>0)
            eb.addField(ConfigTranslate.get("items.silicon"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.silicon)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.titanium)>0)
            eb.addField(ConfigTranslate.get("items.titanium"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.titanium)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.thorium)>0)
            eb.addField(ConfigTranslate.get("items.thorium"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.thorium)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.metaglass)>0)
            eb.addField(ConfigTranslate.get("items.metaglass"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.metaglass)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.plastanium)>0)
            eb.addField(ConfigTranslate.get("items.plastanium"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.plastanium)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.phasefabric)>0)
            eb.addField(ConfigTranslate.get("items.phasefabric"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.phasefabric)))
                    .replace("{1}", String.valueOf(capacity)), true);

            if(core.get(Items.surgealloy)>0)
            eb.addField(ConfigTranslate.get("items.surgealloy"), ConfigTranslate.get("cmd.items.field")
                    .replace("{0}", String.valueOf(core.get(Items.surgealloy)))
                    .replace("{1}", String.valueOf(capacity)), true);


            channel.sendMessage(eb.build()).queue();
        } else {
            channel.sendMessage(ConfigTranslate.get("teams.notFound")).queue();
        }
    }

}
