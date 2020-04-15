package matrix.commands.client;

import matrix.utils.ConfigTranslate;
import matrix.utils.Map;
import mindustry.entities.type.Player;

public class SpawnOre {
    public static void main(Player player, String[] args) {
        Map.spawnOre(player, args);
        player.sendMessage(ConfigTranslate.get("cmd.spawnOre.ok"));
    }
}
