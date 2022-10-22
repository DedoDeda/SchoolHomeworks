package _22._10._04;

public abstract class Creature {

    protected String name;
    protected boolean female;

    public Creature(String name, boolean female) {
        this.name = name;
        this.female = female;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean bFemale) {
        this.female = bFemale;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", female=" + female +
                '}';
    }
}
