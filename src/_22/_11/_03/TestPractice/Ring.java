package _22._11._03.TestPractice;

public class Ring {

    private String size;
    private int color;

    public Ring() {
        size = "L";
        color = 0;
    }

    public Ring(String size, int color) {
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Ring{" +
                "size='" + size + '\'' +
                ", color=" + color +
                '}';
    }
}
