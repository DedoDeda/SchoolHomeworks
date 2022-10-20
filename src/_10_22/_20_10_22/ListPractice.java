package _10_22._20_10_22;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class ListPractice {

    public static void main(String[] args) {
        System.out.println(ListUtils.make());
    }

    public static int max(Node<Integer> head) {
        return ListUtils.maxNode(head).getValue();
    }

    public static Node<Integer> maxNode(Node<Integer> head) {
        return ListUtils.maxNode(head);
    }

    public static int calcMaxAccSeqSize(Node<Integer> head) {
        int max = 0;
        int currentSeqSize = 0;
        int prev = head.getValue();
        Node<Integer> node = head;

        while (node.hasNext()) {
            node = node.getNext();

            if (node.getValue() > prev) {
                currentSeqSize++;
            } else if (currentSeqSize > max) {
                max = currentSeqSize;
            }
        }

        if (currentSeqSize > max) {
            max = currentSeqSize;
        }

        return max;
    }

    public static boolean equals(Node<Integer> head1, Node<Integer> head2) {
        return ListUtils.equals(head1, head2);
    }
}