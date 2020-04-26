package matrix.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ChatGuard {

    public static boolean check (String content){
        String[] Strings = get();

        for (int i=0; i < Strings.length; i++) {
            int finalI = i;
            if (content.indexOf(Strings[finalI]) >= 0){
                return true;
            }
        }
        return false;
    }
    
    public static String[] get() {
        String out = "CHAT_GUARD_ERROR";
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            fileInputStream = new FileInputStream("config/mods/Matrix/ChatGuard.properties");
            prop.load(fileInputStream);
            out = prop.getProperty("forbiddenWords");
            out = new String(out.getBytes("ISO-8859-1"), "UTF-8");
            String[] arr = out.split(";");
            return arr;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

}
