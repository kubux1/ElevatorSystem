import java.util.ArrayList;
import java.util.List;

public class Elevator {
    // Elevator directions
    static final byte UP = 1;
    static final byte IDLE = 0;
    static final byte DOWN = -1;

    private int elevatorId;
    private int currentFloor;
    private byte direction;
    private List<Request> requests = new ArrayList<Request>();

    // Unique ID generator for each new elevator in the system
    private static int id = 0;

    public Elevator() {
    this.elevatorId = id;
    this.currentFloor = 0;
    this.direction = 0;
    id++;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void move() {
        this.currentFloor += this.direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(){
        // If there are not requests to handle then go IDLE
        if(!requests.isEmpty()) {
            // First request in the list is the one which sets a direction
            Request req = requests.get(0);
            // Either we set direction to pick up passenger or go to direction choosed by passenger when he is picked up
            if(!req.isBeingHandled()) {
                if (currentFloor < req.getCurrentFloor()) {
                    direction = UP;
                } else if (currentFloor > req.getCurrentFloor()) {
                    direction = DOWN;
                }
            } else {
                if (currentFloor < req.getTargetFloor()) {
                    direction = UP;
                } else if (currentFloor > req.getTargetFloor()) {
                    direction = DOWN;
                }
            }
        } else
            // If there are not requests to handle then go idle
            direction = IDLE;
    }

    public List<Request> getRequests() {
        return this.requests;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public boolean isThisFirstReq() {
        if(this.requests.size() == 1) {
            return true;
        } else {
            return false;
        }
    }
    // We either pick up a passenger or let him out on a desired floor
    public void handleRequests() {
        List<Request> requestsFinished = new ArrayList<>();

        for(Request request : this.requests) {
            // Check if a passenger is in the elevator and if we are on his target floor
            if((request.isBeingHandled()) && (this.currentFloor == request.getTargetFloor())) {
                requestsFinished.add(request);
            }
            // Check if a passenger is still not in an elevator and if we are on his current floor to pick him up
            if((!request.isBeingHandled()) && (this.currentFloor == request.getCurrentFloor())) {
                request.setIfIsBeingHandled(true);
            }
        }
        // Delete finished requests
        this.requests.removeAll(requestsFinished);
        // Update elevator direction if needed
        setDirection();
    }

}
