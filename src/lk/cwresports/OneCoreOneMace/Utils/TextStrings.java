package lk.cwresports.OneCoreOneMace.Utils;

import org.bukkit.ChatColor;

public class TextStrings {
    public static String NOT_AVAILABLE = "n/a";


    public static String colorize(String massage) {
        return ChatColor.translateAlternateColorCodes('&', massage);
    }

    public static void loadTextStringsFromConfigs() {
        // this method ensure this class is loading properly.
        // TODO: implement to get config data.

    }
}
