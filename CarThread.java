public class CarThread extends Thread {
    private final int carId;
    private final int gateId;
    private final int arrivalTime;
    private final int parkingDuration;
    private final ParkingSystem parkingSystem;

    private long queueWaitStartTime;
    private long parkingStartTime;

    public CarThread(int gateId, int carId, int arrivalTime, int parkingDuration, ParkingSystem parkingSystem) {
        this.carId = carId;
        this.gateId = gateId;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingSystem = parkingSystem;
    }

    public int getCarId() {
        return carId;
    }

    public int getGateId() {
        return gateId;
    }

    public void setParkingStartTime(long parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public long getParkingStartTime() {
        return parkingStartTime;
    }

    public long getQueueWaitStartTime() {
        return queueWaitStartTime;
    }

    @Override
    public void run() {
        try {
            // Simulate car arrival time
            Thread.sleep(arrivalTime * 1000L);
            System.out.println("Car " + carId + " from Gate " + gateId + " arrived at time " + arrivalTime);

            // Record the actual queue wait start time after arrival
            queueWaitStartTime = System.currentTimeMillis();

            // Add car to the queue
            parkingSystem.queue.put(this);

            // Attempt to park once the car gets a spot
            parkingSystem.park(this);


            // Simulate parking duration
            Thread.sleep(parkingDuration * 1000L);

            // Leave the parking spot
            parkingSystem.leave(this);

        } catch (InterruptedException e) {
            System.err.println("Car " + carId + " from Gate " + gateId + " case an error.");
            Thread.currentThread().interrupt();
        }
    }
}
