package SummerAssignment.Chapter1;

import java.util.Arrays;
import java.util.Random;

public class Ex3 {

    public static void main(String[] args) {

    }

    public static class Pixel {
        int r, g, b;

        public static Pixel createRandom() {
            Random random = new Random();
            return new Pixel(random.nextInt(0, 256), random.nextInt(0, 256), random.nextInt(0, 256));
        }

         public Pixel(int r, int g, int b) {
            if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                throw new IllegalArgumentException("Out of range RGB value(s).");
            }

            this.r = r;
            this.g = g;
            this.b = b;
        }

        public boolean isRed() {
            return r > 0 && g == 0 && b == 0;
        }

        public boolean isGreen() {
            return g > 0 && r == 0 && b == 0;
        }

        public boolean isBlue() {
            return b > 0 && r == 0 && g == 0;
        }

        public boolean isBlack() {
            return r == 0 && g == 0 && b == 0;
        }

        public boolean isWhite() {
            return r == 255 && g == 255 && b == 255;
        }

        @Override
        public String toString() {
            return "Pixel{" +
                    "r=" + r +
                    ", g=" + g +
                    ", b=" + b +
                    '}';
        }
    }

    public static class Structure {
        Pixel[] pixels;

        public static Structure createRandom(int size) {
            Pixel[] pixels = new Pixel[size];
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = Pixel.createRandom();
            }
            return new Structure(pixels);
        }

        public Structure(Pixel... pixels) {
            this.pixels = pixels;
        }

        public boolean isBalanced() {
            int rCount = 0;
            int gCount = 0;
            int bCount = 0;

            for (Pixel pixel : pixels) {
                if (pixel.isRed()) {
                    rCount++;
                }
                else if (pixel.isGreen()) {
                    gCount++;
                }
                else if (pixel.isBlue()) {
                    bCount++;
                }
            }

            return rCount == gCount && rCount == bCount;
        }

        public boolean isBlackAndWhite() {
            boolean hasBlack = false;
            boolean hasWhite = false;

            for (Pixel pixel : pixels) {

                if (pixel.isBlack()) {
                    hasBlack = true;
                }
                else if (pixel.isWhite()) {
                    hasWhite = true;
                }
                else {
                    return false;
                }
            }

            return hasBlack && hasWhite;
        }

        @Override
        public String toString() {
            return "Structure{ pixels = " + Arrays.toString(pixels) + " }";
        }
    }
}
