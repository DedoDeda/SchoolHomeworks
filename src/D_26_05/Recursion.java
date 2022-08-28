package D_26_05;

public class Recursion {
    public static void main(String[] args) {
        System.out.println(factorial(3));
        System.out.println(multiply(3, 6));
        System.out.println(power(4, 2));
        System.out.println(divide(12, 3));
        System.out.println(modulo(4, 2));
        System.out.println(getSumNumsTo(5));
        System.out.println(getSum3DivisibleNumsTo(6));
    }

    public static int factorial(int n) {
        if (n <= 1)
            return 1;

        return n * factorial(n - 1);
    }

    public static int multiply(int a, int b) {
        if (b <= 1)
            return a;

        return a + multiply(a, b - 1);
    }

    public static int power(int a, int b) {
        if (b <= 1)
            return a;

        return a * multiply(a, b - 1);
    }

    /**
     * @param a Non-negative. Divided by b.
     * @param b Positive. Divides a.
     * @return a / b.
     */
    public static int divide(int a, int b) {
        if (a < b)
            return 0;

        return 1 + divide(a - b, b);
    }

    public static int modulo(int a, int b) {
        // Input check (relevant only at the first call).
        if (!(a > 0 && b > 0))
            return 0;
        if (a < b)
            return a;

        // Calculation.
        if (a - b < b)
            return a - b;

        return modulo(a - b, b);
    }

    public static int getSumNumsTo(int n) {
        if (n == 0)
            return 0;

        return n + getSumNumsTo(n - 1);
    }

    public static int getSum3DivisibleNumsTo(int n) {
        if (n == 0)
            return 0;

        if (n % 3 == 0)
            return n + getSum3DivisibleNumsTo(n - 1);
        return getSum3DivisibleNumsTo(n - 1);
    }
}
