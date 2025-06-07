package lk.cwresports.OneCoreOneMace.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MaceHolderChangeEvent extends Event {

    public static final HandlerList handlerList = new HandlerList();
    private final OfflinePlayer oldHolder;
    private final OfflinePlayer newHolder;

    public MaceHolderChangeEvent(OfflinePlayer oldHolder, OfflinePlayer newHolder) {
        this.oldHolder = oldHolder;
        this.newHolder = newHolder;
    }

    public OfflinePlayer getOldHolder() {
        return oldHolder;
    }

    public OfflinePlayer getNewHolder() {
        return newHolder;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
