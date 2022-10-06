package _10_22._06_10_22;

public class Person {

    public String meet(Person other) {
        return other.meetPerson();
    }

    protected String meetThief() {
        return "";
    }

    protected String meetPerson() {
        return "Hi! How are you today?";
    }

    protected String meetPoliceman() {
        return "";
    }
}
