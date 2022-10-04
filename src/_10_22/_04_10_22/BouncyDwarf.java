package _10_22._04_10_22;

public class BouncyDwarf extends Dwarf {

    protected int jumpHeight;

    public BouncyDwarf(String name, boolean female, int height, int jumpHeight) {
        super(name, female, height);
        this.jumpHeight = jumpHeight;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    @Override
    public String toString() {
        return "BouncyDwarf{" +
                "jumpHeight=" + jumpHeight +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", female=" + female +
                '}';
    }
}
