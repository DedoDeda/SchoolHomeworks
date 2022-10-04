package _10_22._04_10_22;

public class Wizard extends Creature {

    protected int power;

    public Wizard(String name, boolean female, int power) {
        super(name, female);
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "power=" + power +
                ", name='" + name + '\'' +
                ", female=" + female +
                '}';
    }
}
