public class Request {
    private final int floor;
    private final int targetFloor;

    public Request(int floor, int targetFloor) {
        this.floor = floor;
        this.targetFloor = targetFloor;
    }

    public int getFloor() {
        return floor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
