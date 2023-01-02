package Reusable;

import unit4.collectionsLib.Node;
import unit4.collectionsLib.Stack;

import java.util.Comparator;
import java.util.function.Predicate;

public class ListUtils {

    @SafeVarargs
    public static <T> Node<T> make(T... elems) {
        if (elems.length == 0) {
            return null;
        }

        Node<T> list = new Node<>(elems[0]);
        Node<T> node = list;

        for (int i = 1; i < elems.length; i++) {
            Node<T> next = new Node<>(elems[i]);
            node.setNext(next);
            node = next;
        }

        return list;
    }

    public static Node<Integer> maxNode(Node<Integer> list) {
        Node<Integer> maxNode = list;
        Node<Integer> node = list;

        while (node.hasNext()) {
            node = node.getNext();
            if (node.getValue() > maxNode.getValue()) {
                maxNode = node;
            }
        }

        return maxNode;
    }

    public static <T> boolean equals(Node<T> list1, Node<T> list2) {
        Node<T> node1 = list1;
        Node<T> node2 = list2;

        while (node1 != null && node2 != null) {
            if (!node1.getValue().equals(node2.getValue())) {
                return false;
            }

            node1 = node1.getNext();
            node2 = node2.getNext();
        }

        return node1 == null && node2 == null;
    }

    public static <T> Node<T> getReversed(Node<T> list) {
        Stack<T> stack = new Stack<>();
        Node<T> pos = list;

        while (pos != null) {
            stack.push(pos.getValue());
            pos = pos.getNext();
        }

        if (stack.isEmpty()) {
            return null;
        }

        Node<T> reversedList = new Node<>(stack.pop());
        pos = reversedList;
        while (!stack.isEmpty()) {
            Node<T> next = new Node<>(stack.pop());
            pos.setNext(next);
            pos = next;
        }
        return reversedList;
    }

    public static <T> int sizeRec(Node<T> pos) {
        if (pos == null) {
            return 0;
        }

        return 1 + sizeRec(pos.getNext());
    }

    /** Get the size (number of elements) of the given list. */
    public static <T> int size(Node<T> list) {
        // Iterate until we are finished with the list,
        // increasing the counter at each iteration.
        int size = 0;
        for (Node<T> pos = list; pos != null; pos = pos.getNext(), size++);

        return size;
    }

    /** Copies the list's nodes, and values (using the Copyable interface). */
    public static <T extends Copyable<T>> Node<T> deepCopy(Node<T> list) {
        // If the list is empty, just return an empty list (null).
        if (list == null) {
            return null;
        }

        // Init the list.
        Node<T> copy = new Node<>(list.getValue().copy());
        for (Node<T> pos = list, copyPos = copy;
             pos.hasNext();
             pos = pos.getNext(), copyPos = copyPos.getNext()) {
            // Copy and add to the copy list.
            T nextVal = pos.getNext().getValue().copy();
            copyPos.setNext(new Node<>(nextVal));
        }

        return copy;
    }

    public static <T> Node<T> copy(Node<T> list) {
        if (list == null) {
            return null;
        }

        Node<T> copy = new Node<>(list.getValue());
        for (Node<T> pos = list, newPos = copy;
             pos.hasNext();
             pos = pos.getNext(), newPos = newPos.getNext()) {
            T nextVal = pos.getNext().getValue();
            newPos.setNext(new Node<>(nextVal));
        }

        return copy;
    }

    public static <T> Node<T> insertBefore(Node<T> list, T value) {
        return new Node<>(value, list);
    }

    public static <T> Node<T> insertLast(Node<T> list, T value) {
        if (list == null) {
            return new Node<>(value);
        }

        Node<T> pos;
        for (pos = list; pos.hasNext(); pos = pos.getNext());

        pos.setNext(new Node<>(value));
        return list;
    }

    public static <T> Node<T> insertAfter(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }

        node.setNext(new Node<>(value, node.getNext()));
        return node.getNext();
    }

    public static Node<Integer> insertSorted(Node<Integer> list, int value) {
        if (list == null) {
            return new Node<>(value);
        }
        if (value < list.getValue()) {
            return insertBefore(list, value);
        }

        Node<Integer> pos;
        for (pos = list; pos.hasNext(); pos = pos.getNext()) {
            Node<Integer> next = pos.getNext();
            if (value < next.getValue()) {
                // Insert the value between pos and its next.
                pos.setNext(insertBefore(next, value));
                return list;
            }
        }

        // The value is greater than any element in the list and shall be last.
        // @FIXME Shouldn't set list here?
        insertAfter(pos, value);
        return list;
    }

    public static <T> boolean isCircular(Node<T> lst) {
        for (Node<T> pos = lst; pos != null; pos = pos.getNext()) {
            for (Node<T> innerPos = lst; innerPos != pos; innerPos = innerPos.getNext()) {
                if (pos.getNext() == innerPos) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Inserts the given value to a sorted list, given a comparator to compare
     * any type of values.
     */
    public static <T> Node<T> insertSorted(Node<T> list, T value, Comparator<T> comparator) {
        // If the list is empty, return a new list from the given value.
        if (list == null) {
            return new Node<>(value);
        }
        // If the value should be first, insert it as first and return.
        if (comparator.compare(value, list.getValue()) < 0) {
            return insertBefore(list, value);
        }

        // Iterate until we find the position to insert the value.
        Node<T> pos;
        for (pos = list; pos.hasNext(); pos = pos.getNext()) {
            Node<T> next = pos.getNext();
            if (comparator.compare(value, next.getValue()) < 0) {
                // Insert the value between pos and its next.
                pos.setNext(insertBefore(next, value));
                return list;
            }
        }

        // If we reach here, then value is greater than any
        // element in the list and should be last.
        insertAfter(pos, value);
        return list;
    }

    public static <T> Node<T> removeFirstOccur(Node<T> list, T target) {
        if (list == null) {
            return null;
        }

        if (list.getValue().equals(target)) {
            return list.getNext();
        }

        for (Node<T> pos = list; pos.hasNext(); pos = pos.getNext()) {
            Node<T> next = pos.getNext();
            if (next.getValue().equals(target)) {
                pos.setNext(next.getNext());
                return list;
            }
        }

        return list;
    }

    /** Removes the first element for which removeCond returns true. */
    public static <T> Node<T> removeFirstOccur(Node<T> list, Predicate<T> removeCond) {
        // If the list is empty, then there's nothing to remove.
        if (list == null) {
            return null;
        }
        // If the first element should be removed, return the next node.
        if (removeCond.test(list.getValue())) {
            return list.getNext();
        }

        // Iterate until we find an element to remove or until we iterated
        // over all the list.
        for (Node<T> pos = list; pos.hasNext(); pos = pos.getNext()) {
            Node<T> next = pos.getNext();
            if (removeCond.test(next.getValue())) {
                // Unlink next from the list.
                pos.setNext(next.getNext());
                return list;
            }
        }

        return list;
    }

    /** Finds a node in the list using a user defined predicate. */
    public static <T> Node<T> findNode(Node<T> list, Predicate<Node<T>> findCond) {
        /* Iterate over the list and return the node for which findCond.test() returns true.
        If not found return null. */
        for (Node<T> pos = list; pos != null; pos = pos.getNext()) {
            if (findCond.test(pos)) {
                return pos;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Node<T> list) {
        Object[] array = new Object[size(list)];
        int idx = 0;
        for (Node<T> pos = list; pos != null; pos = pos.getNext(), idx++) {
            array[idx] = pos.getValue();
        }

        return (T[]) array;
    }

    public static <T> String toString(Node<T> list) {
        StringBuilder builder = new StringBuilder();
        for (Node<T> node = list; node != null; node = node.getNext()) {
            builder.append('[').append(node).append("]->");
        }
        builder.append("null");

        return builder.toString();
    }


}
