import java.util.List;
import java.util.Scanner;

public class Main {
    static boolean skip = false;
    public static void main(String[] args){
        ElevatorSystem system = new ElevatorSystem(2);

        Request rq = new Request(2, 3, 1);
        Request rq2 = new Request(9, 3, -1);

        system.pickup(rq);
        system.pickup(rq2);

        // Run Simulation
        while(!system.checkIfSimFinished()) {
            system.step();
            if(!skip) {
                playSimulation(system);
            }
        }

        System.out.println("Total simulation time: " + system.totalSimTime);
    }

    public static void playSimulation(ElevatorSystem system) {
        Scanner scanner = new Scanner(System.in);
        boolean finishInput = false;
        while(!finishInput) {
            System.out.print("Do you want to add new request (Y or N)?\n");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("Y")) {
                addRequest(system, scanner);
            } else if(choice.equalsIgnoreCase("skip")) {
                skip = true;
                finishInput = true;
            } else {
                finishInput = true;
            }
        }
    }

    public static void addRequest(ElevatorSystem system, Scanner scanner){
        System.out.print("Enter current floor: ");
        int currentFloor = scanner.nextInt();
        System.out.print("Enter target floor: ");
        int targetFloor = scanner.nextInt();
        System.out.print("Enter direction(Enter '1' for UP or '-1' for DOWN): ");
        int direction = scanner.nextInt();
        Request rq = new Request(currentFloor, targetFloor, direction);
        system.pickup(rq);
    }

}