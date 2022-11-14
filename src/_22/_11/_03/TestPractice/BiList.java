package _22._11._03.TestPractice;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class BiList {

    public static void main(String[] args) {
        System.out.println(generateBilist(ListUtils.make(88, -9, 0, 10, 6, 13)));
    }

    private Node<Integer> lst1;
    private Node<Integer> lst2;

    public BiList(Node<Integer> lst1, Node<Integer> lst2) {
        this.lst1 = lst1;
        this.lst2 = lst2;
    }

    public static BiList generateBilist(Node<Integer> lst) {
        if (lst == null) {
            return new BiList(null, null);
        }

        final int halfSize = ListUtils.size(lst) / 2;

        Node<Integer> max = ListUtils.maxNode(lst);
        Node<Integer> lst2 = ListUtils.removeFirstOccur(lst, max.getValue());
        Node<Integer> lst1 = new Node<>(max.getValue());
        Node<Integer> pos1 = lst1;

        for (int i = 1; i < halfSize; i++) {
            System.out.println();
            max = ListUtils.maxNode(lst2);
            lst2 = ListUtils.removeFirstOccur(lst2, max.getValue());
            pos1.setNext(new Node<>(max.getValue()));
            pos1 = lst1.getNext();
        }

        return new BiList(lst1, lst2);
    }

    @Override
    public String toString() {
        return "BiList{" +
                "lst1=" + ListUtils.toString(lst1) +
                ", lst2=" + ListUtils.toString(lst2) +
                '}';
    }
}
