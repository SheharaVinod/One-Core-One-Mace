package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MaceDropAndPickUpEventListener implements Listener {
    @EventHandler
    public void onMaceDropEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (MaceHolder.getOfflinePlayer() == player) {
            ItemStack droppedItem = event.getItemDrop().getItemStack();
            if (droppedItem.getType() == Material.MACE || droppedItem.getType() == Material.HEAVY_CORE) {
                MaceHolder.setAsDropped(true);
            }
        }
    }

    @EventHandler
    public void onMacePickUpEvent(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (MaceHolder.getOfflinePlayer() == player) {
                ItemStack itemStack = event.getItem().getItemStack();
                if (itemStack.getType() == Material.MACE || itemStack.getType() == Material.HEAVY_CORE) {
                    MaceHolder.setAsDropped(false);
                }
            }
        }
    }

    @EventHandler
    public void onMaceHolderDieEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (MaceHolder.getOfflinePlayer() == player) {
            for (ItemStack drop : event.getDrops()) {
                if (drop.getType() == Material.MACE || drop.getType() == Material.HEAVY_CORE) {
                    MaceHolder.setAsDropped(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerPutAndGetMaceFromContainer(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (MaceHolder.getOfflinePlayer() == player) {
            Inventory inventory = player.getInventory();
            boolean isContain = !(inventory.contains(Material.MACE) || inventory.contains(Material.HEAVY_CORE));
            MaceHolder.setAsDropped(isContain);
        }
    }

    @EventHandler
    public void onPlayerPutAndGetMaceFromContainer(InventoryCloseEvent event) {
        if (MaceHolder.getOfflinePlayer() == event.getPlayer()) {
            Inventory inventory = event.getPlayer().getInventory();
            boolean isContain = !(inventory.contains(Material.MACE) || inventory.contains(Material.HEAVY_CORE));
            MaceHolder.setAsDropped(isContain);
        }
    }

    public static void register(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new MaceDropAndPickUpEventListener(), plugin);
    }


}
