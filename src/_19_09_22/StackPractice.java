package _19_09_22;

import Reusable.StackUtils;
import unit4.collectionsLib.Stack;

public class StackPractice {

    public static void main(String[] args) {

    }

    public static int sum(Stack<Integer> stack) {
        int sum = 0;
        Stack<Integer> temp = new Stack<>(); // Temp stack.

        // Calc sum and copy.
        while (!stack.isEmpty()) {
            int e = stack.pop();
            sum += e;
            temp.push(e);
        }

        // Set the values back to stack.
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return sum;
    }
}
