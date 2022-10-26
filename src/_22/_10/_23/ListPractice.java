package _22._10._23;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class ListPractice {

    public static void main(String[] args) {
    }

    /**
     * O(n) where n is the sum of the two lists' sizes.
     * @param head1 Ascending Sorted list head.
     * @param head2 Ascending sorted list head.
     */
    public static Node<Integer> mergeSortedAsc(Node<Integer> head1, Node<Integer> head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node<Integer> newHead;
        Node<Integer> pos1 = head1;
        Node<Integer> pos2 = head2;

        if (pos1.getValue() < pos2.getValue()) {
            newHead = new Node<>(pos1.getValue());
            pos1 = pos1.getNext();
        }
        else {
            newHead = new Node<>(pos2.getValue());
            pos2 = pos2.getNext();
        }
        Node<Integer> newPos = newHead;

        for (; pos1 != null; pos1 = pos1.getNext()) {
            // Get the current pos1's value.
            int val1 = pos1.getValue();
            for (; pos2 != null; pos2 = pos2.getNext()) {
                // Get the current pos2's value.
                int val2 = pos2.getValue();
                // If val1 <= val2, val1 exit and insert val1 first.
                if (val1 <= val2) {
                    break;
                }

                newPos = ListUtils.insertAfter(newPos, val2);
            }

            newPos = ListUtils.insertAfter(newPos, val1);
        }

        for (; pos2 != null; pos2 = pos2.getNext()) {
            newPos = ListUtils.insertAfter(newPos, pos2.getValue());
        }

        return newHead;
    }

    public static Node<Integer> getReversed(Node<Integer> head) {
        return ListUtils.getReversed(head);
    }

    public static Node<Integer> copy(Node<Integer> head) {
        return ListUtils.copy(head);
    }

    public static Node<Integer> getEvens(Node<Integer> head) {
        Node<Integer> evensHead = null;

        for (Node<Integer> pos = head; pos != null; pos = pos.getNext()) {
            if (pos.getValue() % 2 == 0) {
                // Init the list.
                evensHead = new Node<>(pos.getValue());

                // Now we now evensHead isn't null.
                for (Node<Integer> evensPos = evensHead; pos.hasNext(); pos = pos.getNext()) {
                    int nextVal = pos.getNext().getValue();
                    if (nextVal % 2 == 0) {
                        Node<Integer> next = new Node<>(nextVal);
                        evensPos.setNext(next);
                        evensPos = next;
                    }
                }

                break;
            }
        }

        return evensHead;
    }

    public static Node<Integer> getOddIndexNodes(Node<Integer> head) {
        Node<Integer> newHead;
        if (head != null && head.hasNext()) {
            newHead = new Node<>(head.getNext().getValue());
        }
        else {
            return null;
        }

        int idx = 0;
        for (Node<Integer> pos = head.getNext(), newPos = newHead; pos.hasNext(); pos = pos.getNext(), idx++) {
            if (idx % 2 != 0) {
                Node<Integer> next = new Node<>(pos.getNext().getValue());
                newPos.setNext(next);
                newPos = next;
            }
        }

        return newHead;
    }

    public static int sizeRec(Node<Integer> head) {
        return ListUtils.sizeRec(head);
    }
}
