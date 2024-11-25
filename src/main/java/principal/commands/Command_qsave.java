package principal.commands;

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
                    QuestionsPlugin.InSaveData = false;
                    sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&aStopped data saving.")));
                }else{
                    sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&cData saving is already disabled")));
                }
                return true;
            }
        }
        sender.sendMessage(MessageUtil.MessageColor(
                QuestionsPlugin.prefix+"&cTo use this command you must pass </qsave> alone, or as a second parameter <enable> or <disable>. For more information </qhelp>"));
        return true;
    }
}
