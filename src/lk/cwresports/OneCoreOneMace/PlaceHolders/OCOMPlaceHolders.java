package lk.cwresports.OneCoreOneMace.PlaceHolders;

import lk.cwresports.OneCoreOneMace.Core.MaceHolder;
import lk.cwresports.OneCoreOneMace.Utils.CwRBetterConsoleLogger;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class OCOMPlaceHolders extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "cwr";
    }

    @Override
    public String getAuthor() {
        return "Mr_Unknown";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }


    @Override
    public String onRequest(OfflinePlayer player, String params) {
        //  #   %cwr_ocom_mace_holder%

        if (player != null && player.isOnline()) {
            // Player requested_player = player.getPlayer();
            if (params.equalsIgnoreCase("ocom_mace_holder")) {
                return MaceHolder.getOfflinePlayersName();
            }
        }
        return null;
    }

    public static void registerHook() {
        new OCOMPlaceHolders().register();
        CwRBetterConsoleLogger.log("PlaceHolders are registered.");
    }
}
