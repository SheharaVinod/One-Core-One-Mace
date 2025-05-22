package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.API.Events.MacePickUpEvent;
import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MaceHolderChangeEventListener implements Listener {
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            if (event.getItem().getItemStack().getType() == Material.MACE) {
                MacePickUpEvent pickUpEvent = new MacePickUpEvent((Player) event.getEntity(), event.getItem().getItemStack());
                Bukkit.getServer().getPluginManager().callEvent(pickUpEvent);
            }
        }
    }

    @EventHandler
    public void clickOnItemInInventory(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (MaceHolder.getOfflinePlayer() == player) return;

        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null) return;

        if (currentItem.getType() == Material.MACE) {
            if (MaceHolder.isMaceDropped()) {
                MacePickUpEvent pickUpEvent = new MacePickUpEvent(player, currentItem);
                Bukkit.getServer().getPluginManager().callEvent(pickUpEvent);
                MaceHolder.setAsDropped(false);
            }
        }
    }

    public static void register(Plugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new MaceHolderChangeEventListener(), plugin);
    }
}
