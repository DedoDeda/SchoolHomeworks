package _22._10._06;

public class Policeman extends Person {

    @Override
    public String meet(Person other) {
        return other.meetPoliceman();
    }

    @Override
    protected String meetThief() {
        return "Got you! You're going to jail now!";
    }

    @Override
    protected String meetPerson() {
        return "Do you need help?";
    }

    @Override
    protected String meetPoliceman() {
        return "Do you have a donut?";
    }
}
