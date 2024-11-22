// Shahd Elnassag ^_^
// Car is a Thread
public class CarThread extends Thread{
    private final int carId;
    private final int arriveTime;
    private final int durationTime;
    private final int gateId;
    private final ParkingSystem parkingSystem;

    private long parkingTime;
    private long arriveTimeMillis;
    private long queueWaitStart;

    // Constructor
    public CarThread(int gateId, int carId, int arriveTime, int durationTime, ParkingSystem parkingSystem) {
        this.carId = carId;
        this.arriveTime = arriveTime;
        this.durationTime = durationTime;
        this.gateId = gateId;
        this.parkingSystem = parkingSystem;
        this.arriveTimeMillis = System.currentTimeMillis() + (arriveTime * 1000L);
    }

    public int getCarId() {
        return carId;
    }

    public int getGateId() {
        return gateId;
    }
    public long getParkingTime() {
        return parkingTime;
    }


    public long getQueueWaitStart() {
        return queueWaitStart;
    }


    public void setParkingTime(long parkingTime) {
        this.parkingTime = parkingTime;
    }


    @Override
    public void run(){

        try {
            long currentTime = System.currentTimeMillis();
            long waitTime = arriveTimeMillis - currentTime;
            if (waitTime > 0) {
                // Sleep for the remaining time until car arrives

                Thread.sleep(waitTime);
            }
            System.out.println("Car " + carId + " from Gate " + gateId + " arrived at time " + arriveTime);
            // Record when the car starts waiting in the queue
            this.queueWaitStart = System.currentTimeMillis();
            // Add to the queue
//            ParkingSystem.queue.add(this);
            boolean f =false;
            synchronized (parkingSystem) {
                System.out.println("Car " + carId + " from Gate " + gateId + " arrived at time " + arriveTime);

                this.queueWaitStart = System.currentTimeMillis();
                ParkingSystem.queue.add(this);
                if (parkingSystem.isFull()) {
                    System.out.println("Car " + carId + " from Gate " + gateId + " waiting for a spot.");
                    f = true;
                }
                else
                    parkingSystem.park(this);
            }
            if(f)parkingSystem.park(this);
    parkingTime = System.currentTimeMillis();
    Thread.sleep(durationTime * 1000L);

        parkingSystem.leave(this);


                // Wait if parking is full
                while (parkingSystem.isFull()) {
                    System.out.println("Car " + carId + " from Gate " + gateId + " waiting for a spot.");
                    parkingSystem.wait();
                }
                // Park the car
                parkingSystem.park(this);
                parkingTime = System.currentTimeMillis();
            }

            // Car stays parked for the specified duration
            Thread.sleep(durationTime * 1000L);

            // Car leaves the parking
            synchronized (parkingSystem) {
                parkingSystem.leave(this);
//                System.out.println("Car " + carId + " left the parking.");
                parkingSystem.notifyAll();
            }


        } catch (InterruptedException e) {
            System.out.println("Car " + carId + " Cause an error occured.");
            e.printStackTrace();

        }


    }


}
