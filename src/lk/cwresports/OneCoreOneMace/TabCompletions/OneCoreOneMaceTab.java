package lk.cwresports.OneCoreOneMace.TabCompletions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

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
                    String[] str = {"true", "false"};
                    return List.of(str);
                }

                String[] str = {"join", "click","using"};
                return List.of(str);
            }

            if (strings[0].equalsIgnoreCase("change_mace_holder")) {
                return null;
            }

            if (strings[0].equalsIgnoreCase("drop_heavy_core")) {
                String[] str = {"true", "false"};
                return List.of(str);
            }

            String[] str = {"drop_heavy_core", "change_mace_holder", "reset_mace_holder", "remove_extra_mace_on"};
            return List.of(str);
        }

        return List.of();
    }
}
