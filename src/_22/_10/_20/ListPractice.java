package _22._10._20;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class ListPractice {

    public static void main(String[] args) {
    }

    public static int max(Node<Integer> list) {
        return ListUtils.maxNode(list).getValue();
    }

    public static Node<Integer> maxNode(Node<Integer> list) {
        return ListUtils.maxNode(list);
    }

    public static int calcMaxAscSeqSize(Node<Integer> list) {
        int maxSeqSize = 0;
        int currentSeqSize = 1;
        int prev = list.getValue();
        Node<Integer> node = list;

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

    public static boolean equals(Node<Integer> list1, Node<Integer> list2) {
        return ListUtils.equals(list1, list2);
    }
}