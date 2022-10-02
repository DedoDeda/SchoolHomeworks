package _10_22._02_10_22;

import Reusable.QueueUtils;
import unit4.collectionsLib.Queue;

public class QueuePractice {

    public static void main(String[] args) {
    }

    /** O(n^2) where n is the size of q. */
    public static Queue<Integer> getCouplesQueue(Queue<Integer> q) {
        Queue<Integer> copy = QueueUtils.shallowCopy(q);
        Queue<Integer> couplesQueue = new Queue<>();

        while (!copy.isEmpty()) {
             int head = copy.remove();
             int count = QueueUtils.countAndRemove(copy, head) + 1;
             if (count == 2) {
                 couplesQueue.insert(head);
             }
        }

        return couplesQueue;
    }
}
