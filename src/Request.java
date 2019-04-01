public class Request {
     private int currentFloor;
     private int targetFloor;
     private int direction;
     // False - passenger called an elevator and is waiting on his floor for the elevator
     // True - passenger is in the elevator and is waiting to be dropped on his target floor
     private boolean isBeingHandled;


    public Request(int currentFloor, int targetFloor, int direction) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        this.direction = direction;
        this.isBeingHandled = false;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isBeingHandled() {
        return isBeingHandled;
    }

    public void setIfIsBeingHandled(boolean handled) {
        isBeingHandled = handled;
    }

}
