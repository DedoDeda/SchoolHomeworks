package _22._11._03._20;

import unit4.collectionsLib.Node;

public class RecursionList {

    public static void printRec(Node<Integer> list) {
        if (list == null) {
            return;
        }

        System.out.println(list.getValue());
        printRec(list.getNext());
    }

    public static int countNodes(Node<Integer> list) {
        if (list == null) {
            return 0;
        }

        return 1 + countNodes(list.getNext());
    }

    public static int countEvenNodes(Node<Integer> list) {
        if (list == null) {
            return 0;
        }

        if (list.getValue() % 2 == 0) {
            return 1 + countEvenNodes(list.getNext());
        }

        return countEvenNodes(list.getNext());
    }

    public static int sumOdds(Node<Integer> list) {
        if (list == null) {
            return 0;
        }

        if (list.getValue() % 2 != 0) {
            return list.getValue() + sumOdds(list.getNext());
        }

        return sumOdds(list.getNext());
    }

    public static boolean isAllEven(Node<Integer> list) {
        if (list == null) {
            return true;
        }

        return list.getValue() % 2 == 0 && isAllEven(list.getNext());
    }

    public static boolean evenExists(Node<Integer> list) {
        if (list == null) {
            return true;
        }

    return list.getValue() % 2 == 0 || evenExists(list.getNext());
    }
}
