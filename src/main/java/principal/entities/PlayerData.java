package principal.entities;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String name;
    private int won;
    private int place;

    public PlayerData(UUID uuid, String name, int won) {
        this.uuid = uuid;
        this.name = name;
        this.won = won;
    }

    public PlayerData(PlayerData playerData){
        this.uuid = playerData.getUuid();
        this.name = playerData.getName();
        this.won = playerData.getWon();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getWon() {
        return won;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
