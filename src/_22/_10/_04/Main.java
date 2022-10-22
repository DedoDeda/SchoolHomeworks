package _22._10._04;

public class Main {

    public static void main(String[] args) {

        Creature[] creatures = {
                new Dwarf("Itzik", false, 100),
                new BouncyDwarf("Itzik", false,102, 80),
                new Wizard("Moises", true, 50),
                new ProgrammingWizard("Norm", false, 55, "C#"),
                new Troll("Landver", true, 25)
        };

        for (Creature c : creatures) {
            System.out.println(c);
        }

    }
}
