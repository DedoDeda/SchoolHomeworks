package SummerAssignment.Task3;

public class Ex1To3 {

    public static void main(String[] args) {
    }

    public static boolean equals(char[] arr1, char[] arr2) {
        return equals(arr1, arr2, 0);
    }

    public static boolean equals(char[] arr1, char[] arr2, int index) {
        if (index == arr1.length) {
            return true;
        }

        return arr1[index] == arr2[index] && equals(arr1, arr2, index + 1);
    }

    public static boolean isSorted(int[] arr) {
        return isSorted(arr, 0);
    }

    public static boolean isSorted(int[] arr, int index) {
        if (index == arr.length - 1) {
            return true;
        }
        return arr[index + 1] >= arr[index] && isSorted(arr, index + 1);
    }

    public static boolean isPalindrome(String string) {
        return isPalindrome(string, 0);
    }

    public static boolean isPalindrome(String string, int index) {
        int length = string.length();
        if (index * 2 >= string.length()) {
            return true;
        }

        return string.charAt(index) == string.charAt(length - index - 1) && isPalindrome(string, index + 1);
    }
}
