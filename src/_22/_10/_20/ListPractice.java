package _22._10._20;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class ListPractice {

    public static void main(String[] args) {
    }

    public static int max(Node<Integer> head) {
        return ListUtils.maxNode(head).getValue();
    }

    public static Node<Integer> maxNode(Node<Integer> head) {
        return ListUtils.maxNode(head);
    }

    public static int calcMaxAscSeqSize(Node<Integer> head) {
        int maxSeqSize = 0;
        int currentSeqSize = 1;
        int prev = head.getValue();
        Node<Integer> node = head;

        while (node.hasNext()) {
            node = node.getNext();

            if (node.getValue() > prev) {
                currentSeqSize++;
            } else {
                if (currentSeqSize > maxSeqSize) {
                    maxSeqSize = currentSeqSize;
                }

                currentSeqSize = 1;
            }

            prev = node.getValue();
        }

        if (currentSeqSize > maxSeqSize) {
            maxSeqSize = currentSeqSize;
        }

        return maxSeqSize;
    }

    public static boolean equals(Node<Integer> head1, Node<Integer> head2) {
        return ListUtils.valsEqual(head1, head2);
    }
}