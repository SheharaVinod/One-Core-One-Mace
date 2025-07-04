package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import lk.cwresports.OneCoreOneMace.Utils.CwRBetterConsoleLogger;
import lk.cwresports.OneCoreOneMace.Utils.CwRPermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class RemoveAnyMaceOnJoin implements Listener {
    private static Plugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        CwRBetterConsoleLogger.debug("PlayerJoinEvent executing..(RemoveAnyMaceOnJoin.onJoin)");
        if (we_need_to_remove_the_mace_on_join()) {
            if (CwRPermissionManager.hasHolderBypass(event.getPlayer())) {
                return;
            }
            Player player = event.getPlayer();
            if (player.getInventory().contains(Material.MACE)) {
                if (CwRPermissionManager.hasHolderBypass(player)) {
                    return;
                }

                if (player != MaceHolder.getOfflinePlayer()) {
                    player.getInventory().remove(Material.MACE);
                }
            }
        }
    }

    private boolean we_need_to_remove_the_mace_on_join() {
        return plugin.getConfig().getBoolean(ConfigPaths.REMOVE_EXTRA_MACE_ON_JOIN, ConfigPaths.REMOVE_EXTRA_MACE_ON_JOIN_DEFAULT);
    }

    public static void register(Plugin plugin) {
        RemoveAnyMaceOnJoin.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveAnyMaceOnJoin(), plugin);
    }
}
