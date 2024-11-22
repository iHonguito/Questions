package principal.config;

import org.bukkit.configuration.file.FileConfiguration;
import principal.QuestionsPlugin;
import principal.entities.Question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QuestionManager {
    private CustomConfig customConfig;
    private List<Question> questions;
    public QuestionManager() {
    }

    public QuestionManager(QuestionsPlugin plugin) {
        customConfig = new CustomConfig("questions.yml", null, plugin);
        questions = new ArrayList<>();
        this.loadConfig();
    }

    private void loadConfig(){
        customConfig.registerConfig();
        this.getConfig();
        customConfig.reloadConfig();
    }

    private void getConfig(){
        FileConfiguration config = customConfig.getConfig();
        List<Map<?, ?>> questions = config.getMapList("questions");
        for (Map<?, ?> questionData : questions){
            String question = (String) questionData.get("question");
            String answer = (String) questionData.get("answer");
            String type = (String) questionData.get("type");
            this.questions.add(new Question(question, answer, type));
        }
    }

    public List<Question> getQuestions(){
        return this.questions;
    }
}
