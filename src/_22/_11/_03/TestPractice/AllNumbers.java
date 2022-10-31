package _22._11._03.TestPractice;

public class AllNumbers {

    private int[] arrayNum;

    public int lastOddValue() {
        int lastOddValue = 0;

        for (int val : arrayNum) {
            if (val % 2 != 0) {
                lastOddValue = val;
            }
        }

        return lastOddValue;
    }
}
