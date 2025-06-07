package lk.cwresports.OneCoreOneMace.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MaceHolderDropHisWorthEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    private final boolean dropped;

    public MaceHolderDropHisWorthEvent(boolean dropped) {
        this.dropped = dropped;
    }

    public boolean isDropped() {
        return dropped;
    }

    public boolean isPickUp() {
        return !dropped;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }


    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
