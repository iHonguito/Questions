package principal.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import principal.QuestionsPlugin;
import principal.config.MainCustomConfigManager;
import principal.service.ShowQuestion;
import principal.threads.ExecuteQuestion;

public class PlayerListener implements Listener {

    private final ShowQuestion showQuestion;

    public PlayerListener(MainCustomConfigManager mainCustomConfigManager, QuestionsPlugin plugin) {
        showQuestion = new ShowQuestion(mainCustomConfigManager, plugin);
    }

    @EventHandler
    public void whenAnsweringTheQuestion(AsyncPlayerChatEvent event){
        if (QuestionsPlugin.InQuestion){
            if (ExecuteQuestion.lastQuestion.getAnswer().equals(event.getMessage())){
                long elapsedTime = System.currentTimeMillis() - ExecuteQuestion.startTime;
                QuestionsPlugin.changeStateInQuestion();
                Player playerWhoAnswering = event.getPlayer();
                showQuestion.messageWhenAnUserAnswersTheQuestion(playerWhoAnswering, ExecuteQuestion.lastQuestion);
            }
        }
    }
}
