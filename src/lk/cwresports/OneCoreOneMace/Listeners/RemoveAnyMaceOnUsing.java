package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import lk.cwresports.OneCoreOneMace.Utils.CwRBetterConsoleLogger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class RemoveAnyMaceOnUsing implements Listener {
    private static Plugin plugin;

    @EventHandler
    public void onInteracting(PlayerInteractEvent event) {
        CwRBetterConsoleLogger.debug("PlayerInteractEvent executing..(RemoveAnyMaceOnUsing.onInteracting)");
        if (we_need_to_remove_the_mace_on_using()) {
            Player player = event.getPlayer();
            if (MaceHolder.getOfflinePlayer() != player) {
                ItemStack itemStack = event.getItem();
                if (itemStack == null) {
                    return;
                }
                if (itemStack.getType() == Material.MACE) {
                    player.getInventory().remove(Material.MACE);
                }
            }
        }
    }


    private boolean we_need_to_remove_the_mace_on_using() {
        return plugin.getConfig().getBoolean(ConfigPaths.REMOVE_EXTRA_MACE_ON_USE, ConfigPaths.REMOVE_EXTRA_MACE_ON_USE_DEFAULT);
    }


    public static void register(Plugin plugin) {
        RemoveAnyMaceOnUsing.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveAnyMaceOnUsing(), plugin);
    }
}
