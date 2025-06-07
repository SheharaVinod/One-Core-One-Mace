package lk.cwresports.OneCoreOneMace.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.plugin.Plugin;

public class RemoveExtraMaceOnCrafting implements Listener {
    @EventHandler
    public void onMaceCraftEvent(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getInventory().contains(Material.MACE)) {
            event.getRecipe().getResult().setType(Material.AIR);
        }
    }

    public static void register(Plugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveExtraMaceOnCrafting(), plugin);
    }
}
