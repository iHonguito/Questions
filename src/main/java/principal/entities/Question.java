package principal.entities;

public class Question {
    private String question;
    private String answer;
    private String type;

    public Question() {
    }

    public Question(String question, String answer, String type) {
        this.question = question;
        this.answer = answer;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
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
}
