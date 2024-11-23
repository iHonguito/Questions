package principal.service;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import principal.QuestionsPlugin;
import principal.config.MainCustomConfigManager;
import principal.config.QuestionManager;
import principal.entities.Question;
import principal.utils.MessageUtil;

import java.util.List;
import java.util.Random;

public class ShowQuestion {
    private String tittle;
    private String sub_tittle;
    private String motivational_phrase;
    private String footer;
    private String when_the_users_do_not_answer;
    private String when_a_user_answers;
    private List<?> rewards;

    private List<Question> questions;

    public ShowQuestion() {
    }

    public ShowQuestion(MainCustomConfigManager mainCustomConfigManager, QuestionsPlugin plugin) {
        QuestionManager questionManager = new QuestionManager(plugin);
        questions = questionManager.getQuestions();

        this.tittle = mainCustomConfigManager.getTittle();
        this.sub_tittle = mainCustomConfigManager.getSub_tittle();
        this.motivational_phrase = mainCustomConfigManager.getMotivational_phrase();
        this.footer = mainCustomConfigManager.getFooter();
        this.when_the_users_do_not_answer = mainCustomConfigManager.getWhen_the_users_do_not_answer();
        this.when_a_user_answers = mainCustomConfigManager.getWhen_a_user_answers();
        this.rewards = mainCustomConfigManager.getRewards();
    }

    public Question show(){
        Random random = new Random();
        int index_random = random.nextInt(questions.size());
        Question question = questions.get(index_random);
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(tittle)));
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(sub_tittle)));
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(motivational_phrase)));
        if (question.getType().equals("Sort")){
            String disordered_answer = shuffle(question.getAnswer());
            Bukkit.broadcastMessage(
                    MessageUtil.MessageColor(
                            MessageUtil.MessageHexColor(
                                    question.getQuestion()
                                    .replace("%question%", disordered_answer))));
        }else{
            Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(question.getQuestion())));
        }
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(footer)));
        return question;
    }

    public void messageWhenAnUserAnswersTheQuestion(Player player, Question lastQuestion){
        Bukkit.broadcastMessage(this.when_a_user_answers
                .replace("%player%", player.getName())
                .replace("%answer%", lastQuestion.getAnswer()));
    }

    public void messageWhenTheUsersDoNotAnswerTheQuestion(Question lastQuestion){
        Bukkit.broadcastMessage(when_the_users_do_not_answer
                .replace("%answer%", lastQuestion.getAnswer()));
    }

    private String shuffle(String input) {
        char[] letters = input.toCharArray();
        Random random = new Random();
        for (int i = letters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }
        return new String(letters);
    }
}
