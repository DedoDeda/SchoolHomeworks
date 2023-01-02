package _22._12._15;

import java.util.Arrays;

public class AirportInfo {

    public static <T> void sortTransport(Comparable<T>[] transport) {
        Arrays.sort(transport);
    }

    public static String reportAll(Movable[] movables) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < movables.length - 1; i++) {
            Movable m = movables[i];
            builder.append("Movable type: ").append(m.getType()).append('\n');
            builder.append(m.details()).append('\n');
            builder.append("Source: ").append(m.getSource()).append('\n');
            builder.append("Destination: ").append(m.getDestination()).append('\n');
            builder.append('\n');
        }

        builder.append("Movable type: ").append(movables[movables.length - 1].getType()).append('\n');
        builder.append(movables[movables.length - 1].details()).append('\n');
        builder.append("Source: ").append(movables[movables.length - 1].getSource()).append('\n');
        builder.append("Destination: ").append(movables[movables.length - 1].getDestination()).append('\n');

        return builder.toString();
    }

    public static void moveAll(Movable[] movables) {
        for (Movable m : movables) {
            m.move();
        }
    }
}
