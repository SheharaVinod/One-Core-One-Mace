package lk.cwresports.OneCoreOneMace.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MaceHolderNullEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }


    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
