package matrix.discordBot.commands;

import matrix.utils.ConfigTranslate;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;
import java.util.Properties;


public class SetMsgChannel extends ListenerAdapter {

    //JDABuilder bot = Bot.bot;

    public static void main(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        if(!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            channel.sendMessage(ConfigTranslate.get("noPerm")).queue(response -> {});
            return;
        }
        channel.sendMessage(ConfigTranslate.get("cmd.setMsgChannel.ok")).queue(response -> {});
        String id = channel.getId();

        File file = new File("config/mods/Matrix/config.properties");

        InputStream in = null;
        OutputStream out = null;

        Properties props = new Properties();
        try {
            in = new FileInputStream(file);
            props.load(in);
            props.setProperty("discordChannelId", id);
            out = new FileOutputStream(file);
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null ) {
                try{in.close();}catch(IOException e){e.printStackTrace();}
            }
            if (out != null) {
                try{out.close();}catch(IOException e){e.printStackTrace();}
            }
        }
    }



}