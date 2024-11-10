// Shahd Elnassag ^_^

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;



public class ParkingSystem {

    private final int  spots;
    private final Semaphore semaphoreSpots;
    public static final BlockingQueue<CarThread> queue = new LinkedBlockingQueue<>();
    private volatile int currentCarsInSpots = 0;

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
        car = queue.take();

        long waitTime = (System.currentTimeMillis() - car.getQueueWaitStart()) / 1000L;

        semaphoreSpots.acquire();

        currentCarsInSpots++;

        car.setParkingTime(System.currentTimeMillis());

        System.out.println("Car " + car.getCarId() + " from Gate " + car.getGateId() +
                    " parked after waiting for " + waitTime +
                    " units of time. (Parking Status: " + currentCarsInSpots + " spots occupied)");


    }

    public void leave(CarThread car)  {
        long parkDuration = (System.currentTimeMillis() - car.getParkingTime()) / 1000L;

        currentCarsInSpots--;

        System.out.println("Car " + car.getCarId() + " from Gate " + car.getGateId() + " left after " + parkDuration +
                        " units of time. (Parking Status: " + currentCarsInSpots + " spots occupied)");
        semaphoreSpots.release();

    }


}
