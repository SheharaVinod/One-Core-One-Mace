package lk.cwresports.OneCoreOneMace.API.Events;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class HeavyCoreDropEvent extends Event implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();
    private boolean cansel = false;

    public OfflinePlayer getMaceHolder() {
        return MaceHolder.getOfflinePlayer();
    }

    public boolean isAlreadyCansel() {
        return MaceHolder.is_cansel_heavy_core_drop_event();
    }

    public void canselForever(boolean b) {
        MaceHolder.cansel_heavy_core_drop_event(b);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return this.cansel;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cansel = b;
    }
}
