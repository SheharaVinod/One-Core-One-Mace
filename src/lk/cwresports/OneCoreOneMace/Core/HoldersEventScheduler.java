package lk.cwresports.OneCoreOneMace.Core;


import lk.cwresports.OneCoreOneMace.Utils.ConfigPaths;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class HoldersEventScheduler implements Listener {
    private static Plugin plugin;
    private static BukkitRunnable timer;
    private static Instant starting_point;
    private static Instant end_point;
    private static long total_play_time = 0;
    private static long how_many_millis_he_should_online = 0;

    private static long _login_time_cash = 0;

    public static void start() {
        long configStartTime = plugin.getConfig().getLong(ConfigPaths.VIRTUAL_DAY_START, ConfigPaths.VIRTUAL_DAY_START_DEFAULT);
        if (configStartTime != 0) {
            long configEndTime = plugin.getConfig().getLong(ConfigPaths.VIRTUAL_DAY_END, ConfigPaths.VIRTUAL_DAY_END_DEFAULT);
            if (configEndTime < Instant.now().toEpochMilli()) {
                starting_point = Instant.ofEpochMilli(configEndTime);
                plugin.getConfig().set(ConfigPaths.VIRTUAL_DAY_START, String.valueOf(starting_point.toEpochMilli()));
                return;
            }
            starting_point = Instant.ofEpochMilli(configStartTime);
            return;
        }
        starting_point = Instant.now();
        plugin.getConfig().set(ConfigPaths.VIRTUAL_DAY_START, String.valueOf(starting_point.toEpochMilli()));
    }

    public static void calculate_end_point() {
        long end_def = plugin.getConfig().getLong(ConfigPaths.HOW_LONG_A_HOLDER_CAN_BE_OFFLINE_IN_MINUTES, ConfigPaths.HOW_LONG_A_HOLDER_CAN_BE_OFFLINE_IN_MINUTES_DEFAULT);
        end_point = starting_point.plus(end_def, ChronoUnit.MINUTES);
        plugin.getConfig().set(ConfigPaths.VIRTUAL_DAY_END, String.valueOf(end_point.toEpochMilli()));
    }

    public static boolean is_virtual_day_is_over() {
        int differance = end_point.compareTo(Instant.now());
        return differance <= 0;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        _login_time_cash = System.currentTimeMillis();
    }

    @EventHandler
    public void onPlayerQuiteEvent(PlayerQuitEvent event) {
        total_play_time += System.currentTimeMillis() - _login_time_cash;
        _login_time_cash = 0;
    }

    private static void how_many_millis_he_should_online() {
        long configAssignTime = plugin.getConfig().getLong(ConfigPaths.HOW_LONG_A_HOLDER_SHOULD_ONLINE_IN_SERVER_IN_MINUTES, ConfigPaths.HOW_LONG_A_HOLDER_SHOULD_ONLINE_IN_SERVER_IN_MINUTES_DEFAULT);
        Instant timeAsInstant = Instant.ofEpochMilli(0).plus(configAssignTime, ChronoUnit.MINUTES);
        how_many_millis_he_should_online = timeAsInstant.toEpochMilli();
    }

    public static void register(Plugin plugin) {
        HoldersEventScheduler.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new HoldersEventScheduler(), plugin);

        how_many_millis_he_should_online();
        loadPlayerData();

        start();
        calculate_end_point();

        timer = new BukkitRunnable() {
            @Override
            public void run() {
                if (MaceHolder.getOfflinePlayer() == null) {
                    return;
                }

                if (is_virtual_day_is_over()) {
                    do_something_end_of_the_day();
                    start();
                    calculate_end_point();
                }
            }
        };

        timer.runTaskTimerAsynchronously(plugin, 0, 20);
    }

    public static void do_something_end_of_the_day() {
        // check player is inactive and punish him while login.
        if (how_many_millis_he_should_online > total_play_time) {
            MaceHolder.setOfflineMaceHolderAlsoForConfig(null);
        }
        total_play_time = 0;
        plugin.getConfig().set(ConfigPaths.VIRTUAL_PLAY_TIME, ConfigPaths.VIRTUAL_PLAY_TIME_DEFAULT);
    }

    public static void loadPlayerData() {
        total_play_time = plugin.getConfig().getLong(ConfigPaths.VIRTUAL_PLAY_TIME, ConfigPaths.VIRTUAL_PLAY_TIME_DEFAULT);
    }

    public static void saveData() {
        // save virtual play time when restarting.
        plugin.getConfig().set(ConfigPaths.VIRTUAL_PLAY_TIME, total_play_time);
    }

    public static void canselTimer() {
        if (timer != null) {
            if (timer.getTaskId() != -1) {
                timer.cancel();
            }
        }
    }
}
