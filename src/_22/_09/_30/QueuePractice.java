package _22._09._30;

import Reusable.QueueUtils;
import unit4.collectionsLib.Queue;

public class QueuePractice {

    public static void main(String[] args) {
        Queue<Integer> q = QueueUtils.make(1, 2, 3);
        System.out.println(size(q));
    }

    public static int size(Queue<Integer> q) {
        return QueueUtils.size(q);
    }
}
