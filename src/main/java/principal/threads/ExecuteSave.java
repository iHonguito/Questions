package principal.threads;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import principal.QuestionsPlugin;
import principal.utils.MessageUtil;

public class ExecuteSave {

    private BukkitTask saveTask;
    QuestionsPlugin plugin;

    public ExecuteSave(QuestionsPlugin plugin) {
        this.plugin = plugin;
    }

    public void run(){
        saveTask = new BukkitRunnable(){
            @Override
            public void run() {
                QuestionsPlugin.playersConfigManager.saveConfigs();
                Bukkit
                        .getConsoleSender()
                        .sendMessage(MessageUtil.MessageColor(
                                MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&aSaved data.")));
            }
        }.runTaskTimer(plugin, 0, QuestionsPlugin.mainCustomConfigManager.getTime_to_save_data() * 20L);
    }

    public void disableSaveData(){
        saveTask.cancel();
    }

}
