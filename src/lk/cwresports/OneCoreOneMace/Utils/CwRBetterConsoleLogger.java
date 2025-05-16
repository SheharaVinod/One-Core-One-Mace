package lk.cwresports.OneCoreOneMace.Utils;

import org.bukkit.plugin.Plugin;

public class CwRBetterConsoleLogger {
    private static String pluginPrefix = "[CwRBetterConsoleLogger]";
    private static Plugin plugin;

    public static void setPluginPrefix(Plugin plugin, String prefix) {
        CwRBetterConsoleLogger.plugin = plugin;
        pluginPrefix = TextStrings.colorize("&7[&r" + prefix + "&7]&r ");
    }

    public static void log(String massage) {
        log(massage, false);
    }

    public static void log(String massage, boolean withoutPrefix) {
        if (withoutPrefix) {
            plugin.getServer().getConsoleSender().sendMessage(massage);
        } else {
            plugin.getServer().getConsoleSender().sendMessage(pluginPrefix + massage);
        }
    }
}
