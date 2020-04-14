package matrix.commands.client;

import matrix.utils.Map;
import mindustry.entities.type.Player;

public class SetBlock {
    public static void main(Player player, String[] args) {
        Map.setBlock(player, args);
    }
}
