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
        if (!sender.hasPermission("questions.admin")){
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+QuestionsPlugin.mainCustomConfigManager.getMessage_when_the_user_does_not_have_permissions())));
            return true;
        }
        if (QuestionsPlugin.InExecution){
            QuestionsPlugin.InExecution = false;
            executeQuestion.DisablePlugin();
            Bukkit.dispatchCommand(sender, "qsave disable");
            sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&aStopped questions."));
        }else{
            sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&cThe plugin is already stopped."));
        }
        return true;
    }
}
