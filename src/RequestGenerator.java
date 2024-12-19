import java.util.Random;

public class RequestGenerator {
    private static final Random random = new Random();
    private static ElevatorController controller;

    public static void setController(ElevatorController elevatorController) {
        controller = elevatorController;
    }

    public static void generateRandomRequest() {
        if (controller != null) {
            int floor = random.nextInt(controller.getMaxFloor()) + 1;
            int targetFloor = random.nextInt(controller.getMaxFloor()) + 1;
            Request request = new Request(floor, targetFloor);
            controller.assignRequest(request);
        }
    }
}
