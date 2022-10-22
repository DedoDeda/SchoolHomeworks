package _22._09._14;

import java.time.Year;

public class Main {

    public static void main(String[] args) {
        testCar();
    }

    public static void testBook() {
        Book b1 = new Book("Long Way", "Henric Longway", 65, 21);
        Book b2 = new Book("Short Way", "Henric Shortway", 56, 12);
        Book b3 = new Book("Nice Way", "Henric Niceway", 69, 23);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        System.out.println("Sum quantities: " + (b1.getQuantity() + b2.getQuantity() + b3.getQuantity()));

        System.out.println("Sum values: " + (b1.value() + b2.value() + b3.value()));
    }

    public static void testStudent() {
        Student[] students = {
                new Student("A", 1, 95, 92),
                new Student("B", 0, 83, 92),
                new Student("C", 3, 78, 90),
                new Student("D", 4, 90, 100),
                new Student("E", 5, 75, 82)
        };

        for (Student s : students) {
            if (s.getClassNum() == 1) {
                System.out.println(s);
            }
        }
    }

    public static void testCar() {
        Car[] cars = {
                new Car("A", 123, 2021, 321234),
                new Car("B", 321, 2022, 3416532),
                new Car("C", 532, 2021, 234216),
                new Car("D", 351, 2019, 32151)
        };

        int lastYear = Year.now().getValue() - 1;
        for (Car c : cars) {
            if (c.getYear() == lastYear) {
                System.out.println(c);
            }
        }

    }
}
