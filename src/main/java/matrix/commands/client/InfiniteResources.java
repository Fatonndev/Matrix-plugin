package matrix.commands.client;

import matrix.utils.ConfigTranslate;
import mindustry.Vars;
import mindustry.entities.type.Player;

public class InfiniteResources {
    public static void set(String[] args, Player player) {
        if (args[0].equals("on")) {
            Vars.state.rules.infiniteResources = true;
            player.sendMessage(ConfigTranslate.get("cmd.infiniteResources.settedOn"));
        } else {
            Vars.state.rules.infiniteResources = false;
            player.sendMessage(ConfigTranslate.get("cmd.infiniteResources.settedOff"));
        }
        for(int id = 0; id < Vars.playerGroup.all().size; id++){
            Player pl = Vars.playerGroup.all().get(id);
            Vars.netServer.sendWorldData(pl);
        }
    }
}
