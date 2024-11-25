package principal.config.manager;

import org.bukkit.configuration.file.FileConfiguration;
import principal.QuestionsPlugin;
import principal.config.CustomConfig;
import principal.entities.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionManager {
    private CustomConfig customConfig;
    private List<Question> questions;
    public QuestionManager() {
    }

    public QuestionManager(QuestionsPlugin plugin) {
        customConfig = new CustomConfig("questions.yml", null, plugin, false);
        questions = new ArrayList<>();
        this.loadConfig();
    }

    public void loadConfig(){
        customConfig.registerConfig();
        this.getConfig();
        customConfig.reloadConfig();
    }

    public void saveConfig(){
        customConfig.saveConfig();
    }

    private void getConfig(){
        this.questions = new ArrayList<>();
        FileConfiguration config = customConfig.getConfig();
        List<Map<?, ?>> questions = config.getMapList("questions");
        for (Map<?, ?> questionData : questions){
            List<String> question = (List<String>) questionData.get("question");
            String answer = (String) questionData.get("answer");
            String type = (String) questionData.get("type");
            this.questions.add(new Question(question, answer, type));
        }
    }

    public List<Question> getQuestions(){
        return this.questions;
    }
}
