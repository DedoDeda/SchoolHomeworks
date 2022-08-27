package D_14_05;

public class Recursion {
    public static void main(String[] args) {
        line1(5);
        System.out.println();
        line2(5);
        System.out.println();
        line2A(5);
        System.out.println();
        line3(5);
        System.out.println();
        line4(5, 1);
        System.out.println();
        line5(5, '*');
        System.out.println();
        line6(4);
        System.out.println();
        line7(6);
        System.out.println();
        line8(5);
        System.out.println();
        line8A(4);
        System.out.println();
        System.out.println();

        System.out.println("-------------------------------");

        System.out.println();
        triangleUp(6);
        System.out.println();
        triangleDown(6);
        System.out.println();
        triangleDownUp(6);
        System.out.println();
        triangleUpDown(6);
        System.out.println();
        sandClock(7, 0);
    }

    public static void line1(int n) {
        System.out.print((6 - n) + " ");

        if (n == 2) {
            System.out.print(5);
        } else {
            line1(n - 1);
        }
    }

    public static void line2(int n) {
        System.out.print(n + " ");

        if (n == 1) {
            System.out.print(1 + " ");
        } else {
            line2(n - 1);
            System.out.print(n + " ");
        }
    }

    public static void line2A(int n) {
        System.out.print(n + " ");

        if (n != 1) {
            line2A(n - 1);
            System.out.print(n + " ");
        }
    }

    public static void line3(int n) {
        System.out.print((6 - n) + " ");

        if (n == 1) {
            System.out.print(5 + " ");
        } else {
            line3(n - 1);
            System.out.print((6 - n) + " ");
        }
    }

    public static void line4(int n, int i) {
        System.out.print(n + " ");

        if (i == n - 1) {
            System.out.print(n);
        } else {
            line4(n, i + 1);
        }
    }

    public static void line5(int n, char c) {
        System.out.print(c + " ");

        if (n == 2) {
            System.out.print(c);
        } else {
            line5(n - 1, c);
        }
    }

    public static void line6(int n) {
        System.out.print(n + " ");

        if (n == 1) {
            System.out.print(2 + " ");
        } else {
            line6(n - 1);
            System.out.print((2 * n) + " ");
        }
    }

    public static void line7(int n) {
        if (n % 2 == 0) {
            System.out.print(-n + " ");
        } else {
            System.out.print(n + " ");
        }

        if (n == 2) {
            System.out.print(1);
        } else {
            line7(n - 1);
        }
    }

    public static void line8(int n) {
        if (n % 2 == 0) {
            System.out.print(n + " ");
        } else {
            System.out.print((10 + n) + " ");
        }

        if (n == 1) {
            System.out.print(0);
        } else {
            line8(n - 1);
        }
    }

    public static void line8A(int n) {
        String s = n % 2 == 0 ? (n + " ") : ((10 + n) + " ");
        System.out.print(s);

        if (n == 1) {
            System.out.print(0 + " ");
            System.out.print(11 + " ");
        } else {
            line8A(n - 1);
            System.out.print(s);
        }
    }

    public static void line(int n) {
        if (n == 0)
            System.out.println();
        else {
            System.out.print(n + " ");
            line(n - 1);
        }
    }

    public static void triangleUp(int n) {
        line(7 - n);

        if (n == 2) {
            line(8 - n);
        } else {
            triangleUp(n - 1);
        }
    }

    public static void triangleDown(int n) {
        line(n);

        if (n == 2) {
            line(n - 1);
        } else {
            triangleDown(n - 1);
        }
    }

    public static void triangleDownUp(int n) {
        line(n);

        if (n == 2) {
            line(n - 1);
            line(2);
        } else {
            triangleDownUp(n - 1);
            line(n);
        }
    }

    public static void triangleUpDown(int n) {
        line(7 - n);

        if (n == 2) {
            line(8 - n);
            line(5);
        } else {
            triangleUpDown(n - 1);
            line(7 - n);
        }
    }

    public static void simpleLine(int n, int character, int numSpaces) {
        System.out.print(" ".repeat(numSpaces));
        for (int i = 0; i < n; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

    public static void sandClock(int n, int i) {
        simpleLine(n, n, i);

        if (n == 0 || n == 1) {
            simpleLine(1, n, i);
        } else {
            sandClock(n - 2, i + 1);
            simpleLine(n, n, i);
        }
    }
}
