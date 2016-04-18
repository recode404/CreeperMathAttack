package listeners;

import creepermathattack.MathGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handles play command by setting difficulty(0-2) and time(30-180)
 */
public class CommandPlay implements CommandExecutor {

    private Player player;
    private int difficulty;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        //set defaults
        player = (Player) sender;
        difficulty = 0;


        //player already in game
        if (player.getLevel() == 1) {
            player.sendMessage(ChatColor.RED + "You are already playing!");
            return false;
        }

        //difficulty check
        if (args.length > 0) {
            difficulty = Integer.parseInt(args[0]);
            if (difficulty != 0 && difficulty != 1 && difficulty != 2) {
                player.sendMessage(ChatColor.RED + "Invalid difficulty. Difficulty must be between 0 and 2.");
                return false;
            }
        }

        //start game and set player to playing
        player.setLevel(1);
        new MathGame(player, difficulty).start();
        return true;
    }
}
