package listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Taylor on 3/31/16.
 */
public class EventPlayerJoin implements Listener {

    private Player player;
    private Location spawnLoc = Bukkit.getWorld("world").getSpawnLocation();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //setup login
        player = event.getPlayer();
        Bukkit.getScheduler().cancelAllTasks();
        event.setJoinMessage("");

        //set player login defaults
        player.setLevel(0);
        player.teleport(spawnLoc);
        player.setGameMode(GameMode.ADVENTURE);
        player.setInvulnerable(true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000000, 10));

        //remove all entities
        event.getPlayer().getWorld().getEntities().stream().filter
                (e -> e instanceof LivingEntity && !(e instanceof Player)).forEach(Entity::remove);

        //send login messages
        player.sendMessage(ChatColor.YELLOW + "This server is for STEM exCEL demo purposes.");
        player.sendMessage(ChatColor.YELLOW + "Type /play [difficulty (0-2)] [time (0-180)] to start the game.");
    }

}
