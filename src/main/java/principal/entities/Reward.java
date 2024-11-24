package principal.entities;

public class Reward {
    private String reward;
    private double provability;

    public Reward(String reward, double provability) {
        this.reward = reward;
        this.provability = provability;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setProvability(double provability) {
        this.provability = provability;
    }

    public String getReward() {
        return reward;
    }

    public double getProvability() {
        return provability;
    }
}
