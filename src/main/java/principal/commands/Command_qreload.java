package principal.commands;

import com.sun.tools.javac.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import principal.QuestionsPlugin;
import principal.config.manager.MainCustomConfigManager;
import principal.config.manager.QuestionManager;
import principal.entities.Question;
import principal.service.ShowQuestion;
import principal.threads.ExecuteQuestion;
import principal.utils.MessageUtil;

public class Command_qreload implements CommandExecutor {


    public Command_qreload() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0){
            sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&cTo use this command you must pass <questions> or <config> as a parameter"));
            return true;
        }

        if (args.length == 1){
            if (args[0].equalsIgnoreCase("questions")){
                ShowQuestion.questionManager.loadConfig();
                ExecuteQuestion.showQuestion.getQuestionsOfQuestionManager();
                sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&aQuestions reloaded."));
                return true;
            }else if (args[0].equalsIgnoreCase("config")){
                QuestionsPlugin.mainCustomConfigManager.loadConfig();
                ExecuteQuestion.showQuestion.loadConfigMainConfig();
                sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&aConfig reloaded."));
                return true;
            }
        }
        sender.sendMessage(MessageUtil.MessageColor(QuestionsPlugin.prefix+"&cTo use this command you must pass <questions> or <config> as a parameter"));
        return true;
    }
}
