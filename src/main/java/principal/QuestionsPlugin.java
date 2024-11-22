package principal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import principal.listeners.PlayerListener;
import principal.threads.ExecuteQuestion;
import principal.config.MainCustomConfigManager;
import principal.utils.MessageUtil;

public class QuestionsPlugin extends JavaPlugin {
    public static String prefix = MessageUtil.MessageColor("&f[&8&lQuestions&f] ");
    public static boolean InQuestion = false;
    MainCustomConfigManager mainCustomConfigManager;
    ExecuteQuestion executeQuestion;
    public void onEnable(){
        mainCustomConfigManager = new MainCustomConfigManager(this);
        executeQuestion = new ExecuteQuestion(mainCustomConfigManager, this);
        registerHandlers();
        executeQuestion.run();
        Bukkit.getConsoleSender().sendMessage(prefix+ MessageUtil.MessageColor("&a&lEnable"));
    }

    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(prefix+ MessageUtil.MessageColor("&c&lDisable"));
    }

    public MainCustomConfigManager getMainCustomConfigManager(){
        return mainCustomConfigManager;
    }
    public static void changeStateInQuestion(){
        InQuestion = !InQuestion;
    }

    public void registerHandlers(){
        getServer().getPluginManager().registerEvents(new PlayerListener(mainCustomConfigManager, this), this);
    }
}
