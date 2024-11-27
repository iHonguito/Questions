package principal.threads;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import principal.QuestionsPlugin;
import principal.config.manager.PlayerDataManager;
import principal.entities.PlayerData;
import principal.utils.MessageUtil;

import java.util.*;
import java.util.stream.Collectors;

public class ExecuteSave {

    private BukkitTask saveTask;
    QuestionsPlugin plugin;

    PlayerDataManager playerDataManager;

    public ExecuteSave(QuestionsPlugin plugin, PlayerDataManager playerDataManager) {
        this.plugin = plugin;
        this.playerDataManager = playerDataManager;
    }

    public void run(){
        saveTask = new BukkitRunnable(){
            @Override
            public void run() {
                QuestionsPlugin.playersConfigManager.saveConfigs();
                takeFirstTenPlayers();
                Bukkit
                        .getConsoleSender()
                        .sendMessage(MessageUtil.MessageColor(
                                MessageUtil.MessageHexColor(QuestionsPlugin.prefix+"&aSaved data.")));
            }
        }.runTaskTimer(plugin, 0, QuestionsPlugin.mainCustomConfigManager.getTime_to_save_data() * 20L);
    }

    public void disableSaveData(){
        saveTask.cancel();
    }

    private PlayerData[] firstTenPlayers = new PlayerData[10];
    private Map<UUID, PlayerData> players = new HashMap<>();
    public void takeFirstTenPlayers(){
        Map<UUID, PlayerData> mapPlayers = playerDataManager.getPlayers();
        List<PlayerData> listPlayers = new ArrayList<>(mapPlayers.values());
        listPlayers = listPlayers.stream()
                .sorted(Comparator.comparingInt(PlayerData::getWon).reversed())
                .toList();
        firstTenPlayers = new PlayerData[10];
        for (int i = 0; i < listPlayers.size(); i++) {
            if (firstTenPlayers[firstTenPlayers.length - 1] == null){
                firstTenPlayers[i] = new PlayerData(listPlayers.get(i));
            }
            PlayerData player = new PlayerData(listPlayers.get(i));
            player.setPlace(i + 1);
            this.players.put(listPlayers.get(i).getUuid(), player);
        }
    }

    public PlayerData[] getFirstTenPlayers() {
        return firstTenPlayers;
    }

    public Map<UUID, PlayerData> getPlayers() {
        return players;
    }
}
