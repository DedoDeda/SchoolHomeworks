package _09_22._20_09_22;

import unit4.collectionsLib.Node;
import unit4.collectionsLib.Stack;

@SuppressWarnings("DuplicatedCode")
public class StackPractice {

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
            temp.push(s);
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

    // Task 14.
    public static class MyStack<T> {

        private Node<T> top;

        @SafeVarargs
        public static <T> MyStack<T> make(T... elems) {
            MyStack<T> stack = new MyStack<>();
            for (T e : elems) {
                stack.push(e);
            }

            return stack;
        }

        public T top() {
            return top.getValue();
        }

        public T pop() {
            T tempTop = top.getValue();
            top = top.getNext();
            return tempTop;
        }

        public void push(T newTop) {
            top = new Node<>(newTop, top);
        }

        /** @implNote The top element's index is 1. */
        public T at(int index) {
            if (isEmpty()) {
                return null;
            }

            Node<T> node = top;
            for (int i = 1; i < index; i++) {
                if (node.hasNext()) {
                    node = node.getNext();
                }
                else {
                    // The given index is out of bounds.
                    return null;
                }
            }

            return node.getValue();
        }

        public boolean isEmpty() {
            return top == null;
        }

        @Override
        public String toString() {
            if (isEmpty()) {
                return "[]";
            }

            Node<T> node = top;
            StringBuilder builder = new StringBuilder("[");
            builder.append(node);
            while (node.hasNext()) {
                node = node.getNext();
                builder.append(", ").append(node);
            }
            builder.append("]");

            return builder.toString();
        }
    }
}
