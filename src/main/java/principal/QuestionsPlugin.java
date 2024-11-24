package principal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import principal.config.manager.PlayerDataManager;
import principal.config.manager.PlayersConfigManager;
import principal.entities.PlayerData;
import principal.listeners.PlayerListener;
import principal.threads.ExecuteQuestion;
import principal.config.manager.MainCustomConfigManager;
import principal.utils.MessageUtil;

public class QuestionsPlugin extends JavaPlugin {
    public static String prefix = MessageUtil.MessageColor("&f[&8&lQuestions&f] ");
    public static boolean InQuestion = false;
    MainCustomConfigManager mainCustomConfigManager;
    ExecuteQuestion executeQuestion;
    public static PlayerDataManager playerDataManager;
    PlayersConfigManager playersConfigManager;
    public void onEnable(){
        mainCustomConfigManager = new MainCustomConfigManager(this);
        executeQuestion = new ExecuteQuestion(mainCustomConfigManager, this);
        playerDataManager = new PlayerDataManager();
        playersConfigManager = new PlayersConfigManager(this, "player_data");
        registerHandlers();
        executeQuestion.run();
        Bukkit.getConsoleSender().sendMessage(prefix+ MessageUtil.MessageColor("&a&lEnable"));
    }

    public void onDisable(){
        playersConfigManager.saveConfigs();
        Bukkit.getConsoleSender().sendMessage(prefix+ MessageUtil.MessageColor("&c&lDisable"));
    }

    public MainCustomConfigManager getMainCustomConfigManager(){
        return mainCustomConfigManager;
    }
    public static void changeStateInQuestion(){
        InQuestion = !InQuestion;
    }

    public void registerHandlers(){
        getServer().getPluginManager().registerEvents(new PlayerListener(mainCustomConfigManager, this, playerDataManager), this);
    }
}
