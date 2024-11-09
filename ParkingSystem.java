import java.util.concurrent.Semaphore;

public class ParkingSystem {

    private int spots;
    private Semaphore semaphoreSpots;

    public ParkingSystem(int spots) {
        this.spots = spots;
        // intialize
        this.semaphoreSpots = new Semaphore(spots);
    }

    // check if there is spots free
    public boolean isFull(){
        return semaphoreSpots.availablePermits() == 0;
    }

    public void park(CarThread car) throws InterruptedException {
        // Log arrival
        System.out.println("Car " + car.getCarId() +
                " from Gate " + car.getGateId() +
                " arrived at time " + car.getArriveTime());

        if (isFull()) {
            System.out.println("Car " + car.getCarId() +
                    " from Gate " + car.getGateId() +
                    " waiting for a spot.");
        }

        // Acquire a parking spot
        semaphoreSpots.acquire();

        // Log parking status after parking
        System.out.println("Car " + car.getCarId() +
                " from Gate " + car.getGateId() +
                " parked. (Parking Status: " + (spots - semaphoreSpots.availablePermits()) +
                " spots occupied)");

        // Car stays in parking
        Thread.sleep(car.getDurationTime());

        // Log exit  and update status
        System.out.println("Car " + car.getCarId() +
                " from Gate " + car.getGateId() +
                " left after " + car.getDurationTime() + " units of time. (Parking Status: " +
                (spots - semaphoreSpots.availablePermits() - 1) + " spots occupied)");

        // Release the parking spot
        semaphoreSpots.release();
    }





}
