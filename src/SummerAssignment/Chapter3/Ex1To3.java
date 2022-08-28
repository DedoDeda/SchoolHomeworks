package SummerAssignment.Chapter3;

public class Ex1To3 {

    public static void main(String[] args) {

    }

    public static boolean equals(char[] arr1, char[] arr2) {
        return equals(arr1, arr2, 0);
    }

    private static boolean equals(char[] arr1, char[] arr2, int index) {
        if (index == arr1.length) {
            return true;
        }

        return arr1[index] == arr2[index] && equals(arr1, arr2, index + 1);
    }

    public static boolean isSorted(int[] arr) {
        return isSorted(arr, 0);
    }

    private static boolean isSorted(int[] arr, final int index) {
        // For an empty array: (arr.length - 1) = -1 < index = 0, so ">=" is needed for this edge case.
        if (index >= arr.length - 1) {
            return true;
        }

        return arr[index + 1] >= arr[index] && isSorted(arr, index + 1);
    }

    public static boolean isPalindrome(String string) {
        return isPalindrome(string, 0);
    }

    private static boolean isPalindrome(String string, final int index) {
        final int length = string.length();

        final boolean reachedStrMiddle = index * 2 >= length;
        if (reachedStrMiddle) {
            return true;
        }

        final int symmetricIndex = length - 1 - index;
        return string.charAt(index) == string.charAt(symmetricIndex) && isPalindrome(string, index + 1);
    }
}
