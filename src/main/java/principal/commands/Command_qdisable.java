package principal.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import principal.QuestionsPlugin;
import principal.entities.Question;
import principal.threads.ExecuteQuestion;
import principal.utils.MessageUtil;

public class Command_qdisable implements CommandExecutor {

    private final ExecuteQuestion executeQuestion;

    public Command_qdisable(ExecuteQuestion executeQuestion) {
        this.executeQuestion = executeQuestion;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (QuestionsPlugin.InExecution){
            QuestionsPlugin.InExecution = false;
            executeQuestion.DisablePlugin();
            Bukkit.broadcastMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&aStopped plugin."));
        }else{
            Bukkit.broadcastMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&cThe plugin is already stopped."));
        }
        return true;
    }
}
