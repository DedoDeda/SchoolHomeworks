package Reusable;

import unit4.collectionsLib.Node;
import unit4.collectionsLib.Stack;

public class ListUtils {

    @SafeVarargs
    public static <T> Node<T> make(T... elems) {
        if (elems.length == 0) {
            return null;
        }

        Node<T> head = new Node<>(elems[0]);
        Node<T> node = head;

        for (int i = 1; i < elems.length; i++) {
            Node<T> next = new Node<>(elems[i]);
            node.setNext(next);
            node = next;
        }

        return head;
    }

    public static Node<Integer> maxNode(Node<Integer> head) {
        Node<Integer> maxNode = head;
        Node<Integer> node = head;

        while (node.hasNext()) {
            node = node.getNext();
            if (node.getValue() > maxNode.getValue()) {
                maxNode = node;
            }
        }

        return maxNode;
    }

    /**
     * @implNote Compares the vals using the == operator.
     */
    public static <T> boolean valsEqual(Node<T> head1, Node<T> head2) {
        Node<T> node1 = head1;
        Node<T> node2 = head2;

        while (node1 != null && node2 != null) {
            if (node1.getValue() != node2.getValue()) {
                return false;
            }

            node1 = node1.getNext();
            node2 = node2.getNext();
        }

        return node1 == null && node2 == null;
    }

    /**
     * @implNote Compares the nodes using the == operator.
     */
    public static <T> boolean equals(Node<T> head1, Node<T> head2) {
        Node<T> node1 = head1;
        Node<T> node2 = head2;

        while (node1 != null && node2 != null) {
            if (node1 != node2) {
                return false;
            }

            node1 = node1.getNext();
            node2 = node2.getNext();
        }

        return node1 == null && node2 == null;
    }

    public static <T> Node<T> getReversed(Node<T> head) {
        Stack<T> stack = new Stack<>();
        Node<T> pos = head;

        while (pos != null) {
            stack.push(pos.getValue());
            pos = pos.getNext();
        }

        if (stack.isEmpty()) {
            return null;
        }

        Node<T> reversedHead = new Node<>(stack.pop());
        pos = reversedHead;
        while (!stack.isEmpty()) {
            Node<T> next = new Node<>(stack.pop());
            pos.setNext(next);
            pos = next;
        }
        return reversedHead;
    }

    public static <T> int sizeRec(Node<T> pos) {
        if (pos == null) {
            return 0;
        }

        return 1 + sizeRec(pos.getNext());
    }

    public static <T> int size(Node<T> head) {
        int size = 0;
        for (Node<T> pos = head; pos != null; pos = pos.getNext(), size++);

        return size;
    }

    public static <T> Node<T> copy(Node<T> head) {
        if (head == null) {
            return null;
        }

        Node<T> newHead = new Node<>(head.getValue());
        for (Node<T> pos = head, newPos = newHead;
             pos.hasNext();
             pos = pos.getNext(), newPos = newPos.getNext()) {
            T nextVal = pos.getNext().getValue();
            newPos.setNext(new Node<>(nextVal));
        }

        return newHead;
    }

    public static <T> Node<T> insertBefore(Node<T> head, T value) {
        return new Node<>(value, head);
    }

    public static <T> Node<T> insertLast(Node<T> head, T value) {
        if (head == null) {
            return new Node<>(value);
        }

        Node<T> pos;
        for (pos = head; pos.hasNext(); pos = pos.getNext());

        pos.setNext(new Node<>(value));
        return head;
    }

    public static <T> Node<T> insertAfter(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }

        node.setNext(new Node<>(value, node.getNext()));
        return node.getNext();
    }

    public static Node<Integer> insertSorted(Node<Integer> head, int value) {
        if (head == null) {
            return new Node<>(value);
        }
        if (value < head.getValue()) {
            return insertBefore(head, value);
        }

        Node<Integer> pos;
        for (pos = head; pos.hasNext(); pos = pos.getNext()) {
            Node<Integer> next = pos.getNext();
            if (value < next.getValue()) {
                // Insert between pos and its next, the value.
                pos.setNext(insertBefore(next, value));
                return head;
            }
        }

        // The value is greater than any element in the list and shall be last.
        insertAfter(pos, value);
        return head;
    }

    /** Removes the first occurrence. */
    public static <T> Node<T> remove(Node<T> list, T target) {
        if (list == null) {
            return null;
        }

        if (list.getValue() == target) {
            return list.getNext();
        }

        for (Node<T> pos = list; pos.hasNext(); pos = pos.getNext()) {
            Node<T> next = pos.getNext();
            if (next.getValue() == target) {
                pos.setNext(next.getNext());
                return list;
            }
        }

        return list;
    }

    public static <T> String toString(Node<T> head) {
        StringBuilder builder = new StringBuilder();
        for (Node<T> node = head; node != null; node = node.getNext()) {
            builder.append('[').append(node).append("]->");
        }
        builder.append("null");

        return builder.toString();
    }


}
