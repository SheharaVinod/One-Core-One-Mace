package lk.cwresports.OneCoreOneMace.Listeners;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import lk.cwresports.OneCoreOneMace.Utils.CwRNameSpaceKeys;
import lk.cwresports.OneCoreOneMace.Utils.CwRPermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

public class GeneralEvents implements Listener {

    private static Plugin plugin;

    @EventHandler
    public void onFistTimePlayerPickUpHeavyCore(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack pickedItem = event.getItem().getItemStack();
            ItemMeta itemMeta = pickedItem.getItemMeta();

            if (pickedItem.getType() != Material.HEAVY_CORE) {
                return;
            }

            // clear heavy core if another one.
            if (MaceHolder.getOfflinePlayer() == player) {
                if (itemMeta == null) {
                    pickedItem.setType(Material.AIR);
                    return;
                }
                PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
                NamespacedKey cwrMaceHolder = new NamespacedKey(plugin, CwRNameSpaceKeys.CWR_MACE_HOLDER);

                if (dataContainer.has(cwrMaceHolder, PersistentDataType.STRING)) {
                    String uuid = dataContainer.get(cwrMaceHolder, PersistentDataType.STRING);
                    if (uuid == null) return;
                    UUID from_string = UUID.fromString(uuid);
                    if (!player.getUniqueId().equals(from_string)) {
                        pickedItem.setType(Material.AIR);
                        return;
                    }
                }
            }

            // fist time pick item , set data container.
            if (MaceHolder.getOfflinePlayer() == null) {
                if (CwRPermissionManager.hasHolderBypass(player)) return;

                if (itemMeta == null) return;
                PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
                NamespacedKey cwrMaceHolder = new NamespacedKey(plugin, CwRNameSpaceKeys.CWR_MACE_HOLDER);

                if (!dataContainer.has(cwrMaceHolder, PersistentDataType.STRING)) {
                    UUID playerUUID = player.getUniqueId();
                    dataContainer.set(cwrMaceHolder, PersistentDataType.STRING, playerUUID.toString());
                    pickedItem.setItemMeta(itemMeta);
                }

                MaceHolder.setOfflineMaceHolderAlsoForConfig(player);
            }

            // check if the heavy core is now on a new player.
            if (MaceHolder.getOfflinePlayer() != player) {
                if (itemMeta == null) {
                    pickedItem.setType(Material.AIR);
                    return;
                }
                PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
                NamespacedKey cwrMaceHolder = new NamespacedKey(plugin, CwRNameSpaceKeys.CWR_MACE_HOLDER);

                if (dataContainer.has(cwrMaceHolder, PersistentDataType.STRING)) {
                    UUID playerUUID = player.getUniqueId();
                    dataContainer.set(cwrMaceHolder, PersistentDataType.STRING, playerUUID.toString());
                    pickedItem.setItemMeta(itemMeta);
                }

                MaceHolder.setOfflineMaceHolderAlsoForConfig(player);
            }
        }
    }

    @EventHandler
    public void onPlayerCraftMaceEvent(CraftItemEvent event) {
        Recipe recipe = event.getRecipe();
        ItemStack result = recipe.getResult();
        if (result.getType() != Material.MACE) {
            return;
        }

        CraftingInventory craftingInv = event.getInventory();
        ItemStack[] matrix = craftingInv.getMatrix();

        for (ItemStack untilHeavyCore : matrix) {
            if (untilHeavyCore.getType() != Material.HEAVY_CORE) {
                continue;
            }
            ItemMeta itemMeta = untilHeavyCore.getItemMeta();
            if (itemMeta == null) return;
            PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
            NamespacedKey namespacedKey = new NamespacedKey(plugin, CwRNameSpaceKeys.CWR_MACE_HOLDER);
            if (dataContainer.has(namespacedKey, PersistentDataType.STRING)) {
                ItemMeta resultItemMeta = result.getItemMeta();
                String previous_uuid = dataContainer.get(namespacedKey, PersistentDataType.STRING);
                if (previous_uuid == null) return;
                if (resultItemMeta == null) {
                    return;
                }
                PersistentDataContainer resultDataContainer = resultItemMeta.getPersistentDataContainer();
                resultDataContainer.set(namespacedKey, PersistentDataType.STRING, previous_uuid);
                result.setItemMeta(resultItemMeta);
            } else {
                result.setType(Material.AIR);
                return;
            }
        }
    }

    @EventHandler
    public void canselCrafterCraftItemEvent(CrafterCraftEvent event) {
        boolean can_craft_using_crafter = plugin.getConfig().getBoolean(ConfigPaths.CAN_CRAFT_MACE_USING_CRAFTER, ConfigPaths.CAN_CRAFT_MACE_USING_CRAFTER_DEFAULT);
        if (can_craft_using_crafter) return;

        if (event.getResult().getType() == Material.MACE) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onLootGenerateEventCanselIfAHeavyCore(LootGenerateEvent event) {
        List<ItemStack> loot = event.getLoot();
        loot.removeIf(itemStack -> {
            if (itemStack.getType() != Material.HEAVY_CORE) {
                return false;
            }
            if (MaceHolder.getOfflinePlayer() == null) {
                return false;
            }
            // should_cansel_heavy_core_drop_event
            return MaceHolder.is_cansel_heavy_core_drop_event();
        });
    }

    @EventHandler
    public void ifSomeOtherPlayerGetTheMaceOrHeavyCore(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        ItemStack itemStack = event.getItem().getItemStack();
        // check it is a mace or a heavy core.
        if (!(itemStack.getType() == Material.MACE || itemStack.getType() == Material.HEAVY_CORE)) return;

        // now we know mace holder is changed.
        if (MaceHolder.getOfflinePlayer() != player) {
            if (MaceHolder.isMaceDropped()) {

                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta == null) return;
                PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
                NamespacedKey namespacedKey = new NamespacedKey(plugin, CwRNameSpaceKeys.CWR_MACE_HOLDER);
                if (dataContainer.has(namespacedKey, PersistentDataType.STRING)) {
                    UUID current_players_uuid = player.getUniqueId();
                    dataContainer.set(namespacedKey, PersistentDataType.STRING, current_players_uuid.toString());
                    itemStack.setItemMeta(itemMeta);
                } else {
                    return;
                }

                MaceHolder.setAsDropped(false);
                MaceHolder.setOfflineMaceHolderAlsoForConfig(player);
            }
        }
    }

    public static void register(Plugin plugin) {
        GeneralEvents.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(new GeneralEvents(), plugin);
    }


}
