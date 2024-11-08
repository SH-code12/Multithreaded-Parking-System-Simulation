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
        // car wait to park
        semaphoreSpots.acquire();

        System.out.println("Car "+ car.getCarId()+
                " from Gate "+car.getGateId()+
                " parked. (Parking Status: " + (spots - semaphoreSpots.availablePermits())+
                "spots occupied)");

        Thread.sleep(car.getDurationTime());

        System.out.println("Car "+ car.getCarId()+
                " from Gate "+car.getGateId()+
                " left. (Parking Status: " + (spots - semaphoreSpots.availablePermits())+
                "spots occupied)");

        // method to return permits after exiting the critical section
        semaphoreSpots.release();

    }




}
