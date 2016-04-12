package tasks;

import creepermathattack.MathGame;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Counts down timer and spawns mobs at set interval(spawnTime)
 */
public class MathGameTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final Player player;
    private int time, spawnTime, difficulty, x, y, z;
    private Location loc;
    private String name;

    public MathGameTask(JavaPlugin plugin, Player player, int difficulty, int time) {
        this.plugin = plugin;
        this.player = player;
        this.difficulty = difficulty;
        this.time = 10;
        spawnTime = 5;
    }

    @Override
    public void run() {

        //once timer is up game ends
        if (time < 1) {
            player.sendMessage(ChatColor.RED + "Game Over!");
            player.sendMessage(ChatColor.GREEN + "Final Score: " + MathGame.getScore());

            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "kill @e[type=!Player]");
            player.setLevel(0);
            this.cancel();
        }

        //spawns mobs every spawnTime intervals
        else if (time % spawnTime == 0)
            spawnMobs();

        //30 second warning
        if (time == 30)
            player.sendMessage(ChatColor.RED + "30 seconds remaining!");

        //5 second warning
        else if (time == 5)
            player.sendMessage(ChatColor.RED + "5 seconds remaining!");

        //1 second pass
        time--;
    }

    private void spawnMobs() {
        loc = player.getLocation();
        name = MathGame.getProb();

        //sets random locations between 0-20 for X and Z
        x = new Random().nextInt(20);
        z = new Random().nextInt(20);
        y = loc.getBlockY();

        //spawn creeper riding invisible chicken with name as math problem
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),
                "summon Chicken " + x + " " + y + " " + z + " " +
                        " {ActiveEffects: [{Id:14,Amplifier:0,Duration:2147483647,Ambient:1,ShowParticles:0b}], " +
                        "NoAI:1b, Passengers:[{ id:Creeper,NoAI:1b,Glowing:1,Invulnerable:1, CustomName:" + name +
                        ", CustomNameVisible:1 }] }");
    }

}
