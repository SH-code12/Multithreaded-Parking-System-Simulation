// Shahd Elnassag ^_^

import java.util.concurrent.Semaphore;

public class ParkingSystem {

    private final int  spots;
    private final Semaphore semaphoreSpots;
    private int currentCarsInSpots = 0;

    public ParkingSystem(int spots) {
        this.spots = spots;
        // intialize
        this.semaphoreSpots = new Semaphore(spots);
    }

    public int getCurrentCars(){
        return currentCarsInSpots;
    }

    // check if there is spots free
    public boolean isFull(){
        return semaphoreSpots.availablePermits() == 0;
    }

    public void park(CarThread car) throws InterruptedException {
        long waitTime = System.currentTimeMillis() - car.getArriveTimeMillis();
        semaphoreSpots.acquire();
        currentCarsInSpots++;
        car.setParkingTime(System.currentTimeMillis());
        System.out.println("Car " + car.getCarId() + " from Gate " + car.getGateId() +
                " parked after waiting for " + (waitTime/1000)+
                " units of time. (Parking Status: " + currentCarsInSpots + " spots occupied)");
    }

    public void leave(CarThread car) {
        long parkDuration = (System.currentTimeMillis() - car.getParkingTime()) / 1000;
        semaphoreSpots.release();
        currentCarsInSpots--;
        System.out.println("Car " + car.getCarId() + " from Gate " + car.getGateId() + " left after " +parkDuration+
                " units of time. (Parking Status: " + currentCarsInSpots + " spots occupied)");
    }


}
