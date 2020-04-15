package matrix.utils;

import arc.math.Mathf;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.entities.type.Player;
import mindustry.game.Team;
import mindustry.world.Block;

public class Map {


    public static int getCapacity(Team team) {
        int capacity = 0;

        for(int x = 0; x < Vars.state.teams.get(team).cores.size; x++){
            if(Vars.state.teams.get(team).cores.get(x).block == Blocks.coreShard) capacity+=Blocks.coreShard.itemCapacity;
            if(Vars.state.teams.get(team).cores.get(x).block == Blocks.coreFoundation) capacity+=Blocks.coreFoundation.itemCapacity;
            if(Vars.state.teams.get(team).cores.get(x).block == Blocks.coreNucleus) capacity+=Blocks.coreNucleus.itemCapacity;
        }

        return capacity;
    }

    public static void spawnOre(Player player, String[] Args) {

        String[] args = Args[0].split(" ");
        Block block = Blocks.oreCopper;
        int radius = 1;

        if(!args[0].matches("\\d+")) {
            player.sendMessage(ConfigTranslate.get("cmd.spawnOre.error"));
        } else if(Long.parseLong(args[0]) >= 1000) {
            radius = 1000;
        } else radius = Integer.parseInt(args[0]);

        int x = Math.round(player.x/8);
        int y = Math.round(player.y/8);

        if(args.length>=2) {
            if (args[1].equals("lead")) block = Blocks.oreLead;
            if (args[1].equals("coal")) block = Blocks.oreCoal;
            if (args[1].equals("titanium")) block = Blocks.oreTitanium;
            if (args[1].equals("thorium")) block = Blocks.oreThorium;
            if (args[1].equals("scrap")) block = Blocks.oreScrap;
        }

        for(int rx = -radius; rx <= radius; rx++){
            for(int ry = -radius; ry <= radius; ry++){
                if(Mathf.dst2(rx, ry) <= (radius - 0.5f) * (radius - 0.5f)){
                    int wx = x + rx, wy = y + ry;

                    if(wx < 0 || wy < 0 || wx >= Vars.world.width() || wy >= Vars.world.height()){
                            continue;
                    }
                    Vars.world.tile(wx, wy).setOverlay(block);
                }
            }
        }

        for(int id = 0; id < Vars.playerGroup.all().size; id++){
            Player pl = Vars.playerGroup.all().get(id);
            Vars.netServer.sendWorldData(pl);
        }

    }

    public static void setBlock(Player player, String[] args) {

        int x = Math.round(player.x/8);
        int y = Math.round(player.y/8);

        Block block = Vars.content.blocks().find(b -> b.name.equals(args[0]));

        if (block != null) {
            player.sendMessage(ConfigTranslate.get("cmd.setBlock.setted"));
            Vars.world.tile(x, y).setBlock(block);
            Vars.world.tile(x, y).setTeam(player.getTeam());

            for(int id = 0; id < Vars.playerGroup.all().size; id++){
                Player pl = Vars.playerGroup.all().get(id);
                Vars.netServer.sendWorldData(pl);
            }
        } else player.sendMessage(ConfigTranslate.get("cmd.setBlock.nosetted"));
    }

}
