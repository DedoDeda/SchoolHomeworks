package _22._11._03.TestPractice;

import Reusable.ListUtils;
import Reusable.QueueUtils;
import unit4.collectionsLib.Node;
import unit4.collectionsLib.Queue;
import unit4.collectionsLib.Stack;

public class TestPractice {

    public static void main(String[] args) {
        var lst = ListUtils.make(1, 2, 3, 4);
        lst = cycle(lst, 2);
        System.out.println(ListUtils.toString(lst));
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
}
