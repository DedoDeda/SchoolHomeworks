package _22._09._15;

import Reusable.Vec2;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        task1();
        task2(new int[]{1, 3, 5, 6, 93, 101, 102});
        task3_a(new Vec2(3, 4));
        task3_b();
    }

    public static void task1() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("n=");
        int n = scanner.nextInt();

        int[] arr = makeRandomArray(n, 1, 101, random);
        System.out.println(Arrays.toString(arr));
        bubbleSortAsc(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] makeRandomArray(int size, int min, int max, Random random) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = random.nextInt(min, max);
        }

        return res;
    }

    public static int[] makeRandomArray(int size, int min, int max) {
        Random random = new Random();

        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = random.nextInt(min, max);
        }

        return res;
    }

    public static void bubbleSortAsc(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void task2(int[] arr) {
        System.out.println(Arrays.toString(arr));
        reverse(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void reverse(int[] arr) {
        for (int i = 0; i < (arr.length / 2); i++) {
            final int symmetricIndex = arr.length - i - 1;
            swap(arr, i, symmetricIndex);
        }
    }

    public static void task3_a(Vec2 point) {
        System.out.println("Distance: " + point.size());
    }

    public static void task3_b() {
        Random random = new Random();

        Vec2[] points = new Vec2[10];
        for (int i = 0; i < points.length; i++) {
            points[i] = Vec2.makeRandom(2, 15, random);
        }
        System.out.println(Arrays.toString(points));
        selectionSortAsc(points);
        System.out.println(Arrays.toString(points));
    }

    public static void selectionSortAsc(Vec2[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int indexOfMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].size() < arr[indexOfMin].size()) {
                    indexOfMin = j;
                }
            }
            swap(arr, i, indexOfMin);
        }
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
