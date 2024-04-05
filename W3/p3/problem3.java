package W3.p3;

import W3.ArrayQueue;

// There is an ATM and N people want to use it. Each person comes to the ATM at a specific time A[i]
// (number of minutes from the opening time) 
// and intends to use it in D[i] minutes. The array A has been sorted. What is the maximum and average waiting time?

public class problem3 {
    static class Event{
        int arrivalTime;//the time that people arrive
        int durationUsingTime; //THe time that the people using the ATM.

        Event(int arrival, int duration){
            this.arrivalTime = arrival;
            this.durationUsingTime = duration;
        }
    }

    public static void main(String[] args) {
        ArrayQueue<Event> queue = new ArrayQueue<>();

        Event e1 = new Event(0, 5);
        Event e2 = new Event(3, 4);
        Event e3 = new Event(6, 3);
        Event e4 = new Event(100, 3);

        queue.enQueue(e1);
        queue.enQueue(e2);
        queue.enQueue(e3);
        queue.enQueue(e4);

        int nextAvailableTime = 0;
        int waitingTime = 0;
        int maxWaitingTime = 0;
        int person = 0;

        while(queue.size() > 1){
            System.out.println("Person " + person);
            int arrivalTime = queue.peekFront().arrivalTime;
            int durationTime = queue.peekFront().durationUsingTime;

            System.out.println("Arrival Time: " + arrivalTime);
            nextAvailableTime = Math.max(arrivalTime, durationTime);
            
            System.out.printf("Use until: %d\n", arrivalTime + durationTime);
            waitingTime = nextAvailableTime - arrivalTime;
            System.out.printf("Need to wait: %d\n", waitingTime);
            maxWaitingTime = Math.max(maxWaitingTime, waitingTime);

            queue.deQueue();//Get that person out of the queue
            person++;
            System.out.println("-----");
            // break;
        }
    }
}


