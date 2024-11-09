import java.util.List;

// Gate is a Thread
public class GateThread extends Thread {

    private List<CarThread> cars;
    private ParkingSystem parkingSystem;

    // Constructor
    public GateThread(List<CarThread> cars, ParkingSystem parkingSystem) {
        this.cars = cars;
        this.parkingSystem = parkingSystem;
    }

    public void run() {
        System.out.println("Will add GateThread Implementation Soon God Willing ^_^ ");
        // try not sure at all
        for (CarThread car : cars) {
            // start execution
            car.start();
        }

    }

}

