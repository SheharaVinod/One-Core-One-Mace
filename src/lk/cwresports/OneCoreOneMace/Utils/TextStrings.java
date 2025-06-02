package lk.cwresports.OneCoreOneMace.Utils;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class TextStrings {
    public static String NOT_AVAILABLE = "n/a";
    public static String MACE_HOLDER_SET = "n/a";
    public static String MACE_HOLDER_CHANGE_SEND_TO_OLD_ONE_MASSAGE = "n/a";
    public static String HOLDER_REMOVE_MACE_FROM_HIS_INV_MASSAGE = "n/a";
    public static String MACE_HOLDER_NULL_MASSAGE = "n/a";

    public static String colorize(String massage, boolean remove_prefix) {
        if (remove_prefix) {
            return ChatColor.translateAlternateColorCodes('&', massage);
        }
        return colorize(massage);
    }


    public static String colorize(String massage) {
        return CwRBetterConsoleLogger.getPREFIX() + ChatColor.translateAlternateColorCodes('&', massage);
    }

    public static void loadTextStringsFromConfigs(Plugin plugin) {
        // this method ensure this class is loading properly.
        NOT_AVAILABLE = plugin.getConfig().getString(ConfigPaths.PLAYER_NOT_AVAILABLE, ConfigPaths.PLAYER_NOT_AVAILABLE_DEFAULT);
        MACE_HOLDER_SET = plugin.getConfig().getString(ConfigPaths.MACE_HOLDER_SET_MASSAGE, ConfigPaths.MACE_HOLDER_SET_MASSAGE_DEFAULT);
        MACE_HOLDER_CHANGE_SEND_TO_OLD_ONE_MASSAGE = plugin.getConfig().getString(ConfigPaths.MACE_HOLDER_CHANGE_SEND_TO_OLD_ONE_MASSAGE, ConfigPaths.MACE_HOLDER_CHANGE_SEND_TO_OLD_ONE_MASSAGE_DEFAULT);
        HOLDER_REMOVE_MACE_FROM_HIS_INV_MASSAGE = plugin.getConfig().getString(ConfigPaths.HOLDER_REMOVE_MACE_FROM_HIS_INV_MASSAGE, ConfigPaths.HOLDER_REMOVE_MACE_FROM_HIS_INV_MASSAGE_DEFAULT);
        MACE_HOLDER_NULL_MASSAGE = plugin.getConfig().getString(ConfigPaths.MACE_HOLDER_NULL_MASSAGE, ConfigPaths.MACE_HOLDER_NULL_MASSAGE_DEFAULT);

        colorizeEveryThing();
    }

    private static void colorizeEveryThing() {
        NOT_AVAILABLE = colorize(NOT_AVAILABLE, true);
        MACE_HOLDER_SET = colorize(MACE_HOLDER_SET, true);
        MACE_HOLDER_CHANGE_SEND_TO_OLD_ONE_MASSAGE = colorize(MACE_HOLDER_CHANGE_SEND_TO_OLD_ONE_MASSAGE, true);
        HOLDER_REMOVE_MACE_FROM_HIS_INV_MASSAGE = colorize(HOLDER_REMOVE_MACE_FROM_HIS_INV_MASSAGE, true);
        MACE_HOLDER_NULL_MASSAGE = colorize(MACE_HOLDER_NULL_MASSAGE, true);
    }
}
