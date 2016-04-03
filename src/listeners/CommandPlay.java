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
    private int time = 60, difficulty = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        player = (Player) sender;

        //player already in game
        if (player.getLevel() == 1) {
            player.sendMessage(ChatColor.RED + "You are already playing!");
            return false;
        }

        //difficulty error
        if (args.length > 0) {
            difficulty = Integer.parseInt(args[0]);
            if (difficulty != 0 && difficulty != 1 && difficulty != 2) {
                player.sendMessage(ChatColor.RED + "Invalid difficulty. Difficulty must be between 0 and 2.");
                return false;
            }
        }

        //time error
        if (args.length > 1) {
            time = Integer.parseInt(args[1]);
            if (time < 30 || time > 180) {
                player.sendMessage(ChatColor.RED + "Invalid time. Time must be between 30 and 180.");
                return false;
            }
        }

        //start game
        player.setLevel(1);
        new MathGame(player, difficulty, time).start();
        return true;
    }
}
