package lk.cwresports.OneCoreOneMace;

import lk.cwresports.OneCoreOneMace.Commands.OneCoreOneMaceCommand;
import lk.cwresports.OneCoreOneMace.Core.HolderEventListener;
import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Listeners.*;
import lk.cwresports.OneCoreOneMace.TabCompletions.OneCoreOneMaceTab;
import lk.cwresports.OneCoreOneMace.Utils.CwRCommandManager;
import lk.cwresports.OneCoreOneMace.Utils.TextStrings;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class OneCoreOneMace extends JavaPlugin {

    @Override
    public void onEnable() {
        // save default configs.
        saveDefaultConfig();

        // Load text Strings.
        TextStrings.loadTextStringsFromConfigs();

        // register core classes.
        MaceHolder.register(this);
        MaceHolder.loadOfflineMaceHolderUsingConfig();

        // register commands.
        Objects.requireNonNull(getCommand(CwRCommandManager.ONE_CORE_ONE_MACE)).setExecutor(new OneCoreOneMaceCommand());

        // register tabs.
        Objects.requireNonNull(getCommand(CwRCommandManager.ONE_CORE_ONE_MACE)).setTabCompleter(new OneCoreOneMaceTab());

        // register events.
        MaceEventListener.Register(this);
        MaceHolderChangeEventListener.register(this);

        HolderEventListener.register(this);
        RemoveAnyMaceOnJoin.register(this);
        RemoveAnyMaceOnClickingInventory.register(this);
        CanselMaceCrafting.register(this);
        RemoveAnyMaceOnUsing.register(this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
