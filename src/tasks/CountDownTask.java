package tasks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * A simple count down that counts down before the game starts.
 */
public class CountDownTask extends BukkitRunnable {


    private final JavaPlugin plugin;
    private final Player player;
    private int counter;

    public CountDownTask(JavaPlugin plugin, Player player, int counter) {
        this.plugin = plugin;
        this.counter = counter;
        this.player = player;
    }

    @Override
    public void run() {

        //if counter should still count
        if (counter > 0) {
            player.sendMessage(ChatColor.YELLOW + "Starting in " + counter + ".");
            counter--;
        }

        //counter ends
        else {
            player.sendMessage(ChatColor.YELLOW + "Starting...");
            this.cancel();
        }
    }

}
