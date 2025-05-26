package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;

public class CanselMaceCrafting implements Listener {
    @EventHandler
    public void onCrafting(PrepareItemCraftEvent event) {
        Recipe recipe = event.getRecipe();
        if (recipe == null) return;
        if (recipe.getResult().getType() == Material.MACE) {
            if (!MaceHolder.can_crafting_a_mace()) {
                recipe.getResult().setType(Material.AIR);
            }
        }
    }

    public static void register(Plugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new CanselMaceCrafting(), plugin);
    }
}
