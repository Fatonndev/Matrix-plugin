package matrix;

import arc.*;
import arc.util.*;

import matrix.commands.client.Broadcast;
import matrix.commands.client.SetBlock;
import matrix.commands.client.SetTeam;
import matrix.commands.client.SpawnOre;
import matrix.discordBot.communication.SendToDiscord;
import matrix.utils.*;

import mindustry.*;
import mindustry.entities.type.*;
import mindustry.game.EventType;
import mindustry.plugin.Plugin;
import matrix.discordBot.Bot;

import javax.security.auth.login.LoginException;

public class Main extends Plugin{


    //register event handlers and create variables in the constructor
    public Main() throws LoginException, InterruptedException {

        Config.main();
        Bot.main();

        Events.on(EventType.PlayerJoin.class, event -> {

            TitleManager.main();

            if(Boolean.parseBoolean(Config.get("allowFreeAnimatedNick"))) {
                if (Boolean.parseBoolean(Config.get("allowAnimatedNick")))
                    AnimatedNick.main(event.player);
            } else {
                if (Boolean.parseBoolean(Config.get("allowAnimatedNick"))) {
                    if (event.player.isAdmin) AnimatedNick.main(event.player);
                }
            }

            if(Vars.netServer.admins.getPlayerLimit() != 0) {
                String sendString = ConfigTranslate.get("onJoin")
                        .replace("{0}", RemoveColors.main(event.player.name))
                        .replace("{1}", String.valueOf(Vars.playerGroup.size() + 1))
                        .replace("{2}", String.valueOf(Vars.netServer.admins.getPlayerLimit()));
                SendToDiscord.sendBotMessage(sendString);
            } else {
                String sendString = ConfigTranslate.get("onJoinUnlimited")
                        .replace("{0}", RemoveColors.main(event.player.name))
                        .replace("{1}", String.valueOf(Vars.playerGroup.size() + 1));
                SendToDiscord.sendBotMessage(sendString);
            }

        });

        Events.on(EventType.PlayerLeave.class, event -> {
            if(Vars.netServer.admins.getPlayerLimit() != 0) {
                String sendString = ConfigTranslate.get("onLeave")
                        .replace("{0}", RemoveColors.main(event.player.name))
                        .replace("{1}", String.valueOf(Vars.playerGroup.size() - 1))
                        .replace("{2}", String.valueOf(Vars.netServer.admins.getPlayerLimit()));
                SendToDiscord.sendBotMessage(sendString);
            } else {
                String sendString = ConfigTranslate.get("onLeaveUnlimited")
                        .replace("{0}", RemoveColors.main(event.player.name))
                        .replace("{1}", String.valueOf(Vars.playerGroup.size() - 1));
                SendToDiscord.sendBotMessage(sendString);
            }
        });

        Events.on(EventType.PlayerChatEvent.class, (event) -> {
            String msg = event.message;
            String nick = event.player.name;

            // Запускаем проверку на запрещенные слова
            if(!msg.startsWith("/")) {
                if (!event.player.isAdmin && Boolean.valueOf(Config.get("chatGuard"))) {
                    if (!ChatGuard.check(msg)) SendToDiscord.send(nick, msg);
                } else {
                    SendToDiscord.send(nick, msg);
                }
            } else SendToDiscord.log(nick, msg);

        });

    }

    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("ping", "Return \"Pong!\"", args -> { Log.info("Pong!"); });
    }

    @Override
    public void registerClientCommands(CommandHandler handler){

        handler.<Player>register(ConfigTranslate.get("cmd.setTeam.name"), ConfigTranslate.get("cmd.setTeam.params"), ConfigTranslate.get("cmd.setTeam.description"), (args, player) -> {
            SetTeam.set(args, player);
        });
        handler.<Player>register(ConfigTranslate.get("cmd.spawnOre.name"), ConfigTranslate.get("cmd.spawnOre.params"), ConfigTranslate.get("cmd.spawnOre.description"), (args, player) -> {
            SpawnOre.main(player, args);
        });
        handler.<Player>register(ConfigTranslate.get("cmd.setBlock.name"), ConfigTranslate.get("cmd.setBlock.params"), ConfigTranslate.get("cmd.setBlock.description"), (args, player) -> {
            SetBlock.main(player, args);
        });
        handler.<Player>register(ConfigTranslate.get("cmd.infiniteResources.name"), "<on/off>", ConfigTranslate.get("cmd.infiniteResources.description"), (args, player) -> {
            if(player.isAdmin && Boolean.parseBoolean(Config.get("infiniteResourcesCmd")))
                if(args[0].equals("on")) {
                    Vars.state.rules.infiniteResources = true;
                } else {
                    Vars.state.rules.infiniteResources = false;
                }
        });
        handler.<Player>register(ConfigTranslate.get("cmd.broadcast.name"), "<info...>", ConfigTranslate.get("cmd.infiniteResources.description"), (args, player) -> {
            if (player.isAdmin) Broadcast.bc(args, player);
        });
        handler.<Player>register("js", "<script...>", "Run arbitrary Javascript.", (arg, player) -> {
            if (player.isAdmin) Vars.mods.getScripts().runConsole(arg[0]);
        });

        //handler.removeCommand();

        //register a whisper command which can be used to send other players messages
        handler.<Player>register("whisper", "<player> <text...>", "Whisper text to another player.", (args, player) -> {
            //find player by name
            Player other = Vars.playerGroup.find(p -> p.name.equalsIgnoreCase(args[0]));

            //give error message with scarlet-colored text if player isn't found
            if(other == null){
                player.sendMessage("[scarlet]No player by that name found!");
                return;
            }

            //send the other player a message, using [lightgray] for gray text color and [] to reset color
            other.sendMessage("[lightgray](whisper) " + player.name + ":[] " + args[1]);
        });
    }
}

