package SummerAssignment.Task1;

public class Ex1To2 {

    public static void main(String[] args) {

    }

    public static int[] multiply(final int[] arr1, final int[] arr2) {
        int[] res = new int[arr2.length];
        for (int i = 0; i < arr2.length; i++) {
            if (i < arr1.length) {
                res[i] = arr1[i] * arr2[i];
            }
            else {
                res[i] = arr2[i];
            }
        }
        return res;
    }

    public static boolean isPerfect(int[] arr) {
        int coveredElementsCount = 0;
        int nextIndex = arr[0];
        arr[0] = 0;

        while (nextIndex != 0 && coveredElementsCount < arr.length) {
            final int tmp = arr[nextIndex];
            arr[nextIndex] = 0;
            nextIndex = tmp;
            coveredElementsCount++;
        }

        return coveredElementsCount == arr.length - 1;
    }

}
