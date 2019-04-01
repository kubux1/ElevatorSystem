public class ElevatorStatus {
    private  int elevatorId;
    private int currentFloor;
    private int targetFloor;

    public ElevatorStatus(int elevatorId, int currentFloor, int targetFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
    }

}
