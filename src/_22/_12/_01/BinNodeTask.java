package _22._12._01;

import Reusable.BinNodeUtils;
import unit4.collectionsLib.BinNode;

import java.util.Scanner;


public class BinNodeTask {
    public static void main(String[] args) {
        BinNodeUtils.print(BinNodeUtils.makeRandomSorted(7887));
        System.out.println("--- TASK 3 ---");
        task3();
        System.out.println("--- TASK 4 ---");
        task4();
    }

    /** Prints the reverse of the given list. */
    public static <T> void printReversed(BinNode<T> lst) {
        BinNode<T> reversed = BinNodeUtils.getReversed(lst);
        BinNodeUtils.print(reversed);
    }

    public static void task3() {
        // Make the list.
        BinNode<Integer> lst = BinNodeUtils.make(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        BinNodeUtils.print(lst);
        printReversed(lst);

        // Insert first.
        lst = BinNodeUtils.insertFirst(lst, -696);
        BinNodeUtils.print(lst);
        printReversed(lst);

        // Insert sorted.
        lst = BinNodeUtils.insertSorted(lst, 10000);
        BinNodeUtils.print(lst);
        printReversed(lst);

        // Insert before.
        lst = BinNodeUtils.insertBefore(lst, BinNodeUtils.at(lst, 6), 6324);
        BinNodeUtils.print(lst);
        printReversed(lst);

        // Delete according to input.
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; ++i) {
            System.out.println("Enter number to delete from list:");
            int input = scanner.nextInt();

            lst = BinNodeUtils.removeFirstOccur(lst, input);
            BinNodeUtils.print(lst);
            printReversed(lst);
        }
        scanner.close();
    }

    public static void task4() {
        // Make the sorted list from the array.
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        BinNode<Integer> lst = null;
        for (int i : arr) {
            lst = BinNodeUtils.insertSorted(lst, i);
            BinNodeUtils.print(lst);
            printReversed(lst);
        }

        // Insert first.
        lst = BinNodeUtils.insertFirst(lst, -696);
        BinNodeUtils.print(lst);
        printReversed(lst);

        // Insert after using BinNodeUtils::at().
        lst = BinNodeUtils.insertAfter(lst, BinNodeUtils.at(lst, 5), 10000);
        BinNodeUtils.print(lst);
        printReversed(lst);

        // Insert before.
        lst = BinNodeUtils.insertBefore(lst, BinNodeUtils.at(lst, 6), 6324);
        BinNodeUtils.print(lst);
        printReversed(lst);

        // Delete according to input.
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; ++i) {
            System.out.println("Enter number to delete from list:");
            int input = scanner.nextInt();
            lst = BinNodeUtils.removeFirstOccur(lst, input);
            BinNodeUtils.print(lst);
            printReversed(lst);
        }
        scanner.close();
    }
}
