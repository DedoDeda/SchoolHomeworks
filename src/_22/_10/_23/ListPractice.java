package _22._10._23;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class ListPractice {

    public static void main(String[] args) {
        var lst1 = ListUtils.make(2, 5);
        var lst2 = ListUtils.make(10, 15);
        var res = mergeSortedAsc(lst1, lst2);
        System.out.println(ListUtils.toString(res));
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

        while (pos1 != null && pos2 != null) {
            if (pos1.getValue() < pos2.getValue()) {
                newPos = ListUtils.insertAfter(newPos, pos1.getValue());
                pos1 = pos1.getNext();
            }
            else {
                newPos = ListUtils.insertAfter(newPos, pos2.getValue());
                pos2 = pos2.getNext();
            }
        }

        if (pos1 == null) {
            newPos.setNext(pos2);
        }
        else { // pos2 = null
            newPos.setNext(pos1);
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
