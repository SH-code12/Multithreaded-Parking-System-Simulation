// Shahd Elnassag ^_^

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Simulation {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Shahd Elnassag ^_^ ");
        System.out.println("This Assignment2 for operating system, Hello  \uD83D\uDE07");

        int noSpots = 4;
        int gateCount1 = 0;
        int gateCount2 = 0;
        int gateCount3 = 0;
        ParkingSystem parkingSystem = new ParkingSystem(noSpots);

        // list of cars at each gate
        List<CarThread> gateCars1 = new ArrayList<>();
        List<CarThread> gateCars2 = new ArrayList<>();
        List<CarThread> gateCars3 = new ArrayList<>();

        try {
            File inputFile = new File("input.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                String myData = myReader.nextLine();
                String [] argsData = myData.split(", ");

                int gateID = Integer.parseInt(argsData[0].split(" ")[1]);
                int carID = Integer.parseInt(argsData[1].split(" ")[1]);
                int arriveTime = Integer.parseInt(argsData[2].split(" ")[1]);
                int durationTime = Integer.parseInt(argsData[3].split(" ")[1]);

                CarThread car = new CarThread(gateID,carID,arriveTime,durationTime,parkingSystem);

                if(gateID == 1){
                    gateCars1.add(car);
                    gateCount1++;
                } else if (gateID == 2) {
                    gateCars2.add(car);
                    gateCount2++;

                } else if (gateID == 3) {
                    gateCars3.add(car);
                    gateCount3++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error in Simulation Class");
            e.printStackTrace();
        }

        GateThread Gate1 = new GateThread(gateCars1);
        GateThread Gate2 = new GateThread(gateCars2);
        GateThread Gate3 = new GateThread(gateCars3);

        Gate1.start();
        Gate2.start();
        Gate3.start();

        Gate1.join();
        Gate2.join();
        Gate3.join();

        System.out.println("Total Cars Served: "+ (gateCount1 +gateCount2 +gateCount3));
        System.out.println("Current Cars in Parking: " + parkingSystem.getCurrentCars());
        System.out.println("Details:");
        System.out.println("Gate 1 served "+ gateCount1 + " cars");
        System.out.println("Gate 2 served "+ gateCount2 + " cars");
        System.out.println("Gate 3 served "+gateCount3 + " cars");


    }
}
