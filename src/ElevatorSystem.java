import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ElevatorSystem {

    // If elevator is not moving it has no target floor
    private static final byte NO_TARGET_FLOOR = -1;
    // Index of request in requests list which sets an elevator target floor
    private static final byte MASTER_REQUEST = 0;
    private static final int MAX_ELEV_NUMBER = 16;

    // List of elevators available in the system
    private List<Elevator> elevators = new ArrayList<Elevator>();
    // Requests to be assigned to the elevator
    private List<Request> awaitingRequests = new ArrayList<Request>();

    public static int totalSimTime = 0;

    public ElevatorSystem (int elevatorsQuantity) {
        if(elevatorsQuantity <= MAX_ELEV_NUMBER) {
            for (int i = 0; i < elevatorsQuantity; i++) {
                elevators.add(new Elevator());
            }
        } else {
            System.out.print("I am not an elevator system in Burj Khalifa :(");
        }
    }

    void pickup(Request request) {
        this.awaitingRequests.add(request);
    }

    List<ElevatorStatus> status (){
        List<ElevatorStatus> elevatorsStatus = new ArrayList<>();
        int targetFloor;

        for(Elevator elevator : elevators) {
            if(!elevator.getRequests().isEmpty()) {
                Request req = elevator.getRequests().get(MASTER_REQUEST);
                if (req.isBeingHandled()) {
                    targetFloor = req.getTargetFloor();
                } else {
                    targetFloor = req.getCurrentFloor();
                }
            } else {
                targetFloor = NO_TARGET_FLOOR;
            }
            elevatorsStatus.add(new ElevatorStatus(elevator.getElevatorId(), elevator.getCurrentFloor(), targetFloor));
        }
        return elevatorsStatus;
    }

    void step() {
        // Count simulation time. Just for performance testing
        totalSimTime +=1;

        // Move elevators, check if any req can be finished
        for (Elevator elevator : elevators) {
            // Elevator will move only if has direction setted to UP or DOWN
            elevator.move();
            // If elevator is moving, check if it has some requests to finish
            if(elevator.getDirection() != Elevator.IDLE) {
                elevator.handleRequests();
            }
        }
        List<Request> requestsDispatched = new ArrayList<>();
        // Assign awaiting requests to elevators
        for(Request request : awaitingRequests) {
            Elevator elevator = chooseBestElev(request);
            if(elevator != null) {
                elevator.addRequest(request);
                requestsDispatched.add(request);
                // If this is elev first req we need to set elev direction to make it move
                if (elevator.isThisFirstReq()) {
                    elevator.setDirection();
                }
            }
        }
        // Remove requests which were assigned to the elevators
        awaitingRequests.removeAll(requestsDispatched);
    }

    // Choose which elevator will be able to pick up a passenger in the smallest number of steps
    private Elevator chooseBestElev(Request request){
        Elevator bestElevator = null;
        int shortestDistance = 0;
        // Variable to mark first elevator matching criteria, this elev does not compare distance
        boolean isFirst = true;
        for(Elevator elevator : elevators) {
            if(isFirst && ((elevator.getDirection() == request.getDirection()) || elevator.getDirection() == Elevator.IDLE)) {
                shortestDistance = abs(elevator.getCurrentFloor() - request.getCurrentFloor());
                bestElevator = elevator;
                isFirst = false;
                continue;
            }

            int newDist = abs(elevator.getCurrentFloor() - request.getCurrentFloor());
            if((newDist < shortestDistance) && ((elevator.getDirection() == request.getDirection()) || elevator.getDirection() == Elevator.IDLE)) {
                shortestDistance = newDist;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }

    // If there are not awaiting requests and all elevators finished theirs req then simulation is finished
    public boolean checkIfSimFinished (){
        // Check if there are still some requests to finish by elevators
        for(Elevator elevator : elevators) {
            if(!elevator.getRequests().isEmpty())
                return false;
        }
        // Check if there are still awaiting requests to be handled by elevators
        if (!awaitingRequests.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
