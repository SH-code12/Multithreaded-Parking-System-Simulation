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

        // another try not sure
        try {
            Thread.sleep(arriveTime);
            if(!parkingSystem.isFull()){
                parkingSystem.park(this);

            }
        } catch (InterruptedException e) {
            System.out.println("Car " + carId + " Cause an error occured.");
            throw new RuntimeException(e);

        }


    }


}
