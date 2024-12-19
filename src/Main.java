import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(2, 10); // 2 лифта, 10 этажей
        RequestGenerator.setController(controller);
        controller.start();
    }
}
