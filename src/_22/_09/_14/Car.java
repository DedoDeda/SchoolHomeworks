package _22._09._14;

public class Car {

    private static int nextID;

    private int ID;

    private String model;

    private int engineCapacity;

    private int year;

    private int mileage;

    public Car(String model, int engineCapacity, int year, int mileage) {
        ID = nextID;
        nextID++;

        this.model = model;
        this.engineCapacity = engineCapacity;
        this.year = year;
        this.mileage = mileage;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID=" + ID +
                ", model='" + model + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", year=" + year +
                ", mileage=" + mileage +
                '}';
    }
}
