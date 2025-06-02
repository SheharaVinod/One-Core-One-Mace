package lk.cwresports.OneCoreOneMace.Utils;

import org.bukkit.plugin.Plugin;

public class CwRBetterConsoleLogger {
    private static Plugin plugin;
    private static String PREFIX = "&7[&6CwR&f-OCOM&7]&r ";

    public static void registerLogger(Plugin plugin, String prefix) {
        CwRBetterConsoleLogger.plugin = plugin;
        PREFIX = TextStrings.colorize(prefix + " ", true);
    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public static void log(String massage) {
        log(massage, false);
    }

    public static void log(String massage, boolean withoutPrefix) {
        if (withoutPrefix) {
            plugin.getServer().getConsoleSender().sendMessage(massage);
        } else {
            plugin.getServer().getConsoleSender().sendMessage(TextStrings.colorize(massage));
        }
    }
}
