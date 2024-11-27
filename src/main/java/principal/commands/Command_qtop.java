package principal.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import principal.QuestionsPlugin;
import principal.entities.PlayerData;
import principal.threads.ExecuteSave;
import principal.utils.MessageUtil;



public class Command_qtop implements CommandExecutor {

    ExecuteSave executeSave;

    public Command_qtop(ExecuteSave executeSave) {
        this.executeSave = executeSave;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("questions.user")){
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+QuestionsPlugin.mainCustomConfigManager.getMessage_when_the_user_does_not_have_permissions())));
            return true;
        }

        if (args.length == 0){
            PlayerData[] firstTenPlayers = executeSave.getFirstTenPlayers();
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.mainCustomConfigManager.getTop_tittle())));
            sender.sendMessage("");
            for (int i = 0; i < firstTenPlayers.length; i++) {
                if (firstTenPlayers[i] != null){
                    int index = i + 1;
                    PlayerData player = firstTenPlayers[i];
                    sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.mainCustomConfigManager.getTop_content()
                            .replace("%player%", player.getName())
                            .replace("%place%", String.valueOf(index))
                            .replace("%won%", String.valueOf(player.getWon())))));
                }
            }
            sender.sendMessage("");
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.mainCustomConfigManager.getTop_footer())));
        }

        if (args.length == 1){
            PlayerData playerData = QuestionsPlugin.playerDataManager.getPlayerByName(args[0]);
            if (playerData == null){
                sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.mainCustomConfigManager.getTop_in_case_the_player_is_not_fount()
                        .replace("%prefix%", QuestionsPlugin.prefix))));
                return true;
            }
            PlayerData playerFromMap = executeSave.getPlayers().get(playerData.getUuid());
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(
                    QuestionsPlugin.mainCustomConfigManager.getTop_single_user()
                            .replace("%prefix%", QuestionsPlugin.prefix)
                            .replace("%player%", playerFromMap.getName())
                            .replace("%place%", String.valueOf(playerFromMap.getPlace()))
                            .replace("%won%", String.valueOf(playerFromMap.getWon()))
            )));
            return true;
        }
        return true;
    }
}
