package _22._12._15;

public class Train implements Movable, Comparable<Train> {

    String id;
    String source;
    String destination;
    int maxPassengers;

    public Train(String id, String source, String destination, int maxPassengers) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.maxPassengers = maxPassengers;
    }

    @Override
    public int compareTo(Train o) {
        return maxPassengers - o.maxPassengers;
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
        return "Train";
    }

    @Override
    public String details() {
        return String.format("Train ID: %s\nPassengers: %s", id, maxPassengers);
    }

    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", maxPassengers=" + maxPassengers +
                '}';
    }
}
