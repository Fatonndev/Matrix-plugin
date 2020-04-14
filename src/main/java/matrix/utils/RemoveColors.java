package matrix.utils;

public class RemoveColors {
    public static String main(String str) {

        boolean ok = true;
        String resultStr = null;

        while (ok) {
            if (str.contains("[") && str.contains("]")) {
                resultStr = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
                str = str.replace("["+resultStr+"]", "");
            } else ok = false;
        }

        return str;

    }
}
