package lk.cwresports.OneCoreOneMace.Core;

import lk.cwresports.OneCoreOneMace.Utils.TextStrings;
import lk.cwresports.OneCoreOneMace.api.MaceHolderChangeEvent;
import lk.cwresports.OneCoreOneMace.api.MaceHolderDropHisWorthEvent;
import lk.cwresports.OneCoreOneMace.api.MaceHolderNullEvent;
import lk.cwresports.OneCoreOneMace.api.MaceHolderSetEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class SendMassagesUsingAPI implements Listener {
    private static Plugin plugin;

    @EventHandler
    public void onMaceHolderSetEvent(MaceHolderSetEvent event) {
        if (event.getMaceHolder().isOnline()) {
            Player maceHolder = event.getMaceHolder().getPlayer();
            if (maceHolder == null) return;
            maceHolder.sendMessage(TextStrings.colorize(TextStrings.MACE_HOLDER_SET));
        }
    }

    @EventHandler
    public void onMaceHolderChangeEvent(MaceHolderChangeEvent event) {
        if (event.getOldHolder().isOnline()) {
            Player oldHolder = event.getOldHolder().getPlayer();
            if (oldHolder == null) return;
            oldHolder.sendMessage(TextStrings.MACE_HOLDER_CHANGE_SEND_TO_OLD_ONE_MASSAGE);
        }

        if (event.getNewHolder().isOnline()) {
            Player maceHolder = event.getNewHolder().getPlayer();
            if (maceHolder == null) return;
            maceHolder.sendMessage(TextStrings.colorize(TextStrings.MACE_HOLDER_SET));
        }
    }

    @EventHandler
    public void onMaceHolderDropHisWorthEvent(MaceHolderDropHisWorthEvent event) {
        if (event.isDropped()) {
            if (MaceHolder.getOfflinePlayer() == null) return;
            if (MaceHolder.getOfflinePlayer().isOnline()) {
                Player player = MaceHolder.getOfflinePlayer().getPlayer();
                if (player == null) return;
                player.sendMessage(TextStrings.HOLDER_REMOVE_MACE_FROM_HIS_INV_MASSAGE);
            }
        }
    }

    @EventHandler
    public void onMaceHolderNullEvent(MaceHolderNullEvent event) {
        plugin.getServer().getOnlinePlayers().forEach((player -> {
            if (player == null) return;
            player.sendMessage(TextStrings.MACE_HOLDER_NULL_MASSAGE);
        }));
    }

    public static void register(Plugin plugin) {
        SendMassagesUsingAPI.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(new SendMassagesUsingAPI(), plugin);
    }
}
