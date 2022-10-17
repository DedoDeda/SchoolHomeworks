package Reusable;

import unit4.collectionsLib.Queue;

public class QueueUtils {

    @SafeVarargs
    public static <T> Queue<T> make(T... elems) {
        Queue<T> q = new Queue<>();
        for (T e : elems) {
            q.insert(e);
        }

        return q;
    }

    public static <T> int size(Queue<T> q) {
        Queue<T> temp = new Queue<>();
        int size = 0;
        while (!q.isEmpty()) {
            size++;
            temp.insert(q.remove());
        }

        while (!temp.isEmpty()) {
            q.insert(temp.remove());
        }

        return size;
    }

    public static <T> Queue<T> shallowCopy(Queue<T> orig) {
        Queue<T> temp = new Queue<>();
        Queue<T> copy = new Queue<>();

        while (!orig.isEmpty()) {
            T head = orig.remove();
            copy.insert(head);
            temp.insert(head);
        }

        while (!temp.isEmpty()) {
            orig.insert(temp.remove());
        }

        return copy;
    }

    /** @implNote Compares with the == operator. */
    public static <T> int countAndRemove(Queue<T> q, T target) {
        Queue<T> temp = new Queue<>();
        int count = 0;

        while (!q.isEmpty()) {
            T head = q.remove();
            if (target == head) {
                count++;
            }
            else {
                temp.insert(head);
            }
        }

        while (!temp.isEmpty()) {
            q.insert(temp.remove());
        }

        return count;
    }

    /**
     * Compares by the == operator.
     */
    public static <T> boolean equals(Queue<T> q1, Queue<T> q2) {
        boolean equals = true;

        Queue<T> temp1 = new Queue<>();
        Queue<T> temp2 = new Queue<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            T e1 = q1.remove();
            T e2 = q2.remove();

            temp1.insert(e1);
            temp2.insert(e2);

            if (e1 != e2) {
                equals = false;
            }
        }

        while (!q1.isEmpty()) {
            temp1.insert(q1.remove());
            equals = false;
        }
        while (!q2.isEmpty()) {
            temp2.insert(q2.remove());
            equals = false;
        }

        while (!temp1.isEmpty()) {
            q1.insert(temp1.remove());
        }
        while (!temp2.isEmpty()) {
            q2.insert(temp2.remove());
        }

        return equals;
    }
}
