package _22._11._03.TestPractice;

import Reusable.StackUtils;
import unit4.collectionsLib.Stack;

public class Pole {

    public static void main(String[] args) {
        Stack<Ring> rings = StackUtils.make(
                new Ring("L", 0),
                new Ring("L", 1),
                new Ring("L", 3),
                new Ring("S", 1),
                new Ring("L", 0)
        );
        Pole pole = new Pole(rings);
        pole.sort();
        System.out.println(pole.rings);
    }

    public Pole(Stack<Ring> rings) {
        this.rings = rings;
    }

    private Stack<Ring> rings;

    public void add(Ring ring) {
        rings.push(ring);
    }

    public Ring remove() {
        return rings.pop();
    }

    public boolean isEmpty() {
        return rings.isEmpty();
    }

    /**
     * O(n^2) where n is the number of rings.
     * This is because removeFirstLargeRing() is O(n).
     * In the worst case, all the rings are large,
     * which means that for reach ring we call removeFirstLargeRing().
     */
    public void sort() {
        Stack<Ring> sortedRings = new Stack<>();

        // Push all the large rings to the sorted stack.
        Ring ring = removeFirstLargeRing();
        while (ring != null) {
            sortedRings.push(ring);
            ring = removeFirstLargeRing();
        }

        // Move the small rings to the sorted stack.
        StackUtils.move(rings, sortedRings);
        rings = sortedRings;
    }

    /**
     * O(n) where n is the number of rings.
     */
    private Ring removeFirstLargeRing() {
        Ring res = null;
        Stack<Ring> temp = new Stack<>();

        while (!rings.isEmpty()) {
            Ring ring = rings.pop();
            if (ring.getSize().equals("L")) {
                res = ring;
                break;
            }

            temp.push(ring);
        }

        StackUtils.move(temp, rings);

        return res;
    }

    public Stack<Ring> getRings() {
        return rings;
    }
}

