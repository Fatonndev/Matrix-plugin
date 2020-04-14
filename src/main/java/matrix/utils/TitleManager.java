package matrix.utils;

import mindustry.gen.Call;

public class TitleManager {
    public static void main() {
        // Call.onConnect(player.con, "mindustry.ru", 6567);
        Call.onInfoToast(ConfigTranslate.get("title"), 5000);
    }
}
