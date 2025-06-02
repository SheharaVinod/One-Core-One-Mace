package lk.cwresports.OneCoreOneMace.Core;

import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import lk.cwresports.OneCoreOneMace.Utils.TextStrings;
import lk.cwresports.OneCoreOneMace.api.MaceHolderChangeEvent;
import lk.cwresports.OneCoreOneMace.api.MaceHolderDropHisWorthEvent;
import lk.cwresports.OneCoreOneMace.api.MaceHolderNullEvent;
import lk.cwresports.OneCoreOneMace.api.MaceHolderSetEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class MaceHolder {
    private static OfflinePlayer offlinePlayer = null;
    private static Plugin plugin;

    public static OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }

    public static String getOfflinePlayersName() {
        if (offlinePlayer == null) {
            return TextStrings.NOT_AVAILABLE;
        }
        return offlinePlayer.getName();
    }

    public static void setOfflineMaceHolderAlsoForConfig(OfflinePlayer offlinePlayer) {
        if (MaceHolder.offlinePlayer != null) {
            if (offlinePlayer != MaceHolder.offlinePlayer) {
                // change event.
                Event changeEvent = new MaceHolderChangeEvent(MaceHolder.offlinePlayer, offlinePlayer);
                Bukkit.getPluginManager().callEvent(changeEvent);
            }
        } else {
            // Now we know we don't have a mace holder. (MaceHolder.offlinePlayer == null)
            if (offlinePlayer != null) {
                // fist time mace holder.
                Event maceHolderSetEvent = new MaceHolderSetEvent(offlinePlayer);
                Bukkit.getPluginManager().callEvent(maceHolderSetEvent);
            } else {
                // mace holder null event.
                Event maceHolderNullEvent = new MaceHolderNullEvent();
                Bukkit.getPluginManager().callEvent(maceHolderNullEvent);
            }
        }

        MaceHolder.offlinePlayer = offlinePlayer;
        if (offlinePlayer == null) {
            plugin.getConfig().set(ConfigPaths.HOLDERS_UUID, ConfigPaths.HOLDERS_UUID_DEFAULT);
            plugin.saveConfig();
            return;
        }
        plugin.getConfig().set(ConfigPaths.HOLDERS_UUID, offlinePlayer.getUniqueId().toString());
        plugin.saveConfig();
    }

    public static void loadOfflineMaceHolderUsingConfig() {
        String offlineUUID = plugin.getConfig().getString(ConfigPaths.HOLDERS_UUID, ConfigPaths.HOLDERS_UUID_DEFAULT);
        if (offlineUUID.equalsIgnoreCase(ConfigPaths.HOLDERS_UUID_DEFAULT)) {
            MaceHolder.offlinePlayer = null;
            return;
        }
        try {
            MaceHolder.offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(offlineUUID));
        } catch (Exception e) {
            e.printStackTrace();
            MaceHolder.offlinePlayer = null;
        }
    }

    public static void setAsDropped(boolean b) {
        plugin.getConfig().set(ConfigPaths.IS_HEAVY_CORE_DROPPED, b);
        plugin.saveConfig();

        Event maceDropped = new MaceHolderDropHisWorthEvent(b);
        Bukkit.getPluginManager().callEvent(maceDropped);
    }

    public static boolean isMaceDropped() {
        return plugin.getConfig().getBoolean(ConfigPaths.IS_HEAVY_CORE_DROPPED, ConfigPaths.IS_HEAVY_CORE_DROPPED_DEFAULT);
    }

    public static boolean is_cansel_heavy_core_drop_event() {
        return plugin.getConfig().getBoolean(ConfigPaths.CANCEL_HEAVY_CORE_DROP_EVENT, ConfigPaths.CANCEL_HEAVY_CORE_DROP_EVENT_DEFAULT);
    }

    public static void cansel_heavy_core_drop_event(boolean b) {
        plugin.getConfig().set(ConfigPaths.CANCEL_HEAVY_CORE_DROP_EVENT, b);
        plugin.saveConfig();
    }

    public static boolean can_crafting_a_mace() {
        return offlinePlayer == null;
    }

    public static void register(Plugin plugin) {
        MaceHolder.plugin = plugin;
        MaceHolder.loadOfflineMaceHolderUsingConfig();
    }
}
