package _22._12._15;

import java.util.*;

/**
 * This class tests classes Airplane,Train and AirportInfo.
 * In this class we create an array of trains (trainList)
 * and an array of planes (planeList). We initialize the array
 * with trains and planes that are created randomly.
 * We sort the trains by the number of passengers in each train.
 * We sort the planes by their altitude (lowest to highest)
 * We print the reports of the trains and of the planes.
 */
public class AirportInfoTest_External {

    static final String[] SOURCES = {
            "Jerusalem", "Paris", "New-York","London", "Roma"};
    static final String[] DESTINATIONS = {
            "Barcelona", "Madrid", "Copenhagen", "Helsinki", "Bordeaux" };

    static final int TOTAL = SOURCES.length;

    public static void main(String args[]) {
        Train[] trainList=new Train[5];
        Airplane[] planeList=new Airplane[5];
        Random r=new Random();
        //Initializes array of 10 trains 
        for (int i=0; i<trainList.length; i++) {
            trainList[i]=new Train(String.valueOf(r.nextInt(500)), SOURCES[r.nextInt(TOTAL)], DESTINATIONS[r.nextInt(TOTAL)],i*20+r.nextInt(20*(i+1)));
        }

        System.out.println("---Sorting trains...---\n");

        //sort trains
        AirportInfo.sortTransport(trainList);



        //prints the reports of all the trains	
        System.out.print(AirportInfo.reportAll(trainList));

        //Initializes array of 10 planes
        for (int i=0; i<planeList.length; i++)
            planeList[i]=new Airplane(String.valueOf(r.nextInt(1500)), SOURCES[r.nextInt(TOTAL)], DESTINATIONS[r.nextInt(TOTAL)], i*10+r.nextInt(10*(i+1)));

        System.out.println("---Sorting planes...---\n");

        //sort trains
        AirportInfo.sortTransport(planeList);

        //prints the reports of all the plains	
        System.out.print(AirportInfo.reportAll(planeList));


        //Creates an array of Movable objects
        Movable[] m=new Movable[trainList.length*2];

        //initializes an array of Movable objects (trains and planes) 
        for (int i=0; i<m.length; i+=2) {
            m[i]=trainList[i/2];
            m[i+1]=planeList[i/2];
        }

        System.out.println("---All together...---\n");
        //prints the reports of all the trains and planes
        System.out.print(AirportInfo.reportAll(m));

        //cause all the planes and trains to move
        for (int i=0; i<m.length; i++) {
            m[i].move();
        }

        System.out.println("---All together after move...---\n");
        //prints the reports of all the trains and planes after move
        System.out.print(AirportInfo.reportAll(m));
    }
}//close class