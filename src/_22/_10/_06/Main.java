package _22._10._06;

public class Main {

    public static void main(String[] args) {
        Policeman pm = new Policeman();
        Person p = new Person();
        Thief t = new Thief();

        System.out.println(p.meet(pm));
        System.out.println(p.meet(p));
        System.out.println(p.meet(t));
    }
}
