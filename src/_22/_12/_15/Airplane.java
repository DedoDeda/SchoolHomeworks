package _22._12._15;

public class Airplane implements  Comparable<Airplane>, Movable {

    String id;
    String source;
    String destination;
    int maxAltitude;

    public Airplane(String id, String source, String destination, int maxAltitude) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public int compareTo(Airplane o) {
        return maxAltitude - o.maxAltitude;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public void move() {
        String temp = source;
        source = destination;
        destination = temp;
    }

    @Override
    public String getType() {
        return "Airplane";
    }

    @Override
    public String details() {
        return String.format("Airplane ID: %s\nAltitude: %s", id, maxAltitude);
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", maxAltitude=" + maxAltitude +
                '}';
    }
}
