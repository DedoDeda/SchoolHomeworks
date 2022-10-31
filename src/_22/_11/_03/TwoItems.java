package _22._11._03;

import Reusable.StackUtils;
import unit4.collectionsLib.Stack;

public class TwoItems {

    public static void main(String[] args) {

    }

    public static Stack<TwoItems> stackTwoItems(Stack<Integer> stack) {
        // @todo replace with StackUtils.size() after merge.
        final int size = StackUtils.sizeRec(stack);
        Stack<TwoItems> res = new Stack<>();
        for (int i = 0; i < size / 2; i++) {
            int e = stack.pop();
            res.push(new TwoItems(e, 0));
        }

        Stack<TwoItems> temp = new Stack<>();
        for (int i = 0; i < size / 2; i++) {
            int e = stack.pop();
            TwoItems twoItems = res.pop();
            twoItems.num2 = e;
            temp.push(twoItems);
        }

        StackUtils.move(temp, res);

        return res;
    }

    private int num1;
    private int num2;

    public TwoItems(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "TwoItems{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }
}
