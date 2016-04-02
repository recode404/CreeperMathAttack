package creepermathattack;

import listeners.CommandPlay;
import listeners.EventAsyncPlayerChat;
import listeners.EventPlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Taylor on 3/31/16.
 */
public class Main extends JavaPlugin {

    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerListeners() {
        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new EventPlayerJoin(), this);
        pm.registerEvents(new EventAsyncPlayerChat(), this);
    }

    private void registerCommands() {
        this.getCommand("play").setExecutor(new CommandPlay());
    }
}
