package principal.config.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import principal.config.CustomConfig;
import principal.entities.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    private Map<UUID, PlayerData> players;
    private final Map<String, UUID> playerNames;

    public PlayerDataManager() {
        this.players = new HashMap<>();
        this.playerNames = new HashMap<>();
    }

    public Map<UUID, PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(Map<UUID, PlayerData> players) {
        this.players = players;
        for (Map.Entry<UUID, PlayerData> entry : players.entrySet()){
            playerNames.put(entry.getValue().getName(), entry.getValue().getUuid());
        }
    }

    public void addPlayer(PlayerData playerData){
        players.put(playerData.getUuid(), playerData);
        playerNames.put(playerData.getName(), playerData.getUuid());
    }

    public PlayerData getPlayer(Player player, boolean create){
        PlayerData playerData = players.get(player.getUniqueId());
        if (playerData == null && create){
            playerData = new PlayerData(player.getUniqueId(), player.getName(), 0);
            addPlayer(playerData);
        }
        return playerData;
    }

    public PlayerData getPlayerByName(String playerName){
        UUID uuid = playerNames.get(playerName);
        return players.get(uuid);
    }

    public void addWin(Player player){
        PlayerData playerData = getPlayer(player, true);
        playerData.setWon(1+playerData.getWon());
    }

    public int getWonByName(String playerName){
        return getPlayerByName(playerName).getWon();
    }

    public int getWonByPlayer(Player player){
        return getPlayer(player, false).getWon();
    }

}
