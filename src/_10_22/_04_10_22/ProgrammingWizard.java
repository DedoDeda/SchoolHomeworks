package _10_22._04_10_22;

public class ProgrammingWizard extends Wizard {

    protected String programmingLanguage;

    public ProgrammingWizard(String name, boolean female, int power, String programmingLanguage) {
        super(name, female, power);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String toString() {
        return "ProgrammingWizard{" +
                "programmingLanguage='" + programmingLanguage + '\'' +
                ", power=" + power +
                ", name='" + name + '\'' +
                ", female=" + female +
                '}';
    }
}
