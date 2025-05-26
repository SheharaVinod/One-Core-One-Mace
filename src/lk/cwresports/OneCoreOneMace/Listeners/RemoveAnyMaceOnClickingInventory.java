package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import lk.cwresports.OneCoreOneMace.Utils.CwRPermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class RemoveAnyMaceOnClickingInventory implements Listener {
    private static Plugin plugin;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (we_need_to_remove_the_mace_on_clicking_inventory()) {
            if (event.getWhoClicked() instanceof Player player) {
                if (MaceHolder.getOfflinePlayer() == player) {
                    return;
                }

                if (!CwRPermissionManager.hasHolderBypass(player)) {
                    ItemStack currentItem = event.getCurrentItem();
                    if (currentItem == null) return;
                    if (currentItem.getType() == Material.MACE) {
                        event.getInventory().remove(Material.MACE);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemCraft(InventoryClickEvent event) {
        // register mace crafting player.
        if (event.getWhoClicked() instanceof Player player) {
            if (MaceHolder.getOfflinePlayer() == player) {
                return;
            }

            if (MaceHolder.getOfflinePlayer() == null) {
                ItemStack currentItem = event.getCurrentItem();
                if (currentItem == null) return;
                if (currentItem.getType() == Material.MACE) {
                    MaceHolder.setOfflineMaceHolderAlsoForConfig(player);
                }
            }
        }
    }

    private boolean we_need_to_remove_the_mace_on_clicking_inventory() {
        return plugin.getConfig().getBoolean(ConfigPaths.REMOVE_EXTRA_MACE_ON_CLICK, ConfigPaths.REMOVE_EXTRA_MACE_ON_CLICK_DEFAULT);
    }

    public static void register(Plugin plugin) {
        RemoveAnyMaceOnClickingInventory.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveAnyMaceOnClickingInventory(), plugin);
    }
}
