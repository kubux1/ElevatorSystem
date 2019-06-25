# ElevatorSystem
Elevator system where user inputs passengers current and target floor


Main target of the elevator system: To finish requests as quickly as possible.
I do not take into account the optimization of electricity costs (whether the elevators will carry out 100 or 1000 trips, but if in the same number of steps they serve passengers, it will still be the same good algorithm).
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
Because I think that it is worth optimizing your work and that you should support your work by the work of others, I must admit that I did not invent the algorithm myself, but read about it on the internet.
Sources that I used:
https://www.quora.com/What-algorithm-is-used-in-modern-day-elevators
https://en.wikipedia.org/wiki/Elevator#Elevator_algorithm
http://www.columbia.edu/~cs2035/courses/ieor4405.S13/p14.pdf

I got rid of the proposed "update" method because I do not change the lift ID, the current floor number changes in the every simulation step (only if elevator has some requests to handle)
and the elevator target floor is eiether the floor on which their is waiting first passenger  who called an elevator or the target floor of the first request is standing in the array of serviced requests by the elevator or -1 if the lift is standing.

I added the "targetFloor" parameter to the "pickup" method. The idea is that the elevator does not know what floor the passenger wants to go to before he/she enter elevator, but the passenger usually knows. I added this to facilitate my work ;)

Perhaps as the target floor of the elevator I should return the highest / lowest floor, which the elevator is currently going to, but I decided to choose the tharget floor of the first passenger - it is a simple modification.

I added the option of adding passengers manually during simulation.
I was no longer focusing on securing this option from user errors.
Typing the word "skip" after "Do you want to add new request (Y or N)?" disables the option of adding passengers and completes the simulation.

I used the "totalSimTime" counter to measure the simulation time, it is only used for testing the efficiency of the elevator system.

I made several self-tests and I was unable to suspend the elevators system after the implementing patches. If you succeed to suspend it, please let me know what kind of data caused it :)
Suggestions as to the quality of the code are also welcome
