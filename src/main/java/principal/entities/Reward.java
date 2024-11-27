package principal.entities;

public class Reward {
    private String reward;
    private double probability;

    public Reward(String reward, double probability) {
        this.reward = reward;
        this.probability = probability;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getReward() {
        return reward;
    }

    public double getProbability() {
        return probability;
    }
}
