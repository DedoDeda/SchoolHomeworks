package _22._09._14;

public class Student {

    private static int nextSerialNum;

    private int serialNum;

    private String name;

    private int classNum;

    private double gradeA;

    private double gradeB;

    private double annualGrade;

    public Student(String name, int classNum, double gradeA, double gradeB) {
        serialNum = nextSerialNum;
        nextSerialNum++;

        this.name = name;
        this.classNum = classNum;
        this.gradeA = gradeA;
        this.gradeB = gradeB;

        annualGrade = (gradeA + gradeB) / 2.F;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public double getGradeA() {
        return gradeA;
    }

    public void setGradeA(int gradeA) {
        this.gradeA = gradeA;
    }

    public double getGradeB() {
        return gradeB;
    }

    public void setGradeB(int gradeB) {
        this.gradeB = gradeB;
    }

    public double getAnnualGrade() {
        return annualGrade;
    }

    public void setAnnualGrade(int annualGrade) {
        this.annualGrade = annualGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "serialNum=" + serialNum +
                ", name='" + name + '\'' +
                ", classNum=" + classNum +
                ", gradeA=" + gradeA +
                ", gradeB=" + gradeB +
                ", annualGrade=" + annualGrade +
                '}';
    }
}
