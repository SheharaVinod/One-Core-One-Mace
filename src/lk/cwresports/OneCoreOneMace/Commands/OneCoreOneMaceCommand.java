package lk.cwresports.OneCoreOneMace.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OneCoreOneMaceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        return true;
    }

    private boolean drop_heavy_core(CommandSender commandSender, Command command, String s, String[] strings) {
        // Enable or disable


        return true;
    }

    private boolean change_mace_holder(CommandSender commandSender, Command command, String s, String[] strings) {
        // to given name.


        return true;
    }

    private boolean reset_mace_holder(CommandSender commandSender, Command command, String s, String[] strings) {
        // reset mace holder to null.


        return true;
    }

    private boolean remove_extra_mace_on(CommandSender commandSender, Command command, String s, String[] strings) {
        // ocom remove_extra_mace_on [join|click] [true|false]

        return true;
    }


    private boolean can_craft_using_auto_crafter(CommandSender commandSender, Command command, String s, String[] strings) {
        // ocom can_craft_using_auto_crafter [true|false]

        return true;
    }

}
