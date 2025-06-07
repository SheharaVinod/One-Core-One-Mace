package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MaceDropAndPickUpEventListener implements Listener {


    private static BukkitRunnable runnable = null;

//    @EventHandler
//    public void onMaceDropEvent(PlayerDropItemEvent event) {
//        CwRBetterConsoleLogger.debug("PlayerDropItemEvent executing..(MaceDropAndPickUpEventListener.onMaceDropEvent)");
//        Player player = event.getPlayer();
//        if (MaceHolder.getOfflinePlayer() == player) {
//            ItemStack droppedItem = event.getItemDrop().getItemStack();
//            if (droppedItem.getType() == Material.MACE || droppedItem.getType() == Material.HEAVY_CORE) {
//                MaceHolder.setAsDropped(true);
//            }
//        }
//    }

//    @EventHandler
//    public void onMacePickUpEvent(EntityPickupItemEvent event) {
//        CwRBetterConsoleLogger.debug("EntityPickupItemEvent executing..(MaceDropAndPickUpEventListener.onMacePickUpEvent)");
//        if (event.getEntity() instanceof Player player) {
//            if (MaceHolder.getOfflinePlayer() == player) {
//                ItemStack itemStack = event.getItem().getItemStack();
//                if (itemStack.getType() == Material.MACE || itemStack.getType() == Material.HEAVY_CORE) {
//                    MaceHolder.setAsDropped(false);
//                }
//            }
//        }
//    }

//    @EventHandler
//    public void onMaceHolderDieEvent(PlayerDeathEvent event) {
//        CwRBetterConsoleLogger.debug("PlayerDeathEvent executing..(MaceDropAndPickUpEventListener.onMaceHolderDieEvent)");
//        Player player = event.getEntity();
//        if (MaceHolder.getOfflinePlayer() == player) {
//            for (ItemStack drop : event.getDrops()) {
//                if (drop.getType() == Material.MACE || drop.getType() == Material.HEAVY_CORE) {
//                    MaceHolder.setAsDropped(true);
//                }
//            }
//        }
//    }

//    @EventHandler
//    public void onPlayerRespawn(PlayerRespawnEvent event) {
//        CwRBetterConsoleLogger.debug("PlayerRespawnEvent executing..(MaceDropAndPickUpEventListener.onPlayerRespawn)");
//        Player player = event.getPlayer();
//        if (player == MaceHolder.getOfflinePlayer()) {
//            if (!(player.getInventory().contains(Material.MACE) || player.getInventory().contains(Material.HEAVY_CORE))) {
//                MaceHolder.setAsDropped(true);
//            }
//        }
//    }

//    @EventHandler
//    public void onPlayerPutAndGetMaceFromContainer(InventoryClickEvent event) {
//        CwRBetterConsoleLogger.debug("InventoryClickEvent executing..(MaceDropAndPickUpEventListener)");
//        Player player = (Player) event.getWhoClicked();
//        if (MaceHolder.getOfflinePlayer() == player) {
//            Inventory inventory = player.getInventory();
//            boolean isContain = !(inventory.contains(Material.MACE) || inventory.contains(Material.HEAVY_CORE));
//            MaceHolder.setAsDropped(isContain);
//        }
//    }

//    @EventHandler
//    public void onPlayerPutAndGetMaceFromContainer(InventoryCloseEvent event) {
//        CwRBetterConsoleLogger.debug("InventoryCloseEvent executing..(MaceDropAndPickUpEventListener)");
//        if (MaceHolder.getOfflinePlayer() == event.getPlayer()) {
//            Inventory inventory = event.getPlayer().getInventory();
//            boolean isContain = !(inventory.contains(Material.MACE) || inventory.contains(Material.HEAVY_CORE));
//            MaceHolder.setAsDropped(isContain);
//        }
//    }

    public static void register(Plugin plugin) {
        MaceDropAndPickUpEventListener.runnable = new BukkitRunnable() {
            boolean is_dropped = false;

            @Override
            public void run() {
                if (MaceHolder.getOfflinePlayer() == null) {
                    return;
                }
                if (!MaceHolder.getOfflinePlayer().isOnline()) {
                    return;
                }

                Player maceHolder = MaceHolder.getOfflinePlayer().getPlayer();
                if (maceHolder == null) return;
                if (maceHolder.getInventory().contains(Material.MACE)) {
                    if (MaceHolder.isMaceDropped() != is_dropped) {
                        MaceHolder.setAsDropped(false);
                        is_dropped = false;
                    }
                } else {
                    if (MaceHolder.isMaceDropped() != is_dropped) {
                        MaceHolder.setAsDropped(true);
                        is_dropped = true;
                    }
                }
            }
        };
        MaceDropAndPickUpEventListener.runnable.runTaskTimer(plugin, 0, 1);

        plugin.getServer().getPluginManager().registerEvents(new MaceDropAndPickUpEventListener(), plugin);
    }

    public static void canselRunnable() {
        if (MaceDropAndPickUpEventListener.runnable != null) {
            if (MaceDropAndPickUpEventListener.runnable.getTaskId() != -1) {
                if (!MaceDropAndPickUpEventListener.runnable.isCancelled()) {
                    MaceDropAndPickUpEventListener.runnable.cancel();
                }
            }
        }
    }
}
