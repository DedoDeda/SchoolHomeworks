package _22._11._03.TestPractice;

import Reusable.ListUtils;
import Reusable.QueueUtils;
import Reusable.StackUtils;
import unit4.collectionsLib.Node;
import unit4.collectionsLib.Queue;
import unit4.collectionsLib.Stack;

public class TestPractice {

    public static void main(String[] args) {
    }

    public static int toNumber(Queue<Integer> numQueue) {
        int num = 0;
        for (int coefficient = 1; !numQueue.isEmpty(); coefficient *= 10) {
            num += coefficient * numQueue.remove();
        }

        return num;
    }

    public static int bigNumber(Node<Queue<Integer>> list) {
        if (list == null) {
            return 0;
        }

        int max = toNumber(list.getValue());
        for (Node<Queue<Integer>> pos = list.getNext(); pos != null; pos = pos.getNext()) {
            int num = toNumber(pos.getValue());
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    public static Node<Integer> cycle(Node<Integer> lst, int n) {
        if (n == 0) {
            return lst;
        }

        Stack<Integer> stack = new Stack<>();
        for (; lst != null; lst = lst.getNext()) {
            stack.push(lst.getValue());
        }

        Stack<Integer> end = new Stack<>();
        for (int i = 0; i < n; i++) {
            end.push(stack.pop());
        }

        Stack<Integer> rest = new Stack<>();
        while (!stack.isEmpty()) {
            rest.push(stack.pop());
        }

        lst = new Node<>(end.pop());
        Node<Integer> pos = lst;
        while (!end.isEmpty()) {
            Node<Integer> next = new Node<>(end.pop());
            pos.setNext(next);
            pos = next;
        }

        while (!rest.isEmpty()) {
            Node<Integer> next = new Node<>(rest.pop());
            pos.setNext(next);
            pos = next;
        }

        return lst;
    }

    public static boolean isTripleList(Node<Integer> lst) {
        final int size = ListUtils.size(lst);
        // An empty list is not a triple list.
        if (size == 0) {
            return false;
        }

        // A triple list size must be divided by 3.
        if (size % 3 != 0) {
            return false;
        }

        // Check the sequences.
        Queue<Integer> seq = new Queue<>();
        Node<Integer> pos = lst;
        for (int i = 0; i < size / 3; i++, pos = pos.getNext()) {
            seq.insert(pos.getValue());
        }

        for (; pos != null; pos = pos.getNext()) {
            int seqVal = seq.remove();
            seq.insert(seqVal);
            if (seqVal != pos.getValue()) {
                return false;
            }
        }

        return true;
    }

    public static int lastAndRemove(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }

        Stack<Integer> temp = new Stack<>();
        StackUtils.move(stack, temp);
        int val = temp.pop();
        StackUtils.move(temp, stack);

        return val;
    }

    public static boolean allSameDigitCount(Node<Integer> pos) {
        if (pos == null || !pos.hasNext()) {
            return true;
        }

        return numDigits(pos.getValue()) == numDigits(pos.getNext().getValue())
                && allSameDigitCount(pos.getNext());
    }

    private static int numDigits(int num) {
        int numDigits = 1;

        while (num >= 10) {
            numDigits++;
            num /= 10;
        }

        return numDigits;
    }
}
