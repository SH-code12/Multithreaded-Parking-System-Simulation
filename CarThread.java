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

    public long getArriveTimeMillis() {
        return arriveTimeMillis  ;
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
            boolean f =false;
            synchronized (parkingSystem) {
                System.out.println("Car " + carId + " from Gate " + gateId + " arrived at time " + arriveTime);
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


        } catch (InterruptedException e) {
            System.out.println("Car " + carId + " Cause an error occured.");
            e.printStackTrace();

        }


    }


}
