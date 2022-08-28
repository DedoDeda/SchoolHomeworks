package SummerAssignment.Chapter2;

public class Ex1To8 {

    public static void main(String[] args) {

    }

    public static String findLongest(String[] strings) {
        String longest = "";
        for (String string : strings) {
            if (string.length() > longest.length()) {
                longest = string;
            }
        }

        return longest;
    }

    public static int findOccurrences(String target, String[] others) {
        int count = 0;
        for (String string : others) {
            if (string.equals(target)) {
                count++;
            }
        }

        return count;
    }

    public static void compareMultiple(String target, String[] others) {
        for (String other : others) {
            final int res = target.compareTo(other);
            if (res > 0) {
                System.out.println("String \"" + target +
                        "\" comes after string \"" + other + "\".");
            } else if (res < 0) {
                System.out.println("String \"" + target +
                        "\" comes before string \"" + other + "\".");
            } else {
                System.out.println("String \"" + target +
                        "\" has the same magnitude \"" + other + "\".");
            }
        }
    }

    public static boolean contains(String sub, String string) {
        //noinspection IndexOfReplaceableByContains
        return string.indexOf(sub) != -1;
    }

    public static int findOccurrences(String sub, String string) {
        if (sub.isEmpty()) {
            return 0;
        }

        final int subLen = sub.length();

        int count = 0;
        for (int i = 0; i <= string.length() - subLen; i++) {
            if (string.substring(i, i + subLen).equals(sub)) {
                count++;
            }
        }

        return count;
    }

    public static boolean containsOnce(char sub, String string) {
        boolean contains = false;
        for (int i = 0; i < string.length(); i++) {

            if (sub == string.charAt(i)) {

                if (contains) {
                    return false;
                }
                else {
                    contains = true;
                }

            }

        }

        return contains;
    }

    public static boolean isValidEmail(String email) {
        return containsOnce('@', email) && !(email.startsWith("@") || email.endsWith("@"));
    }

    public static boolean isValidID(String id) {
        final int length = id.length();
        if (length != 9) {
            return false;
        }

        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPhoneNumber(String number) {
        final int numLength = number.length();

        if (!(numLength == 11 &&
                number.charAt(0) == '0' &&
                number.charAt(1) == '5' &&
                Character.isDigit(number.charAt(2))
                && number.charAt(3) == '-')
                || number.charAt(4) == '0') {
            return false;
        }

        for (int i = 5; i < numLength; i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
