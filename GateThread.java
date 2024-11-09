// Shahd Elnassag ^_^
// Gate is a Thread
import java.util.List;

// Gate is a Thread
public class GateThread extends Thread {

    private List<CarThread> cars;

    // Constructor
    public GateThread(List<CarThread> cars) {
        this.cars = cars;
    }

    public void run() {
        // start execution
        for (CarThread car : cars) {
            // start execution
            car.start();
        }
        // wait till end list of cars
        for (CarThread car : cars) {
            // start execution
            try {
                car.join();
            } catch (InterruptedException e) {
                System.out.println("Error with run in GateThread class ");
                throw new RuntimeException(e);
            }
        }

    }

}

