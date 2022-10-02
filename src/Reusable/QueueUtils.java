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

    /** @implNote Compares by address - not by value. */
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
}
