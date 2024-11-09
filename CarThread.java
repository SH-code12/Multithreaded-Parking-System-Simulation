// Car is a Thread
public class CarThread extends Thread{
    private  int carId;
    private int arriveTime;
    private int durationTime;
    private int gateId;
    private ParkingSystem parkingSystem;

    // Constructor
    public CarThread(int gateId, int carId, int arriveTime, int durationTime, ParkingSystem parkingSystem) {
        this.carId = carId;
        this.arriveTime = arriveTime;
        this.durationTime = durationTime;
        this.gateId = gateId;
        this.parkingSystem = parkingSystem;
    }

    // Setter and getters

    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getArriveTime() {
        return arriveTime;
    }
    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getDurationTime() {
        return durationTime;
    }
    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public int getGateId() {
        return gateId;
    }
    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    @Override
    public void run(){

        System.out.println("Will add CarThread Implementation Soon God Willing ^_^ ");
        // another try not sure
        try {
            Thread.sleep(arriveTime);
            if(!parkingSystem.isFull()){
                parkingSystem.park(this);
                System.out.println("Car "+ carId + " from Gate "+ gateId +" arrived at time "+arriveTime);

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
