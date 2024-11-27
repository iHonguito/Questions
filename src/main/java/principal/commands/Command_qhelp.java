package principal.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import principal.QuestionsPlugin;
import principal.utils.MessageUtil;

public class Command_qhelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("questions.admin")){
            sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(QuestionsPlugin.prefix+QuestionsPlugin.mainCustomConfigManager.getMessage_when_the_user_does_not_have_permissions())));
            return true;
        }
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor("      &#E100FF☄ &#8B00FF&l&oQ&#9200FF&l&ou&#9800FF&l&oe&#9F00FF&l&os&#A500FF&l&ot&#AC00FF&l&oi&#B300FF&l&oo&#B900FF&l&on&#C000FF&l&os &#CD00FF&l&oH&#D400FF&l&oe&#DA00FF&l&ol&#E100FF&l&op &#E100FF☄")));
        sender.sendMessage("");
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qhelp&f: Shows a guide to using the commands in this plugin.")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qtop&f: Shows the top with the first 10 players.")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qtop &5<player>&f: Shows player information.")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qenable&f: In case the plugin is disabled, it will make it work again.")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qdisable&f: In case the plugin is enabled, it will disable it.")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qreload &5<questions>&f: Access the question settings again.")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qreload &5<config>&f: Access the config settings again")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qsave&f: Save players data. (Manual)")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qsave &5<enable>&f: Enable saving players data.")));
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &d/qsave &5<disable>&f: Disable saving players data.")));
        sender.sendMessage("");
        sender.sendMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(" &7Developer: &8lsEmpty")));
        sender.sendMessage("");
        return true;
    }
}
