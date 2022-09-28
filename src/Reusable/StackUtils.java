package Reusable;

import unit4.collectionsLib.Stack;

public class StackUtils {

    @SafeVarargs
    public static <T> Stack<T> make(T... elems) {
        Stack<T> stack = new Stack<>();
        for (T e : elems) {
            stack.push(e);
        }

        return stack;
    }

    public static <T> boolean exists(Stack<T> stack, T target) {
        boolean exists = false;
        Stack<T> temp = new Stack<>(); // Temp stack.

        // Calc sum and copy.
        while (!stack.isEmpty()) {
            T e = stack.pop();
            temp.push(e);

            if (e == target) {
                exists = true;
                break;
            }
        }

        // Set the values back to stack.
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return exists;
    }

    public static <T> int sizeRec(Stack<T> stack) {
        if (stack.isEmpty()) {
            return 0;
        }

        T e = stack.pop();
        int size = sizeRec(stack);
        stack.push(e);
        return size + 1;
    }

    public static <T> Stack<T> copy(Stack<T> orig) {
        Stack<T> temp = new Stack<>();

        // Copy orig to temp (temp is reversed).
        while (!orig.isEmpty()) {
            T e = orig.pop();
            temp.push(e);
        }

        Stack<T> result = new Stack<>();

        // Copy from temp to the result (order is correct now) and push the elems back to orig.
        while (!temp.isEmpty()) {
            T e = temp.pop();
            result.push(e);
            orig.push(e);
        }

        return result;
    }

    public static Stack<Integer> getStringsLengths(Stack<String> stack) {
        Stack<String> temp = new Stack<>();

        // Copy stack to temp (temp is reversed).
        while (!stack.isEmpty()) {
            String s = stack.pop();
            stack.push(s);
        }

        Stack<Integer> result = new Stack<>();

        // Copy temp to stack and push the lengths to the result (result order is correct).
        while (!temp.isEmpty()) {
            String s = temp.pop();
            result.push(s.length());
            stack.push(s);
        }

        return result;
    }
}