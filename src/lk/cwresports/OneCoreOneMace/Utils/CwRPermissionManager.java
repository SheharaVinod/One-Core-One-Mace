package lk.cwresports.OneCoreOneMace.Utils;

import org.bukkit.entity.Player;

public class CwRPermissionManager {
    private final static String HOLDER_BYPASS = "cwr-core.one-core-one-mace.bypass-holder";
    private final static String HAS_ADMIN_PERM = "cwr-core.one-core-one-mace.admin";

    public static boolean hasHolderBypass(Player player) {
        return player.hasPermission(CwRPermissionManager.HOLDER_BYPASS) || player.hasPermission(CwRPermissionManager.HAS_ADMIN_PERM);
    }

    public static boolean hasAdminPerm(Player player) {
        return player.hasPermission(CwRPermissionManager.HAS_ADMIN_PERM);
    }
}
