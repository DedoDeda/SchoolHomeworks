package _29_09_22;

import java.util.Scanner;
import java.util.Stack;

public class StackPractice {

    public static void main(String[] args) {
        System.out.println(isXYZSequence());
    }

    public static boolean isXYZSequence() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; true; i++) {
            System.out.println("Sequence #" + i);

            if (!isXYZ(scanner)) {
                return false;
            }
        }
    }

    public static boolean isXYZ(Scanner scanner) {
        Stack<Character> inputStack = new Stack<>();

        while (true) {
            System.out.println("Enter a char (currently before Z): ");
            char input = scanner.next().charAt(0);
            if (!isLetter(input)) {
                continue;
            }

            if (input == 'Z') {
                break;
            }

            inputStack.push(input);
        }

        while (true) {
            if (inputStack.isEmpty()) {
                return true;
            }

            System.out.println("Enter a char (after Z):");
            char input = scanner.next().charAt(0);
            if (!isLetter(input)) {
                continue;
            }

            if (input != inputStack.pop()) {
                return false;
            }
        }
    }

    public static boolean isLetter(char c) {
        return isLowercaseLetter(c) || isUppercaseLetter(c);
    }

    public static boolean isUppercaseLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isLowercaseLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

}
