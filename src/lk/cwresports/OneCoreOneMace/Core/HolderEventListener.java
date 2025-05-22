package lk.cwresports.OneCoreOneMace.Core;

import lk.cwresports.OneCoreOneMace.API.Events.HeavyCoreDropEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class HolderEventListener implements Listener {

    @EventHandler
    public void onCoreDropEvent(HeavyCoreDropEvent event) {
        if (event.isAlreadyCansel()) {
            event.setCancelled(true);
        } else {
            if (event.getMaceHolder() != null) {
                event.canselForever(true);
            }
        }
    }



    public static void register(Plugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new HolderEventListener(), plugin);
    }
}
