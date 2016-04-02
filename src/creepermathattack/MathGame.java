package creepermathattack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import tasks.CountDownTask;
import tasks.MathGameTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Main source for the game that handles the problems, difficulty, and score tracking.
 */
public class MathGame {

    private static ArrayList<String> currAns;
    private static int score, difficulty;
    private static HashMap<String, String> probs;
    private Player player;
    private int time;
    private Location gameStartLocation = Bukkit.getWorld("world").getSpawnLocation();

    public MathGame(Player player, int difficulty, int time) {
        this.player = player;
        this.difficulty = difficulty;
        this.time = time;
        score = 0;
        probs = new HashMap<>();
        currAns = new ArrayList<>();
    }

    //gets a random problem that doesn't exist from given problem set and adds it to current problems
    public static String getProb() {
        Object[] ss = probs.keySet().toArray();
        Object[] ps = probs.values().toArray();
        int r = new Random().nextInt(ps.length);

        Object p = ps[r];
        while (currAns.contains(ss[r].toString())) {
            r = new Random().nextInt(ps.length);
            p = ps[r];
        }

        currAns.add(ss[r].toString());
        return p.toString();
    }

    //tries to remove problem for give answer if exists and returns string of removed problem
    public static String tryRemoveProb(String ans) {
        if (!currAns.contains(ans))
            return null;

        currAns.remove(ans);
        return probs.get(ans);
    }

    //adds points based on difficulty (easy=1, normal=2, hard=3)
    public static void addPoint() {
        score += (difficulty + 1);
    }

    public static int getScore() {
        return score;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public void start() {
        //set player game defaults
        player.teleport(gameStartLocation);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();

        //send game information
        player.sendMessage(ChatColor.GRAY + "********************************************************************************");
        player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Creeper Math Attack");
        player.sendMessage(ChatColor.YELLOW + "Type the answer to the math problem above a Creeper's head to defeat it.");
        player.sendMessage(ChatColor.GRAY + "********************************************************************************");

        //sets problems based on difficulty
        switch (difficulty) {
            case 0:
                fillEasy();
                break;
            case 1:
                fillNormal();
                break;
            default:
                fillHard();
                break;
        }

        //start countdown after 5 seconds, counts down for 5 seconds
        new CountDownTask(Main.getInstance(), player, 5).runTaskTimer(Main.getInstance(), 100, 20);

        //start game for time seconds 3 seconds after countdown stops
        new MathGameTask(Main.getInstance(), player, difficulty, time).runTaskTimer(Main.getInstance(), 260, 20);
    }

    //easy math problems (difficulty=0)
    private void fillEasy() {
        probs.put("1", "1 + 0 = ?");
        probs.put("2", "1 + 1 = ?");
        probs.put("3", "2 + 1 = ?");
        probs.put("4", "2 + 2 = ?");
        probs.put("5", "0 + 5 = ?");
        probs.put("6", "3 + 3 = ?");
        probs.put("7", "4 + 3 = ?");
        probs.put("8", "4 + 4 = ?");
        probs.put("9", "3 + 6 = ?");
        probs.put("10", "5 + 5 = ?");
        probs.put("11", "3 + 8 = ?");
        probs.put("12", "5 + 7 = ?");
        probs.put("13", "12 + 1 = ?");
        probs.put("14", "4 + 10 = ?");
        probs.put("15", "10 + 5 = ?");
        probs.put("16", "10 + 6 = ?");
        probs.put("17", "7 + 10 = ?");
        probs.put("18", "9 + 9 = ?");
        probs.put("19", "14 + 5 = ?");
        probs.put("20", "10 + 10 = ?");
    }

    //normal math problems (difficulty=1)
    private void fillNormal() {
        probs.put("1", "1 * 1 = ?");
        probs.put("3", "3 * 1 = ?");
        probs.put("5", "5 * 1 = ?");
        probs.put("9", "3 * 3 = ?");
        probs.put("10", "5 * 2 = ?");
        probs.put("12", "4 * 3 = ?");
        probs.put("15", "5 * 3 = ?");
        probs.put("18", "6 * 3 = ?");
        probs.put("20", "10 * 2 = ?");
        probs.put("22", "11 * 2 = ?");
        probs.put("25", "5 * 5 = ?");
        probs.put("30", "5 * 6 = ?");
        probs.put("50", "10 * 5 = ?");
        probs.put("75", "3 * 25 = ?");
        probs.put("100", "10 * 10 = ?");
        probs.put("90", "30 * 3 = ?");
        probs.put("200", "10 * 20 = ?");
        probs.put("350", "7 * 50 = ?");
        probs.put("500", "10 * 50 = ?");
        probs.put("750", "250 * 3 = ?");
    }

    //hard math problems (difficulty=2)
    private void fillHard() {
        probs.put("5", "25 / 5 = ?");
        probs.put("7", "21 / 3 = ?");
        probs.put("10", "2 * 5 = ?");
        probs.put("15", "45 / 3 = ?");
        probs.put("25", "100 / 4 = ?");
        probs.put("30", "6 * 5 = ?");
        probs.put("50", "100 / 2 = ?");
        probs.put("75", "150 / 2 = ?");
        probs.put("100", "10 * 10 = ?");
        probs.put("125", "100 + 10 + 15 = ?");
        probs.put("150", "50 * 3 = ?");
        probs.put("175", "(10 * 10) + 75 = ?");
        probs.put("200", "50 * 4 = ?");
        probs.put("210", "150 + 60 = ?");
        probs.put("250", "200 + 25 + 25 = ?");
        probs.put("275", "200 + 50 + 25 = ?");
        probs.put("300", "6 * 50 = ?");
        probs.put("450", "900 / 2 = ?");
        probs.put("500", "250 * 2 = ?");
        probs.put("800", "40 * 20 = ?");
    }

}
