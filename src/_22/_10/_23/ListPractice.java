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
     * @param list1 Ascending Sorted list head.
     * @param list2 Ascending sorted list head.
     */
    public static Node<Integer> mergeSortedAsc(Node<Integer> list1, Node<Integer> list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        Node<Integer> newList;
        Node<Integer> pos1 = list1;
        Node<Integer> pos2 = list2;

        if (pos1.getValue() < pos2.getValue()) {
            newList = new Node<>(pos1.getValue());
            pos1 = pos1.getNext();
        }
        else {
            newList = new Node<>(pos2.getValue());
            pos2 = pos2.getNext();
        }
        Node<Integer> newPos = newList;

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
            newPos.setNext(copy(pos2));
        }
        else { // pos2 = null
            newPos.setNext(copy(pos1));
        }

        return newList;
    }

    public static Node<Integer> getReversed(Node<Integer> list) {
        return ListUtils.getReversed(list);
    }

    public static Node<Integer> copy(Node<Integer> list) {
        return ListUtils.copy(list);
    }

    public static Node<Integer> getEvens(Node<Integer> list) {
        Node<Integer> evensHead = null;

        for (Node<Integer> pos = list; pos != null; pos = pos.getNext()) {
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

    public static Node<Integer> getOddIndexNodes(Node<Integer> list) {
        Node<Integer> newHead;
        if (list != null && list.hasNext()) {
            newHead = new Node<>(list.getNext().getValue());
        }
        else {
            return null;
        }

        int idx = 0;
        for (Node<Integer> pos = list.getNext(), newPos = newHead; pos.hasNext(); pos = pos.getNext(), idx++) {
            if (idx % 2 != 0) {
                Node<Integer> next = new Node<>(pos.getNext().getValue());
                newPos.setNext(next);
                newPos = next;
            }
        }

        return newHead;
    }

    public static int sizeRec(Node<Integer> list) {
        return ListUtils.sizeRec(list);
    }
}
