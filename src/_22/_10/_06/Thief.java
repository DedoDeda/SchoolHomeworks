package _22._10._06;

public class Thief extends Person {

    @Override
    public String meet(Person other) {
        return other.meetThief();
    }

    @Override
    protected String meetThief() {
        return "How much money have you stolen today?";
    }

    @Override
    protected String meetPerson() {
        return "Give me your money!";
    }

    @Override
    protected String meetPoliceman() {
        return "";
    }
}
