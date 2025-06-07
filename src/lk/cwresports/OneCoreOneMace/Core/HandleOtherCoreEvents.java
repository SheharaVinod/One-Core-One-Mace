package lk.cwresports.OneCoreOneMace.Core;

import lk.cwresports.OneCoreOneMace.api.MaceHolderChangeEvent;
import lk.cwresports.OneCoreOneMace.api.MaceHolderNullEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class HandleOtherCoreEvents implements Listener {
    @EventHandler
    public void onMaceHolderNull(MaceHolderNullEvent event) {
        Bukkit.getOnlinePlayers().forEach((player -> {
            if (player.getInventory().contains(Material.MACE)) {
                player.getInventory().remove(Material.MACE);
            }
        }));
    }

//    @EventHandler
//    public void onMaceHolderChangeEvent(MaceHolderChangeEvent event) {
//        if (event.getNewHolder() != null) {
//            if (event.getNewHolder().isOnline()) {
//                Player player = event.getNewHolder().getPlayer();
//                if (player == null) return;
//                if (!player.getInventory().contains(Material.MACE)) {
//                    MaceHolder.setAsDropped(true);
//                }
//            }
//        }
//    }
}
