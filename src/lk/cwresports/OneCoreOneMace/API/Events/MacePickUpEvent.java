package lk.cwresports.OneCoreOneMace.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class MacePickUpEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    private final Player pickUpPlayer;
    private final ItemStack mace;

    public MacePickUpEvent(Player player, ItemStack itemStack) {
        this.pickUpPlayer = player;
        this.mace = itemStack;
    }

    public Player getPickUpPlayer() {
        return pickUpPlayer;
    }

    public ItemStack getMace() {
        return mace;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
