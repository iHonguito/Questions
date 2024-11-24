package principal.config.manager;

import org.bukkit.configuration.file.FileConfiguration;
import principal.QuestionsPlugin;
import principal.config.CustomConfig;
import principal.config.DataFolderConfigManager;
import principal.entities.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayersConfigManager extends DataFolderConfigManager {

    public PlayersConfigManager(QuestionsPlugin plugin, String folderName) {
        super(plugin, folderName);
    }

    @Override
    public void loadConfigs() {
        Map<UUID, PlayerData> playersData = new HashMap<>();
        for (CustomConfig customConfig : configs){
            FileConfiguration config = customConfig.getConfig();
            UUID uuid = UUID.fromString(customConfig.getPath().replace(".yml", ""));
            String name = config.getString("name");
            int won = config.getInt("won");
            playersData.put(uuid, new PlayerData(uuid, name, won));
        }
        QuestionsPlugin.playerDataManager.setPlayers(playersData);
    }

    @Override
    public void saveConfigs() {
        Map<UUID, PlayerData> playersData = QuestionsPlugin.playerDataManager.getPlayers();
        for (Map.Entry<UUID, PlayerData> entry : playersData.entrySet()){
            PlayerData playerData = entry.getValue();
            String path = playerData.getUuid().toString()+".yml";
            CustomConfig customConfig = super.getConfigFile(path);
            if (customConfig == null){
                customConfig = registerConfigFile(path);
            }
            FileConfiguration config = customConfig.getConfig();
            config.set("name", playerData.getName());
            config.set("won", playerData.getWon());
        }
        saveConfigFiles();
    }
}
