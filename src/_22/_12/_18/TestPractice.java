package _22._12._18;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class TestPractice {

    public static void main(String[] args) {
        var list = ListUtils.make(92, 4, 543);
        System.out.println(ListUtils.toString(list));
        System.out.println(ListUtils.toString(getDigitsList(list)));
    }

    public static Node<Integer> getDigitsList(Node<Integer> numbersList) {
        // Dummy - its next is the list's head.
        Node<Integer> dummy = new Node<>(-1);

        // Add each number to the result list as digits.
        Node<Integer> resultPos = dummy;
        for (Node<Integer> pos = numbersList; pos != null; pos = pos.getNext()) {
            // Add the digits, and update our pos to the list's end.
            resultPos = addDigitsNumber(resultPos, pos.getValue());
            // Add a separator (-9).
            resultPos.setNext(new Node<>(-9));
            // Update our pos to be the last element (the seperator).
            resultPos = resultPos.getNext();
        }

        // Return the list's head.
        return dummy.getNext();
    }

    /** Adds a 'digit number' after the given node. Returns the last node in the modified list. */
    public static Node<Integer> addDigitsNumber(Node<Integer> list, int num) {
        // Iterate on the number's digits, and add each digit to the list.
        Node<Integer> pos = list;
        while (num > 0) {
            // Get the current digit.
            int dig = num % 10;
            // Insert the digit after pos and set pos to the end of the list.
            pos = ListUtils.insertAfter(pos, dig);
            // Cut the number's last digit.
            num /= 10;
        }

        // We return the last node in the list.
        return pos;
    }
}
