package SummerAssignment.Task2;

public class Ex1To8 {

    public static String findLongest(String[] strings) {
        String longest = strings[0];
        for (int i = 1; i < strings.length; i++) {
            if (strings[i].length() > longest.length()) {
                longest = strings[i];
            }
        }

        return longest;
    }

    public static int getEqualityCount(String target, String[] others) {
        int count = 0;
        for (String string : others) {

            if (string.equals(target)) {
                count++;
            }
        }

        return count;
    }

    public static int[] getComparisons(String target, String[] others) {
        int[] comparisons = new int[others.length];

        for (int i = 0; i < others.length; i++) {
            comparisons[i] = target.compareTo(others[i]);
        }

        return comparisons;
    }

    public static boolean contains(String sub, String string) {
        //noinspection IndexOfReplaceableByContains
        return string.indexOf(sub) != -1;
    }

    public static boolean contains(char sub, String string) {
        return string.indexOf(sub) != -1;
    }

    public static int getOccurrencesCount(String sub, String string) {
        int count = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.substring(i).contains(sub)) {
                count++;
            }
        }

        return count;
    }

    public static int getOccurrencesCount(char sub, String string) {
        int count = 0;

        for (int i = 0; i < string.length(); i++) {
            if (contains(sub, string.substring(i))) {
                count++;
            }
        }

        return count;
    }

    public static boolean isEmailAddressValid(String address) {
        return getOccurrencesCount('@', address) == 1 && !(address.startsWith("@") || address.endsWith("@"));
    }

    public static boolean isDigit(char c) {
        final String digits = "0123456789";
        return contains(c, digits);
    }

    public static boolean isIDValid(String id) {
        int length = id.length();
        if (length != 9) {
            return false;
        }

        for (int i = 0; i < id.length(); i++) {
            if (!isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPhoneNumber(String number) {
        int length = number.length();
        if (!(length == 11 && number.charAt(0) == '0' &&
                number.charAt(1) == '5' && isDigit(number.charAt(2))
                && number.charAt(3) == '-') || number.charAt(4) == '0') {
            return false;
        }

        for (int i = 5; i < length; i++) {
            if (!isDigit(number.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
