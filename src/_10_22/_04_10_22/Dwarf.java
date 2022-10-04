package _10_22._04_10_22;

public class Dwarf extends Creature {

    /** (cm) */
    protected int height;

    public Dwarf(String name, boolean female, int height) {
        super(name, female);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Dwarf{" +
                "height=" + height +
                ", name='" + name + '\'' +
                ", female=" + female +
                '}';
    }
}
