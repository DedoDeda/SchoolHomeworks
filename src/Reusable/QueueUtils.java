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
}
