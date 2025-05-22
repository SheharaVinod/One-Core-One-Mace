package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.API.Events.HeavyCoreDropEvent;
import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class MaceEventListener implements Listener {

    @EventHandler
    public void onLootGenerateEvent(LootGenerateEvent event) {
        List<ItemStack> loot = event.getLoot();
        loot.removeIf(this::shouldCancelItem);
    }

    private boolean shouldCancelItem(ItemStack item) {
        if (MaceHolder.getOfflinePlayer() == null) {
            return false;
        }
        return isHeavyCoreAndShouldCansel(item);
    }

    private boolean isHeavyCoreAndShouldCansel(ItemStack itemStack) {
        if (itemStack != null) {
            boolean isHeavyCore = (itemStack.getType() == Material.HEAVY_CORE);
            if (isHeavyCore) {
                HeavyCoreDropEvent heavyCoreDropEvent = new HeavyCoreDropEvent();
                Bukkit.getServer().getPluginManager().callEvent(heavyCoreDropEvent);
                return heavyCoreDropEvent.isCancelled();
            }
        }
        return false;
    }


    public static void Register(Plugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new MaceEventListener(), plugin);
    }
}
