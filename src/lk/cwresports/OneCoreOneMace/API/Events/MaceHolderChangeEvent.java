package lk.cwresports.OneCoreOneMace.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MaceHolderChangeEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();
    private final Player old_holder;
    private final Player new_holder;


    public MaceHolderChangeEvent(Player old_holder, Player new_holder) {
        this.old_holder = old_holder;
        this.new_holder = new_holder;
    }

    public Player getOldHolder() {
        return old_holder;
    }

    public Player getNewHolder() {
        return new_holder;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    static public HandlerList getHandlerList() {
        return handlerList;
    }
}
