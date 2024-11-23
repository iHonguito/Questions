package principal.entities;

import java.util.List;

public class Question {
    private List<String> question;
    private String answer;
    private String type;

    public Question() {
    }

    public Question(List<String> question, String answer, String type) {
        this.question = question;
        this.answer = answer;
        this.type = type;
    }


    public String getAnswer() {
        return answer;
    }


    public String getType() {
        return type;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getQuestion() {
        return question;
    }

    public void setQuestion(List<String> question) {
        this.question = question;
    }
}
