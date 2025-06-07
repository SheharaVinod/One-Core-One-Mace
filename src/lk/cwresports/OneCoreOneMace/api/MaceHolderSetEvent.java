package lk.cwresports.OneCoreOneMace.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MaceHolderSetEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final OfflinePlayer maceHolder;

    public MaceHolderSetEvent(OfflinePlayer maceHolder) {
        this.maceHolder = maceHolder;
    }

    public OfflinePlayer getMaceHolder() {
        return maceHolder;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
