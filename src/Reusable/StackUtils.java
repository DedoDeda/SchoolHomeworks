package Reusable;

import unit4.collectionsLib.Stack;

public class StackUtils {

    @SafeVarargs
    public static <T> Stack<T> make(T... elems) {
        Stack<T> stack = new Stack<>();
        for (int i = elems.length - 1; i >= 0; i--) {
            T e = elems[i];
            stack.push(e);
        }

        return stack;
    }

    public static <T> void move(Stack<T> source, Stack<T> dest) {
        while (!source.isEmpty()) {
            dest.push(source.pop());
        }
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

    public static <T> Stack<T> getExtended(Stack<T> s1, Stack<T> s2) {
        Stack<T> temp = new Stack<>();
        Stack<T> res = new Stack<>();

        while (!s1.isEmpty()) {
            T e = s1.pop();
            res.push(e);
            temp.push(e);
        }
        while (!temp.isEmpty()) {
            s1.push(temp.pop());
        }

        while (!s2.isEmpty()) {
            T e = s2.pop();
            res.push(e);
            temp.push(e);
        }
        while (!temp.isEmpty()) {
            s2.push(temp.pop());
        }

        return res;
    }
    /**
     * O(n^2)
     */
    public static void sortAsc(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            int max = findMax(stack);
            findAndPop(stack, max);
            temp.push(max);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    /**
     * O(n)
     * @implNote Compares with the == operator.
     */
    public static <T> void findAndPop(Stack<T> stack, T target) {
        Stack<T> temp = new Stack<>();
        while (!stack.isEmpty()) {
            T e = stack.pop();
            if (e == target) {
                break;
            }

            temp.push(e);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    /**
     * O(n)
     * @param stack Non-empty stack.
     */
    public static int findMax(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        int max = stack.pop();
        temp.push(max);
        while (!stack.isEmpty()) {
            int e = stack.pop();
            temp.push(e);
            if (e > max) {
                max = e;
            }
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return max;
    }

    /**
     * @implNote Index 0 is the top. Compares the values using the == operator.
     */
    public static <T> int indexOf(Stack<T> stack, T target) {
        Stack<T> copy = copy(stack);

        int index = 0;
        while (!copy.isEmpty()) {
            if (target == copy.pop()) {
                return index;
            }
            index++;
        }

        // Doesn't exist.
        return -1;
    }

    /**
     * @param index less than the stack's size.
     */
    public static <T> void popAt(Stack<T> stack, int index) {
        if (stack.isEmpty()) {
            return;
        }

        // 0, 1, 2
        Stack<T> temp = new Stack<>();
        for (int i = 0; i < index; i++) {
            temp.push(stack.pop());
        }
        stack.pop();
        StackUtils.move(temp, stack);
    }

    /**
     * @implNote Index 0 is the top. Compares the values using the == operator.
     */
    public static <T> void popAllOccurrences(Stack<T> stack, T target) {
        Stack<T> temp = new Stack<>();

        while (!stack.isEmpty()) {
            T e = stack.pop();
            if (e != target) {
                temp.push(e);
            }
        }
        StackUtils.move(temp, stack);
    }

    public static <T> Stack<T> getReversed(Stack<T> stack) {
        Stack<T> temp = new Stack<>();
        Stack<T> reverse = new Stack<>();
        while (!stack.isEmpty()) {
            T e = stack.pop();
            temp.push(e);
            reverse.push(e);
        }
        move(temp, stack);

        return reverse;
    }

}
