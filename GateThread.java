import java.util.List;

public class GateThread extends Thread {
    private final List<CarThread> cars;

    public GateThread(List<CarThread> cars) {
        this.cars = cars;
    }

    @Override
    public void run() {

        for (CarThread car : cars) {
            // Start each car thread
            car.start();
        }
        for (CarThread car : cars) {
            try {
                // Wait for all cars to finish
                car.join();
            } catch (InterruptedException e) {
                System.err.println("Gate " + car.getGateId() + " encountered an error.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
