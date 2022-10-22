package Reusable;

import unit4.collectionsLib.Node;

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
     * @implNote Compares using the == operator.
     */
    public static <T> boolean equals(Node<T> head1, Node<T> head2) {
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

    public static <T> String toString(Node<T> head) {
        StringBuilder builder = new StringBuilder();
        for (Node<T> node = head; node != null; node = node.getNext()) {
            builder.append('[').append(node).append("]->");
        }
        builder.append("null");

        return builder.toString();
    }
}
