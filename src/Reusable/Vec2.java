package Reusable;

import java.util.Random;

public class Vec2 {

    public double x;
    public double y;

    public static Vec2 makeRandom(int min, int max, Random random) {
        return new Vec2(random.nextInt(min, max), random.nextInt(min, max));
    }

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double size() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
