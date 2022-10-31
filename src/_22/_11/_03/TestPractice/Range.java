package _22._11._03.TestPractice;

import Reusable.ListUtils;
import unit4.collectionsLib.Node;

public class Range {

    public static void main(String[] args) {
    }

    /**
     * @param rangeList Non-empty, ascending sorted list, without empty values.
     * @param list Non-null ascending sorted list.
     */
    public static boolean isIncluded(Node<Integer> list, Node<Range> rangeList) {
        Node<Range> rangePos = rangeList;
        for (Node<Integer> pos = list; pos != null; pos = pos.getNext()) {
            // Iterate through rangePos until we find a range
            // that includes pos (or until there are no more ranges).
            while (!rangePos.getValue().isIncluded(pos.getValue())) {
                if (rangePos.hasNext()) {
                    rangePos = rangePos.getNext();
                }
                // If we iterated through all the ranges,
                // and still haven't found, rangeList doesn't include list.
                else {
                    return false;
                }
            }
        }
        // If we passed all the conditions, rangeList includes list.
        return true;
    }

    public static boolean isIncludedV2(Node<Integer> list, Node<Range> rangeList) {
        Node<Range> rangePos = rangeList;
        Node<Integer> pos = list;

        while (rangePos != null && pos != null) {
            if (rangePos.getValue().isIncluded(pos.getValue())) {
                pos = pos.getNext();
            }
            else {
                rangePos = rangePos.getNext();
            }
        }

        // If we have no ranges left, rangeList includes list
        // only if we also don't have any integers left.
        if (rangePos == null) {
            return pos == null;
        }
        // pos = null meaning we iterated over all the ints
        // and found a range that includes them in rangeList.
        return true;
    }

    private int low;
    private int high;

    public Range(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public boolean isIncluded(int x) {
        return x >= low && x <= high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }
}
