package matrix.commands.client;

import arc.graphics.Color;
import mindustry.content.Bullets;
import mindustry.content.Fx;
import mindustry.entities.type.Player;
import mindustry.type.Mech;
import mindustry.type.Weapon;

public class Lich {
    public static void create(Player player) {
        Mech lich = new Mech("lich", true) {
            {
                drillPower = 1;
                mineSpeed = 1.5f;
                mass = 1.2f;
                speed = 0.5f;
                itemCapacity = 40;
                boostSpeed = 0.95f;
                buildPower = 1.2f;
                engineColor = Color.valueOf("ffd37f");
                health = 250f;

                weapon = new Weapon("blaster"){{
                    length = 1.5f;
                    reload = 14f;
                    alternate = true;
                    ejectEffect = Fx.shellEjectSmall;
                    bullet = Bullets.standardMechSmall;
                }};
            }
        };
        player.mech = lich;
    }
}
