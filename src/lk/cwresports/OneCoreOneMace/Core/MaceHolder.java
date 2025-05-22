package lk.cwresports.OneCoreOneMace.Core;

import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class MaceHolder {
    private static OfflinePlayer offlinePlayer = null;
    private static Plugin plugin;
    private static boolean is_dropped = false;

    public static OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }

    public static void setOfflineMaceHolderAlsoForConfig(OfflinePlayer offlinePlayer) {
        MaceHolder.offlinePlayer = offlinePlayer;
        plugin.getConfig().set(ConfigPaths.HOLDERS_UUID, offlinePlayer.getUniqueId().toString());
        plugin.saveConfig();
    }

    public static void loadOfflineMaceHolderUsingConfig() {
        String offlineUUID = plugin.getConfig().getString(ConfigPaths.HOLDERS_UUID, ConfigPaths.HOLDERS_UUID_DEFAULT);
        if (offlineUUID.equalsIgnoreCase(ConfigPaths.HOLDERS_UUID_DEFAULT)) {
            MaceHolder.offlinePlayer = null;
        }
        try {
            MaceHolder.offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(offlineUUID));
        } catch (Exception e) {
            e.printStackTrace();
            MaceHolder.offlinePlayer = null;
        }
    }

    public static void setAsDropped(boolean b) {
        is_dropped = b;
    }

    public static boolean isMaceDropped() {
        return is_dropped;
    }

    public static boolean is_cansel_heavy_core_drop_event() {
        return plugin.getConfig().getBoolean(ConfigPaths.CANCEL_HEAVY_CORE_DROP_EVENT, ConfigPaths.CANCEL_HEAVY_CORE_DROP_EVENT_DEFAULT);
    }

    public static void cansel_heavy_core_drop_event(boolean b) {
        plugin.getConfig().set(ConfigPaths.CANCEL_HEAVY_CORE_DROP_EVENT, b);
        plugin.saveConfig();
    }

    public static boolean can_crafting_a_mace() {
        return true;
    }

    public static void register(Plugin plugin) {
        MaceHolder.plugin = plugin;
    }
}
