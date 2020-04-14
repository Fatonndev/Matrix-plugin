package matrix.utils;

import mindustry.Vars;
import mindustry.entities.type.Player;
public class AnimatedNick {

    public static void main(Player player) {
        Update upd;
        upd = new Update();
        upd.main(player);
    }

}

class Update {

    static int in = 0;
    static boolean aBoolean = false;
    static boolean ok = true;
    static boolean first = true;
    static Thread th;

    public static void main(Player player) {
        if (player.name.startsWith(ConfigTranslate.get("AnimatedNick.colorName"))) {
            player.name = player.name.replace(ConfigTranslate.get("AnimatedNick.colorName"), "");
            String[] arr = player.name.split("");

            th = new Thread(new Runnable() {
                public void run() {
                    try {
                        while(ok) {
                            boolean oK = Chek(player);
                            Thread.sleep(700);
                            if (oK) {
                                in = aliveMethod(player, arr);
                            } else th.stop();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            });
            th.start();
        }
    }

    public static boolean Chek(Player player) {
        if(!first) {
            for(int id = 0; id < Vars.playerGroup.all().size; id++){
                Player pl = Vars.playerGroup.all().get(id);
                if(pl == player) return true;
            }
            return false;
        } else {
            first = false;
            return true;
        }
    }

    static int aliveMethod(Player player, String[] arr){

        if(in>=arr.length-1) aBoolean = true;
        if(in<=0) aBoolean = false;
        if(!aBoolean) {
            in++;
        } else in--;

        arr[in] = ConfigTranslate.get("AnimatedNick.color")+arr[in];
        if(!aBoolean) {
            if (!arr[in - 1].isEmpty()) arr[in - 1] = arr[in - 1].replace(ConfigTranslate.get("AnimatedNick.color"), "");
        } else {
            if (!arr[in + 1].isEmpty()) arr[in + 1] = arr[in + 1].replace(ConfigTranslate.get("AnimatedNick.color"), "");
        }

        StringBuilder newStr = new StringBuilder();
        for(String data: arr){
            newStr.append(data);
        }
        player.name = newStr.toString();
        return in;
    }
}
