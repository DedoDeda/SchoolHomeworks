package _22._08.SummerAssignment.Chapter1;

public class Ex1To2 {

    public static void main(String[] args) {

    }

    public static int[] multiply(int[] arr1, int[] arr2) {
        // Sort the arrays.
        int[] largest;
        int[] smallest;
        if (arr1.length > arr2.length) {
            largest = arr1;
            smallest = arr2;
        }
        else {
            largest = arr2;
            smallest = arr1;
        }

        int[] res = new int[largest.length];
        for (int i = 0; i < largest.length; i++) {
            if (i < smallest.length) {
                res[i] = smallest[i] * largest[i];
            }
            else {
                res[i] = largest[i];
            }
        }

        return res;
    }

    public static boolean isPerfect(int[] arr) {
        int numScannedElems = 1;
        // A valid array is expected.
        int nextIndex = arr[0];
        arr[0] = 0;

        while (nextIndex != 0) {
            final int elem = arr[nextIndex];
            arr[nextIndex] = 0;
            nextIndex = elem;
            numScannedElems++;
        }

        return numScannedElems == arr.length;
    }

}
