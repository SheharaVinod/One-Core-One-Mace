package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class MaceDropAndPickUpEventListener implements Listener {

    @EventHandler
    public void onMaceDropEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (MaceHolder.getOfflinePlayer() == player) {
            ItemStack droppedItem = event.getItemDrop().getItemStack();
            if (droppedItem.getType() == Material.MACE) {
                MaceHolder.setAsDropped(true);
            }
        }
    }

    @EventHandler
    public void onMacePickUpEvent(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (MaceHolder.getOfflinePlayer() == player) {
                ItemStack itemStack = event.getItem().getItemStack();
                if (itemStack.getType() == Material.MACE) {
                    MaceHolder.setAsDropped(false);
                }
            }
        }
    }

    @EventHandler
    public void onHeavyCorePicUpEvent(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (MaceHolder.getOfflinePlayer() != player) {
                ItemStack itemStack = event.getItem().getItemStack();
                if (itemStack.getType() == Material.HEAVY_CORE) {
                    MaceHolder.cansel_heavy_core_drop_event(true);
                }
            }
        }
    }

}
