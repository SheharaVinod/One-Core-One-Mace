package lk.cwresports.OneCoreOneMace;

import lk.cwresports.OneCoreOneMace.Commands.OneCoreOneMaceCommand;
import lk.cwresports.OneCoreOneMace.Core.HoldersEventScheduler;
import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Core.SendMassagesUsingAPI;
import lk.cwresports.OneCoreOneMace.Listeners.*;
import lk.cwresports.OneCoreOneMace.PlaceHolders.OCOMPlaceHolders;
import lk.cwresports.OneCoreOneMace.TabCompletions.OneCoreOneMaceTab;
import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import lk.cwresports.OneCoreOneMace.Utils.CwRBetterConsoleLogger;
import lk.cwresports.OneCoreOneMace.Utils.CwRCommandManager;
import lk.cwresports.OneCoreOneMace.Utils.TextStrings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class OneCoreOneMace extends JavaPlugin {

    @Override
    public void onEnable() {
        // save default configs.
        saveDefaultConfig();

        // Load text Strings.
        String prefix = getConfig().getString(ConfigPaths.PREFIX_PATH, ConfigPaths.PREFIX_PATH_DEFAULT);

        try {
            Class.forName("lk.cwresports.OneCoreOneMace.Utils.TextStrings");
            Class.forName("lk.cwresports.OneCoreOneMace.Utils.CwRBetterConsoleLogger");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        CwRBetterConsoleLogger.registerLogger(this, prefix);
        TextStrings.loadTextStringsFromConfigs(this);

        // register core classes.
        MaceHolder.register(this);

        // register commands.
        Objects.requireNonNull(getCommand(CwRCommandManager.ONE_CORE_ONE_MACE)).setExecutor(new OneCoreOneMaceCommand(this));

        // register tabs.
        Objects.requireNonNull(getCommand(CwRCommandManager.ONE_CORE_ONE_MACE)).setTabCompleter(new OneCoreOneMaceTab());

        // register events.
        HoldersEventScheduler.register(this);

        GeneralEvents.register(this);
        MaceDropAndPickUpEventListener.register(this);
        RemoveAnyMaceOnJoin.register(this);
        RemoveAnyMaceOnClickingInventory.register(this);
        RemoveAnyMaceOnUsing.register(this);
        SendMassagesUsingAPI.register(this);
        RemoveExtraMaceOnCrafting.register(this);

        CwRBetterConsoleLogger.log("-----------------------------------");
        CwRBetterConsoleLogger.log("  ██████╗██╗    ██╗██████╗");
        CwRBetterConsoleLogger.log(" ██╔════╝██║    ██║██╔══██╗");
        CwRBetterConsoleLogger.log(" ██║     ██║ █╗ ██║██████╔╝");
        CwRBetterConsoleLogger.log(" ██║     ██║███╗██║██╔══██╗");
        CwRBetterConsoleLogger.log(" ╚██████╗╚███╔███╔╝██║  ██║");
        CwRBetterConsoleLogger.log("  ╚═════╝ ╚══╝╚══╝ ╚═╝  ╚═╝  (Dev)");
        CwRBetterConsoleLogger.log("-----------------------------------");
        CwRBetterConsoleLogger.log("----------One-Core-One-Mace--------");
        CwRBetterConsoleLogger.log("-----------------------------------");

        registerOtherPlugins();
    }

    public void registerOtherPlugins() {
        Plugin placeHolderAPI = Bukkit.getPluginManager().getPlugin("PlaceHolderAPI");
        boolean hasPAPI = placeHolderAPI != null && placeHolderAPI.isEnabled();

        if (hasPAPI) {
            CwRBetterConsoleLogger.log("PlaceHolder Found; hooks are registered.");
            OCOMPlaceHolders.registerHook();
        } else {
            CwRBetterConsoleLogger.log("Could not found the PlaceHolder to register hooks.");
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
        HoldersEventScheduler.saveData();
        HoldersEventScheduler.canselTimer();
    }

}
