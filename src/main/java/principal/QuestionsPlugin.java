package principal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import principal.commands.Command_qdisable;
import principal.commands.Command_qenable;
import principal.commands.Command_qreload;
import principal.commands.Command_qsave;
import principal.config.manager.PlayerDataManager;
import principal.config.manager.PlayersConfigManager;
import principal.listeners.PlayerListener;
import principal.threads.ExecuteQuestion;
import principal.config.manager.MainCustomConfigManager;
import principal.threads.ExecuteSave;
import principal.utils.MessageUtil;

public class QuestionsPlugin extends JavaPlugin {
    public static String prefix = MessageUtil.MessageColor(MessageUtil.MessageHexColor("&#8B00FF&l&oQ&#A100FF&l&ou&#B600FF&l&oe&#CC00FF&l&os&#E100FF&l&ot&#E100FF&l&oi&#E100FF&l&oo&#E100FF&l&on&#E100FF&l&os &8&l» "));
    public static boolean InQuestion = false;
    public static boolean InExecution = true;
    public static boolean InSaveData = true;
    public static MainCustomConfigManager mainCustomConfigManager;
    public static PlayersConfigManager playersConfigManager;
    public static PlayerDataManager playerDataManager;
    ExecuteQuestion executeQuestion;
    ExecuteSave executeSave;
    public void onEnable(){
        mainCustomConfigManager = new MainCustomConfigManager(this);
        executeQuestion = new ExecuteQuestion(mainCustomConfigManager, this);
        executeSave = new ExecuteSave(this);
        playerDataManager = new PlayerDataManager();
        playersConfigManager = new PlayersConfigManager(this, "player_data");
        registerHandlers();
        registerCommands();
        executeQuestion.run();
        executeSave.run();
        Bukkit.getConsoleSender().sendMessage(prefix+ MessageUtil.MessageColor("&a&lEnable"));
    }

    public void onDisable(){
        playersConfigManager.saveConfigs();
        Bukkit.getConsoleSender().sendMessage(prefix+ MessageUtil.MessageColor("&c&lDisable"));
    }

    public static void changeStateInQuestion(){
        InQuestion = !InQuestion;
    }

    public void registerHandlers(){
        getServer().getPluginManager().registerEvents(new PlayerListener(mainCustomConfigManager, this, playerDataManager), this);
    }

    public void registerCommands(){
        getCommand("qenable").setExecutor(new Command_qenable(executeQuestion));
        getCommand("qdisable").setExecutor(new Command_qdisable(executeQuestion));
        getCommand("qreload").setExecutor(new Command_qreload());
        getCommand("qsave").setExecutor(new Command_qsave(playersConfigManager, executeSave));
    }
}
