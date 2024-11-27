package principal.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import principal.QuestionsPlugin;
import principal.config.manager.MainCustomConfigManager;
import principal.config.manager.PlayerDataManager;
import principal.service.ShowQuestion;
import principal.threads.ExecuteQuestion;

import java.text.DecimalFormat;

public class PlayerListener implements Listener {

    private final PlayerDataManager playerDataManager;

    public PlayerListener(MainCustomConfigManager mainCustomConfigManager, QuestionsPlugin plugin, PlayerDataManager playerDataManager) {
        this.playerDataManager = playerDataManager;
    }

    @EventHandler
    public void enteringPlayer(PlayerJoinEvent event){
        playerDataManager.getPlayer(event.getPlayer(), true);
    }

    @EventHandler
    public void whenAnsweringTheQuestion(AsyncPlayerChatEvent event){
        if (QuestionsPlugin.InQuestion){
            if (ExecuteQuestion.lastQuestion.getAnswer().equals(event.getMessage())){
                long elapsedTime = System.currentTimeMillis() - ExecuteQuestion.startTime;
                DecimalFormat df = new DecimalFormat("#.##");
                double secondsWithOutFormat = (double) elapsedTime / 1000;
                String seconds = df.format(secondsWithOutFormat);
                QuestionsPlugin.changeStateInQuestion();
                Player playerWhoAnswering = event.getPlayer();
                ExecuteQuestion.showQuestion.messageWhenAnUserAnswersTheQuestion(playerWhoAnswering, ExecuteQuestion.lastQuestion, seconds);
            }
        }
    }
}
