package Reusable;

import unit4.collectionsLib.Node;

public class ListUtils {

    @SafeVarargs
    public static <T> Node<T> make(T... elems) {
        if (elems.length == 0) {
            return null;
        }
        if (elems.length == 1) {
            return new Node<T>(elems[0]);
        }

        Node<T> head = new Node<>(elems[0]);
        Node<T> node = head;

        for (int i = 0; i < elems.length - 1; i++) {
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

        while (node1.hasNext() && node2.hasNext()) {
            if (node1.getNext().getValue() != node2.getNext().getValue()) {
                return false;
            }

            node1 = node1.getNext();
            node2 = node2.getNext();
        }

        return !(node1.hasNext() || node2.hasNext());
    }
}
