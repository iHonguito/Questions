package principal.utils;

import org.bukkit.ChatColor;

public class MessageUtil {

    public static String MessageColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
