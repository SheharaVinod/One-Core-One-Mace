package lk.cwresports.OneCoreOneMace.TabCompletions;

import lk.cwresports.OneCoreOneMace.Utils.CwRCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class OneCoreOneMaceTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            if (strings[0].equalsIgnoreCase("reset_mace_holder")) {
                return List.of();
            }

            if (strings[0].equalsIgnoreCase("remove_extra_mace_on")) {
                if (strings.length == 2) {
                    String[] str = {"join", "click", "using"};
                    return List.of(str);
                }
                if (strings.length == 3) {
                    String[] str = {"true", "false"};
                    return List.of(str);
                }
                if (strings.length > 2) {
                    return List.of();
                }
            }

            if (strings[0].equalsIgnoreCase("change_mace_holder")) {
                if (strings.length > 2) {
                    return List.of();
                }

                List<String> names = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach((player -> {
                    names.add(player.getName());
                }));
                return names;
            }

            if (strings[0].equalsIgnoreCase("drop_heavy_core")) {
                if (strings.length > 1) {
                    return List.of();
                }

                String[] str = {CwRCommandManager.enable, CwRCommandManager.disable};
                return List.of(str);
            }

            String[] str = {"drop_heavy_core", "change_mace_holder", "reset_mace_holder", "remove_extra_mace_on"};
            return List.of(str);
        }

        return List.of();
    }
}
