package _22._11._03;

import Reusable.ListUtils;
import Reusable.StackUtils;
import unit4.collectionsLib.Node;
import unit4.collectionsLib.Queue;
import unit4.collectionsLib.Stack;

public class TestPractice {

    public static void main(String[] args) {

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
}
