package Reusable;

import unit4.collectionsLib.BinNode;
import unit4.collectionsLib.Stack;

import java.util.Random;

public class BinNodeUtils {

    /**
     * Makes a list from an array.
     */
    @SafeVarargs
    public static <T> BinNode<T> make(T... elems) {
        // No elems - null list.
        if (elems.length == 0) {
            return null;
        }

        BinNode<T> list = new BinNode<>(elems[0]);
        BinNode<T> pos = list;

        // Insert each element.
        for (int i = 1; i < elems.length; i++) {
            BinNode<T> next = new BinNode<>(pos, elems[i], null);
            pos.setRight(next);
            pos = next;
        }

        return list;
    }

    /**
     * Makes a random sorted list given a size.
     */
    public static BinNode<Integer> makeRandomSorted(int size) {
        Random random = new Random();
        BinNode<Integer> lst = null;

        // Insert sorted each random value.
        for (int i = 0; i < size; i++) {
            lst = insertSorted(lst, random.nextInt());
        }

        return lst;
    }

    /**
     * Inserts a first element to a list.
     */
    public static <T> BinNode<T> insertFirst(BinNode<T> list, T value) {
        // Empty list - just return a new list.
        if (list == null) {
            return new BinNode<>(value);
        }

        // Link before list.
        BinNode<T> newNode = new BinNode<>(null, value, list);
        list.setLeft(newNode);
        return newNode;
    }

    /**
     * Inserts to a list after the given node.
     * @param node Null means insert first.
     */
    public static <T> BinNode<T> insertAfter(BinNode<T> lst, BinNode<T> node, T value) {
        // List null - just return a new list.
        if (lst == null) {
            return new BinNode<>(value);
        }
        // Node null - insert first.
        if (node == null) {
            return insertFirst(lst, value);
        }
        // Last node - insert last.
        if (!node.hasRight()) {
            BinNode<T> newNode = new BinNode<>(node, value, null);
            node.setRight(newNode);
            return lst;
        }

        // Link between node and it's next.
        BinNode<T> newNode = new BinNode<>(node, value, node.getRight());
        node.getRight().setLeft(newNode);
        node.setRight(newNode);
        return lst;
    }

    /** Inserts to a list before the given node. */
    public static <T> BinNode<T> insertBefore(BinNode<T> lst, BinNode<T> node, T value) {
        // List null - just return a new list.
        if (lst == null) {
            return new BinNode<>(value);
        }
        // Node null or first node - insert first.
        if (node == null || !node.hasLeft()) {
            return insertFirst(lst, value);
        }

        // Link between node and it's previous.
        BinNode<T> newNode = new BinNode<>(node.getLeft(), value, node);
        node.getLeft().setRight(newNode);
        node.setLeft(newNode);
        return lst;
    }

    /** Inserts to a sorted list, keeping it sorted. */
    public static BinNode<Integer> insertSorted(BinNode<Integer> list, int value) {
        // Null list - just return a new list.
        if (list == null) {
            return new BinNode<>(value);
        }
        // If should be first, insert as first.
        if (value < list.getValue()) {
            return insertFirst(list, value);
        }

        // Iterate until we find the position to insert.
        BinNode<Integer> pos;
        for (pos = list; pos.hasRight(); pos = pos.getRight()) {
            BinNode<Integer> next = pos.getRight();
            if (value < next.getValue()) {
                // Insert the value between pos and its next.
                list = insertBefore(list, next, value);
                return list;
            }
        }

        // The value is greater than any element in the list and should be last.
        list = insertAfter(list, pos, value);
        return list;
    }

    /** Removes the first element from the list. */
    public static <T> BinNode<T> removeFirst(BinNode<T> list) {
        // If the list is empty or has a single element - return an empty list.
        if (list == null || !list.hasRight()) {
            return null;
        }

        // Unlink the first node.
        list.getRight().setLeft(null);
        // Return the second node.
        return list.getRight();
    }

    /** Removes pos from the list. */
    public static <T> BinNode<T> remove(BinNode<T> lst, BinNode<T> pos) {
        // If pos is null - do nothing.
        if (pos == null) {
            return lst;
        }
        // If pos is first, remove first.
        if (!pos.hasLeft()) {
            return removeFirst(lst);
        }
        // If pos is last, unlink only from left.
        if (!pos.hasRight()) {
            pos.getLeft().setRight(null);
            return lst;
        }

        // Unlink pos between it's left and right.
        pos.getLeft().setRight(pos.getRight());
        pos.getRight().setLeft(pos.getLeft());
        return lst;
    }

    /** Removes the first occurrence of the target from the list. */
    public static <T> BinNode<T> removeFirstOccur(BinNode<T> lst, T target) {
        // Find the target.
        BinNode<T> firstOccur = findFirstOccur(lst, target);
        // Remove it.
        return remove(lst, firstOccur);
    }

    /** Remove all occurrences of the target from the list. */
    public static <T> BinNode<T> removeAll(BinNode<T> lst, T target) {
        // Iterate until and remove every target we find.
        for (BinNode<T> pos = lst; pos != null; pos = pos.getRight()) {
            if (pos.getValue().equals(target)) {
                lst = remove(lst, pos);
            }
        }

        return lst;
    }

    /** Get the last element of the list. */
    public static <T> BinNode<T> getLast(BinNode<T> list) {
        if (list == null) {
            return null;
        }

        BinNode<T> pos;
        // Move pos to be the last.
        for (pos = list; pos.hasRight(); pos = pos.getRight()) ;
        return pos;
    }

    /** Get the element at the given index in the list. Returns null for invalid indexes. */
    public static <T> BinNode<T> at(BinNode<T> list, int idx) {
        BinNode<T> pos = list;
        for (int i = 0; i < idx && pos != null; i++, pos = pos.getRight()) ;
        return pos;
    }

    /** Gets a reversed copy of the list. */
    public static <T> BinNode<T> getReversed(BinNode<T> list) {
        // List null - just return null.
        if (list == null) {
            return null;
        }

        // Copy the list to a stack.
        Stack<T> tempStack = new Stack<>();
        for (BinNode<T> pos = list; pos != null; pos = pos.getRight()) {
            tempStack.push(pos.getValue());
        }

        // Move the stack to a new (reversed) list.
        BinNode<T> reversedList = new BinNode<>(tempStack.pop());
        BinNode<T> pos = reversedList;
        while (!tempStack.isEmpty()) {
            BinNode<T> next = new BinNode<T>(pos, tempStack.pop(), null);
            pos.setRight(next);
            pos = next;
        }

        return reversedList;
    }

    /** Get from the list the first node whose value equals to the target. */
    public static <T> BinNode<T> findFirstOccur(BinNode<T> lst, T target) {
        // Iterate until we find the target and return it (if we ever find).
        for (BinNode<T> pos = lst; pos != null; pos = pos.getRight()) {
            if (pos.getValue().equals(target)) {
                return pos;
            }
        }

        return null;
    }

    /** Get a formatted string of the list. */
    public static <T> String toString(BinNode<T> list) {
        StringBuilder builder = new StringBuilder();
        for (BinNode<T> node = list; node != null; node = node.getRight()) {
            builder.append('[').append(node).append("]<->");
        }
        builder.append("null");

        return builder.toString();
    }

    /** Print the list to the console. */
    public static <T> void print(BinNode<T> list) {
        System.out.println(toString(list));
    }
}
