package matrix.commands.client;

import matrix.utils.ConfigTranslate;
import mindustry.Vars;
import mindustry.entities.type.Player;

public class Teleport {

    public static void toPoint(Player player, String[] arg) {

        if(arg.length >= 2) {
            try {
                player.setNet(Float.parseFloat(arg[0]), Float.parseFloat(arg[1]));
            } catch (Exception ignored) {
                player.sendMessage(ConfigTranslate.get("cmd.tp.err"));
                return;
            }
            player.sendMessage(ConfigTranslate.get("cmd.tp.ok.TpToPoint"));
        } else {
            player.sendMessage(ConfigTranslate.get("cmd.tp.notOkPoint"));
        }
    }

    public static void toPlayer(Player player, String[] args) {

        if(Vars.playerGroup.all().find(player1 -> player1.name.equals(args[0])).isValid()) {
            Player pl = Vars.playerGroup.all().find(player1 -> player1.name.equals(args[0]));
            player.setNet(pl.x, pl.y);
            player.sendMessage(ConfigTranslate.get("cmd.tp.ok.TpToPlayer"));
        } else {
            player.sendMessage(ConfigTranslate.get("cmd.tp.notOkPlayer"));
        }

    }

}
