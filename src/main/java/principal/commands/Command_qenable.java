package principal.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import principal.QuestionsPlugin;
import principal.threads.ExecuteQuestion;
import principal.utils.MessageUtil;

public class Command_qenable implements CommandExecutor {

    private final ExecuteQuestion executeQuestion;

    public Command_qenable(ExecuteQuestion executeQuestion) {
        this.executeQuestion = executeQuestion;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("questions.admin")){
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+QuestionsPlugin.mainCustomConfigManager.getMessage_when_the_user_does_not_have_permissions())));
            return true;
        }
        if (!QuestionsPlugin.InExecution){
            QuestionsPlugin.InExecution = true;
            executeQuestion.run();
            Bukkit.dispatchCommand(sender, "qsave enable");
            sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&aRunning questions."));
        }else{
            sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&cThe plugin is already running."));
        }
        return true;
    }
}
