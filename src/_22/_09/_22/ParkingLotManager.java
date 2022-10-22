package _22._09._22;

import unit4.collectionsLib.Stack;

import java.util.Scanner;

public class ParkingLotManager {

    private static Scanner scanner = new Scanner(System.in);

    private Stack<Integer> cars;
    private int numCars;



    public ParkingLotManager() {
        cars = new Stack<>();
        numCars = 0;
    }

    public void mainLoop() {

        while (true) {

            System.out.println("Action:");
            char action = scanner.next().charAt(0);
            if (action != 'A' && action != 'D') {
                continue;
            }

            System.out.println("Car number: ");
            int carNum = scanner.nextInt();

            if (action == 'A') {
                addCar(carNum);
            }
            else { // action is 'D'
                delCar(carNum);
            }

        }
    }

    public void addCar(int carNum) {
        if (numCars < 10) {
            cars.push(carNum);
            System.out.println("Successfully added car " + carNum + "!");
        }
        else {
            System.out.println("Parking lot is full! Cannot add!");
        }
    }

    public void delCar(int targetCarNum) {
        Stack<Integer> temp = new Stack<>();
        while (!cars.isEmpty()) {
            int carNum = cars.pop();
            if (carNum == targetCarNum) {
                System.out.println("Successfully deleted car " + targetCarNum + "!");
                numCars--;
                break;
            }

            temp.push(carNum);
        }

        while (!temp.isEmpty()) {
            cars.push(temp.pop());
        }


    }

}
