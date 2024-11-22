import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingSystem {

    private final int spots;
    private final implementSemaphore semaphoreSpots;
    public static final BlockingQueue<CarThread> queue = new LinkedBlockingQueue<>();
    private final AtomicInteger currentCarsInSpots = new AtomicInteger(0);

    public ParkingSystem(int spots) {
        this.spots = spots;
        this.semaphoreSpots = new implementSemaphore(spots);
    }

    public int getCurrentCars() {
        return currentCarsInSpots.get();
    }

    // Log the current parking status
    private synchronized void logParkingStatus(String message) {
        System.out.println(message + " (Parking Status: " + currentCarsInSpots.get() + " spots occupied)");
    }

    public void park(CarThread car) throws InterruptedException {
        // Wait for an available parking spot
        semaphoreSpots.acquire();

        // Update the parking status
        currentCarsInSpots.incrementAndGet();

        long waitTime = (System.currentTimeMillis() - car.getQueueWaitStartTime()) / 1000L;

        // Log the parking status
        if (waitTime > 0) {
            logParkingStatus("Car " + car.getCarId() + " from Gate " + car.getGateId() +
                    " parked after waiting for " + waitTime + " units of time.");
        } else {
            logParkingStatus("Car " + car.getCarId() + " from Gate " + car.getGateId() + " parked.");
        }

        // Set the parking start time
        car.setParkingStartTime(System.currentTimeMillis());
    }

    public void leave(CarThread car) {
        // Decrease the number of cars in spots
        currentCarsInSpots.decrementAndGet();

        long parkedDuration = (System.currentTimeMillis() - car.getParkingStartTime()) / 1000L;

        // Log the leaving status
        logParkingStatus("Car " + car.getCarId() + " from Gate " + car.getGateId() +
                " left after " + parkedDuration + " units of time.");

        // Release the parking spot
        semaphoreSpots.release();
    }
}
