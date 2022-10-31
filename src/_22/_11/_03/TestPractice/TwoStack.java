package _22._11._03.TestPractice;

import Reusable.StackUtils;
import unit4.collectionsLib.Stack;
import unit4.utilsLib.ListUtils;

public class TwoStack {

    public static void main(String[] args) {

    }

    private Stack<Integer> numbers;
    private Stack<Integer> sums;

    public Stack<Integer> getNums(int x) {
        // We can change sums and numbers.
        Stack<Integer> nums = new Stack<>();
        int index = StackUtils.indexOf(sums, x);
        // By definition, len of sums == len of numbers.
        for (int i = 0; i < index + 1; i++) {
            nums.push(numbers.pop());
        }

        return nums;
    }

    public void eraseNum(int x) {
        int index = StackUtils.indexOf(numbers, x);
        StackUtils.popAt(numbers, index);

        updateSums();
    }

    private void updateSums() {
        while (!sums.isEmpty()) {
            sums.pop();
        }

        Stack<Integer> reversedNumbers = StackUtils.getReversed(numbers);
        int sum = 0;
        while (!reversedNumbers.isEmpty()) {
            sum += reversedNumbers.pop();
            sums.push(sum);
        }
    }
}
