package lk.cwresports.OneCoreOneMace.Commands;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import lk.cwresports.OneCoreOneMace.Utils.CwRCommandManager;
import lk.cwresports.OneCoreOneMace.Utils.CwRPermissionManager;
import lk.cwresports.OneCoreOneMace.Utils.TextStrings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class OneCoreOneMaceCommand implements CommandExecutor {

    private final Plugin plugin;

    public OneCoreOneMaceCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            if (!CwRPermissionManager.hasAdminPerm(player)) {
                return true;
            }

            if (strings.length > 0) {
                // ocom drop_heavy_core enable
                if (strings[0].equalsIgnoreCase(CwRCommandManager.drop_heavy_core)) {
                    return drop_heavy_core(commandSender, command, s, strings);
                }

                // ocom change_mace_holder <names>
                if (strings[0].equalsIgnoreCase(CwRCommandManager.change_mace_holder)) {
                    return change_mace_holder(commandSender, command, s, strings);
                }

                // ocom reset_mace_holder <names>
                if (strings[0].equalsIgnoreCase(CwRCommandManager.reset_mace_holder)) {
                    return reset_mace_holder(commandSender, command, s, strings);
                }

                // ocom remove_extra_mace_on [join|click] [true|false]
                if (strings[0].equalsIgnoreCase(CwRCommandManager.remove_extra_mace_on)) {
                    return remove_extra_mace_on(commandSender, command, s, strings);
                }
            }
        }
        return true;
    }

    private boolean drop_heavy_core(CommandSender commandSender, Command command, String s, String[] strings) {
        // Enable or disable
        if (strings.length > 1) {
            if (strings[1].equalsIgnoreCase(CwRCommandManager.enable)) {
                MaceHolder.cansel_heavy_core_drop_event(false);
            }

            if (strings[1].equalsIgnoreCase(CwRCommandManager.disable)) {
                MaceHolder.cansel_heavy_core_drop_event(true);
            }
        }
        return true;
    }

    private boolean change_mace_holder(CommandSender commandSender, Command command, String s, String[] strings) {
        // to given name.
        if (strings.length > 1) {
            try {
                Player player = Bukkit.getPlayer(strings[1]);
                if (player == null) return true;
                MaceHolder.setOfflineMaceHolderAlsoForConfig(player);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private boolean reset_mace_holder(CommandSender commandSender, Command command, String s, String[] strings) {
        // reset mace holder to null.
        MaceHolder.setOfflineMaceHolderAlsoForConfig(null);
        commandSender.sendMessage(TextStrings.colorize("&6Reset mace holder to null."));
        return true;
    }

    private boolean remove_extra_mace_on(CommandSender commandSender, Command command, String s, String[] strings) {
        // ocom remove_extra_mace_on [join|click] [true|false]
        if (strings.length > 1) {
            if (strings[1].equalsIgnoreCase("join")) {
                if (strings.length > 2) {
                    if (strings[2].equalsIgnoreCase("true")) {
                        plugin.getConfig().set(ConfigPaths.REMOVE_EXTRA_MACE_ON_JOIN, true);
                        commandSender.sendMessage(TextStrings.colorize("&6Remove extra mace on join true."));
                    }
                    if (strings[2].equalsIgnoreCase("false")) {
                        plugin.getConfig().set(ConfigPaths.REMOVE_EXTRA_MACE_ON_JOIN, false);
                        commandSender.sendMessage(TextStrings.colorize("&6Remove extra mace on join false."));
                    }
                }
            }
            if (strings[1].equalsIgnoreCase("click")) {
                if (strings.length > 2) {
                    if (strings[2].equalsIgnoreCase("true")) {
                        plugin.getConfig().set(ConfigPaths.REMOVE_EXTRA_MACE_ON_CLICK, true);
                        commandSender.sendMessage(TextStrings.colorize("&6Remove extra mace on clicking inventory true."));
                    }
                    if (strings[2].equalsIgnoreCase("false")) {
                        plugin.getConfig().set(ConfigPaths.REMOVE_EXTRA_MACE_ON_CLICK, false);
                        commandSender.sendMessage(TextStrings.colorize("&6Remove extra mace on clicking inventory false."));
                    }
                }
            }
            if (strings[1].equalsIgnoreCase("using")) {
                if (strings.length > 2) {
                    if (strings[2].equalsIgnoreCase("true")) {
                        plugin.getConfig().set(ConfigPaths.REMOVE_EXTRA_MACE_ON_USE, true);
                        commandSender.sendMessage(TextStrings.colorize("&6Remove extra mace on using true."));
                    }
                    if (strings[2].equalsIgnoreCase("false")) {
                        plugin.getConfig().set(ConfigPaths.REMOVE_EXTRA_MACE_ON_USE, false);
                        commandSender.sendMessage(TextStrings.colorize("&6Remove extra mace on using false."));
                    }
                }
            }
        }
        return true;
    }
}
