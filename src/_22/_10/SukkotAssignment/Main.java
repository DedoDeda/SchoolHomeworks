package _22._10.SukkotAssignment;

import Reusable.QueueUtils;
import Reusable.StackUtils;
import unit4.collectionsLib.Queue;
import unit4.collectionsLib.Stack;

public class Main {

    public static void main(String[] args) {
    }

    /**
     * O(n^2)
     */
    public static Stack<Integer> mergeSorted(Stack<Integer> s1, Stack<Integer> s2) {
        Stack<Integer> res = StackUtils.getExtended(s1, s2);
        StackUtils.sortAcc(res);
        return res;
    }

    /**
     * O(n)
     */
    public static void split(Stack<Integer> stack, Stack<Integer> outOdds, Stack<Integer> outEvens) {
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            int e = stack.pop();
            if (e % 2 == 0) {
                outEvens.push(e);
            }
            else {
                outOdds.push(e);
            }

            temp.push(e);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public static boolean isIdentical(Queue<Integer> q1, Queue<Integer> q2) {
        return QueueUtils.equals(q1, q2);
    }

    public static boolean isSimilar(Queue<Integer> q1, Queue<Integer> q2) {
        int size = QueueUtils.size(q1);
        for (int i = 0; i < size; i++) {
            q1.insert(q1.remove());

            if (isIdentical(q1, q2)) {
                return true;
            }
        }

        return false;
    }
}
