package principal.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import principal.QuestionsPlugin;
import principal.config.manager.PlayersConfigManager;
import principal.threads.ExecuteQuestion;
import principal.threads.ExecuteSave;
import principal.utils.MessageUtil;

public class Command_qsave implements CommandExecutor {

    PlayersConfigManager playersConfigManager;
    ExecuteSave executeSave;

    public Command_qsave(PlayersConfigManager playersConfigManager, ExecuteSave executeSave) {
        this.playersConfigManager = playersConfigManager;
        this.executeSave = executeSave;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("questions.admin")){
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+QuestionsPlugin.mainCustomConfigManager.getMessage_when_the_user_does_not_have_permissions())));
            return true;
        }

        if (args.length == 0){
            playersConfigManager.saveConfigs();
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&aSaved data")));
            return true;
        }
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("enable")){
                if (!QuestionsPlugin.InSaveData){
                    executeSave.run();
                    QuestionsPlugin.InSaveData = true;
                    sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&aStarted data saving.")));
                }else{
                    sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&cData saving is already enabled")));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("disable")){
                if (QuestionsPlugin.InSaveData){
                    executeSave.disableSaveData();
                    QuestionsPlugin.playersConfigManager.saveConfigs();
                    QuestionsPlugin.InSaveData = false;
                    sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&aStopped data saving.")));
                    Bukkit
                            .getConsoleSender()
                            .sendMessage(MessageUtil.MessageColor(
                                    MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&aSaved data.")));
                }else{
                    sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&cData saving is already disabled")));
                }
                return true;
            }
        }
        return true;
    }
}
