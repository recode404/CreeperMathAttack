package listeners;

import creepermathattack.MathGame;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Listeners for player's answer when they are playing
 */
public class EventAsyncPlayerChat implements Listener {

    private Player player;

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        String text = event.getMessage();
        player = event.getPlayer();
        event.setCancelled(true);

        //player is not playing
        if (player.getLevel() == 0)
            return;

        //invalid answer length
        if (text.length() > 3) {
            player.sendMessage(ChatColor.RED + "Not a correct answer!");
            return;
        }

        String name = MathGame.tryRemoveProb(text);

        //player enters correct answer, so add correct point and remove creeper
        if (name != null) {
            MathGame.addPoint();
            player.getWorld().getEntities().stream().filter
                    (e -> e.getPassenger() != null && e.getPassenger().getCustomName().equals(name)).forEach(e -> {
                e.getPassenger().remove();
                e.remove();
            });

            player.sendMessage(ChatColor.GREEN + "+" + (MathGame.getDifficulty() + 1) + " point(s)");
            player.sendMessage(ChatColor.GREEN + "Total: " + MathGame.getScore());
        } else
            player.sendMessage(ChatColor.RED + "Not a correct answer!");

    }
}
