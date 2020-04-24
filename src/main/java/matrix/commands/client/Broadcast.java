package matrix.commands.client;

import matrix.utils.ConfigTranslate;
import mindustry.entities.type.Player;
import mindustry.gen.Call;

public class Broadcast {
    public static void bc (String[] arg, Player pl) {
        String[] args = arg[0].split(" ");

            StringBuilder newStr = new StringBuilder();
            for(String data: args){
                newStr.append(data+" ");
            }

            Call.onInfoMessage(ConfigTranslate.get("cmd.broadcast.prefix")+String.valueOf(newStr));
    }

}
