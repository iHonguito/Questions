package principal.config;

import org.bukkit.configuration.file.FileConfiguration;
import principal.QuestionsPlugin;
import principal.entities.Reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainCustomConfigManager {

    private CustomConfig customConfig;

    // Config variables
    private String tittle;
    private String sub_tittle;
    private String motivational_phrase;
    private String footer;
    private String when_the_users_do_not_answer;
    private String when_a_user_answers;
    private List<Reward> rewards;
    private int time_range_to_execute;
    private long waiting_time;
    private int number_of_users_to_run;

    public MainCustomConfigManager() {
    }

    public MainCustomConfigManager(QuestionsPlugin plugin) {
        customConfig = new CustomConfig("config.yml", null, plugin);
        this.loadConfig();
    }

    private void loadConfig(){
        customConfig.registerConfig();
        this.getConfig();
        customConfig.reloadConfig();
    }

    public void getConfig(){
        FileConfiguration config = customConfig.getConfig();
        this.tittle = config.getString("config.game.tittle");
        this.sub_tittle = config.getString("config.game.sub_tittle");
        this.motivational_phrase = config.getString("config.game.motivational_phrase");
        this.footer = config.getString("config.game.footer");
        this.when_the_users_do_not_answer = config.getString("config.finished_game.when_the_users_do_not_answer");
        this.when_a_user_answers = config.getString("config.finished_game.when_a_user_answers");
        this.time_range_to_execute = config.getInt("config.time_range_to_execute");
        this.waiting_time = config.getLong("config.waiting_time");
        this.number_of_users_to_run = config.getInt("config.number_of_users_to_run");
        this.rewards = new ArrayList<>();
        List<Map<?, ?>> rewards = config.getMapList("config.rewards");
        for (Map<?, ?> rewardData : rewards){
            String reward = (String) rewardData.get("reward");
            double provability = (double) rewardData.get("provability");
            this.rewards.add(new Reward(reward, provability));
        }

    }

    public void saveConfig(){
        customConfig.saveConfig();
    }

    public String getTittle() {
        return tittle;
    }

    public String getSub_tittle() {
        return sub_tittle;
    }

    public String getMotivational_phrase() {
        return motivational_phrase;
    }

    public String getFooter() {
        return footer;
    }

    public String getWhen_the_users_do_not_answer() {
        return when_the_users_do_not_answer;
    }

    public String getWhen_a_user_answers() {
        return when_a_user_answers;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public int getTime_range_to_execute() {
        return time_range_to_execute;
    }

    public long getWaiting_time() {
        return waiting_time;
    }

    public int getNumber_of_users_to_run() {
        return number_of_users_to_run;
    }
}
