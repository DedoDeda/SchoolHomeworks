package _22._12._15;

import java.util.Arrays;

public class AirportInfoTest {

    public static void main(String[] args) {
        // Make the arrays.
        Train[] trainTransport = {
                new Train("1", "Modi'in", "Tel-Aviv Hashalom", 8000),
                new Train("2", "Tel-Aviv Hashalom", "Nhariya", 7000),
                new Train("3", "Tel-Aviv Hashalom", "Nhariya", 5000),
                new Train("3", "Eilat", "Nhariya", 5000),
                new Train("3", "Tel-Aviv Hashalom", "Modi'in", 9000),
        };
        Airplane[] airplaneTransport = {
                new Airplane("1", "Tel-Aviv", "Paris", 1800),
                new Airplane("1", "Paris", "Tel-Aviv", 1600),
                new Airplane("1", "NYC", "Turkey", 1640),
                new Airplane("1", "Normandy", "London", 1200),

        };

        // Test before moving.
        testTransportSortAndReport(trainTransport, airplaneTransport);
        // Move.
        AirportInfo.moveAll(trainTransport);
        AirportInfo.moveAll(airplaneTransport);
        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.println();
        // Test after moving.
        testTransportSortAndReport(trainTransport, airplaneTransport);
    }

    private static void testTransportSortAndReport(Train[] trainTransport, Airplane[] airplaneTransport) {
        // Train transport sort test.
        System.out.println(Arrays.toString(trainTransport));
        AirportInfo.sortTransport(trainTransport);
        System.out.println(Arrays.toString(trainTransport));

        System.out.println();

        // Airplane transport sort test.
        System.out.println(Arrays.toString(airplaneTransport));
        AirportInfo.sortTransport(airplaneTransport);
        System.out.println(Arrays.toString(airplaneTransport));

        // Report test.
        System.out.println();
        System.out.println(AirportInfo.reportAll(trainTransport));
        System.out.println(AirportInfo.reportAll(airplaneTransport));
    }
}
