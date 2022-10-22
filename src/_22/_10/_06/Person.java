package _22._10._06;

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
