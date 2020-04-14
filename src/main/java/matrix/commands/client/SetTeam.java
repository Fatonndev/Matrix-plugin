package matrix.commands.client;

import arc.util.Log;
import matrix.utils.ConfigTranslate;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.entities.type.Player;
import mindustry.game.Team;

public class SetTeam {

    public static void set(String[] args, Player player) {

        boolean BLUE_TEAM = false;
        boolean GREEN_TEAM = false;
        boolean RED_TEAM = false;
        boolean PURPLE_TEAM = false;
        boolean YELLOW_TEAM = false;
        boolean notOk = false;

        for(int x = 0; x < Vars.world.width(); x++){
            for(int y = 0; y < Vars.world.height(); y++){
                if(Vars.world.tile(x, y).block() == Blocks.coreShard || Vars.world.tile(x, y).block() == Blocks.coreFoundation || Vars.world.tile(x, y).block() == Blocks.coreNucleus){
                    if(Vars.world.tile(x, y).getTeam() == Team.blue)
                        BLUE_TEAM = true;
                    if(Vars.world.tile(x, y).getTeam() == Team.green)
                        GREEN_TEAM = true;
                    if(Vars.world.tile(x, y).getTeam() == Team.crux)
                        RED_TEAM = true;
                    if(Vars.world.tile(x, y).getTeam() == Team.purple)
                        PURPLE_TEAM = true;
                    if(Vars.world.tile(x, y).getTeam() == Team.sharded)
                        YELLOW_TEAM = true;
                }
            }
        }

        if(args[0].equals("blue") && BLUE_TEAM){
            player.setTeam(Team.blue);
            notOk=true;
        }

        if(args[0].equals("green") && GREEN_TEAM){
            player.setTeam(Team.green);
            notOk=true;

        }

        if(args[0].equals("red") && RED_TEAM){
            player.setTeam(Team.crux);
            notOk=true;
        }

        if(args[0].equals("purple") && PURPLE_TEAM){
            player.setTeam(Team.purple);
            notOk=true;
        }

        if(args[0].equals("yellow") && YELLOW_TEAM){
            player.setTeam(Team.sharded);
            notOk=true;
        }

        if(notOk) { player.sendMessage(ConfigTranslate.get("cmd.setTeam.ok"));
        } else player.sendMessage(ConfigTranslate.get("cmd.setTeam.notFound"));
    }
}
