package principal.config.manager;

import org.bukkit.configuration.file.FileConfiguration;
import principal.QuestionsPlugin;
import principal.config.CustomConfig;
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
    private int time_to_save_data;

    // Top
    private String top_tittle;
    private String top_content;
    private String top_footer;
    private String top_single_user;
    private String top_in_case_the_player_is_not_fount;

    private String message_when_the_user_does_not_have_permissions;

    public MainCustomConfigManager() {
    }

    public MainCustomConfigManager(QuestionsPlugin plugin) {
        customConfig = new CustomConfig("config.yml", null, plugin, false);
        this.rewards = new ArrayList<>();
        this.loadConfig();
    }

    public void loadConfig() {
        customConfig.registerConfig();
        this.getConfig();
        customConfig.reloadConfig();
    }

    public void getConfig() {
        this.rewards = new ArrayList<>();
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
        this.time_to_save_data = config.getInt("config.time_to_save_data");
        //Top
        this.top_tittle = config.getString("config.top.tittle");
        this.top_content = config.getString("config.top.content");
        this.top_footer = config.getString("config.top.footer");
        this.top_single_user = config.getString("config.top.single_user");
        this.top_in_case_the_player_is_not_fount = config.getString("config.top.in_case_the_player_is_not_fount");

        this.message_when_the_user_does_not_have_permissions = config.getString("config.message_when_the_user_does_not_have_permissions");
        List<Map<?, ?>> rewards = config.getMapList("config.rewards");
        for (Map<?, ?> rewardData : rewards) {
            String reward = (String) rewardData.get("reward");
            double probability = 0.0;
            Object probabilityObject = rewardData.get("probability");
            System.out.println("Object: " + probabilityObject);
            if (probabilityObject instanceof Double) {
                probability = (Double) rewardData.get("probability");
            } else if (probabilityObject instanceof Integer) {
                probability = ((Integer) rewardData.get("probability")).doubleValue();
            } else {
                throw new IllegalArgumentException("Error in reward configuration (Probability)");
            }
            this.rewards.add(new Reward(reward, probability));
        }

    }

    public void saveConfig() {
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

    public int getTime_to_save_data() {
        return time_to_save_data;
    }

    public String getTop_tittle() {
        return top_tittle;
    }

    public String getTop_content() {
        return top_content;
    }

    public String getTop_footer() {
        return top_footer;
    }

    public String getTop_single_user() {
        return top_single_user;
    }

    public String getTop_in_case_the_player_is_not_fount() {
        return top_in_case_the_player_is_not_fount;
    }

    public String getMessage_when_the_user_does_not_have_permissions() {
        return message_when_the_user_does_not_have_permissions;
    }
}
