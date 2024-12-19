import java.util.*;
import java.util.concurrent.*;

public class ElevatorController {
    private final List<Elevator> elevators = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final int maxFloor;

    public ElevatorController(int elevatorCount, int maxFloor) {
        this.maxFloor = maxFloor;
        ElevatorGUI gui = new ElevatorGUI(elevatorCount);
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i + 1, gui));
        }
    }

    public void start() {
        for (Elevator elevator : elevators) {
            executorService.execute(elevator);
        }
        generateRequests();
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void assignRequest(Request request) {
        Elevator bestElevator = elevators.stream()
                .filter(e -> e.isMovingTowards(request.getFloor()))
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - request.getFloor())))
                .orElseGet(() -> elevators.stream()
                        .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - request.getFloor())))
                        .orElse(null));

        if (bestElevator != null) {
            bestElevator.addRequest(request);
        }
    }

    private void generateRequests() {
        new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(1000);
                    int floor = random.nextInt(maxFloor) + 1;
                    int targetFloor = random.nextInt(maxFloor) + 1;
                    Request request = new Request(floor, targetFloor);
                    assignRequest(request);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
