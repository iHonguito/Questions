package principal.service;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import principal.QuestionsPlugin;
import principal.config.manager.MainCustomConfigManager;
import principal.config.manager.PlayerDataManager;
import principal.config.manager.QuestionManager;
import principal.entities.Question;
import principal.entities.Reward;
import principal.utils.MessageUtil;

import java.util.List;
import java.util.Random;

public class ShowQuestion {

    public static QuestionManager questionManager;
    private MainCustomConfigManager mainCustomConfigManager;

    private String tittle;
    private String sub_tittle;
    private String motivational_phrase;
    private String footer;
    private String when_the_users_do_not_answer;
    private String when_a_user_answers;
    private List<Reward> rewards;

    private QuestionsPlugin plugin;

    private List<Question> questions;

    public ShowQuestion() {
    }

    public ShowQuestion(MainCustomConfigManager mainCustomConfigManager, QuestionsPlugin plugin) {
        questionManager = new QuestionManager(plugin);
        getQuestionsOfQuestionManager();
        this.plugin = plugin;
        this.mainCustomConfigManager = mainCustomConfigManager;
        loadConfigMainConfig();

    }

    public void getQuestionsOfQuestionManager(){
        questions = questionManager.getQuestions();
    }

    public void loadConfigMainConfig(){
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
        Bukkit.broadcastMessage("");
        if (motivational_phrase != null){
            Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(motivational_phrase)));
        }
        if (question.getType().equals("Sort")){
            String disordered_answer = shuffle(question.getAnswer());
            for (String questionData : question.getQuestion()){
                Bukkit.broadcastMessage(MessageUtil.MessageColor(
                        MessageUtil.MessageHexColor(
                                questionData.replace("%question%", disordered_answer))));
            }
        }else{
            for (String questionData : question.getQuestion()){
                Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(questionData)));
            }
        }
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(footer)));
        return question;
    }

    public void messageWhenAnUserAnswersTheQuestion(Player player, Question lastQuestion){
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(tittle)));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(MessageUtil.MessageColor(
                MessageUtil.MessageHexColor(
                this.when_a_user_answers
                .replace("%player%", player.getName())
                .replace("%answer%", lastQuestion.getAnswer()))));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(footer)));
        selectReward(player);
        QuestionsPlugin.playerDataManager.addWin(player);
    }

    public void messageWhenTheUsersDoNotAnswerTheQuestion(Question lastQuestion){
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(tittle)));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(MessageUtil.MessageColor(
                MessageUtil.MessageHexColor(when_the_users_do_not_answer.replace("%answer%", lastQuestion.getAnswer()))));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(MessageUtil.MessageColor(MessageUtil.MessageHexColor(footer)));
    }

    private void selectReward(Player player){
        String playerName = player.getName();
        double totalProvability = rewards.stream()
                .mapToDouble(Reward::getProvability)
                .sum();

        double randomValue = Math.random() * totalProvability;
        double cumulativeProvability = 0.0;
        for (Reward reward : rewards){
            cumulativeProvability += reward.getProvability();
            if (randomValue <= cumulativeProvability){
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.getReward().replace("%player%", playerName));
                    }
                }.runTask(plugin);
                return;
            }
        }
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
