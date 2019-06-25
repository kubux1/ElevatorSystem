# ElevatorSystem
Elevator system where user inputs passengers current and target floor.

Main target of the elevator system: To finish requests as quickly as possible.
I do not take into account the optimization of electricity costs (whether the elevators will carry out 100 or 1000 trips, but if in the same number of simulation steps they serve passengers, it will still be the same good algorithm).
I assume that the elevators have infinite capacity (it is easy to add a limiting condition to the code).
I assume that the elevator does not know what floor the passenger will want to take until he or she enters the elevator.

What happens in the simulation step:
- Elevators with requests are moving up or down 1 floor.
- In the same step passenger can enter / exit to / from the elevator.
- Any number of new requests can be placed in the elevator system.
- If an elevator call is from the X floor in a given step and the elevator just goes to this floor then in the same step the passenger will be able to enter it.

The elevator algorithm:
 - Proceed in the same direction until the last request in that direction.
 - If there is no request, stop and proceed towards other direction, if there is any request from other direction.
Sources that I used:
https://www.quora.com/What-algorithm-is-used-in-modern-day-elevators
https://en.wikipedia.org/wiki/Elevator#Elevator_algorithm
http://www.columbia.edu/~cs2035/courses/ieor4405.S13/p14.pdf

I added the "targetFloor" parameter to the "pickup" method. The idea is that the elevator does not know what floor the passenger wants to go to before he/she enter elevator, but the passenger usually knows. I added this to facilitate my work.

Perhaps as the target floor of the elevator I should return the highest / lowest floor, which the elevator is currently going to, but I decided to choose the tharget floor of the first passenger - it is a simple modification.

I added the option of adding passengers manually during simulation.

I used the "totalSimTime" counter to measure the simulation time, it is only used for testing the efficiency of the elevator system.
