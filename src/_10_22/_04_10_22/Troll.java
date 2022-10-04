package _10_22._04_10_22;

public class Troll extends Creature {

    protected int wickedness;

    public Troll(String name, boolean female, int wickedness) {
        super(name, female);
        this.wickedness = wickedness;
    }

    public String compare(Wizard wizard) {
        return wizard.getPower() > wickedness
                ? wizard.toString()
                : this.toString();
    }

    public int getWickedness() {
        return wickedness;
    }

    public void setWickedness(int wickedness) {
        this.wickedness = wickedness;
    }

    @Override
    public String toString() {
        return "Troll{" +
                "wickedness=" + wickedness +
                ", name='" + name + '\'' +
                ", female=" + female +
                '}';
    }
}
